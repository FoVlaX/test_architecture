package com.example.pagerlistapp.di

import androidx.recyclerview.widget.RecyclerView
import com.example.pagerlistapp.adapters.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import kotlin.reflect.KClass

@Module
class AdaptersModule {

    @Provides
    fun adaptersFactory(adapters: Set<@JvmSuppressWildcards KClass<out RecyclerView.Adapter<RecyclerView.ViewHolder>> > ) : IAdapterFactory {
        return AdaptersFactory(adapters = adapters)
    }

    @ElementsIntoSet
    @Provides
    fun appAdapters() : Set<KClass<out RecyclerView.Adapter<RecyclerView.ViewHolder>>> = HashSet(arrayListOf(
            LoadAdapter::class,  ImageAdapter::class, RickAndMortyAdapter::class
    ))

}