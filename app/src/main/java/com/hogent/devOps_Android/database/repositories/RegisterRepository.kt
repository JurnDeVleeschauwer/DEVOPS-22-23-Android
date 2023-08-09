package com.hogent.devOps_Android.database.repositories

import com.hogent.devOps_Android.database.daos.CustomerDao
import com.hogent.devOps_Android.database.entities.Customer


class RegisterRepository(private val dao: CustomerDao) {
    val klanten = dao.getAllUsers()

    suspend fun insert(klant: Customer){
        return dao.insert(klant)
    }

}