package com.android.concept.data.db.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.concept.MainApplication
import com.android.concept.data.models.Product

@Database(
    entities = [Product::class],
    version = 3
)


abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao



    companion object {

        private var instance: ProductDatabase? = null

        fun getInstance() : ProductDatabase? {
            return instance ?: createDatabase()
        }

        private fun createDatabase(): ProductDatabase? {
            instance = Room.databaseBuilder(
                MainApplication.appContext,
                ProductDatabase::class.java,
                "product_db.db"
            ).build()
            return instance
        }
    }
}