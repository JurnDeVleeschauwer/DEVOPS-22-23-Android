package com.hogent.devOps_Android.ui.vms.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.daos.ProjectDao

class VMListViewModelFactory( val db: DatabaseImp, val customer_id: Long): ViewModelProvider.Factory {


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(VMListViewModel::class.java)){
                return VMListViewModel(db, customer_id) as T;
            }
            return super.create(modelClass)
        }
    }

