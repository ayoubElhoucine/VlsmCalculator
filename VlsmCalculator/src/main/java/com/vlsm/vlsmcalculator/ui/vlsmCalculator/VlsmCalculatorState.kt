package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import com.vlsm.vlsmcalculator.common.Networking

@Composable
internal fun rememberVlsmCalculatorState (
    context: Context = LocalContext.current,
    ipAddress: MutableState<String> = mutableStateOf(""),
    hostNumbers: SnapshotStateList<Int?> = mutableStateListOf(null, null, null),
) = remember(ipAddress, hostNumbers) {
    VlsmCalculatorState(context, ipAddress, hostNumbers)
}

@Stable
internal class VlsmCalculatorState(
    val context: Context,
    val ipAddress: MutableState<String>,
    val hostNumbers: SnapshotStateList<Int?>,
) {

    val enabled: Boolean get() = ipAddress.value.isNotBlank() && hostNumbers.filterNotNull().isNotEmpty()

    fun updateHostNumbers(text: String, index: Int) {
        hostNumbers[index] = text.toIntOrNull()
        hostNumbers.lastOrNull()?.let {
            hostNumbers.add(null)
        }
    }

    fun calculate() = Networking.getInstance().calculateVlsm(
        ipAddress.value,
        HashMap(hostNumbers.filterNotNull().associateBy({hostNumbers.indexOf(it).toString()}, {it}))
    )

}