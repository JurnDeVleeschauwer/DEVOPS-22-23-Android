package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.UserEntitiy

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(klant: UserEntitiy)

    @Update
    fun update(klant: UserEntitiy)

    @Query("SELECT * FROM user_table WHERE id = :key")
    fun get(key: Long): LiveData<UserEntitiy?>

    @Query("SELECT * FROM user_table ORDER BY id desc")
    fun getAllUsers(): LiveData<Array<UserEntitiy>>
    
}



