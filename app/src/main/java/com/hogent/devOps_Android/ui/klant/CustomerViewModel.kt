package com.hogent.devOps_Android.ui.klant

import android.media.Image
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.database.daos.CustomerDao
import com.hogent.devOps_Android.database.entities.ContactDetails1
import com.hogent.devOps_Android.database.entities.ContactDetails2
import com.hogent.devOps_Android.database.entities.Customer
import timber.log.Timber

class CustomerViewModel (private val customerId : Long, db: CustomerDao) : ViewModel() {


    private val database = db;

    private val _form = MutableLiveData( EditForm(ContactOne("", "", ""), ContactTwo("", "", "")))
    private val _klant = MediatorLiveData<Customer>()

    val inEditMode  = MutableLiveData(false)


    private val _errorToast = MutableLiveData<Boolean>()
    private val _successToast = MutableLiveData<Boolean>()


    val klant: LiveData<Customer>
        get() = _klant
    val success:  LiveData<Boolean>
        get() = _successToast
    val error:  LiveData<Boolean>
        get() = _errorToast


    fun setEmail(contactPerson: Int, text: Editable){
            if (contactPerson == 1) {
                val contact = _form.value!!.contact1
                _form.postValue(EditForm(ContactOne(text.toString(), contact.phone,contact.fullName), _form.value!!.contact2));

            } else if (contactPerson == 2) {
                val contact = _form.value!!.contact2
                _form.postValue(EditForm(_form.value!!.contact1,ContactTwo(text.toString(), contact.phone, contact.fullName)));
            }
    }
    fun setPhone(contactPerson: Int, text: Editable){

        if(contactPerson == 1){
            val contact = _form.value!!.contact1
            _form.postValue(EditForm(ContactOne(contact.email, text.toString(), contact.fullName), _form.value!!.contact2));
        }
        else if(contactPerson == 2){
            val contact = _form.value!!.contact2
            _form.postValue(EditForm(_form.value!!.contact1,ContactTwo(contact.email, text.toString(), contact.fullName)));
        }
    }
    fun setFullName(contactPerson: Int, text: Editable){

        if(contactPerson == 1){
            val contact = _form.value!!.contact1
            _form.postValue(EditForm(ContactOne(contact.email, contact.phone, text.toString()), _form.value!!.contact2));
        }
        else if(contactPerson == 2){
            val contact = _form.value!!.contact2
            _form.postValue(EditForm(_form.value!!.contact1,ContactTwo(contact.email, contact.phone, text.toString())));
        }
    }

    fun onEditButtonPressed() {
        inEditMode.postValue(true)
        val contactps1 = klant.value!!.contactPs1
        val contactps2 = klant.value!!.contactPs2
        var contactOne = ContactOne("", "", "")
        var contactTwo = ContactTwo("", "", "")

        if (contactps1 != null) {
            contactOne = ContactOne(contactps1.contact1_email, contactps1.contact1_phone, contactps1.contact1_firstname + " " + contactps1.contact1_lastname);
        }
        if(contactps2 != null){
            contactTwo = ContactTwo(contactps2.contact2_email, contactps2.contact2_phone, contactps2.contact2_firstname + " " + contactps2.contact2_lastname);
        }

        _form.postValue(EditForm(contactOne, contactTwo));

    }

    fun onCancelButtonPressed(){
        _form.value!!.reset()
        inEditMode.postValue(false)
    }

    fun onSubmitButtonPressed(){
        if(_form.value!!.isValid()) {
            Timber.d("VALID")
            inEditMode.postValue(false);
            _successToast.postValue(true);
            persistCustomer();
        }else{
            Timber.d("INVALID")
            _errorToast.postValue(true);
        }
    }

    fun getVisibleId(): Int{
        return View.VISIBLE;
    }
    fun getInvisibleId(): Int{
        return View.INVISIBLE
    }
    private fun persistCustomer() {
        val customer: Customer = klant!!.value!!.copy()

        val contactDetails1 = ContactDetails1(_form.value!!.contact1.phone, _form.value!!.contact1.email, _form.value!!.contact1.fullName.split(" ")[0], _form.value!!.contact1.fullName.substringAfter(" "));
        customer.contactPs1 = contactDetails1
        if(_form.value!!.contact2.isValid()){
            val contactDetails2 = ContactDetails2(_form.value!!.contact2.phone, _form.value!!.contact2.email, _form.value!!.contact2.fullName.split(" ")[0], _form.value!!.contact2.fullName.substringAfter(" "));
            customer.contactPs2 = contactDetails2
        }
        database.update(customer);
    }


    fun doneErrorToast(){
        _errorToast.postValue(false);
    }
    fun doneSuccessToast(){
        _successToast.postValue(false);
    }

    fun getError():String{
        return _form.value!!.getError();
    }


    init {
        _klant.addSource(database.get(customerId), _klant::setValue)
    }
}