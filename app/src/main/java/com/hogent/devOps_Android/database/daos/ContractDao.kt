package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.Contract

@Dao
interface ContractDao {
    @Insert
    fun insert(contract: Contract)

    @Update
    fun update(contract: Contract)

    @Query("SELECT * FROM contract_table WHERE id = :key")
    fun get(key: Long): Contract?
}