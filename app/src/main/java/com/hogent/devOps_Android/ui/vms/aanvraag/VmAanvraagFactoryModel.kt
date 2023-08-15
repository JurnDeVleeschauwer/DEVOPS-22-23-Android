package com.hogent.devOps_Android.ui.vms.aanvraag

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VmAanvraagFactoryModel(private val application: Application) : ViewModelProvider.Factory {

    // ik kom u branch conflicten, groetjes darjoww

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VmAanvraagViewModel::class.java)) {
            return VmAanvraagViewModel(application) as T
        }
        return super.create(modelClass)
    }
}
