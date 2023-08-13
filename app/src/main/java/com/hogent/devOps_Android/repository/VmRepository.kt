package com.hogent.devOps_Android.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import com.hogent.devOps_Android.database.entities.Role
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.hogent.devOps_Android.network.VmApi
import com.hogent.devOps_Android.database.entities.asDomainModel
import com.hogent.devOps_Android.database.entities.asDatabaseModel
import com.hogent.devOps_Android.network.NetworkNetworkUserContainer
import com.hogent.devOps_Android.network.NetworkProject
import com.hogent.devOps_Android.network.NetworkUser
import com.hogent.devOps_Android.network.NetworkUser_metadata
import com.hogent.devOps_Android.network.NetworkVMDetail
import timber.log.Timber


class VmRepository(private val database: DatabaseImp, customer_id: String) {

    val projects: LiveData<List<NetworkProject>> =
        database.projectDao.getByCustomerId(customer_id)!!.map {
            it.asDomainModel()
    }
    var UserId = customer_id

    val vms: List<NetworkVMDetail> = database.virtualMachineDao.getAll().asDomainModel()

    val user: LiveData<NetworkNetworkUserContainer> = database.customerDao.get(customer_id).map { it.asDomainModel()}
    suspend fun refresh() {
        withContext(Dispatchers.IO){
            val projects = VmApi.retrofitService.GetIndexOfProjectByIdUser(UserId).await()
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


    suspend fun refreshUser() {
        withContext(Dispatchers.IO) {
            Timber.i("GetUser")
            var userDetail = VmApi.retrofitService.GetIndexOfUserById(UserId).await()

            Timber.i(userDetail.toString())
            database.customerDao.insertAll(userDetail.asDatabaseModel())
        }
    }


}