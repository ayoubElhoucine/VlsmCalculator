package com.vlsm.vlsmcalculator.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberAppState(
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(navController, coroutineScope) {
    AppState(context, navController, coroutineScope)
}

@Stable
internal class AppState(
    val context: Context,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {

    fun navigateTo(
        route: String
    ) = navController.navigate(route = route)

    fun popBack() = navController.navigateUp()

    fun popBack(to: String) = navController.popBackStack(
        route = to,
        inclusive = false,
    )
}
