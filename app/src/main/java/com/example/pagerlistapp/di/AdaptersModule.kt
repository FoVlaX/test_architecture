package com.example.pagerlistapp.di

import com.example.pagerlistapp.adapters.EventAdapter
import com.example.pagerlistapp.adapters.LoadAdapter
import com.example.pagerlistapp.adapters.WorksAdapter
import dagger.Module
import dagger.Provides

@Module
class AdaptersModule {

    @Provides
    fun loadAdapter() : LoadAdapter = LoadAdapter()

    @Provides
    fun worksAdapter() : WorksAdapter = WorksAdapter()

    @Provides
    fun eventAdapter(): EventAdapter = EventAdapter()

}