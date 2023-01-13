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
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.db.room.ProductDatabase
import com.android.concept.models.Product
import com.android.concept.utils.Utils
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var db: ProductDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        println("Activity : onCreate")

        supportActionBar?.title = "Android Demo"

        db = ProductDatabase.getInstance()


        setupClickListeners()


    }

    private fun setupClickListeners() {

        binding.buttonAddProduct.setOnClickListener{

            GlobalScope.launch(Dispatchers.IO) {
                var id = binding.editTextId.text.toString().toInt()
                var name = binding.editTextName.text.toString()
                var price = binding.editTextPrice.text.toString().toInt()

                var product = Product(id,name,price)
                addProductToDb(product)
            }
        }

        binding.buttonDeleteProducts.setOnClickListener{

            GlobalScope.launch(Dispatchers.IO) {

                if(binding.editTextId.text.toString().equals("")){
                    db?.getProductDao()?.deleteAll()
                }else{
                    var id = binding.editTextId.text.toString().toInt()
                    db?.getProductDao()?.deleteById(id)
                }

                withContext(Dispatchers.Main){
                    Utils.showToast("Deleted")
                }
            }
        }

        binding.buttonShowProducts.setOnClickListener{

            GlobalScope.launch(Dispatchers.IO) {
                var productList = db?.getProductDao()?.getAllProductData() ?: mutableListOf()

                withContext(Dispatchers.Main){
                    var arrayAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1,productList)
                    binding.listView.adapter = arrayAdapter
                }
            }
        }
    }

    private suspend fun addProductToDb(product: Product) {
        db?.getProductDao()?.upsert(product)
        withContext(Dispatchers.Main){
            Utils.showToast("Product Added")
        }
    }


}