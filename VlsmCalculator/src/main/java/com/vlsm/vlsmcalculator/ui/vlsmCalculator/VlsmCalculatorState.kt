package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.vlsm.vlsmcalculator.common.Networking
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
internal fun rememberVlsmCalculatorState (
    context: Context = LocalContext.current,
) = remember {
    VlsmCalculatorState(context)
}

@Stable
internal class VlsmCalculatorState(
    val context: Context,
) {

    val ipAddress = MutableStateFlow("")
    val hostNumbers = MutableStateFlow(arrayListOf<Int>())

    fun calculate() = Networking.getInstance().calculateVlsm(
        ipAddress.value,
        HashMap(hostNumbers.value.associateBy({hostNumbers.value.indexOf(it).toString()}, {it}))
    )

}