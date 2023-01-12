package com.android.concept

import android.app.Application
import android.content.Context
import com.android.concept.db.Prefs
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MainApplication.appContext = applicationContext

        prefs = Prefs(appContext)

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {

        lateinit  var appContext: Context
        var prefs:Prefs? = null

    }
}