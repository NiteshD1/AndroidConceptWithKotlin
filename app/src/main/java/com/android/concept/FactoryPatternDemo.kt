package com.android.concept

interface Currency {
    fun symbol(): String
    fun code(): String
}

enum class Country {
    UnitedState, Spain
}

class USDollar : Currency {
    override fun symbol(): String {
        return "$"
    }

    override fun code(): String {
        return "USD"
    }
}

class Euro : Currency {
    override fun symbol(): String {
        return "€"
    }

    override fun code(): String {
        return "EUR"
    }
}




object CurrencyFactory {

    fun currency(country: Country): Currency {
        return when (country) {
            Country.UnitedState -> {
                USDollar()
            }
            Country.Spain -> {
                Euro()
            }
        }
    }
}

fun main(){
    var currency = CurrencyFactory.currency(Country.Spain)

    println("${currency.code()} and ${currency.symbol()}")
}