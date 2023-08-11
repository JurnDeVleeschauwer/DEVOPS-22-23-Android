package com.hogent.devOps_Android.ui.klant

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.database.daos.CustomerDao

class CustomerViewModelFactory(var app: Application, private val UserId: String) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CustomerViewModel::class.java)){
            return CustomerViewModel(app, UserId) as T;
        }
        return super.create(modelClass)
    }
    }
