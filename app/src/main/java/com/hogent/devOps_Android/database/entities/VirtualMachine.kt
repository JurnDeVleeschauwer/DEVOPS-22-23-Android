package com.hogent.devOps_Android.database.entities
import androidx.room.*
import org.json.JSONObject
import java.time.LocalDate


@Entity(tableName = "virtualmachine_table",
    foreignKeys = [ForeignKey(
        entity = Contract::class,
        childColumns = ["contractId"],
        parentColumns = ["id"]
    )])
data class VirtualMachine(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val name : String = "",
    val connection : Connection,
    val status : VirtualMachineStatus = VirtualMachineStatus.NONE,
    val operatingSystem: OperatingSystem = OperatingSystem.NONE,
    val hardware: HardWare = HardWare(0,0,0),
    val project_id : Long = 0L,
    val mode : String = "",
    val contractId : Long = 0L,
    val backup : Backup,

)



data class HardWare(
    val memory: Int,
    val storage: Int,
    val cpu: Int
)

enum class OperatingSystem{
    NONE,
    WINDOWS_2012,
    WINDOWS_2016,
    WINDOWS_2019,
    LINUX_UBUNTU,
    LINUX_KALI,
    RASPBERRY_PI
}
enum class VirtualMachineStatus{
    NONE,
    GEREED,
    RUNNING,
    TERMINATED,
    AANGEVRAAGD
}

/*
class OperatingSystemConverter{
    @TypeConverter
    fun toOperatingSystem(string: String): OperatingSystem{
        return OperatingSystem.valueOf(string.uppercase());
    }
    @TypeConverter
    fun toString(operatingSystem: OperatingSystem): String{
        return operatingSystem.toString();
    }
}
*/
class HardwareConverter{
    @TypeConverter
    fun fromHardware(hardware: HardWare): String {
        return JSONObject().apply {
            put("memory", hardware.memory)
            put("storage", hardware.storage)
            put("cpu", hardware.cpu)
        }.toString();
    }
    @TypeConverter
    fun toHardware(json: String) : HardWare{
        val hardware = JSONObject(json)
        return HardWare(hardware.get("memory") as Int,  hardware.get("storage") as Int, hardware.get("cpu") as Int)
    }
}

data class Backup(
    val type: BackupType,
    val date: LocalDate,
)


class BackupConverter{
    @TypeConverter
    fun fromBackup(backup: Backup): String {
        return JSONObject().apply {
            put("type", backup.type)
            put("backupDate", backup.date)
        }.toString();
    }
    @TypeConverter
    fun toBackup(json: String) : Backup{
        val backup = JSONObject(json)
        val backupType = backup.get("type").toString()

        return Backup(BackupType.valueOf(backupType),  LocalDate.parse(backup.get("backupDate").toString()))
    }
}

data class Connection(
    val fqdn: String,
    val ipAdres: String,
    val username: String,
    val password: String,
)


class ConnectionConverter{
    @TypeConverter
    fun fromConnection(connection: Connection): String {
        return JSONObject().apply {
            put("fqdn", connection.fqdn)
            put("ipAdres", connection.ipAdres)
            put("username", connection.username)
            put("password", connection.password)
        }.toString();
    }
    @TypeConverter
    fun toConnection(json: String) : Connection{
        val connection = JSONObject(json)
        return Connection(connection.get("fqdn") as String,  connection.get("ipAdres") as String, connection.get("username") as String, connection.get("password") as String)
    }
}

//enum class OperatingSystem(str: String) {
//    WINDOWS10("Windows 10"),
//    MACOS("MacOS"),
//    LINUX("Linux"),
//}
//enum class Status(str: String) {
//    WAITING_APPROVEMENT("Wachten op goedkeuring"),  // No connection || No server
//    READY("Gereed"),                                // has connection && server
//    RUNNING("Actief"),
//    PAUSED("Pauze"),
//    STOPPED("Gestopt")
//}

enum class BackupType(str: String) {
    DAGELIJKS("Dagelijks"),  // No connection || No server
    WEKELIJKS("Wekelijks"),                                // has connection && server
    MAANDELIJKS("Maandelijks"),
}