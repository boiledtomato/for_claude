package com.example.zlauncher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import com.example.zlauncher.core.designsystem.ZLauncherTheme
import com.example.zlauncher.core.designsystem.resolveDark
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.data.widgets.WidgetRepository
import com.example.zlauncher.domain.model.ThemeMode
import android.os.SystemClock
import com.example.zlauncher.ui.console.ConsoleDeepLink
import com.example.zlauncher.ui.console.PaneRequest
import com.example.zlauncher.ui.navigation.ZLauncherNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var widgetHost: WidgetHostController

    @Inject
    lateinit var widgetRepository: WidgetRepository

    @Inject
    lateinit var preferences: LauncherPreferencesRepository

    private val homeKeyPresses = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    /** 外から「この面を開いて」と言われたぶん（ウィジェットの設定画面など） */
    private val paneRequests = MutableSharedFlow<PaneRequest>(extraBufferCapacity = 1, replay = 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Flow の組み立ては composition の外で 1 回だけ。中で map すると再構成のたびに
        // 新しい Flow ができ、collect がやり直しになる
        consumePaneRequest(intent)

        val themeModeFlow = preferences.state.map { it.themeMode }
        setContent {
            // 保存済みの配色。読み込みが終わるまでは既定（端末の設定に従う）で描く
            val themeMode by themeModeFlow.collectAsStateWithLifecycle(initialValue = ThemeMode.SYSTEM)

            // ステータスバーのアイコンは配色に合わせて置き直す。端末が夜でも配色を明るい方に
            // 固定できるので、システム任せにすると白い地に白いアイコンが乗る
            val dark = themeMode.resolveDark()
            LaunchedEffect(dark) {
                val style = if (dark) {
                    SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
                } else {
                    SystemBarStyle.light(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    )
                }
                enableEdgeToEdge(statusBarStyle = style, navigationBarStyle = style)
            }

            ZLauncherTheme(mode = themeMode) {
                ZLauncherNavHost(homeKeyPresses, paneRequests, widgetHost)
            }
        }
    }

    /** ウィジェットの更新はホストが listening 中しか届かない */
    override fun onStart() {
        super.onStart()
        widgetHost.startListening()
        // 掃除は listening 開始後に行う。開始前だと有効なウィジェットまで
        // 「提供元が無い」と判定して消しかねない
        lifecycleScope.launch { widgetRepository.pruneMissing() }
    }

    override fun onStop() {
        super.onStop()
        widgetHost.stopListening()
    }

    /**
     * ランチャーの Activity は終了しないので、HOME キーの再押下は新規起動ではなくここに来る。
     * ここで拾わないと「HOME キーが効かないランチャー」になる。
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // 面の指定つきで来たときは HOME キーとして扱わない（指定した面まで行って止まる）
        if (!consumePaneRequest(intent)) homeKeyPresses.tryEmit(Unit)
    }

    /**
     * 付加情報は**一度使ったら消す**。残しておくと、プロセスが作り直されたときに
     * 保存された Intent からもう一度同じ面へ飛ばされる。
     */
    private fun consumePaneRequest(intent: Intent?): Boolean {
        val pane = intent?.getStringExtra(ConsoleDeepLink.EXTRA_PANE) ?: return false
        intent.removeExtra(ConsoleDeepLink.EXTRA_PANE)
        return paneRequests.tryEmit(PaneRequest(pane, SystemClock.uptimeMillis()))
    }
}
