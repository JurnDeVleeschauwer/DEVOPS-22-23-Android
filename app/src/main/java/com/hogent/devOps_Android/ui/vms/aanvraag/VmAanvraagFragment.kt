package com.hogent.devOps_Android.ui.vms.aanvraag

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.databinding.AddvmFragmentBinding
import com.hogent.devOps_Android.util.closeKeyboardOnTouch
import java.util.*

class VmAanvraagFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(this.activity).application
        val binding: AddvmFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.addvm_fragment, container, false)

        val viewModelFactory = VmAanvraagFactoryModel(application)
        val vmAanvraagView = ViewModelProvider(this, viewModelFactory)[VmAanvraagViewModel::class.java]

        binding.viewmodel = vmAanvraagView
        binding.lifecycleOwner = this
        binding.root.closeKeyboardOnTouch()

        val seek = binding.root.findViewById<SeekBar>(R.id.aantalVcpuAanvraag)
        val text = binding.root.findViewById<TextView>(R.id.titleVcpuAanvraag)
        seek?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                text.text = getString(R.string.title_aantal_vcpu) + ' ' + p1.toString()
                vmAanvraagView.coresCpuChanged(p1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        val spinner_memory = binding.root.findViewById<Spinner>(R.id.memoryVmAanvraagDropdownList)
        val listMemory = arrayListOf("2GB", "4GB", "6GB", "8GB", "10GB", "12GB", "14GB", "16GB")
        val contex = this.requireContext()
        if (spinner_memory != null) {
            val adapter = ArrayAdapter(contex, android.R.layout.simple_spinner_item, listMemory)
            spinner_memory.adapter = adapter
        }
        spinner_memory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selected = listMemory[pos]
                vmAanvraagView.memoryGBChanged(selected)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val listBackup = arrayListOf("Dagelijks", "Wekenlijks", "Maandelijks", "Nooit")
        val spinnerBackup = binding.root.findViewById<Spinner>(R.id.backupVmDropdownList)
        val adapter = ArrayAdapter(contex, android.R.layout.simple_spinner_item, listBackup)
        spinnerBackup.adapter = adapter
        spinnerBackup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                val selected = listBackup[pos]
                vmAanvraagView.backupTypeChanged(selected)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.groupModeVm.setOnCheckedChangeListener { radioGroup, i ->
            val value = binding.root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            vmAanvraagView.modeChanged(value.text.toString())
        }

        binding.groupOsVm.setOnCheckedChangeListener { radioGroup, i ->
            val value = binding.root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            vmAanvraagView.osChanged(value.text.toString())
        }

        binding.startDateVmAanvraag.setOnDateChangedListener { datePicker, year, month, day ->
            val start = Date(year, month, day)
            vmAanvraagView.startDateChanged(start)
        }

        binding.endDateVmAanvraag.setOnDateChangedListener { datePicker, year, month, day ->
            val end = Date(year, month, day)
            vmAanvraagView.endDateChanged(end)
        }

        vmAanvraagView.errorDateToast.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it) {
                    Toast.makeText(requireContext(), "De startdatum moet voor de einddatum zijn.", Toast.LENGTH_SHORT).show()
                    vmAanvraagView.doneToastingDateError()
                }
            }
        )
        vmAanvraagView.errorValuesToast.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (it) {
                    Toast.makeText(requireContext(), "Gegevens zijn niet correct ingevuld", Toast.LENGTH_SHORT).show()
                    vmAanvraagView.doneToastingValuesError()
                }
            }
        )

        return binding.root
    }
}
