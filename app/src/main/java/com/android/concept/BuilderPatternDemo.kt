package com.android.concept

enum class Color{
    BLACK,WHITE,BLUE,RED
}

class Car private constructor(
    val color : Color,
    val hasAc : Boolean,
    val hasSunRoof : Boolean
){
    class Builder{
        private var color = Color.WHITE
        private var hasAc = false
        private var hasSunRoof = false

        fun color(value: Color) = apply { color = value  }
        fun hasAc(value: Boolean) = apply { hasAc = value }
        fun hasSunRoof(value: Boolean) = apply { hasSunRoof = value }

        fun build() = Car(color,hasAc,hasSunRoof)
    }

    fun calculateCustomPrice(): Int{
        var price = 800000

        if(color == Color.BLACK) price += 100000
        if(hasAc) price += 100000
        if(hasSunRoof) price += 200000

        return price
    }
}

fun main(){
    val customCar = Car.Builder()
        .color(Color.BLACK)
        .hasSunRoof(true)
        .build()

    println("Car Price : ${customCar.calculateCustomPrice()}")
}