package com.android.concept.utils

import android.widget.Toast
import com.android.concept.MainApplication

object Utils {
    fun showToast(message : String){
        Toast.makeText(MainApplication.appContext,message,Toast.LENGTH_LONG).show()
    }
}