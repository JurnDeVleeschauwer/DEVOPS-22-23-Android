package com.hogent.devOps_Android.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hogent.devOps_Android.database.entities.ProjectVirtualMachineEntity

@Dao
interface ProjectVirtualMachineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg projects: ProjectVirtualMachineEntity)

    @Query("select * from project_virtualmachine_table p where p.project_id == :key")
    fun getByProjectId(key: Long): List<ProjectVirtualMachineEntity>?



}
