package com.hogent.devOps_Android.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.hogent.devOps_Android.network.NetworkNetworkUserContainer
import com.hogent.devOps_Android.network.NetworkUser
import com.hogent.devOps_Android.network.NetworkUser_metadata
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import org.json.JSONObject
import timber.log.Timber

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


fun UserEntitiy.asDomainModel() : NetworkNetworkUserContainer {
    return NetworkNetworkUserContainer( NetworkUser(
            UserId = UserId,
            FirstName = FirstName ,
            Name = Name,
            Email = Email,
            Role = Role,
            user_metadata = NetworkUser_metadata(user_metadata.Bedrijf, user_metadata.Course, user_metadata.Intern)
    )
        )

}

fun NetworkNetworkUserContainer.asDatabaseModel() : UserEntitiy{
    return UserEntitiy(
            UserId = user.UserId,
            FirstName = if(user.FirstName == null) "" else user.FirstName ,
            Name = if(user.Name == null) "" else user.Name,
            Email = user.Email,
            Role = user.Role,
            user_metadata = User_metadata(user.user_metadata.Bedrijf, user.user_metadata.Course, user.user_metadata.Intern)
        )

}

enum class Role(val value: Int)  {
    Klant(0),
    BeheerderZien(1),
    BeheerderBeheren(2),
    Admin(3);
    companion object {
        fun fromValueOrNull(value: Int): Role? {
            return values().firstOrNull { it.value == value }
        }
    }
}

class RoleEnumJsonAdapter : JsonAdapter<Role>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Role? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            Role.fromValueOrNull(reader.nextInt())
        } else {
            reader.nextNull()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Role?) {
        writer.value(value?.value)
    }
}

enum class Course(val value: Int) {
    TOEGEPASTE_INFORMATICA(0),
    AGRO_EN_BIOTECHNOLOGIE(1),
    BIOMEDISCHE_LABORATORIUMTECHNOLOGIE(2),
    CHEMIE(3),
    DIGITAL_DESIGN_AND_DEVELOPMENT(4),
    ELEKTROMECHANICA(5);
    companion object {
        fun fromValueOrNull(value: Int): Course? {
            return values().firstOrNull { it.value == value }
        }
    }
}

class CourseEnumJsonAdapter : JsonAdapter<Course>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Course? {
        return if (reader.peek() != JsonReader.Token.NULL) {
            Course.fromValueOrNull(reader.nextInt())
        } else {
            reader.nextNull()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Course?) {
        writer.value(value?.value)
    }
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
        Timber.i(user_metadata.toString())
        return User_metadata(if(!user_metadata.has("Bedrijf")) null else user_metadata.get("Bedrijf") as String,  if(!user_metadata.has("Course")) null else Course.valueOf(user_metadata.get("Course").toString()) as Course, user_metadata.get("Intern") as Boolean)
    }
}