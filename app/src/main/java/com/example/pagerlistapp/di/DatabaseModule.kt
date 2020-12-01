package com.example.pagerlistapp.di

import android.content.Context
import androidx.room.Room
import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(context: Context): AppDatabase {
        val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                context.getString(R.string.database_name)
        ).build()
        return instance;

    }

}