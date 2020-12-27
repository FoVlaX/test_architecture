package com.example.pagerlistapp.dao

import androidx.room.*
import com.example.pagerlistapp.amodels.Value

@Dao
interface ValueDao {
    @Query("SELECT * FROM value")
    fun getAll(): List<Value?>?
    @Query("SELECT * FROM value WHERE nameValue = :name ORDER BY numberCount LIMIT :offset, :count")
    fun getForName(name: String, offset: Int, count: Int): List<Value?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Value?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<Value?>)
    @Update
    fun update(student: Value?)
    @Delete
    fun delete(student: Value?)
}