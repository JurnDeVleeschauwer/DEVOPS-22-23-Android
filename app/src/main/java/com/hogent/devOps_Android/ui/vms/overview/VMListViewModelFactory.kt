package com.hogent.devOps_Android.ui.vms.overview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VMListViewModelFactory(var application: Application, val customer_id: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMListViewModel::class.java)) {
            return VMListViewModel(application, customer_id) as T
        }
        return super.create(modelClass)
    }
}
