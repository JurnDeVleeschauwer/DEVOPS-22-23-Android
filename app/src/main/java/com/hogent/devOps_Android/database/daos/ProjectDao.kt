package com.hogent.devOps_Android.database.daos

import androidx.room.*
import com.hogent.devOps_Android.database.entities.ProjectEntitiy

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg projects: List<ProjectEntitiy>)

    @Query("SELECT * FROM project_table WHERE id = :key")
    fun get(key : Long): ProjectEntitiy

    @Query("select * from project_table p where p.User.userid == :key")
    fun getByCustomerId(key: Long): List<ProjectEntitiy>?



}


