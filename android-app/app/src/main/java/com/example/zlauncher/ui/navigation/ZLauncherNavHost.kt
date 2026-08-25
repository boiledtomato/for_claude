package com.example.zlauncher.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.ui.console.ConsoleScreen
import com.example.zlauncher.ui.home.HomeScreen
import com.example.zlauncher.ui.widgets.WidgetPickerScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object Route {
    const val HOME = "home"
    const val DASHBOARD = "dashboard"
    const val WIDGETS = "widgets"
}

@Composable
fun ZLauncherNavHost(homeKeyPresses: Flow<Unit>, widgetHost: WidgetHostController) {
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

    // 画面の入れ替わりも滑らせる。切り替わりが瞬間だと位置関係が分からなくなる
    NavHost(
        navController = navController,
        startDestination = Route.HOME,
        enterTransition = {
            fadeIn(tween(ZMotion.TRANSITION_MS)) + slideInHorizontally { width -> width / 10 }
        },
        exitTransition = {
            fadeOut(tween(ZMotion.TRANSITION_MS)) + slideOutHorizontally { width -> -width / 24 }
        },
        popEnterTransition = {
            fadeIn(tween(ZMotion.TRANSITION_MS)) + slideInHorizontally { width -> -width / 24 }
        },
        popExitTransition = {
            fadeOut(tween(ZMotion.TRANSITION_MS)) + slideOutHorizontally { width -> width / 10 }
        },
    ) {
        composable(Route.HOME) {
            HomeScreen(
                onOpenConsole = { navController.navigate(Route.DASHBOARD) },
                widgetHost = widgetHost,
                gridState = homeGridState,
            )
        }
        composable(Route.DASHBOARD) {
            ConsoleScreen(
                onBack = { navController.popBackStack() },
                onAddWidget = { navController.navigate(Route.WIDGETS) },
            )
        }
        composable(Route.WIDGETS) {
            WidgetPickerScreen(onDone = { navController.popBackStack() })
        }
    }
}
