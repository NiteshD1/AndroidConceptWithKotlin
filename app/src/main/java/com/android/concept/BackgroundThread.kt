package com.android.concept

import android.os.Handler
import android.os.Looper

class BackgroundThread : Thread() {

    private var handler : Handler? = null

    override fun run() {
        super.run()

        Looper.prepare()

        handler = Looper.myLooper()?.let{
            Handler(it)
        }

        Looper.loop()
    }

    fun addTaskToBackgroundThread(runnable: Runnable){
        handler?.post(runnable)
    }

    fun removeTaskFromBackgroundThread(runnable: Runnable){
        handler?.removeCallbacks(runnable)
    }
}