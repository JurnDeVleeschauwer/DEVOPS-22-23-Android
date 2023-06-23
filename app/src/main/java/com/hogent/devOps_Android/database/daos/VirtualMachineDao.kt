package com.hogent.devOps_Android.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hogent.devOps_Android.database.entities.Customer
import com.hogent.devOps_Android.database.entities.VirtualMachine

@Dao
interface VirtualMachineDao {
    @Insert
    fun insert(vm: VirtualMachine)

    @Update
    fun update(vm: VirtualMachine)

    @Query("SELECT * FROM virtualmachine_table WHERE id = :key")
    suspend fun get(key: Long): VirtualMachine?

    @Query("SELECT * FROM customer_table WHERE id = :key")
    fun getKlant(key: Long): Customer

    @Query("SELECT * FROM virtualmachine_table WHERE project_id = :key")
    fun getByProjectId(key: Long): List<VirtualMachine>?


}


