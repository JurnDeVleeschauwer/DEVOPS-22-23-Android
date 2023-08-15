package com.hogent.devOps_Android.ui.vms.overview

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.network.NetworkVMDetail
import timber.log.Timber

class VirtualMachineListAdapter(
    private var virtualmachineList: MutableList<NetworkVMDetail>,
    private var application: Application
) : RecyclerView.Adapter<VirtualMachineListAdapter.ViewHolder>() {

    var header = false

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // cardview ( = container )  als voorbeeld maar kan je ook gebruiken
        val textView1: TextView = itemView.findViewById(R.id.status)
        val textView2: TextView = itemView.findViewById(R.id.naam)
        // val textView3: TextView = itemView.findViewById(R.id.klant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.virtual_machine_container, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!header) {
            holder.textView1.textSize = 15F
            holder.textView2.textSize = 15F
            // holder.textView3.textSize = 15F
            header = true
        } else {
            val virtualmachine = virtualmachineList?.get(position - 1)
            // Timber.i("onBindViewHolder:")
            // Timber.i(virtualmachine.toString())
            // hier heb je het project en de holder, je kan er dingen op setten
            holder.textView1.text = virtualmachine?.Mode.toString()
            holder.textView2.text = virtualmachine?.Name
            // holder.textView3.text = virtualmachine?.Why

            holder.itemView.setOnClickListener {
                Timber.d(String.format("VM ID :  %s", virtualmachine!!.Id.toString()))
                Navigation.findNavController(it).navigate(VMListFragmentDirections.actionFromVmlistToDetail(virtualmachine!!.Id))
            }
        }
    }

    override fun getItemCount(): Int {
        // Timber.i("getItemCount:")
        // Timber.i(virtualmachineList.toString())
        return virtualmachineList?.size!! + 1
    }
}
