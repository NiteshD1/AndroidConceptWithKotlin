package com.android.concept.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("title")
    val name : String?,
    val price: Double?,
    @SerializedName("image")
    val imageUrl : String?
    )