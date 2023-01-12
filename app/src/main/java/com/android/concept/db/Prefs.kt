package com.android.concept.db

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    val EMAIL_KEY = "EMAIL_KEY"
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("global_shared_preferences",Context.MODE_PRIVATE)

    var email: String?
    get() = sharedPreferences.getString(EMAIL_KEY,null)
    set(value) = sharedPreferences.edit().putString(EMAIL_KEY,value).apply()

}