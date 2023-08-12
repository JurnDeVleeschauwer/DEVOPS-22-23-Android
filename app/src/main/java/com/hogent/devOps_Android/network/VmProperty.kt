package com.hogent.devOps_Android.network

import com.hogent.devOps_Android.database.entities.Backup
import com.hogent.devOps_Android.database.entities.Connection
import com.hogent.devOps_Android.database.entities.Course
import com.hogent.devOps_Android.domain.User
import com.squareup.moshi.JsonClass
import com.hogent.devOps_Android.database.entities.HardWare
import com.hogent.devOps_Android.database.entities.VirtualMachineStatus
import com.hogent.devOps_Android.database.entities.OperatingSystem
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import com.hogent.devOps_Android.database.entities.Role
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class NetworkVMContainer(val videos: List<NetworkVMDetail>)

@JsonClass(generateAdapter = true)
data class NetworkVMDetail(
    val Id: Long,
    val Name: String,
    val Mode: VirtualMachineStatus,
    val Hardware: HardWare,
    val OperatingSystem: OperatingSystem,
    val ContractId: Long,
    val BackUp: Backup,
    //public FysiekeServer? FysiekeServer { get; set; }
    val VMConnection: Connection,
    val Why: String)



@JsonClass(generateAdapter = true)
data class NetworkProjectenContainer(val videos: List<NetworkProject>)

@JsonClass(generateAdapter = true)
data class NetworkProject(
    val Id: Long,
    val Name: String,
    val User: User)


@JsonClass(generateAdapter = true)
data class NetworkProjectenDetailContainer(val videos: List<NetworkProjectDetail>)

@JsonClass(generateAdapter = true)
data class NetworkProjectDetail(
    val Id: Long,
    val Name: String,
    val User: User,
    val VirtualMachines: List<ProjectVirtualMachineEntity>)


@JsonClass(generateAdapter = true)
data class NetworkNetworkUserContainer(@Json(name = "user")val user: NetworkUser)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    @Json(name = "id") val UserId: String,
    @Json(name = "firstName") val FirstName: String?,
    @Json(name = "name") val Name: String?,
    @Json(name = "email") val Email: String,
    @Json(name = "role") val Role: Role,
    @Json(name = "user_metadata") val user_metadata: NetworkUser_metadata
)

@JsonClass(generateAdapter = true)
data class NetworkUser_metadata(
    @Json(name = "bedrijf") val Bedrijf: String?,
    @Json(name = "course") val Course: Course?,
    @Json(name = "intern") val Intern: Boolean,

    )
