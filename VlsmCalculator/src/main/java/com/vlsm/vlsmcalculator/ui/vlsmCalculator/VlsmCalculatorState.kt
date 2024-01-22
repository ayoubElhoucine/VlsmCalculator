package com.vlsm.vlsmcalculator.ui.vlsmCalculator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberVlsmCalculatorState (
    context: Context = LocalContext.current,
) = remember {
    VlsmCalculatorState(context)
}

@Stable
class VlsmCalculatorState(
    val context: Context,
) {

}