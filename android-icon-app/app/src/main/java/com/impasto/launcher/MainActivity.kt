package com.impasto.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import com.impasto.launcher.data.AppRepository
import com.impasto.launcher.data.LayoutStore
import com.impasto.launcher.ui.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // A home app is where Back already lands, so it must not pop anything.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = Unit
        })

        setContent {
            val repo = remember { AppRepository(applicationContext) }
            val store = remember { LayoutStore(applicationContext) }

            MaterialTheme(colorScheme = darkColorScheme()) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                listOf(Color(0xFF1C2429), Color(0xFF0D1215)),
                            )
                        )
                ) {
                    HomeScreen(repo, store)
                }
            }
        }
    }
}
