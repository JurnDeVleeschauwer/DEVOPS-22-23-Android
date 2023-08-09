package com.hogent.devOps_Android.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.VirtualMachineEntitiy

@Dao
interface VirtualMachineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg vms: VirtualMachineEntitiy)

    @Query("SELECT * FROM virtualmachine_table WHERE id = :key")
    suspend fun get(key: Long): VirtualMachineEntitiy?

    @Query("SELECT * FROM virtualmachine_table")
    fun getAll(): List<VirtualMachineEntitiy>


}


