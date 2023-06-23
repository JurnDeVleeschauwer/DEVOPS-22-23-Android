
package com.hogent.devOps_Android.ui.vms.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.Contract
import com.hogent.devOps_Android.database.entities.VirtualMachine
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class VMDetailsViewModel(application: Application, vm_id: Long) : ViewModel() {

    var db = DatabaseImp.getInstance(application);

    var vmDao = db.virtualMachineDao
    var contractDao = db.contractDao

    private val _vm = MutableLiveData<VirtualMachine>();
    private val _contract = MutableLiveData<Contract>();
    private val _navBack = MutableLiveData(false);

    val vm : LiveData<VirtualMachine>
        get() = _vm

    val contract : LiveData<Contract>
        get() =  _contract

    val navBack : LiveData<Boolean>
        get() = _navBack


    init {
        _vm.value = runBlocking {
            vmDao.get(vm_id)
        }
        if (_vm.value != null) {
            val contr_id = _vm.value!!.contractId;
            _contract.postValue(contractDao.get(contr_id))
        }
    }

    fun navigateBack(){
        _navBack.postValue(true);
    }
}