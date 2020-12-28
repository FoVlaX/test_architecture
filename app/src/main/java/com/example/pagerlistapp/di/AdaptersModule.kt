package com.example.pagerlistapp.di

import com.example.pagerlistapp.adapters.LoadAdapter
import com.example.pagerlistapp.adapters.ImageAdapter
import com.example.pagerlistapp.adapters.RickAndMortyAdapter
import com.fovlax.datasourcelibrary.datasource.LoadDataPos
import dagger.Module
import dagger.Provides

@Module
class AdaptersModule {

    @Provides
    fun loadAdapter() : LoadAdapter = LoadAdapter()

    @Provides
    fun worksAdapter() : ImageAdapter = ImageAdapter()

    @Provides
    fun rickAndMortyAdapter() : RickAndMortyAdapter = RickAndMortyAdapter()

}