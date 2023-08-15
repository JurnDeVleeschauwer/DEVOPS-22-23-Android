package com.hogent.devOps_Android.network

import com.hogent.devOps_Android.database.entities.BackupType
import com.hogent.devOps_Android.database.entities.Course
import com.hogent.devOps_Android.database.entities.OperatingSystem
import com.hogent.devOps_Android.database.entities.Role
import com.hogent.devOps_Android.database.entities.VirtualMachineStatus
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVMContainer(@Json(name = "virtualMachine")val vms: NetworkVMDetail)

@JsonClass(generateAdapter = true)
data class NetworkVMDetail(
    @Json(name = "id") val Id: Long,
    @Json(name = "name") val Name: String,
    @Json(name = "mode") val Mode: VirtualMachineStatus,
    @Json(name = "hardware") val Hardware: NetworkHardware,
    @Json(name = "operatingSystem") val OperatingSystem: OperatingSystem,
    // @Json(name = "contract")val ContractId: Long,
    @Json(name = "backUp") val BackUp: NetworkBackup,
    // public FysiekeServer? FysiekeServer { get; set; }
    @Json(name = "why") val Why: String
)

@JsonClass(generateAdapter = true)
data class NetworkHardware(
    @Json(name = "memory")val Memory: Int,
    @Json(name = "storage")val Storage: Int,
    @Json(name = "amount_vCPU")val Amount_vCPU: Int
)

@JsonClass(generateAdapter = true)
data class NetworkBackup(
    @Json(name = "type")val type: BackupType,
    @Json(name = "lastBackup")val date: String // Rember to change to LocalDate when used later
)

@JsonClass(generateAdapter = true)
data class NetworkProjectenContainer(@Json(name = "projecten")val projects: List<NetworkProject>)

@JsonClass(generateAdapter = true)
data class NetworkProject(
    @Json(name = "id")val Id: Long,
    @Json(name = "name")val Name: String,
    @Json(name = "user")val User: NetworkUserProject
)

@JsonClass(generateAdapter = true)
data class NetworkUserProject(
    @Json(name = "id")val Id: Long,
    @Json(name = "userId")val UserId: String
)

@JsonClass(generateAdapter = true)
data class NetworkProjectenDetailContainer(@Json(name = "project")val projectsDetails: NetworkProjectDetail)

@JsonClass(generateAdapter = true)
data class NetworkProjectDetail(
    @Json(name = "id")val Id: Long,
    @Json(name = "name")val Name: String,
    @Json(name = "virtualMachines")val VirtualMachines: List<NetworkVMDetail>
)

@JsonClass(generateAdapter = true)
data class NetworkVMListContainer(@Json(name = "virtualMachines")val vms: List<NetworkVMDetail>)

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
    @Json(name = "intern") val Intern: Boolean

)
