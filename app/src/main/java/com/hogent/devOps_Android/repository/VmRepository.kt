package com.hogent.devOps_Android.repository

import android.net.Network
import com.hogent.devOps_Android.database.DatabaseImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



class VmRepository(private val database: DatabaseImp) {

    val vms LiveData<List<VirtualMachine>> = Transformations.map(database.VirtualMachine.get()){
        it.asDomainModel()
    }
    suspend fun refresh() {
        withContext(Dispatchers.IO){
            val vms = Network.
            database.virtualMachineDao.insertAll(*vms.asDatabaseModel())
        }
    }
}