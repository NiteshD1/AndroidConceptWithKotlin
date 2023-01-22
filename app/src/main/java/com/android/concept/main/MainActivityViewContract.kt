package com.android.concept.main

import com.android.concept.data.models.Product

interface MainActivityViewContract {
    suspend fun updateProductList(productList : List<Product>?,isSavedProduct : Boolean = false)
}