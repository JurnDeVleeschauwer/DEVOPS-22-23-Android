package com.hogent.devOps_Android.domain

import com.hogent.devOps_Android.database.entities.Backup
import com.hogent.devOps_Android.database.entities.Connection
import com.hogent.devOps_Android.database.entities.HardWare
import com.hogent.devOps_Android.database.entities.OperatingSystem
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity
import com.hogent.devOps_Android.database.entities.Role
import com.hogent.devOps_Android.database.entities.User_metadata
import com.hogent.devOps_Android.database.entities.VirtualMachineStatus
import java.time.LocalDate


data class VirtualMachine(
    var id : Long = 0L,
    val name : String = "",
    val connection : Connection,
    val status : VirtualMachineStatus = VirtualMachineStatus.WAITING_APPROVEMENT,
    val operatingSystem: OperatingSystem = OperatingSystem.WINDOWS_10,
    val hardware: HardWare = HardWare(0,0,0),
    val mode : String = "",
    val contractId : Long = 0L,
    val backup : Backup,
    val why: String = ""
)

data class Project(
    var id : Long = 0L,
    val name : String = "",
    val userid : Long = 0L,
    val vms : List<ProjectVirtualMachineEntity>
)

data class User(
    val UserId: String = "",
    val FirstName: String = "",
    val Name: String = "",
    val Email: String = "",
    val Role: Role = com.hogent.devOps_Android.database.entities.Role.Klant,
    val user_metadata: User_metadata = User_metadata(null, null, false)
)

data class Contract(
    var id: Long = 0L,
    var vmid: Long = 0L,
    var startDate: LocalDate,
    var endDate: LocalDate,
)

