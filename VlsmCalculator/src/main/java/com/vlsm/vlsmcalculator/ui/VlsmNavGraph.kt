package com.vlsm.vlsmcalculator.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vlsm.vlsmcalculator.ui.vlsmCalculator.VlsmCalculatorScreen


internal object Screens {
    const val VLSM_CALCULATOR = "vlsm_calculator"
    const val VLSM_CALCULATOR_RESULT = "vlsm_calculator_result"
}

@Composable
fun VlsmNavGraph(
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
            VlsmCalculatorScreen()
        }

//        composable(Screens.LOGIN) {
//            BackHandler(onBack = finishActivity)
//            LoginScreen {
//                appState.navigateTo(Screens.HOME)
//            }
//        }

    }
}