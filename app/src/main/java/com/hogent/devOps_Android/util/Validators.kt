package com.hogent.devOps_Android.util

import timber.log.Timber

class Validators {
    companion object{
        fun validatePassword(password: String?):Boolean{
            if(password == null){
                Timber.d("Password == null")
                return false;
            }
            if(!password.any {it.isDigit() }){
                Timber.d("Password ! contains digit")
                return false;
            }
            if(!password.any {!it.isLetterOrDigit()}){
                Timber.d("Password ! contains symbol")
                return false;
            }
            if(password.length < 6){
                Timber.d("Password size < 6")
                return false;
            }
            return true
        }

        fun validatePhoneNumber(phoneNumber: String?): Boolean{
            if(phoneNumber == null){
                return false;
            }
            val phoneNumberCharCount: Int = phoneNumber.length;
            val phoneNumberRegex = "^04\\d{8}$"
            val landLineRegex = "^0[1-3,5-9]\\d{7}$"

            return when(phoneNumberCharCount){
                10 -> {
                    phoneNumber.matches(phoneNumberRegex.toRegex())
                }
                9 -> {
                    phoneNumber.matches(landLineRegex.toRegex())
                }
                else -> false
            }
        }


        fun validateEmail(email: String?): Boolean {
            if(email == null){
                return false;
            }

            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"

            return email.matches(emailRegex.toRegex())
        }
    }
}