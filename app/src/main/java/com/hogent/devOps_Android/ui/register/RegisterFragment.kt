package com.hogent.devOps_Android.ui.register

import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.database.DatabaseImp
import com.hogent.devOps_Android.database.repositories.RegisterRepository
import com.hogent.devOps_Android.databinding.FragmentRegisterBinding
import com.hogent.devOps_Android.ui.login.LoginFragment
import com.hogent.devOps_Android.util.closeKeyboardOnTouch

class RegisterFragment : Fragment() {

    /*private lateinit var viewModel : RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = DatabaseImp.getInstance(application).customerDao
        val repository = RegisterRepository(dao)
        val factory = RegisterFactoryModel(repository, application)
        viewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
        binding.lifecycleOwner = this
        binding.registerViewModel = viewModel
        binding.root.closeKeyboardOnTouch()

        Log.d("registercreated", "register fragment created")
        viewModel.navigateHome.observe(viewLifecycleOwner, Observer{
            if(it){
                NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                viewModel.navigated()
            }
        })

        val loginFrag = LoginFragment()
        val bundle = Bundle()
        bundle.putString("message", "Log in met je account.")
        loginFrag.arguments = bundle

        viewModel.navToLogin.observe(viewLifecycleOwner, Observer {
            if(it){
                NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                viewModel.navigated()
            }
        })



        return binding.root
    }*/
}