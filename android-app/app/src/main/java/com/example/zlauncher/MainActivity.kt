package com.example.zlauncher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.zlauncher.core.designsystem.ZLauncherTheme
import androidx.lifecycle.lifecycleScope
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.data.widgets.WidgetRepository
import com.example.zlauncher.ui.navigation.ZLauncherNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var widgetHost: WidgetHostController

    @Inject
    lateinit var widgetRepository: WidgetRepository

    private val homeKeyPresses = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ZLauncherTheme {
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
