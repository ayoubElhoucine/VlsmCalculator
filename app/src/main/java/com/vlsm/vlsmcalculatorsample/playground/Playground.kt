package com.vlsm.vlsmcalculatorsample.playground

import com.vlsm.vlsmcalculator.common.Networking

fun main() {
    val vlsm = Networking.getInstance().calculateVlsm("192.198.10.16", hashMapOf("1" to 4, "2" to 6, "3" to 10))
    println("result === $vlsm")
}