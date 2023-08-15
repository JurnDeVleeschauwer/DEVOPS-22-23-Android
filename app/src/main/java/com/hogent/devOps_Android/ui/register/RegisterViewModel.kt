package com.hogent.devOps_Android.ui.register

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import com.hogent.devOps_Android.database.repositories.RegisterRepository

class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {
    // TODO naar viewmodel veranderen ???
    /*
    val klant = MutableLiveData<Customer?>()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateHome = MutableLiveData<Boolean>(false)
    val navigateHome: LiveData<Boolean>
        get() = _navigateHome
    private val _navToLogin = MutableLiveData<Boolean>(false)
    val navToLogin: LiveData<Boolean>
        get() = _navToLogin


    val inputFirstName = MutableLiveData<String>()
    val inputLastName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()
    val inputConfirmPassword = MutableLiveData<String>()
    val inputPhoneNumber = MutableLiveData<String>()
    val contactPersoon = MutableLiveData<String>()

    //val errorToast
    fun setFirstname(e : Editable){
        inputFirstName.postValue(e.toString())
    }
    fun setLastname(e : Editable){
        inputLastName.postValue(e.toString())
    }
    fun setEmail(e : Editable){
        inputEmail.postValue(e.toString())
    }
    fun setPhoneNumber(e : Editable){
        inputPhoneNumber.postValue(e.toString())
    }
    fun setPassword(e : Editable){
        inputPassword.postValue(e.toString())
    }
    fun setConfirmPassword(e : Editable){
        inputConfirmPassword.postValue(e.toString())
    }

    fun submitButton(){
        //1 or multiple null
        Log.d("registercreated", "registerbtn pressed")
        if(inputFirstName.value == null ||inputLastName.value == null || inputEmail.value==null || inputPassword.value==null ||inputPhoneNumber.value==null || inputConfirmPassword.value==null) {
             android.widget.Toast.makeText(getApplication(), "Je moet alle gegevens ingeven", Toast.LENGTH_SHORT).show()
        }
        //non null
        if(!Validators.validateEmail(inputEmail.value)){
            android.widget.Toast.makeText(getApplication(), "Incorrecte email", Toast.LENGTH_SHORT).show()
        }
        else if(!Validators.validatePhoneNumber(inputPhoneNumber.value)){
            android.widget.Toast.makeText(getApplication(), "Incorrecte gsmnummer", Toast.LENGTH_SHORT).show()
        }
        else if (!Validators.validatePassword(inputPassword.value)){
            android.widget.Toast.makeText(getApplication(), "Wachtwoord voldoet niet aan de eisen", Toast.LENGTH_SHORT).show()
        }
        else if(!inputPassword.value.equals(inputConfirmPassword.value)){
            Toast.makeText(getApplication(),"paswoorden komen niet overeen", Toast.LENGTH_SHORT).show()
            Log.d("registercreated","password: " + inputPassword.value + " confirmpass: " + inputConfirmPassword.value)
        }else{
            val users = repository.klanten.value?.filter{ c -> c.email == inputEmail.value }
            if(users != null) {
                if (users!!.isNotEmpty()) {
                    Toast.makeText(getApplication(), "Email bestaat al", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                uiScope.launch(Dispatchers.Main) {
                    val c = Customer()
                    c.email = inputEmail.value!!
                    c.firstName = inputFirstName.value!!
                    c.lastName = inputLastName.value!!
                    c.phoneNumber = inputPhoneNumber.value!!
                    c.password = inputPassword.value!!
                    klant.postValue(c)
                    Log.d("RegisterView" , klant.value.toString())
                    Log.d("RegisterView" , c.toString())
                    launch(Dispatchers.IO) {
                        insert(c)
                    }
                }
                _navigateHome.postValue(true)
            }
        }
    }

    private suspend fun insert(c: Customer): Job = viewModelScope.launch {
        repository.insert(c)
    }

    fun navigated(){
        _navigateHome.postValue(false)
        _navToLogin.postValue(false)
    }
    fun navToLogin(){
        _navToLogin.postValue(true)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }*/
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }
}
