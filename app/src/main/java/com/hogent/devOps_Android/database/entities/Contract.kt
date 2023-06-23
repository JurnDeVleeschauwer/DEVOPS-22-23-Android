package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Entity(tableName = "contract_table")
data class Contract(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
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