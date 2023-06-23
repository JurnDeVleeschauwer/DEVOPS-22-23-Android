package com.hogent.devOps_Android.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.databinding.FragmentLoginBinding
import com.hogent.devOps_Android.util.AuthenticationManager
import com.hogent.devOps_Android.util.closeKeyboardOnTouch

class LoginFragment : Fragment() {

    override fun onCreateView(   inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?    ): View? {
        val application = requireNotNull(this.activity).application
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        val viewModelFactory = LoginViewModelFactory(application);
        val loginView = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java];

        binding.loginViewModel = loginView;
        binding.lifecycleOwner = this;

        binding.root.closeKeyboardOnTouch();


        loginView.navigateToProfile.observe(viewLifecycleOwner, Observer {
            if (it) {
                NavHostFragment.findNavController(this)
                    .navigate(LoginFragmentDirections.loginToProfile(AuthenticationManager.getInstance(application).klant.value!!.id));
                loginView.doneNavigating();
            }
        });

        loginView.navToRegister.observe(viewLifecycleOwner, Observer {
            if(it){
                NavHostFragment.findNavController(this).navigate(LoginFragmentDirections.loginToRegister())
                loginView.doneNavigating()
            }
        })

        loginView.errorToast.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Verkeerde inloggegevens.", Toast.LENGTH_SHORT)
                    .show();
                loginView.doneErrorToast();
            }
        })
        loginView.successToast.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Welkom terug!", Toast.LENGTH_SHORT)
                    .show();
                loginView.doneSuccessToast();
            }
        })

        Toast.makeText(context, "Log in met je account", Toast.LENGTH_SHORT).show()
        return binding.root
    }
    }

