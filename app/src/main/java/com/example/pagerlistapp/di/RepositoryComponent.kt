package com.example.pagerlistapp.di

import com.example.pagerlistapp.viewmodels.MainActivityViewModel
import dagger.Subcomponent

@Subcomponent
interface RepositoryComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoryComponent
    }
    fun inject(mainActivityViewModel: MainActivityViewModel)
}