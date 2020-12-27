package com.example.pagerlistapp.di


import com.example.pagerlistapp.ImageApiService
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
}