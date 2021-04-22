package com.example.pagerlistapp.dao

import androidx.room.*
import com.example.pagerlistapp.amodels.UIRockAndMortyModel
import com.example.pagerlistapp.amodels.Value

@Dao
interface RDataItemDao {

    @Query("SELECT * FROM imagedataitem")
    fun getAll(): List<UIRockAndMortyModel.ImageDataItem?>?
    @Query("SELECT * FROM imagedataitem WHERE id > :id ORDER BY id LIMIT 0, :count")
    fun getForName(id: Int, count: Int): List<UIRockAndMortyModel.ImageDataItem?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: UIRockAndMortyModel.ImageDataItem?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: List<UIRockAndMortyModel.ImageDataItem?>)
    @Update
    fun update(student: UIRockAndMortyModel.ImageDataItem?)
    @Delete
    fun delete(student: UIRockAndMortyModel.ImageDataItem?)

}