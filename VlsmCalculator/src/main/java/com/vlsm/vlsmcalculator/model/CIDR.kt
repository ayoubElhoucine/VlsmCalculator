package com.vlsm.vlsmcalculator.model

data class CIDR (
    val id: Long,
    val ipAddress: String,
    val maskIp4: Int,
    val maskIp6: Int,
    val type: String,
)