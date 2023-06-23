package com.hogent.devOps_Android.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hogent.devOps_Android.database.entities.Project
import com.hogent.devOps_Android.database.entities.VirtualMachine

@Dao
interface ProjectDao {
    @Insert
    fun insert(project : Project)
    @Update
    fun update(project : Project)

    @Query("SELECT * FROM project_table WHERE id = :key")
    fun get(key : Long): Project?

    @Query("SELECT * FROM project_table")
    fun getAll(): List<Project>?

    @Query("select * from project_table p where p.customer_id == :key")
    fun getByCustomerId(key: Long): List<Project>?



}


