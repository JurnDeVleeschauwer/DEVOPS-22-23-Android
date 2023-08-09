package com.hogent.devOps_Android.network

import com.hogent.devOps_Android.database.entities.Backup
import com.hogent.devOps_Android.database.entities.Connection
import com.hogent.devOps_Android.domain.User
import com.hogent.devOps_Android.domain.VirtualMachine
import com.squareup.moshi.JsonClass

class VmProperty {
    @JsonClass(generateAdapter = true)
    data class NetworkVMContainer(val videos: List<NetworkVMDetail>)

    @JsonClass(generateAdapter = true)
    data class NetworkVMDetail(
        val Id: Long,
        val Name: String,
        val Mode: VirtualMachineMode,
        val Hardware: Hardware,
        val OperatingSystem: OperatingSystemEnum,
        val Contract: Contract,
        val BackUp: Backup,
        public FysiekeServer? FysiekeServer { get; set; }
        val VMConnection: Connection,
        val Why: String)



    @JsonClass(generateAdapter = true)
    data class NetworkProjectenContainer(val videos: List<NetworkProject>)

    @JsonClass(generateAdapter = true)
    data class NetworkProject(
        val Id: Long,
        val Name: String,
        val user: User)


    @JsonClass(generateAdapter = true)
    data class NetworkProjectenDetailContainer(val videos: List<NetworkProjectDetail>)

    @JsonClass(generateAdapter = true)
    data class NetworkProjectDetail(
        val VirtualMachines: List<VirtualMachine>,
        val Users: List<User>)

}