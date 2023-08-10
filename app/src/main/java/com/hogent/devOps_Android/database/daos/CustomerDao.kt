package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.UserEntitiy
import com.hogent.devOps_Android.database.entities.VirtualMachineEntitiy

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserEntitiy)

    @Query("SELECT * FROM user_table WHERE id = :key")
    fun get(key: Long): UserEntitiy


}



