package com.hogent.devOps_Android.database.entities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.*
import com.hogent.devOps_Android.network.NetworkBackup
import com.hogent.devOps_Android.network.NetworkHardware
import com.hogent.devOps_Android.network.NetworkVMDetail
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset


@Entity(tableName = "virtualmachine_table"/*,
    foreignKeys = [ForeignKey(
        entity = ContractEntitiy::class,
        childColumns = ["contractId"],
        parentColumns = ["id"]
    )]*/)
data class VirtualMachineEntitiy(
    @PrimaryKey
    var id : Long = 0L,
    val name : String = "",
    val status : VirtualMachineStatus = VirtualMachineStatus.READY,
    val operatingSystem: OperatingSystem = OperatingSystem.WINDOWS_10,
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

enum class OperatingSystem(val value: Int){
    WINDOWS_10(0),
    WINDOWS_SERVER2019(1),
    KALI_LINUX(2),
    UBUNTU_22_04(3),
    FEDORA_36(4),
    FEDORA_35(5);
    companion object {
        fun fromValueOrNull(value: Int): OperatingSystem? {
            return OperatingSystem.values().firstOrNull { it.value == value }
        }
    }
}

class OperatingSystemEnumJsonAdapter : JsonAdapter<OperatingSystem>() {
    @FromJson
    override fun fromJson(reader: JsonReader): OperatingSystem? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            OperatingSystem.fromValueOrNull(reader.nextInt())
        } else {
            reader.nextNull()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: OperatingSystem?) {
        writer.value(value?.value)
    }
}
enum class VirtualMachineStatus(val value: Int){
    WAITING_APPROVEMENT(0),
    READY(1),
    RUNNING(2),
    PAUSED(3),
    STOPPED(4);
    companion object {
        fun fromValueOrNull(value: Int): VirtualMachineStatus? {
            return VirtualMachineStatus.values().firstOrNull { it.value == value }
        }
    }
}

class VirtualMachineStatusEnumJsonAdapter : JsonAdapter<VirtualMachineStatus>() {
    @FromJson
    override fun fromJson(reader: JsonReader): VirtualMachineStatus? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            VirtualMachineStatus.fromValueOrNull(reader.nextInt())
        } else {
            reader.nextNull()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: VirtualMachineStatus?) {
        writer.value(value?.value)
    }
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
    val date: String,
)


class BackupConverter{
    @TypeConverter
    fun fromBackup(backup: Backup): String {
        return JSONObject().apply {
            put("type", backup.type)
            put("lastBackup", backup.date)
        }.toString();
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toBackup(json: String) : Backup{
        val backup = JSONObject(json)
        val backupType = backup.get("type").toString()

        return Backup(BackupType.valueOf(backupType),  backup.get("lastBackup").toString())
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

enum class BackupType(val value: Int) {
    DAGELIJKS(0),  // No connection || No server
    WEKELIJKS(1),                                // has connection && server
    MAANDELIJKS(2);
    companion object {
        fun fromValueOrNull(value: Int): BackupType? {
            return BackupType.values().firstOrNull { it.value == value }
        }
    }
}
class BackupTypeEnumJsonAdapter : JsonAdapter<BackupType>() {
    @FromJson
    override fun fromJson(reader: JsonReader): BackupType? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            BackupType.fromValueOrNull(reader.nextInt())
        } else {
            reader.nextNull()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: BackupType?) {
        writer.value(value?.value)
    }
}

fun List<VirtualMachineEntitiy>.asDomainModel() : List<NetworkVMDetail>{
    return map {
        NetworkVMDetail(
            Id = it.id,
            Name = it.name,
            OperatingSystem = it.operatingSystem,
            Hardware = NetworkHardware(it.hardware.memory,it.hardware.storage, it.hardware.cpu),
            Mode = it.status,
            BackUp = NetworkBackup(it.backup.type, it.backup.date),
            Why = it.why
        )
    }
}



fun List<NetworkVMDetail>.asDatabaseModel() : List<VirtualMachineEntitiy> {
    return map {
        VirtualMachineEntitiy(
            id = it.Id,
            name = it.Name,
            operatingSystem = it.OperatingSystem,
            hardware = HardWare(it.Hardware.Memory,it.Hardware.Storage, it.Hardware.Amount_vCPU),
            status = it.Mode,
            backup = Backup(it.BackUp.type, it.BackUp.date),
            why = it.Why
        )
    }
}

fun VirtualMachineEntitiy.asDomainModel() : NetworkVMDetail{
    return NetworkVMDetail(
            Id = id,
            Name = name,
            OperatingSystem = operatingSystem,
            Hardware = NetworkHardware(hardware.memory, hardware.storage, hardware.cpu),
            Mode = status,
            BackUp = NetworkBackup(backup.type, backup.date),
            Why = why
        )

}

fun NetworkVMDetail.asDatabaseModel() : VirtualMachineEntitiy {
    return VirtualMachineEntitiy(
            id = Id,
            name = Name,
            operatingSystem = OperatingSystem,
            hardware = HardWare(Hardware.Memory, Hardware.Storage, Hardware.Storage),
            status = Mode,
            backup = Backup(BackUp.type, BackUp.date),
            why = Why
        )

}

class LocalDateJsonAdapter: JsonAdapter<LocalDate>() {
    @RequiresApi(Build.VERSION_CODES.O)
    @FromJson
    override fun fromJson(reader: JsonReader): LocalDate? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            LocalDate.ofEpochDay(reader.nextLong())
        } else {
            reader.nextNull()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDate?) {
        writer.value(value?.toEpochDay())
    }


}