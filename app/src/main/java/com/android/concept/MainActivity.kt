package com.android.concept


import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.utils.Utils
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var thread: Thread
    private lateinit var binding: ActivityMainBinding
    var threadCounter = 0
    var isThreadRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        Timber.d("Current Thread id : "+Thread.currentThread().id.toString())

        setupClickListners()
    }

    private fun setupClickListners() {
        binding.buttonStart.setOnClickListener {


            isThreadRunning = true

            thread = Thread {
                while (isThreadRunning){
                    SystemClock.sleep(1000)
                    threadCounter += 1
                    Timber.d("Current Thread id : "+Thread.currentThread().id.toString())


                    runOnUiThread{
                        binding.textView.text = threadCounter.toString()
                    }
                }
            }
            thread.start()

        }


        binding.buttonStop.setOnClickListener{
            isThreadRunning = false
        }
    }


}