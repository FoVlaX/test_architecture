package com.example.pagerlistapp.di

import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.ImageApiService
import com.example.pagerlistapp.RickAndMortyApiService
import com.example.pagerlistapp.repository.Repository
import com.example.pagerlistapp.repository.DateRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun repository(database: AppDatabase, imageApi: ImageApiService, rickAndMortyApiService: RickAndMortyApiService) : Repository {
        return DateRepository(database = database, apiImage = imageApi, rickAndMortyApiService = rickAndMortyApiService)
    }
}