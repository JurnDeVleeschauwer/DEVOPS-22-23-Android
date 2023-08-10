package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.hogent.devOps_Android.domain.User
import com.hogent.devOps_Android.network.NetworkUser
import org.json.JSONObject

@Entity(tableName = "user_table")
data class UserEntitiy (
    @PrimaryKey()
    val UserId: String,
    val FirstName: String,
    val Name: String,
    val Email: String,
    val Role: Role,
    val user_metadata: User_metadata
)


fun UserEntitiy.asDomainModel() : NetworkUser{
    return NetworkUser(
            UserId = UserId,
            FirstName = FirstName ,
            Name = Name,
            Email = Email,
            Role = Role,
            user_metadata = user_metadata
        )

}

fun NetworkUser.asDatabaseModel() : UserEntitiy{
    return UserEntitiy(
            UserId = UserId,
            FirstName = FirstName ,
            Name = Name,
            Email = Email,
            Role = Role,
            user_metadata = user_metadata
        )

}

enum class Role {
    Klant,
    BeheerderZien,
    BeheerderBeheren,
    Admin
}

enum class Course {
    TOEGEPASTE_INFORMATICA,
    AGRO_EN_BIOTECHNOLOGIE,
    BIOMEDISCHE_LABORATORIUMTECHNOLOGIE,
    CHEMIE, DIGITAL_DESIGN_AND_DEVELOPMENT,
    ELEKTROMECHANICA
}


data class User_metadata(
    val Bedrijf:  String?,
    val Course: Course?,
    val Intern:  Boolean
)

class User_metadataConverter{
    @TypeConverter
    fun fromUser_metadata(user_metadata: User_metadata): String {
        return JSONObject().apply {
            put("Bedrijf", user_metadata.Bedrijf)
            put("Course", user_metadata.Course)
            put("Intern", user_metadata.Intern)
        }.toString();
    }
    @TypeConverter
    fun toUser_metadata(json: String) : User_metadata{
        val user_metadata = JSONObject(json)
        return User_metadata(user_metadata.get("Bedrijf") as String,  user_metadata.get("Course") as Course, user_metadata.get("Intern") as Boolean)
    }
}