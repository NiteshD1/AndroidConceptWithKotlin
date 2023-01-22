package com.android.concept.main


import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.concept.main.adapter.RecyclerViewAdapterForProducts
import com.android.concept.data.api.RetrofitInstance
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.data.db.room.ProductDatabase
import com.android.concept.data.models.Product
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity(),MainActivityViewContract {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapterForProducts
    lateinit var presenter : MainActivityPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Fashion Store"

        presenter = MainActivityPresenter(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.buttonLoadAllProducts.setOnClickListener{

            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                presenter.fetchAllProductDataFromServer()
            }
        }

        binding.buttonLoadSavedProducts.setOnClickListener{
            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                presenter.fetchSavedProductDataFromDb()
            }
        }


    }



    private fun setupRecyclerView(productList: List<Product>?, isSavedProduct: Boolean = false){
        binding.progressBar.visibility = View.GONE

        adapter = RecyclerViewAdapterForProducts(
            productList?.toMutableList() ?: mutableListOf(),
            isSavedProduct,
            presenter
        )

        binding.recyclerView.adapter = adapter

    }

    override suspend fun updateProductList(productList: List<Product>?, isSavedProduct: Boolean) {
        withContext(Dispatchers.Main){
            setupRecyclerView(productList,isSavedProduct)
        }
    }


}