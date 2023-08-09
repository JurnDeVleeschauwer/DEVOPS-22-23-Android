package com.hogent.devOps_Android.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.VirtualMachineEntitiy

@Dao
interface VirtualMachineDao {
    @Insert
    fun insert(vm: VirtualMachineEntitiy)

    @Update
    fun update(vm: VirtualMachineEntitiy)

    @Query("SELECT * FROM virtualmachine_table WHERE id = :key")
    suspend fun get(key: Long): VirtualMachineEntitiy?

    @Query("SELECT * FROM virtualmachine_table WHERE project_id = :key")
    fun getByProjectId(key: Long): List<VirtualMachineEntitiy>?


}


