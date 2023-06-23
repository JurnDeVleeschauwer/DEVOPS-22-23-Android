package com.hogent.devOps_Android.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.daos.CustomerDao

class LoginViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(application) as T;
        }
        return super.create(modelClass)
    }
    }
