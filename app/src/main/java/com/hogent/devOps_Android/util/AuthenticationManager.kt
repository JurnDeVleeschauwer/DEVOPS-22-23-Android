package com.hogent.devOps_Android.util

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.daos.CustomerDao
import com.hogent.devOps_Android.database.entities.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class AuthenticationManager(private val db: CustomerDao) {

    val klant = MutableLiveData<Customer?>()
    var authenticationState = MutableLiveData(AuthenticationState.UNAUTHENTICATED)



    companion object {
        @Volatile
        private lateinit var instance: AuthenticationManager

        fun getInstance(application: Application): AuthenticationManager {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    val db = DatabaseImp.getInstance(application).customerDao
                    instance = AuthenticationManager(db)
                }
                return instance;
            }
        }

    }


    fun loggedIn(): Boolean{
        return authenticationState.value == AuthenticationState.AUTHENTICATED
    }
    fun logOut() {
        klant.postValue(null);
        authenticationState.postValue(AuthenticationState.UNAUTHENTICATED);
    }

    suspend fun login(email: String, pass: String) =
         withContext(Dispatchers.Default) {
            Timber.d(String.format("LOG_IN HAS BEEN CALLED with parameters: %s, %s", email, pass))
            val c: Customer? = db.login(email, pass)
            klant.postValue(c);

            authenticationState.postValue(
                if (c != null) {
                    AuthenticationState.AUTHENTICATED;
                } else {
                    AuthenticationState.UNAUTHENTICATED;
                });

        }
}



enum class AuthenticationState {
    AUTHENTICATED, UNAUTHENTICATED
}