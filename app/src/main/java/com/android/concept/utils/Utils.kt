package com.android.concept.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.android.concept.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Utils {

    private var PREF_PERMISSION = "pref_permission"

    private val preferences: SharedPreferences = MainApplication.appContext.getSharedPreferences("global_shared_preference",Context.MODE_PRIVATE)


    fun showToast(message : String){
        Toast.makeText(MainApplication.appContext,message,Toast.LENGTH_LONG).show()
    }

    suspend fun showToastOnMainThread(message : String){
        withContext(Dispatchers.Main){
            Toast.makeText(MainApplication.appContext,message,Toast.LENGTH_LONG).show()
        }
    }
}