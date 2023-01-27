package com.android.concept

fun main(){

    var arr = IntArray(10)

    println("arr : ${arr.toList()}")

    arr = fillArrayWithRandomNumbers(arr)

    println("arr : ${arr.toList()}")

    var finalResult = getFinalResult(arr)

    println("Final Result is $finalResult")
}

fun fillArrayWithRandomNumbers(arr: IntArray): IntArray {
    for(i in 0..9){
        arr[i] = (0..10).random()
    }
    return arr
}

fun getFinalResult(arr: IntArray): Int {

    var devisor = (0..10).random()

    devisor = 0

    println("devisor : ${devisor}")


    var finalResult = 0
    println("finalResult : ${finalResult}")

    arr.forEach {

        try{
            finalResult += (it/devisor)
            println("finalResult : ${finalResult}")
        }catch (e : Exception){
            println("Exception : ${e.message}")
        }

    }

    return finalResult
}