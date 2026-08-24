package com.example.zlauncher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.zlauncher.core.designsystem.ZLauncherTheme
import com.example.zlauncher.ui.navigation.ZLauncherNavHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeKeyPresses = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ZLauncherTheme {
                ZLauncherNavHost(homeKeyPresses)
            }
        }
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
