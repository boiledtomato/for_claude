package com.example.zlauncher.ui.navigation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zlauncher.ui.dashboard.DashboardScreen
import com.example.zlauncher.ui.home.HomeScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object Route {
    const val HOME = "home"
    const val DASHBOARD = "dashboard"
}

@Composable
fun ZLauncherNavHost(homeKeyPresses: Flow<Unit>) {
    val navController = rememberNavController()
    val homeGridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()

    // HOME キーの再押下。ランチャーは新規起動されないので onNewIntent 経由でここに届く
    LaunchedEffect(Unit) {
        homeKeyPresses.collect {
            navController.popBackStack(Route.HOME, inclusive = false)
            scope.launch { homeGridState.animateScrollToItem(0) }
        }
    }

    NavHost(navController = navController, startDestination = Route.HOME) {
        composable(Route.HOME) {
            HomeScreen(
                onOpenConsole = { navController.navigate(Route.DASHBOARD) },
                gridState = homeGridState,
            )
        }
        composable(Route.DASHBOARD) {
            DashboardScreen(onBack = { navController.popBackStack() })
        }
    }
}
