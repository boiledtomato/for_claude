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
import com.example.zlauncher.ui.apps.AppDrawerScreen
import com.example.zlauncher.ui.widgets.WidgetPickerScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * **ホームはコンソール。** HOME キーで戻ってくるのはコンソール画面で、全アプリの一覧は
 * そこから開くドロワー（[APPS]）に分けてある。
 *
 * 以前は全アプリのグリッドがそのままホームだった。このアプリの中身（カテゴリー、
 * Web Insights、端末の実測値）は全部コンソール側にあるのに、起動して最初に出るのが
 * ただのアプリ一覧では性格が伝わらず、ウィジェットの追加導線も「置かれる場所と
 * 追加する場所が別画面」というねじれを生んでいた。
 */
object Route {
    const val CONSOLE = "console"
    const val APPS = "apps"
    const val WIDGETS = "widgets"
}

@Composable
fun ZLauncherNavHost(homeKeyPresses: Flow<Unit>, widgetHost: WidgetHostController) {
    val navController = rememberNavController()
    val drawerGridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()

    // HOME キーの再押下。ランチャーは新規起動されないので onNewIntent 経由でここに届く。
    // ドロワーやピッカーを開いていてもコンソールまで戻し、ドロワーの位置も先頭へ
    LaunchedEffect(Unit) {
        homeKeyPresses.collect {
            navController.popBackStack(Route.CONSOLE, inclusive = false)
            scope.launch { drawerGridState.animateScrollToItem(0) }
        }
    }

    // 画面の入れ替わりも滑らせる。切り替わりが瞬間だと位置関係が分からなくなる
    NavHost(
        navController = navController,
        startDestination = Route.CONSOLE,
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
        composable(Route.CONSOLE) {
            ConsoleScreen(
                onOpenApps = { navController.navigate(Route.APPS) },
                onAddWidget = { navController.navigate(Route.WIDGETS) },
                widgetHost = widgetHost,
            )
        }
        composable(Route.APPS) {
            AppDrawerScreen(
                onBack = { navController.popBackStack() },
                gridState = drawerGridState,
            )
        }
        composable(Route.WIDGETS) {
            WidgetPickerScreen(onDone = { navController.popBackStack() })
        }
    }
}
