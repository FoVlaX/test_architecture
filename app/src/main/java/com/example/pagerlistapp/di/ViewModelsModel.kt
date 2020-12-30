package com.example.pagerlistapp.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagerlistapp.repository.DateRepository
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module
class ViewModelsModel {

    @Singleton
    @Provides
    fun getFactory(owner: SavedStateRegistryOwner,
                   defaultArgs: Bundle?, dateRepository: DateRepository) : ViewModelsFactory {
        return ViewModelsFactory(owner, defaultArgs, dateRepository)
    }
}