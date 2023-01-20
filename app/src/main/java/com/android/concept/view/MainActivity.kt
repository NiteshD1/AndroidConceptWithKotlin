package com.android.concept.view


import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.concept.controller.MainController
import com.android.concept.view.adapter.RecyclerViewAdapterForProducts
import com.android.concept.data.api.RetrofitInstance
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.data.db.room.ProductDatabase
import com.android.concept.data.models.Product
import com.android.concept.utils.Utils
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapterForProducts
    private val api = RetrofitInstance.api
    private val dao = ProductDatabase.getInstance()?.getProductDao()
    private val controller = MainController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Fashion Store"

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

        val productList = controller.getSavedProductFromDb()

        withContext(Dispatchers.Main){
            setupRecyclerView(productList,true)
        }
    }

    private suspend fun displayProductDataFromServer() {

        val productList = controller.getAllProductDataFromServer()
        withContext(Dispatchers.Main){
            setupRecyclerView(productList)
        }


    }

    private fun setupRecyclerView(productList: List<Product>?, isSavedProduct: Boolean = false){
        binding.progressBar.visibility = View.GONE

        adapter = RecyclerViewAdapterForProducts(
            productList?.toMutableList() ?: mutableListOf(),
            isSavedProduct
        )

        binding.recyclerView.adapter = adapter

    }



}