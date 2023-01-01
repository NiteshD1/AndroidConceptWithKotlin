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
import java.util.concurrent.Executors


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
        val myAsyncTask = MyAsyncTask()
        val executer = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        binding.buttonStart.setOnClickListener {
            isThreadRunning = true
            //myAsyncTask.execute(0)

            executer.execute{
                while (isThreadRunning){
                    SystemClock.sleep(1000)
                    threadCounter += 1
                    Timber.d("Current Thread id : "+Thread.currentThread().id.toString())

                    handler.post{
                        binding.textView.text = threadCounter.toString()
                    }
                }
            }
        }


        binding.buttonStop.setOnClickListener{
            isThreadRunning = false
            executer.shutdown() // if you call this method, you can not use this executer again with start button
        }
    }

    inner class MyAsyncTask : AsyncTask<Int, Int, Unit>() {

        override fun doInBackground(vararg params: Int?) {
            var counter = params[0]
            while (isThreadRunning){
                SystemClock.sleep(1000)
                counter = counter?.plus(1)
                Timber.d("Current Thread id : "+Thread.currentThread().id.toString())
                publishProgress(counter)
            }
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            binding.textView.text = values[0].toString()
        }

    }

}