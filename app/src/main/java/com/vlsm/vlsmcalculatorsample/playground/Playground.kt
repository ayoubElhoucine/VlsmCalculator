package com.vlsm.vlsmcalculatorsample.playground

import com.vlsm.vlsmcalculator.common.VlsmCalculator

fun main() {
    val result = VlsmCalculator.calculateVLSM("192.198.10.16", hashMapOf("1" to 4, "2" to 6, "3" to 10))
    println("result === $result")
}