package com.example.pagerlistapp.di

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pagerlistapp.MainActivity
import com.example.pagerlistapp.fragments.MainFragment
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
    fun inject(mainFragment: MainFragment)
}