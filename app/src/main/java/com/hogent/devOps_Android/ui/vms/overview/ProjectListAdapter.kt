package com.hogent.devOps_Android.ui.vms.overview

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hogent.devOps_Android.R
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import com.hogent.devOps_Android.network.NetworkProject
import com.hogent.devOps_Android.network.NetworkVMDetail
import timber.log.Timber

class ProjectListAdapter(
    private val projectList: List<NetworkProject>?,
    private val virtualmachineList: LiveData<List<NetworkVMDetail>>?,
    private val projectsvirtualmachinsList: LiveData<List<ProjectVirtualMachineEntity>>?,
    private val context: Context?,
    private val application: Application
) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var view: View
    private var newvirtualMachineList = mutableListOf<NetworkVMDetail>()
    // hier ontvang je de view, die wordt gecached om te recyclen of opnieuw terug te zien
    // ideaal dus, want als je de project wilt openklappen en sluiten dan weet hij dit

    // wat je kan doen is: elke project in een container stoppen.  (een container is subklasse van view)
    // en dan komt deze hier terecht, en kan je ook een nieuwe terugsturen met de volgende in de lijst
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // cardview ( = container )  als voorbeeld maar kan je ook gebruiken
        val textView1: TextView = itemView.findViewById(R.id.textView1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.project_container, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectList?.get(position)

        recyclerView = view.findViewById(R.id.virtual_machine_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        Timber.i("ProjectAdapter:")
        Timber.i(virtualmachineList.toString())
        Timber.i(virtualmachineList?.value?.size.toString())
        if (project != null) {
            filterVirtualMachines(project.Id)
        }
        recyclerView.adapter =
            VirtualMachineListAdapter(newvirtualMachineList, this.application)

        // hier heb je het project en de holder, je kan er dingen op setten
        if (project != null) {
            holder.textView1.text = project.Name
        }
    }

    override fun getItemCount(): Int {
        return projectList?.size ?: 0
    }

    private fun filterVirtualMachines(projectId: Long) {
        newvirtualMachineList.clear()
        Timber.i("filterVirtualMachines Project ID:")
        Timber.i(projectId.toString())
        virtualmachineList?.value?.forEach { i ->
            if (projectsvirtualmachinsList?.value != null) {
                for (projectVirtualmachine in projectsvirtualmachinsList.value!!) {
                    if (projectVirtualmachine.project_id == projectId && projectVirtualmachine.vm_id == i.Id) {
                        newvirtualMachineList.add(i)
                    }
                }
            }
        }
        Timber.i("filterVirtualMachines:")
        Timber.i(newvirtualMachineList.toString())
    }
}

/*
*
* In het kort wat je nog zou moeten doen:
* layout maken voor een project en heet deze "project_layout" daar kunt je een layout maken voor 1 project
* maak in deze layout een container voor het project bijvoorbeeld:  <CardView>  ....  </CardView>
* dan kunt je die container AKA project gebruiken als view.
*
* dat is hoe ik het zou doen
*
*
*
* */
