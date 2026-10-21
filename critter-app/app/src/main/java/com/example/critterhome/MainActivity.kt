package com.example.critterhome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.critterhome.ui.BadgesScreen
import com.example.critterhome.ui.BagScreen
import com.example.critterhome.ui.BoxScreen
import com.example.critterhome.ui.CardScreen
import com.example.critterhome.ui.Game
import com.example.critterhome.ui.GameTheme
import com.example.critterhome.ui.HomeScreen
import com.example.critterhome.ui.MainViewModel
import com.example.critterhome.ui.Screen
import com.example.critterhome.ui.SummaryScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            GameTheme {
                val ui by viewModel.ui.collectAsStateWithLifecycle()
                val battery by viewModel.battery.collectAsStateWithLifecycle()

                // ホームアプリは戻るで終わらない。サブ画面なら 1 つ戻り、ホームでは飲み込む
                BackHandler(enabled = true) { viewModel.back() }

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Game.colors.background)
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                        .padding(12.dp),
                ) {
                    AnimatedContent(
                        targetState = viewModel.screen,
                        transitionSpec = { fadeIn(tween(160)) togetherWith fadeOut(tween(120)) },
                        label = "screen",
                    ) { screen ->
                        when (screen) {
                            Screen.Home -> HomeScreen(
                                ui = ui,
                                battery = battery,
                                iconProvider = viewModel::icon,
                                onLaunch = { viewModel.launch(it) },
                                onOpen = viewModel::open,
                                onAdvanceMessage = { viewModel.consumeMessage() },
                            )
                            is Screen.Bag -> BagScreen(
                                ui = ui,
                                initialFilter = screen.filter,
                                iconProvider = viewModel::icon,
                                onLaunch = { viewModel.launch(it) },
                                onAppInfo = viewModel::openAppInfo,
                                onMove = { app, habitat -> viewModel.moveApp(app, habitat) },
                                onBack = { viewModel.back() },
                            )
                            is Screen.Summary -> {
                                val companion = ui.companion(screen.habitat)
                                if (companion == null || !companion.met) {
                                    // 読み込み前。次の再構成で中身が入る
                                    Box(Modifier.fillMaxSize())
                                } else {
                                    SummaryScreen(
                                        companion = companion,
                                        iconProvider = viewModel::icon,
                                        onUseMove = { viewModel.launch(it) },
                                        onBack = { viewModel.back() },
                                    )
                                }
                            }
                            Screen.Box -> BoxScreen(ui = ui, onOpen = viewModel::open, onBack = { viewModel.back() })
                            Screen.Badges -> BadgesScreen(ui = ui, onBack = { viewModel.back() })
                            Screen.Card -> CardScreen(ui = ui, battery = battery, onBack = { viewModel.back() })
                        }
                    }
                }
            }
        }
    }

    /**
     * ホームの Activity は終了しないので、HOME キーの再押下は新規起動ではなくここに来る。
     * 拾わないと「HOME キーで戻れないホーム」になる。
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        viewModel.home()
    }
}
