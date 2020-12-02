package com.example.pagerlistapp.dao

import androidx.room.*
import com.example.pagerlistapp.models.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): List<Event?>?
    @Query("SELECT * FROM event WHERE ids < :id or (:id = 0 and ids = ids) ORDER BY ids DESC LIMIT 0,:count")
    fun getEvents(id: Int?, count: Int?): List<Event?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Event?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<Event?>)
    @Update
    fun update(student: Event?)
    @Delete
    fun delete(student: Event?)
}