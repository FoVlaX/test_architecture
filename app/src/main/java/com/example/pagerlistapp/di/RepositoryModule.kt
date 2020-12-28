package com.example.pagerlistapp.di

import com.example.pagerlistapp.dao.AppDatabase
import com.example.pagerlistapp.ImageApiService
import com.example.pagerlistapp.RickAndMortyApiService
import com.example.pagerlistapp.repository.IRepository
import com.example.pagerlistapp.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun repository(database: AppDatabase, imageApi: ImageApiService, rickAndMortyApiService: RickAndMortyApiService) :IRepository {
        return Repository(database = database, apiImage = imageApi, rickAndMortyApiService = rickAndMortyApiService)
    }
}