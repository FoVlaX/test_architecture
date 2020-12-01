package com.example.pagerlistapp.di

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagerlistapp.viewmodels.ViewModelsFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModel {

    @Provides
    fun getFactory(owner: SavedStateRegistryOwner,
                   defaultArgs: Bundle?) : ViewModelsFactory {
        return ViewModelsFactory(owner, defaultArgs)
    }
}