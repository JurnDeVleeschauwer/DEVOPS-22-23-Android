package com.hogent.devOps_Android.ui.klant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.database.daos.CustomerDao

class CustomerViewModelFactory(private val customerId: String, private val db: CustomerDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CustomerViewModel::class.java)){
            return CustomerViewModel(customerId, db) as T;
        }
        return super.create(modelClass)
    }
    }
