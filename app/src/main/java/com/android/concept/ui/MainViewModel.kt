package com.android.concept.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.concept.data.models.Product
import com.android.concept.data.repository.MainRepository
import com.android.concept.utils.Resource
import com.android.concept.utils.Utils
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val repository = MainRepository()
    val productResourceResponse : MutableLiveData<Resource<List<Product>?>> = MutableLiveData()

    fun addProductToDb(product: Product) = viewModelScope.launch {
        if(repository.getProductById(product.id) == null){
            repository.addProductToDb(product)
            Utils.showToastOnMainThread("Product Saved!")
        }else{
            Utils.showToastOnMainThread("Product Already Saved!")
        }
    }

    fun deleteProductFromDb(product: Product) = viewModelScope.launch {
        repository.deleteProductFromDb(product)
        Utils.showToastOnMainThread("Product Deleted!")
    }

    fun getSavedDataFromDb() = repository.getSavedDataFromDb()

    fun fetchProductDataFromServer() = viewModelScope.launch {

        productResourceResponse.postValue(Resource.Loading())

        val productListResponse = repository.getProductDataFromServer()

        if(productListResponse.isSuccessful){

            productListResponse.body().let {
                val productList = it
                productResourceResponse.postValue(Resource.Success(productList))
            }
        }else{
            productResourceResponse.postValue(Resource.Error("Something Went Wrong!"))
        }
    }


 //   var counter = 0

//    fun getCountValue() : Int{
//        counter += 1
//        return counter
//    }

}