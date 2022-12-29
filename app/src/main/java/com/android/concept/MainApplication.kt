package com.android.concept

import android.app.Application
import android.content.Context
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MainApplication.appContext = applicationContext

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {

        lateinit  var appContext: Context

    }
}