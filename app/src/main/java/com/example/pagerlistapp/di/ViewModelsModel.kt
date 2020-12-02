package com.example.pagerlistapp.di

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelsModel {

    @Singleton
    @Provides
    fun getFactory(owner: SavedStateRegistryOwner,
                   defaultArgs: Bundle?) : ViewModelsFactory {
        return ViewModelsFactory(owner, defaultArgs)
    }
}