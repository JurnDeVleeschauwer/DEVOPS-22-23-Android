package com.hogent.devOps_Android.repository

import androidx.lifecycle.Transformations
import androidx.lifecycle.LiveData
import com.hogent.devOps_Android.database.DatabaseImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.hogent.devOps_Android.domain.VirtualMachine
import com.hogent.devOps_Android.network.VmApi


class VmRepository(private val database: DatabaseImp) {

    val vms: LiveData<List<VirtualMachine>> =
            Transformations.map(database.virtualMachineDao.getAll()){
        it.asDomainModel()
    }
    suspend fun refresh(customer_id: Long) {
        withContext(Dispatchers.IO){
            val vms = VmApi.retrofitService.GetIndexOfProjectByIdUser(customer_id)
            database.virtualMachineDao.insertAll(*vms.asDatabaseModel())
        }
    }
}