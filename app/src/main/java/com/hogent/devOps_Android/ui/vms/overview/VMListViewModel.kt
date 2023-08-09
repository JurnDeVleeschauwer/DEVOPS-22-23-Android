package com.hogent.devOps_Android.ui.vms.overview

import android.app.Application
import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.entities.*
import com.hogent.devOps_Android.network.VmApi
import com.hogent.devOps_Android.network.VmProperty
import com.hogent.devOps_Android.repository.VmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class VMListViewModel(app: Application, customer_id: Long) : ViewModel() {



    private val database = DatabaseImp.getInstance(app.applicationContext)
    private val vmRepository = VmRepository(database)


    init {
        viewModelScope.launch {
            vmRepository.refresh(customer_id)
        }
    }

    val vms = vmRepository.vms


    /*private val db_projecten = db.projectDao;
    private val db_vms = db.virtualMachineDao;

    //mutable live data indien je bijvoorbeeld de naam van een project wil wijzigen
    //anders enkel livedata.
    private val _projecten = MutableLiveData<List<Project>>()
    private var _virtualmachine = MutableLiveData<List<VirtualMachine>>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    //dit is u getter
    val projecten: LiveData<List<Project>>
        get() = _projecten;

    val virtualmachine: LiveData<List<VirtualMachine>>
        get() = _virtualmachine;*/

    /*init {
        _projecten.value = db_projecten.getByCustomerId(customer_id);
        var templist = mutableListOf<VirtualMachine>()
        _projecten.value?.forEach { i ->
            var listvirtualmachine = db_vms.getByProjectId(i.id)
            Timber.i("List from database:")
            Timber.i(listvirtualmachine.toString())
            listvirtualmachine?.forEach { j ->
                Timber.i(j.toString())
                templist.add(j)
                Timber.i("templist plus:")
                Timber.i(templist.toString())
            }
        }
        Timber.i("templist:")
        Timber.i(templist.toString())
        _virtualmachine.value = templist

        Timber.i("Virtual Machine:")
        Timber.i(_virtualmachine.value.toString())
    }*/



    /*private fun getProjectListFromApiByUserId(){
        coroutineScope.launch {
            var getPropertiesDeferred = VmApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _projecten.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _projecten.value = "Failure: ${e.message}"
            }
        }
    }*/


}

