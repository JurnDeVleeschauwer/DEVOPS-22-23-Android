package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hogent.devOps_Android.database.entities.VirtualMachineEntitiy

@Dao
interface VirtualMachineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg vms: VirtualMachineEntitiy)

    @Query("SELECT * FROM virtualmachine_table WHERE id = :key")
    fun get(key: Long): LiveData<VirtualMachineEntitiy>

    @Query("SELECT * FROM virtualmachine_table")
    fun getAll(): LiveData<List<VirtualMachineEntitiy>>

    @Query("SELECT * FROM virtualmachine_table")
    fun getAllList(): List<VirtualMachineEntitiy>
}
