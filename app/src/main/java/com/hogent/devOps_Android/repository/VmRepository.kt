package com.hogent.devOps_Android.repository


import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.hogent.devOps_Android.network.VmApi
import com.hogent.devOps_Android.database.entities.asDomainModel
import com.hogent.devOps_Android.database.entities.asDatabaseModel
import com.hogent.devOps_Android.network.NetworkProject
import com.hogent.devOps_Android.network.NetworkVMDetail


class VmRepository(private val database: DatabaseImp, customer_id: Long?, vm_id: Long?) {

    val projects: List<NetworkProject> =
            database.projectDao.getByCustomerId(customer_id!!)!!.asDomainModel()
    val vm: NetworkVMDetail = database.virtualMachineDao.get(vm_id!!)!!.asDomainModel()
    //val vms: List<NetworkVMDetail> = database.virtualMachineDao.getAll().asDomainModel()

    suspend fun refresh(customer_id: Long) {
        withContext(Dispatchers.IO){
            val projects = VmApi.retrofitService.GetIndexOfProjectByIdUser(customer_id).await()
            database.projectDao.insertAll(projects.asDatabaseModel())

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
    suspend fun getvm(vm_id: Long) {
                    var vmDetail = VmApi.retrofitService.GetIndexOfVmById(vm_id).await()
                    database.virtualMachineDao.insertAll(vmDetail.asDatabaseModel())
                }



}