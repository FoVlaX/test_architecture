package com.example.pagerlistapp.di


import com.example.pagerlistapp.ArtistApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun api(): ArtistApiService {
        return ArtistApiService.create()
    }
}