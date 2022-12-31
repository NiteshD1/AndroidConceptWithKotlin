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
    var isThreadRunning = false;
    var isThreadRunning1 = false;

    private lateinit var runnable: Runnable
    private lateinit var runnable1: Runnable

    private lateinit var backgroundThread: BackgroundThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        Timber.d("Current Thread Id : " + Thread.currentThread().id.toString())
        setupClickListners()
    }

    private fun setupClickListners() {
        backgroundThread = BackgroundThread()
        backgroundThread.start()

        runnable = Runnable{
            while (isThreadRunning){
                SystemClock.sleep(1000)
                threadCounter += 1
                Timber.d("Current Thread Id : " + Thread.currentThread().id.toString())

                runOnUiThread{
                    binding.textView.text = threadCounter.toString()
                }
            }
        }

        runnable1 = Runnable{
            while (isThreadRunning1){
                SystemClock.sleep(1000)
                threadCounter += 5
                Timber.d("Current Thread Id : " + Thread.currentThread().id.toString())

                runOnUiThread{
                    binding.textView.text = threadCounter.toString()
                }
            }
        }

        binding.buttonStart.setOnClickListener {
            isThreadRunning = true

            backgroundThread.addTaskToBackgroundThread(runnable)
        }


        binding.buttonStop.setOnClickListener{
            isThreadRunning = false
            backgroundThread.removeTaskFromBackgroundThread(runnable)
        }

        binding.buttonStart2.setOnClickListener{
            isThreadRunning1 = true
            backgroundThread.addTaskToBackgroundThread(runnable1)
        }

        binding.buttonStop2.setOnClickListener{
            isThreadRunning1 = false
            backgroundThread.removeTaskFromBackgroundThread(runnable1)
        }
    }


}