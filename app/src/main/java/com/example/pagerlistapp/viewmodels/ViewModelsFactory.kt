package com.example.pagerlistapp.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import java.lang.Exception
import javax.inject.Inject

class ViewModelsFactory @Inject constructor
    (owner: SavedStateRegistryOwner,
     defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        var result: T? = null
        for (constructor in modelClass.constructors){
            try{
                result = constructor.newInstance(handle) as T?
                break
            }catch(ex: Exception){

            }
        }
        return result?:throw IllegalArgumentException("Model class $modelClass has no constructor with SavedStateHandle")
    }

}