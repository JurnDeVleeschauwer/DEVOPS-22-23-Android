package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity

@Dao
interface ProjectVirtualMachineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg projects: ProjectVirtualMachineEntity)

    @Query("select * from project_virtualmachine_table where project_id == :key")
    fun getByProjectId(key: Long): List<ProjectVirtualMachineEntity>?

    @Query("select * from project_virtualmachine_table")
    fun getAll(): LiveData<List<ProjectVirtualMachineEntity>>

    @Query("select * from project_virtualmachine_table")
    fun getAllList(): List<ProjectVirtualMachineEntity>

    @Query("DELETE from project_virtualmachine_table")
    fun delete()

}
