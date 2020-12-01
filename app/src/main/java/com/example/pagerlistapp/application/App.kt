package com.example.pagerlistapp.application

import android.app.Application
import com.example.pagerlistapp.di.ApplicationComponent
import com.example.pagerlistapp.di.ContextModule
import com.example.pagerlistapp.di.DaggerApplicationComponent


class App : Application() {
    var PACKAGE_NAME: String? = null
    var applicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        PACKAGE_NAME = this.packageName
        applicationComponent = DaggerApplicationComponent.builder().contextModule(ContextModule(applicationContext)).build()
    }

    companion object {
        var instance: App? = null
    }
}