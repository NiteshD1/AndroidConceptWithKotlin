package com.android.concept.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.android.concept.MainApplication

object Utils {

    private var PREF_PERMISSION = "pref_permission"

    private val preferences: SharedPreferences = MainApplication.appContext.getSharedPreferences("global_shared_preference",Context.MODE_PRIVATE)


    var isPermissionAskedFirstTime: Boolean
        get() = preferences.getBoolean(PREF_PERMISSION, true)
        set(value) = preferences.edit().putBoolean(PREF_PERMISSION,value).apply()

    fun showToast(message : String){
        Toast.makeText(MainApplication.appContext,message,Toast.LENGTH_LONG).show()
    }
}