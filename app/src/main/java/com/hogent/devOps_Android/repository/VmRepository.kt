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
import com.hogent.devOps_Android.database.entities.asDatabaseModelDetail
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

    val vms: LiveData<List<NetworkVMDetail>> = database.virtualMachineDao.getAll().map {
        it.asDomainModel()
    }
    val user: LiveData<NetworkNetworkUserContainer> = database.customerDao.get(customer_id).map { it.asDomainModel()}

    val projectsvms: LiveData<List<ProjectVirtualMachineEntity>> = database.projectVirtualMachineDao.getAll()
    suspend fun refresh() {
        withContext(Dispatchers.IO){
            database.projectVirtualMachineDao.delete()
            val projects = VmApi.retrofitService.GetIndexOfProjectByIdUser(UserId).await()
            projects.projects.asDatabaseModel().map { database.projectDao.insertAll(it) }

            for(project in projects.projects){
                var projectDetail = VmApi.retrofitService.GetIndexOfProjectById(project.Id).await()
                for(vm in projectDetail.projectsDetails.VirtualMachines){
                    var vmDetail = VmApi.retrofitService.GetIndexOfVmById(vm.Id).await()
                    database.virtualMachineDao.insertAll(vmDetail.vms.asDatabaseModel())




                    var projectsVirtualmachineLsit = database.projectVirtualMachineDao.getAllList()
                    Timber.i("projectsVirtualmachineLsit")
                    Timber.i(projectsVirtualmachineLsit.toString())
                    var add = true
                   gere@for(projectVirtualmachine in projectsVirtualmachineLsit){
                        Timber.i("project_id:")
                        Timber.i(projectVirtualmachine.project_id.toString())
                        Timber.i("vm_id:")
                        Timber.i(projectVirtualmachine.vm_id.toString())
                        Timber.i("project.Id:")
                        Timber.i(project.Id.toString())
                        Timber.i("vm.Id:")
                        Timber.i(vm.Id.toString())
                        if(projectVirtualmachine.project_id == project.Id && projectVirtualmachine.vm_id == vm.Id){
                            add = false
                            break@gere
                        }

                    }
                    if (add) {
                        Timber.i("projectVirtualMachineDao: InsertAll")
                        database.projectVirtualMachineDao.insertAll(
                            ProjectVirtualMachineEntity(
                                project_id = project.Id,
                                vm_id = vm.Id
                            )
                        )
                    }
                }
                //database.projectDao.insertAll(projectDetail.projectsDetails.asDatabaseModelDetail())
            }
            Timber.i("Refresh:")
            Timber.i(database.virtualMachineDao.getAllList().toString())
            Timber.i(vms.value.toString())
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