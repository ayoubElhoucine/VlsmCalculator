package com.vlsm.vlsmcalculator.model

data class Subnet(
    val address: String?,
    val allocatedSize: Int,
    val broadcast: String?,
    val decMask: String?,
    val mask: String?,
    val neededSize: Int,
    val range: String?,
)
