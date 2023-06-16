package com.android.concept


import android.os.*
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.concept.adapter.RecyclerViewAdapterForProducts
import com.android.concept.api.RetrofitInstance
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.db.room.ProductDatabase
import com.android.concept.models.Product
import com.android.concept.utils.Utils
import kotlinx.coroutines.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapterForProducts
    private val api = RetrofitInstance.api
    private val dao = ProductDatabase.getInstance()?.getProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Android Demo"

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.buttonLoadAllProducts.setOnClickListener{

            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                displayProductDataFromServer()
            }
        }

        binding.buttonLoadSavedProducts.setOnClickListener{
            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                displayProductsFromDb()
            }
        }


    }

    private suspend fun displayProductsFromDb() {

        val productList = dao?.getAllProducts()

        withContext(Dispatchers.Main){
            setupRecyclerView(productList,true)
        }
    }

    private suspend fun displayProductDataFromServer() {

        val responseProductList = api.getProductList()

        if(responseProductList.isSuccessful){

            responseProductList.body()?.let {

                val productList = it
                println("Products Data : ${productList.toString()}")

                withContext(Dispatchers.Main){
                    setupRecyclerView(productList)
                }
            }
        }else{
            Utils.showToast("Something went wrong,Please try again later!")
        }
    }

    private fun setupRecyclerView(productList: List<Product>?,isSavedProduct: Boolean = false){
        binding.progressBar.visibility = View.GONE

        adapter = RecyclerViewAdapterForProducts(
            productList?.toMutableList() ?: mutableListOf(),
            isSavedProduct
        )

        binding.recyclerView.adapter = adapter

    }



}