package com.example.pagerlistapp.di

import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.ArtistApiService
import com.example.pagerlistapp.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun repository(database: AppDatabase, api: ArtistApiService) :Repository {
        return Repository(database = database, api = api)
    }
}