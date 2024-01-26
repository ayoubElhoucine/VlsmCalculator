package com.vlsm.vlsmcalculator.ui.common

sealed class UiState {
    object Idle: UiState()
    object Loading: UiState()
    data class Success(val data: Any? = null): UiState()
    data class Fail(val message: String? = null): UiState()
}
