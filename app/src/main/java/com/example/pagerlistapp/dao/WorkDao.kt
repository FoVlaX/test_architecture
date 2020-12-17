package com.example.pagerlistapp.dao

import androidx.room.*
import com.example.pagerlistapp.models.Work

@Dao
interface WorkDao {
    @Query("SELECT * FROM work")
    fun getAll(): List<Work?>?
    @Query("SELECT * FROM work ORDER BY positionInDb LIMIT :offset, :count")
    fun getWorks(offset: Int?, count: Int?): List<Work?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Work?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<Work?>)
    @Update
    fun update(student: Work?)
    @Delete
    fun delete(student: Work?)
}