package com.hogent.devOps_Android.database.entities

import android.os.Build
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE


    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.format(formatter)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDate(value: String) : LocalDate {
        return value.let { LocalDate.parse(it, formatter) }


    }
}
fun ContractEntitiy.asDomainModel() : Contract{
    return Contract(
            id = id,
            vmid = vmid,
            startDate = startDate,
            endDate = endDate
        )

}

fun Contract.asDomainModel() : ContractEntitiy{
    return ContractEntitiy(
            id = id,
            vmid = vmid,
            startDate = startDate,
            endDate = endDate
        )

}