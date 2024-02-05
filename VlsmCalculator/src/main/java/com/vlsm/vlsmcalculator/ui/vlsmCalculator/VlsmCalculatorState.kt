package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.vlsm.vlsmcalculator.common.Networking
import com.vlsm.vlsmcalculator.common.isValidIpAddress
import com.vlsm.vlsmcalculator.model.Subnet
import com.vlsm.vlsmcalculator.ui.common.UiState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
internal fun rememberVlsmCalculatorState (
    uiState: MutableState<UiState> = mutableStateOf(UiState.Idle),
    ipAddress: MutableState<String> = mutableStateOf(""),
    hostNumbers: SnapshotStateList<Int?> = mutableStateListOf(null, null, null),
) = remember(ipAddress, hostNumbers) {
    VlsmCalculatorState.Builder().build(uiState, ipAddress, hostNumbers)
}

@Stable
internal class VlsmCalculatorState(
    val uiState: MutableState<UiState>,
    val ipAddress: MutableState<String>,
    val hostNumbers: SnapshotStateList<Int?>,
) {

    companion object {
        private var instance: VlsmCalculatorState? = null
        fun onCleared() {
            instance = null
        }
    }

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

    class Builder {
        fun build(
            uiState: MutableState<UiState>,
            ipAddress: MutableState<String>,
            hostNumbers: SnapshotStateList<Int?>,
        ): VlsmCalculatorState {
            instance ?: run {
                instance = VlsmCalculatorState(uiState, ipAddress, hostNumbers)
            }
            return instance!!
        }
    }

}