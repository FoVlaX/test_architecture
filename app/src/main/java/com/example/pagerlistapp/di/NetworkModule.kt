package com.example.pagerlistapp.di


import com.example.pagerlistapp.ImageApiService
import com.example.pagerlistapp.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun apiImage(): ImageApiService{
        return ImageApiService.create()
    }

    @Provides
    @Singleton
    fun apiRockAndMorty(): RickAndMortyApiService{
        return RickAndMortyApiService.create()
    }
}