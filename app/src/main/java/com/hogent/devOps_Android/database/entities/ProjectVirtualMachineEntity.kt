package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "project_virtualmachine_table",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = ProjectEntitiy::class,
            childColumns = ["project_id"],
            parentColumns = ["id"]
        ), androidx.room.ForeignKey(
            entity = VirtualMachineEntitiy::class,
            childColumns = ["vm_id"],
            parentColumns = ["id"]
        )
    ]
)
data class ProjectVirtualMachineEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val project_id: Long = 0L,
    val vm_id: Long = 0L
)
