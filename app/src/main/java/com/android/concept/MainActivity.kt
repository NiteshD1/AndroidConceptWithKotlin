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
import android.os.*
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.utils.Utils
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.*
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        Timber.d("Current Thread id : "+Thread.currentThread().id.toString())

        binding.buttonStartWorkManager.setOnClickListener{
            startWorkManager()
        }

        binding.buttonStopWorkManager.setOnClickListener{
            stopWorkManager()
        }
    }

    private fun stopWorkManager() {
        WorkManager.getInstance(this).cancelAllWorkByTag("worker")
    }

    private fun startWorkManager() {

//        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
//        val oneTimeRequest = OneTimeWorkRequestBuilder<MyWork>()
//            .setConstraints(constraint)
//            .addTag("worker")
//            .setInitialDelay(10,TimeUnit.SECONDS)
//            .build()
//
//        WorkManager.getInstance(this).enqueue(oneTimeRequest)


        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val oneTimeRequest = PeriodicWorkRequestBuilder<MyWork>(15,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .addTag("worker")
            .setInitialDelay(10,TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this).enqueue(oneTimeRequest)
    }


}