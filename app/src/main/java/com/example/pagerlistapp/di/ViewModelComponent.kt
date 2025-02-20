package com.example.pagerlistapp.di

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagerlistapp.MainActivity
import com.example.pagerlistapp.fragments.BaseFragment
import com.example.pagerlistapp.fragments.PosFragment
import com.example.pagerlistapp.fragments.RickAndMortyFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ViewModelComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun with(owner: SavedStateRegistryOwner): Builder?
        @BindsInstance
        fun with(defaultArgs: Bundle?): Builder?
        fun build(): ViewModelComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(posFragment: BaseFragment)
}