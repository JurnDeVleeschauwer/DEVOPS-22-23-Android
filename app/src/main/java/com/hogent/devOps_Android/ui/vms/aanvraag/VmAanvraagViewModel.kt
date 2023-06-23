package com.hogent.devOps_Android.ui.vms.aanvraag

import android.app.Application
import android.text.Editable
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hogent.devOps_Android.R
import java.util.Date
import kotlin.math.absoluteValue

class VmAanvraagViewModel(app : Application): ViewModel() {

    private  val naamVm = MutableLiveData<String>()
    private val hostnameVm = MutableLiveData<String>()
    private val os = MutableLiveData<String>()
    private val cpuCoresValue = MutableLiveData<Int>()
    private val memory = MutableLiveData<String>()
    private val backUpType = MutableLiveData<String>()
    private val modeVm = MutableLiveData<String>()
    private val storage = MutableLiveData<Int>()
    private val startDate = MutableLiveData<Date>()
    private val endDate = MutableLiveData<Date>()

    //toast
    private val _errorToastDateAanvraag = MutableLiveData<Boolean>()
    val errorDateToast:  LiveData<Boolean>
        get() = _errorToastDateAanvraag
    private val _errorToastValuesAanvraag = MutableLiveData<Boolean>()
    val errorValuesToast:  LiveData<Boolean>
        get() = _errorToastDateAanvraag

    //setters
    fun setNaamVm(v : Editable){
        naamVm.value = v.toString()
    }
    fun setHostnameVm(v : Editable){
        hostnameVm.value = v.toString()
    }
    /*fun setStorage(v : Unit){
        storage.value = (Int)
    }*/


    //functies
    fun coresCpuChanged(progress : Int){
        cpuCoresValue.postValue(progress);
    }
    fun memoryGBChanged(gb :String){
        memory.postValue(gb)
    }
    fun backupTypeChanged(type : String){
        backUpType.postValue(type)
    }
    fun modeChanged(type: String){
        modeVm.postValue(type)
    }
    fun osChanged(type: String){
        os.postValue(type)
    }
    fun startDateChanged(date: Date){
        startDate.postValue(date)
    }
    fun endDateChanged(date: Date){
        endDate.postValue(date)
    }

    //button clicked
    fun aanvragen(){

        if (endDate.equals(startDate) || endDate.value!!.before(startDate.value) ){
            _errorToastDateAanvraag.postValue(true)
        }
        if(hostnameVm.value == null || naamVm.value == null || cpuCoresValue.value == null || memory.value == null || storage.value == null  || startDate.value == null || endDate.value == null || os.value == null || modeVm.value == null){
            _errorToastValuesAanvraag.postValue(true)
        }
        Log.d("vmaanvraag","naam= " + naamVm.value.toString() + " hostname= " + hostnameVm.value.toString() + "\n"
                + "cores= " +cpuCoresValue.value.toString() + " memory= " + memory.value.toString() + "\n"
                + "backup= " + backUpType.value.toString() + " mode= " + modeVm.value.toString() + "\n"
                + "OS= " + os.value.toString() + " start= " + startDate.value.toString() + "end= " + endDate.value.toString())

    }

    fun doneToastingDateError(){
        _errorToastDateAanvraag.postValue(false)
    }
    fun doneToastingValuesError(){
        _errorToastDateAanvraag.postValue(false)
    }
}