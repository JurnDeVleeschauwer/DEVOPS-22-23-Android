package com.hogent.devOps_Android.database.entities
import androidx.room.*
import com.hogent.devOps_Android.domain.Project
import com.hogent.devOps_Android.domain.VirtualMachine

@Entity(tableName = "project_table",
        foreignKeys = [androidx.room.ForeignKey(
        entity = UserEntitiy::class,
        childColumns = ["userid"],
        parentColumns = ["id"]
)])
data class ProjectEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val name : String = "",
    val userid : Long = 0L
)


fun List<ProjectEntitiy>.asDomainModel() : List<Project>{
    return map {
        Project(
            id = it.id,
            name = it.name,
            userid = it.userid
        )
    }
}