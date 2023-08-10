
package com.hogent.devOps_Android.ui.vms.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.repository.VmRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class VMDetailsViewModel(app: Application, vm_id: Long) : ViewModel() {


    private val database = DatabaseImp.getInstance(app.applicationContext)
    private val vmRepository = VmRepository(database, null, vm_id)


    init {
        viewModelScope.launch {
            vmRepository.getvm(vm_id)
        }
    }

    val vm = vmRepository.vm
}