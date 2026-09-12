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

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // Flow の組み立ては composition の外で 1 回だけ。中で map すると再構成のたびに
        // 新しい Flow ができ、collect がやり直しになる
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
                ZLauncherNavHost(homeKeyPresses, widgetHost)
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
        homeKeyPresses.tryEmit(Unit)
    }
}
