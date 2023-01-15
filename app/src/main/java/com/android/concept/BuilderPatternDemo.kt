package com.android.concept


enum class Color {
    Black, Red, White,Blue
}

class Car private constructor(
    val color: Color,
    val hasAC: Boolean,
    val hasSunRoof: Boolean
) {
    class Builder {
        private var color: Color = Color.White
        private var hasAC: Boolean = false
        private var hasSunRoof: Boolean = false

        fun color(value: Color) = apply { color = value }
        fun hasAC(value: Boolean) = apply { hasAC = value }
        fun hasSunRoof(value: Boolean) = apply { hasSunRoof = value }

        fun build() = Car(color, hasAC, hasSunRoof)
    }

    fun getCustomPrice(): Int{
        var price = 800000
        if(color == Color.Black) price += 100000
        if(hasAC) price += 50000
        if(hasSunRoof) price += 200000
        return price
    }
}


fun main(){
    var car = Car.Builder()
        .color(Color.Black)
        .hasAC(true)
        .hasSunRoof(false)
        .build()

    println("${car.color} and ${car.hasAC}")
    println("Car Price : ${car.getCustomPrice()}")

}