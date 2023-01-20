package com.android.concept.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        // lazy - initialize only when it is called and return the same object every time
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(StoreApi::class.java)
        }
    }
}


