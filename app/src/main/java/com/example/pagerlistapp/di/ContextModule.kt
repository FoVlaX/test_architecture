package com.example.pagerlistapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(
        var context: Context
) {
    @JvmName("getAppContext")
    @Singleton
    @Provides
    fun getContext() : Context {
        return context
    }
}