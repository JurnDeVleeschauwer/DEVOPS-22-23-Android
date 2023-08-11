package com.hogent.devOps_Android.repository

import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.asDatabaseModel
import com.hogent.devOps_Android.database.entities.asDomainModel
import com.hogent.devOps_Android.network.NetworkVMDetail
import com.hogent.devOps_Android.network.VmApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val database: DatabaseImp, vm_id: Long?) {
    val vm: NetworkVMDetail = database.virtualMachineDao.get(vm_id!!)!!.asDomainModel()

    suspend fun getvm(vm_id: Long) {
        withContext(Dispatchers.IO) {
            var vmDetail = VmApi.retrofitService.GetIndexOfVmById(vm_id).await()
            database.virtualMachineDao.insertAll(vmDetail.asDatabaseModel())
        }
    }
}