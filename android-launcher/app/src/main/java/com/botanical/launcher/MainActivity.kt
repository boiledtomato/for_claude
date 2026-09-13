package com.botanical.launcher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import com.botanical.launcher.garden.Palette
import com.botanical.launcher.ui.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Palette.Green1,
                    onPrimary = Palette.Cream,
                    surface = Palette.Paper,
                    onSurface = Palette.Ink,
                    background = Palette.Paper,
                    onBackground = Palette.Ink,
                ),
            ) {
                HomeScreen()
            }
        }
    }

    /**
     * すでにホームにいる状態でホームキーが押されたときもここに来る。
     * ランチャーなので finish() はしない。
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
