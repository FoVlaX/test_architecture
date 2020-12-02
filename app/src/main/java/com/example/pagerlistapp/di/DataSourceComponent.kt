package com.example.pagerlistapp.di

import com.example.pagerlistapp.datasource.Functions
import com.example.pagerlistapp.datasource.LoadDataPos
import com.example.pagerlistapp.repository.AbstractRepository
import com.example.pagerlistapp.repository.Repository
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DataSourceModule::class])
interface DataSourceComponent {
    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun withClass( dataSourceFactoryClass: HashMap<String, Class<out Any>> ): Builder?
        @BindsInstance
        fun with(loadDataPos: HashMap<String, Functions>): Builder?
        fun build(): DataSourceComponent
    }

    fun inject(abstractRepository: AbstractRepository)
}