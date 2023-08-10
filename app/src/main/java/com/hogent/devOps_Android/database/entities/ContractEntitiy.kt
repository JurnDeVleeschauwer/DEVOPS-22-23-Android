package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.hogent.devOps_Android.domain.Contract
import com.hogent.devOps_Android.domain.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Entity(tableName = "contract_table",
    foreignKeys = [androidx.room.ForeignKey(
    entity = VirtualMachineEntitiy::class,
    childColumns = ["vmid"],
    parentColumns = ["id"]
)])
data class ContractEntitiy(
    @PrimaryKey
    var id: Long = 0L,
    var vmid: Long = 0L,
    var startDate: LocalDate,
    var endDate: LocalDate,
)

class LocalDateConverter{
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE


    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.format(formatter)

    }
    @TypeConverter
    fun toLocalDate(value: String) : LocalDate {
        return value.let { LocalDate.parse(it, formatter) }


    }
}
fun List<ContractEntitiy>.asDomainModel() : List<Contract>{
    return map {
        Contract(
            id = it.id,
            vmid = it.vmid,
            startDate = it.startDate,
            endDate = it.endDate
        )
    }
}

fun List<Contract>.asDomainModel() : List<ContractEntitiy>{
    return map {
        ContractEntitiy(
            id = it.id,
            vmid = it.vmid,
            startDate = it.startDate,
            endDate = it.endDate
        )
    }
}