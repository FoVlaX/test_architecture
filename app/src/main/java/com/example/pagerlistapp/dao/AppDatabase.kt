package com.example.pagerlistapp.dao


import android.os.Parcelable
import android.renderscript.Sampler
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagerlistapp.amodels.Value



@Database(entities = [Value::class], version = 4,exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun valueDao(): ValueDao?
}
