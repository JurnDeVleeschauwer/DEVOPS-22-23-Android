package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hogent.devOps_Android.database.entities.ProjectEntitiy

@Dao
interface ProjectDao {
    @Insert
    fun insert(project : ProjectEntitiy)
    @Update
    fun update(project : ProjectEntitiy)

    @Query("SELECT * FROM project_table WHERE id = :key")
    fun get(key : Long): ProjectEntitiy?

    @Query("SELECT * FROM project_table")
    fun getAll(): List<ProjectEntitiy>?

    @Query("select * from project_table p where p.customer_id == :key")
    fun getByCustomerId(key: Long): List<ProjectEntitiy>?



}


