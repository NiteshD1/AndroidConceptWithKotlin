package com.android.concept.data.db.room

import androidx.room.*
import com.android.concept.data.models.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: Product)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): MutableList<Product>

    @Delete()
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteById(productId : Int)

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getById(productId : Int) : Product?


}