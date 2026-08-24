package com.example.zlauncher.core.designsystem

import androidx.compose.ui.graphics.Color

/**
 * 配色トークン。モックアップ（カード仕様シート）と 1:1 で対応する。
 * Composable の中に生の hex を書かず、必ずここ経由で参照する。
 */
object ZColors {
    val Background = Color(0xFF0A0E17)
    val Surface = Color(0xFF121826)
    val SurfaceHigh = Color(0xFF182031)
    val SurfaceLow = Color(0xFF0F1523)
    val Outline = Color(0xFF1F2A3C)
    val OutlineStrong = Color(0xFF24314A)
    val TextPrimary = Color(0xFFE6EDF7)
    val TextSecondary = Color(0xFF8A97AB)
    val TextDim = Color(0xFF4E6B9E)
    val Accent = Color(0xFF2E7DF6)
    val AccentAlt = Color(0xFF22D3EE)
    val AccentSoft = Color(0xFF7FB2FF)
    val StatusGreen = Color(0xFF34D399)
    val StatusAmber = Color(0xFFFBBF24)
    val StatusRed = Color(0xFFF87171)
    val StatusNeutral = Color(0xFF2A3550)
}
