package com.hogent.devOps_Android.repository


import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import com.hogent.devOps_Android.database.entities.Role
import com.hogent.devOps_Android.database.entities.User_metadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.hogent.devOps_Android.network.VmApi
import com.hogent.devOps_Android.database.entities.asDomainModel
import com.hogent.devOps_Android.database.entities.asDatabaseModel
import com.hogent.devOps_Android.network.NetworkProject
import com.hogent.devOps_Android.network.NetworkUser
import com.hogent.devOps_Android.network.NetworkVMDetail
import timber.log.Timber


class VmRepository(private val database: DatabaseImp, customer_id: String?) {

    val projects: List<NetworkProject> =
            database.projectDao.getByCustomerId(customer_id!!)!!.asDomainModel()

    //val vms: List<NetworkVMDetail> = database.virtualMachineDao.getAll().asDomainModel()
    val user: NetworkUser = if(database.customerDao.get(customer_id!!) != null) {database.customerDao.get(customer_id!!).asDomainModel()} else NetworkUser(customer_id!!, "firsttest", "test", "emialtest", Role.Klant, User_metadata(null, null, false) )
    suspend fun refresh(customer_id: String) {
        withContext(Dispatchers.IO){
            val projects = VmApi.retrofitService.GetIndexOfProjectByIdUser(customer_id).await()
            projects.asDatabaseModel().map { database.projectDao.insertAll(it) }

            for(project in projects){
                var projectDetail = VmApi.retrofitService.GetIndexOfProjectById(project.Id).await()
                for(vm in projectDetail.VirtualMachines){
                    var vmDetail = VmApi.retrofitService.GetIndexOfVmById(vm.id).await()
                    database.virtualMachineDao.insertAll(vmDetail.asDatabaseModel())
                    ProjectVirtualMachineEntity(project_id = project.Id, vm_id = vm.id)
                    database.projectVirtualMachineDao.insertAll(ProjectVirtualMachineEntity(project_id = project.Id, vm_id = vm.id))
                }
            }
        }
    }


    suspend fun refreshUser(UserId: String) {
        withContext(Dispatchers.IO) {
            var userDetail = VmApi.retrofitService.GetIndexOfUserById(UserId).await()
            Timber.i("GetUser")
            Timber.i(userDetail.toString())
            database.customerDao.insertAll(userDetail.asDatabaseModel())
            refresh(UserId)
        }
    }


}