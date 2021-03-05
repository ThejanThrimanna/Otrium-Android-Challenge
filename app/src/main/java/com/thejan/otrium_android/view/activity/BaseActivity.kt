package com.thejan.otrium_android.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thejan.otrium_android.GitHubApplication
import com.thejan.otrium_android.di.AppComponent

abstract class BaseActivity : AppCompatActivity() {
    val appComponent: AppComponent
        get() = (GitHubApplication.instance).appComponent

    protected abstract fun performInjection()

    override fun onCreate(savedInstanceState: Bundle?) {
        performInjection()
        super.onCreate(savedInstanceState)
    }
}