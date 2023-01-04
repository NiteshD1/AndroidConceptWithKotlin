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

        //coroutineDemo()
        //dispatchersDemo()
//        runBlocking {
//            startCounter()
//        }

        //asyncDemo()
        setupClickListners()
    }

    private fun asyncDemo() {
        GlobalScope.launch {
            val str = async{ receiveDataFromServer()}
            Timber.d(str.await())
        }
    }

    suspend fun receiveDataFromServer(): String{
        delay(5000)
        return "Data Fetched From Server"
    }

    private fun dispatchersDemo() {
//        GlobalScope.launch(Dispatchers.Main) {
//            startCounter()
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            startCounter()
//        }
//
//        GlobalScope.launch(Dispatchers.Default) {
//            startCounter()
//
//        }

        GlobalScope.launch(Dispatchers.Unconfined) {
            startCounter()

        }
    }

    private fun coroutineDemo() {

//        GlobalScope.launch {
//            startCounter()
//        }

        lifecycleScope.launch{
           startCounter()
        }
    }

    suspend fun startCounter(){
        while (true){
            //delay(1000)
            Thread.sleep(1000)
            threadCounter += 1
            Timber.d("Current Thread id : "+Thread.currentThread().id.toString())
            binding.textView.text = threadCounter.toString()
        }
    }
    private fun setupClickListners() {

        binding.buttonStartTestActivity.setOnClickListener{
            startActivity(Intent(this,TestActivity::class.java))
            finish()
        }

        var job : Job? = null
        binding.buttonStart.setOnClickListener {
            isThreadRunning = true

            job = GlobalScope.launch { startCounter() }
        }

        binding.buttonStop.setOnClickListener{
            isThreadRunning = false
            job?.cancel()
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