package com.example.pagerlistapp.dao


import android.os.Parcelable
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagerlistapp.models.Work


@Database(entities = [Work::class], version = 2,exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun workDao(): WorkDao?
}
