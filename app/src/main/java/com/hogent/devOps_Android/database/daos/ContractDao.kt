package com.hogent.devOps_Android.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hogent.devOps_Android.database.entities.ContractEntitiy

@Dao
interface ContractDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg contracts: ContractEntitiy)

    @Query("SELECT * FROM contract_table WHERE id = :key")
    fun get(key: Long): ContractEntitiy?
}
