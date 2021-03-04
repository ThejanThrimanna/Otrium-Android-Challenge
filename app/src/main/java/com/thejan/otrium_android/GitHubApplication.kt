package com.thejan.otrium_android

import android.app.Application
import com.thejan.otrium_android.di.AppComponent
import com.thejan.otrium_android.di.DaggerAppComponent

class GitHubApplication : Application(){

    val appComponent: AppComponent
        get() = staticAppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        staticAppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    companion object {
        lateinit var instance:GitHubApplication
        @JvmStatic lateinit var staticAppComponent: AppComponent

    }
}