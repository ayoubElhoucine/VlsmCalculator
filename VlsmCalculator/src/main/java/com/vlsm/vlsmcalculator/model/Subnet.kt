package com.vlsm.vlsmcalculator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Subnet(
    val address: String?,
    val allocatedSize: Int,
    val broadcast: String?,
    val decMask: String?,
    val mask: String?,
    val neededSize: Int,
    val range: String?,
): Parcelable
