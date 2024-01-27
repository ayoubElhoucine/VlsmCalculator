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
import com.vlsm.vlsmcalculator.common.isValidIpAddress
import com.vlsm.vlsmcalculator.model.Subnet
import com.vlsm.vlsmcalculator.ui.common.UiState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
internal fun rememberVlsmCalculatorState (
    context: Context = LocalContext.current,
    uiState: MutableState<UiState> = mutableStateOf(UiState.Idle),
    ipAddress: MutableState<String> = mutableStateOf(""),
    hostNumbers: SnapshotStateList<Int?> = mutableStateListOf(null, null, null),
) = remember(ipAddress, hostNumbers) {
    VlsmCalculatorState(context, uiState, ipAddress, hostNumbers)
}

@Stable
internal class VlsmCalculatorState(
    val context: Context,
    val uiState: MutableState<UiState>,
    val ipAddress: MutableState<String>,
    val hostNumbers: SnapshotStateList<Int?>,
) {

    val enabled: Boolean get() =
        ipAddress.value.isValidIpAddress() &&
                hostNumbers.filterNotNull().isNotEmpty() &&
                uiState.value != UiState.Loading

    fun updateHostNumbers(text: String, index: Int) {
        hostNumbers[index] = text.toIntOrNull()
        hostNumbers.lastOrNull()?.let {
            hostNumbers.add(null)
        }
    }

    suspend fun calculate(): (List<Subnet>) = suspendCoroutine { continuation ->
        uiState.value = UiState.Loading
        val result = Networking.getInstance().calculateVlsm(
            ipAddress.value,
            HashMap(hostNumbers.filterNotNull().associateBy({hostNumbers.indexOf(it).toString()}, {it}))
        )
        continuation.resume(result)
        uiState.value = UiState.Idle
    }

}