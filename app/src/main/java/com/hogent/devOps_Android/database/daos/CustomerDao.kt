package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hogent.devOps_Android.database.entities.UserEntitiy

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserEntitiy)

    @Query("SELECT * FROM user_table WHERE UserId = :key")
    fun get(key: String): LiveData<UserEntitiy>


}



