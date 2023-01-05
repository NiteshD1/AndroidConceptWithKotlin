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
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.android.concept.api.RetrofitInstance
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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        Timber.d("Current Thread id : "+Thread.currentThread().id.toString())

        binding.buttonFetchData.setOnClickListener{
            fetchProductData()
        }
    }

    private fun fetchProductData() {

        GlobalScope.launch(Dispatchers.IO){

            val responseProductList = RetrofitInstance.api.getProductList()

            if(responseProductList.isSuccessful){

                var productList : MutableList<String> = mutableListOf()

                responseProductList.body().let {

                    it?.forEach(){

                        Timber.d("Product Data : $it")
                        val productString = "ProductId : ${it.id} \nProduct Title : ${it.title} \n Product Price : ${it.price}"

                        productList.add(productString)
                    }
                }

                withContext(Dispatchers.Main){
                    val arrayAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1,productList)
                    binding.listView.adapter = arrayAdapter
                }
            }else{
                responseProductList.errorBody().let {
                    Timber.e("Response Error : $it")
                }
            }
        }
    }


}