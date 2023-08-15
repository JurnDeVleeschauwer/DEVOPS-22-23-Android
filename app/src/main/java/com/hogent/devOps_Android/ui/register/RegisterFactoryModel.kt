package com.hogent.devOps_Android.ui.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.database.repositories.RegisterRepository

class RegisterFactoryModel(
    private val repo: RegisterRepository,
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repo, app) as T
        }
        throw IllegalArgumentException("unknown class")
    }
}
