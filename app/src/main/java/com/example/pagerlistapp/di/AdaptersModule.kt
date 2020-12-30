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
    fun adaptersFactory(adapters: Set<@JvmSuppressWildcards KClass<*> > ) : IAdapterFactory {
        return AdaptersFactory(adapters = adapters)
    }

    @IntoSet
    @Provides
    fun loadAdapter() : KClass<*> = LoadAdapter::class

    @Provides
    @IntoSet
    fun worksAdapter() : KClass<*>  =  ImageAdapter::class

    @IntoSet
    @Provides
    fun rickAndMortyAdapter() :  KClass<*> =  RickAndMortyAdapter::class


}