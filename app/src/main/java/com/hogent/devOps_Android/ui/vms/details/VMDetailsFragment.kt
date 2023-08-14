package com.hogent.devOps_Android.ui.vms.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.databinding.FragmentVmDetailsBinding

class VMDetailsFragment : Fragment() {

    private lateinit var binding: FragmentVmDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vm_details, container, false)


        val appContext = requireNotNull(this.activity).application
        var vm_id : Long = requireArguments().getLong("vm_id")
        val viewModelFactory = VMDetailsViewModelFactory(appContext, vm_id)
        val viewModel = ViewModelProvider(this, viewModelFactory)[VMDetailsViewModel::class.java];


        binding.vmViewModel = viewModel
        binding.lifecycleOwner = this;

        /*viewModel.navBack.observe(viewLifecycleOwner, Observer{
            if(it){
                NavHostFragment.findNavController(this).navigate(VMDetailsFragmentDirections.actionFromDetailToVmlist())
            }
        })*/



        return binding.root
    }

}