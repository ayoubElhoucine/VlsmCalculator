package com.vlsm.vlsmcalculator.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


object Screens {
    const val VLSM_CALCULATOR = "vlsm_calculator"
    const val VLSM_CALCULATOR_RESULT = "vlsm_calculator_result"
}

@Composable
fun AppNavGraph(
    finishActivity: () -> Unit = {},
    appState: AppState,
    startDestination: String = Screens.VLSM_CALCULATOR,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

//        composable(Screens.SPLASH) {
//            BackHandler(onBack = finishActivity)
//            SplashScreen(
//                onLogin = {
//                    appState.navigateTo(Screens.LOGIN)
//                },
//                onHome = {
//                    appState.navigateTo(Screens.HOME)
//                },
//            )
//        }
//
//        composable(Screens.LOGIN) {
//            BackHandler(onBack = finishActivity)
//            LoginScreen {
//                appState.navigateTo(Screens.HOME)
//            }
//        }

    }
}