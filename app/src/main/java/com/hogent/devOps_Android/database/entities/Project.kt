package com.hogent.devOps_Android.database.entities
import androidx.room.*
import org.json.JSONObject
import com.hogent.devOps_Android.database.entities.VirtualMachine

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val name : String = "",
    val customer_id : Long = 0L
)


