package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.ContractEntitiy

@Dao
interface ContractDao {
    @Insert
    fun insert(contract: ContractEntitiy)

    @Update
    fun update(contract: ContractEntitiy)

    @Query("SELECT * FROM contract_table WHERE id = :key")
    fun get(key: Long): ContractEntitiy?
}