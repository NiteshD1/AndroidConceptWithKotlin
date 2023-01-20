package com.android.concept.controller

import com.android.concept.data.api.RetrofitInstance
import com.android.concept.data.db.room.ProductDatabase
import com.android.concept.data.models.Product
import com.android.concept.utils.Utils

class MainController {

    private val dao = ProductDatabase.getInstance()?.getProductDao()
    private val api = RetrofitInstance.api

    suspend fun addProductToDb(product: Product){
        if(dao?.getById(product.id) == null){
            dao?.upsert(product)
            Utils.showToastOnMainThread("Product Saved!")
        }else{
            Utils.showToastOnMainThread("Product Already Saved!")
        }
    }

    suspend fun deleteProductFromDb(product: Product){
        dao?.deleteProduct(product)
        Utils.showToastOnMainThread("Product Deleted!")
    }

    suspend fun getAllProductDataFromServer() : List<Product>?{
        val responseProductList = api.getProductList()

        var productList : List<Product>? = null

        if(responseProductList.isSuccessful){

            responseProductList.body().let {
                productList = it
                println("Products Data : ${productList.toString()}")
            }
        }else{
            Utils.showToast("Something went wrong,Please try again later!")
        }

        return productList
    }

    suspend fun getSavedProductFromDb(): List<Product>?{
        return dao?.getAllProducts()
    }
}