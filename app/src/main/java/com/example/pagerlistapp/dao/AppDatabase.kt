package com.example.pagerlistapp.dao


import android.os.Parcelable
import android.renderscript.Sampler
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagerlistapp.amodels.UIRockAndMortyModel
import com.example.pagerlistapp.amodels.Value



@Database(entities = [Value::class, UIRockAndMortyModel.ImageDataItem::class], version = 5,exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun valueDao(): ValueDao?
    abstract fun rDataItemDao(): RDataItemDao?
}
