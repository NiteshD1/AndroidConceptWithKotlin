package com.android.concept.main

import com.android.concept.data.models.Product

interface MainActivityPresenterContract {
    suspend fun fetchAllProductDataFromServer()
    suspend fun fetchSavedProductDataFromDb()
    suspend fun addProductToDb(product: Product)
    suspend fun deleteProductFromDb(product: Product)
}