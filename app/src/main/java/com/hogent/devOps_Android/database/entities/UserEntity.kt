package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hogent.devOps_Android.domain.Project
import com.hogent.devOps_Android.domain.User

@Entity(tableName = "user_table")
data class UserEntitiy (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val UserId: Long = 0L
)


fun List<UserEntitiy>.asDomainModel() : List<User>{
    return map {
        User(
            id = it.id,
            UserId = it.UserId
        )
    }
}