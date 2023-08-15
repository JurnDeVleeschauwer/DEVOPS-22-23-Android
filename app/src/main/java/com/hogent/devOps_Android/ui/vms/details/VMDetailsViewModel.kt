
package com.hogent.devOps_Android.ui.vms.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.repository.UserRepository
import kotlinx.coroutines.launch

class VMDetailsViewModel(app: Application, vm_id: Long) : ViewModel() {

    private val database = DatabaseImp.getInstance(app.applicationContext)
    private val userRepository = UserRepository(database, vm_id)

    init {
        viewModelScope.launch {
            userRepository.getvm(vm_id)
        }
    }

    val vm = userRepository.vm
}
