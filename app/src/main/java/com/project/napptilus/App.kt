package com.project.napptilus

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
    }
}