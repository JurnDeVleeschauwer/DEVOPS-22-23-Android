package com.hogent.devOps_Android.database.entities
import androidx.room.*
import com.hogent.devOps_Android.domain.VirtualMachine
import org.json.JSONObject
import java.time.LocalDate


@Entity(tableName = "virtualmachine_table",
    foreignKeys = [ForeignKey(
        entity = ContractEntitiy::class,
        childColumns = ["contractId"],
        parentColumns = ["id"]
    )])
data class VirtualMachineEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,
    val name : String = "",
    val connection : Connection,
    val status : VirtualMachineStatus = VirtualMachineStatus.NONE,
    val operatingSystem: OperatingSystem = OperatingSystem.NONE,
    val hardware: HardWare = HardWare(0,0,0),
    val mode : String = "",
    val contractId : Long = 0L,
    val backup : Backup,
    val why : String = ""

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

enum class BackupType(str: String) {
    DAGELIJKS("Dagelijks"),  // No connection || No server
    WEKELIJKS("Wekelijks"),                                // has connection && server
    MAANDELIJKS("Maandelijks"),
}

fun List<VirtualMachineEntitiy>.asDomainModel() : List<VirtualMachine>{
    return map {
        VirtualMachine(
            id = it.id,
            name = it.name,
            connection = it.connection,
            status = it.status,
            operatingSystem = it.operatingSystem,
            hardware = it.hardware,
            mode = it.mode,
            contractId = it.contractId,
            backup = it.backup,
            why = it.why
        )
    }
}

fun List<VirtualMachineEntitiy>.asDatabaseModel() : List<VirtualMachine> {
    return map {
        VirtualMachine(
            id = it.id,
            name = it.name,
            connection = it.connection,
            status = it.status,
            operatingSystem = it.operatingSystem,
            hardware = it.hardware,
            mode = it.mode,
            contractId = it.contractId,
            backup = it.backup,
            why = it.why
        )
    }
}