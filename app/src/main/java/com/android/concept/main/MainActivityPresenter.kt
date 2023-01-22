package com.android.concept.main

import com.android.concept.data.api.RetrofitInstance
import com.android.concept.data.db.room.ProductDatabase
import com.android.concept.data.models.Product
import com.android.concept.utils.Utils

class MainActivityPresenter(val mainView: MainActivityViewContract) : MainActivityPresenterContract {

    val api = RetrofitInstance.api
    val dao = ProductDatabase.getInstance()?.getProductDao()

    override suspend fun fetchAllProductDataFromServer() {
        val responseProductList = api.getProductList()

        var productList : List<Product>? = null

        if(responseProductList.isSuccessful){

            responseProductList.body().let {
                productList = it
                println("Products Data : ${productList.toString()}")
                mainView.updateProductList(productList)
            }
        }else{
            Utils.showToast("Something went wrong,Please try again later!")
        }

    }

    override suspend fun fetchSavedProductDataFromDb() {
        val productList = dao?.getAllProducts() ?: listOf<Product>()
        mainView.updateProductList(productList,true)
    }

    override suspend fun addProductToDb(product: Product) {
        if(dao?.getById(product.id) == null){
            dao?.upsert(product)
            Utils.showToastOnMainThread("Product Saved!")
        }else{
            Utils.showToastOnMainThread("Product Already Saved!")
        }
    }

    override suspend fun deleteProductFromDb(product: Product) {
        dao?.deleteProduct(product)
        Utils.showToastOnMainThread("Product Deleted!")
    }
}