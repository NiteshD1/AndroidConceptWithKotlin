package com.android.concept.data.repository

import com.android.concept.data.api.RetrofitInstance
import com.android.concept.data.db.room.ProductDatabase
import com.android.concept.data.models.Product

class MainRepository {

    val api = RetrofitInstance.api
    val dao = ProductDatabase.getInstance()?.getProductDao()

    suspend fun addProductToDb(product: Product){
        dao?.upsert(product)
    }

    suspend fun deleteProductFromDb(product: Product){
        dao?.deleteProduct(product)
    }

    fun getSavedDataFromDb() = dao?.getAllProducts()

    suspend fun getProductDataFromServer() = api.getProductList()

    suspend fun getProductById(id : Int) = dao?.getById(id)
}