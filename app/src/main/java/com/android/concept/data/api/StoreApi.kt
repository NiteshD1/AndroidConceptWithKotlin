package com.android.concept.data.api

import com.android.concept.data.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {

        @GET("products")
        suspend fun getProductList(): Response<List<Product>>

}