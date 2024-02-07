package com.vlsm.vlsmcalculator.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vlsm.vlsmcalculator.model.Subnet
import com.vlsm.vlsmcalculator.ui.vlsmCalculator.VlsmCalculatorScreen
import com.vlsm.vlsmcalculator.ui.vlsmCalculator.VlsmResultScreen


internal object Screens {
    const val VLSM_CALCULATOR = "vlsm_calculator"
    const val VLSM_CALCULATOR_RESULT = "vlsm_calculator_result"
}

@Composable
fun VlsmCalculatorView(
    startDestination: String = Screens.VLSM_CALCULATOR,
) {
    val appState = rememberAppState()
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(Screens.VLSM_CALCULATOR) {
            BackHandler(onBack = appState::popBack)
            VlsmCalculatorScreen {
                appState.navController.currentBackStackEntry?.savedStateHandle?.set(key = "result", value = it)
                appState.navigateTo(Screens.VLSM_CALCULATOR_RESULT)
            }
        }

        composable(Screens.VLSM_CALCULATOR_RESULT) {
            BackHandler(onBack = appState::popBack)
            appState.navController.previousBackStackEntry?.savedStateHandle?.get<List<Subnet>>("result")?.let { result ->
                VlsmResultScreen(result = result, onBack = appState::popBack)
            }
        }

    }
}