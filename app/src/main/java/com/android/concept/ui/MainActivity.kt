package com.android.concept.ui


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.concept.data.models.Product
import com.android.concept.databinding.ActivityMainBinding
import com.android.concept.ui.adapter.RecyclerViewAdapterForProducts
import com.android.concept.utils.Resource
import com.android.concept.utils.Utils


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapterForProducts
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Fashion Store"

        binding.recyclerView.layoutManager = LinearLayoutManager(this)



        binding.buttonLoadAllProducts.setOnClickListener{

            viewModel.fetchProductDataFromServer()

            viewModel.productResourceResponse.observe(this) {

                when(it){
                    is Resource.Loading -> showProgressbar()
                    is Resource.Error -> {
                        hideProgressbar()
                        Utils.showToast(it.message.toString())
                    }
                    is Resource.Success -> {
                        hideProgressbar()
                        val productList = it.data
                        setupRecyclerView(productList,false)
                    }
                }
            }
        }

        binding.buttonLoadSavedProducts.setOnClickListener{

            showProgressbar()

            viewModel.getSavedDataFromDb()?.observe(this){
                hideProgressbar()
                val productList = it
                setupRecyclerView(productList,true)
            }

        }


    }

    fun showProgressbar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressbar(){
        binding.progressBar.visibility = View.GONE
    }
//    private fun setupCounter() {
//        GlobalScope.launch(Dispatchers.Main) {
//            while (true){
//                binding.textViewCounter.text = viewModel.getCountValue().toString()
//                delay(1000)
//            }
//        }
//    }



    private fun setupRecyclerView(productList: List<Product>?, isSavedProduct: Boolean = false){
        binding.progressBar.visibility = View.GONE

        adapter = RecyclerViewAdapterForProducts(
            productList?.toMutableList() ?: mutableListOf(),
            isSavedProduct,
            viewModel
        )

        binding.recyclerView.adapter = adapter

    }



}