package com.example.pagerlistapp.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, DatabaseModule::class, SubComponentsModule::class])
interface ApplicationComponent {
    fun repositoryComponent(): RepositoryComponent.Factory
    fun viewModelComponent(): ViewModelComponent.Builder
}