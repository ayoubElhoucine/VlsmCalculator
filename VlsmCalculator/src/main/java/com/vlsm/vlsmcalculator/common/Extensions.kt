package com.vlsm.vlsmcalculator.common

import android.net.InetAddresses
import android.os.Build
import android.util.Patterns

fun String.isValidIpAddress(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) InetAddresses.isNumericAddress(this)
    else Patterns.IP_ADDRESS.matcher(this).matches()
}