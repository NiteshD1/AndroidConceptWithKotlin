package com.android.concept.db

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context)
{
    private var PREF_EMAIL = "pref_email"

    private val preferences: SharedPreferences = context.getSharedPreferences("global_shared_preference",Context.MODE_PRIVATE)

    var email: String?
        get() = preferences.getString(PREF_EMAIL, null)
        set(value) = preferences.edit().putString(PREF_EMAIL, value).apply()
}