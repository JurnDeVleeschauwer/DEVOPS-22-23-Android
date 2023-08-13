package com.hogent.devOps_Android.database.entities
import androidx.room.*
import com.hogent.devOps_Android.database.daos.ProjectVirtualMachineDao
import com.hogent.devOps_Android.network.NetworkProject
import com.hogent.devOps_Android.network.NetworkProjectDetail
import com.hogent.devOps_Android.network.NetworkUserProject

@Entity(tableName = "project_table"/*,
        foreignKeys = [androidx.room.ForeignKey(
        entity = UserEntitiy::class,
        childColumns = ["userid"],
        parentColumns = ["UserId"]
)]*/)
data class ProjectEntitiy(
    @PrimaryKey
    var id: Long = 0L,
    val name: String = "",
    val userid: String = ""
)


fun List<ProjectEntitiy>.asDomainModel() : List<NetworkProject>{
    return map {
        NetworkProject(
            Id = it.id,
            Name = it.name,
            User = NetworkUserProject(-1, it.userid)
        )
    }
}

fun  List<NetworkProject>.asDatabaseModel() : List<ProjectEntitiy>{
    return map {
        ProjectEntitiy(
            id = it.Id,
            name = it.Name,
            userid = it.User.UserId
        )
    }
}

/*fun List<ProjectEntitiy>.asDomainModelDetail(projectVirtualMachineDao: ProjectVirtualMachineDao) : List<NetworkProjectDetail> {
    return map {
        NetworkProjectDetail(
            Id = it.id,
            Name = it.name,
            VirtualMachines = ListProjectVirtualMachineEntitytoListNetworkVMDetail(projectVirtualMachineDao.getByProjectId(it.id)!!, it.id)
        )
    }
}

private fun ListProjectVirtualMachineEntitytoListNetworkVMDetail(listProjectVirtualMachineEntity: List<ProjectVirtualMachineEntity>, projectId: Long) {
    var newList: List<ProjectVirtualMachineEntity>
    for(NetworkVMDetail in listNetworkVMDetail){
        newList.add(ProjectVirtualMachineEntity())
    }
    return newList
}*/

fun  NetworkProjectDetail.asDatabaseModelDetail() : ProjectEntitiy{
    return ProjectEntitiy(
            id = Id,
            name = Name,
        )

}