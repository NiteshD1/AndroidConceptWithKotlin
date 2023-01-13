package com.android.concept.db.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.concept.MainApplication
import com.android.concept.models.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object{
        private var instance : ProductDatabase? = null

        fun getInstance() : ProductDatabase? {
            return instance?: createDatabaseAndGetInstance()
        }

        private fun createDatabaseAndGetInstance(): ProductDatabase? {

            instance = Room.databaseBuilder(
                MainApplication.appContext,
                ProductDatabase::class.java,
                "product_db"
            ).build()
            return instance
        }

    }

}