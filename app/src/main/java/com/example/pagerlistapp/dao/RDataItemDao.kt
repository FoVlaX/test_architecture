package com.example.pagerlistapp.dao

import androidx.room.*
import com.example.pagerlistapp.amodels.RDataItem
import com.example.pagerlistapp.amodels.Value

@Dao
interface RDataItemDao {

    @Query("SELECT * FROM rdataitem")
    fun getAll(): List<RDataItem?>?
    @Query("SELECT * FROM rdataitem WHERE id > :id ORDER BY id LIMIT 0, :count")
    fun getForName(id: Int, count: Int): List<RDataItem?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: RDataItem?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<RDataItem?>)
    @Update
    fun update(student: RDataItem?)
    @Delete
    fun delete(student: RDataItem?)

}