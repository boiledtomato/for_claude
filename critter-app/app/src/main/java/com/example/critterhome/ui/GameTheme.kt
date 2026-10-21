package com.example.critterhome.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.critterhome.game.Habitat

/**
 * 配色。携帯ゲーム機の RPG の、生成り色の地に黒い二重枠という見た目に寄せる。
 * 暗い配色は端末の夜間設定に従う。
 *
 * 住みかの色は [habitat] で引く。並びは [Habitat.entries] と同じ。
 */
data class GameColors(
    val background: Color,
    val window: Color,
    /** ゲージの溝など、窓の中で一段沈んだ所 */
    val sunken: Color,
    val ink: Color,
    val inkSoft: Color,
    val inkDim: Color,
    /** 二重枠の内側の細い線 */
    val frameInner: Color,
    val accent: Color,
    val hpGood: Color,
    val hpMid: Color,
    val hpLow: Color,
    /** ひんしの体、まだ出会っていない影 */
    val faint: Color,
    val habitats: List<Color>,
    val isDark: Boolean,
) {
    fun habitat(h: Habitat): Color = habitats[h.ordinal % habitats.size]
}

val LightGameColors = GameColors(
    background = Color(0xFFF4EFDC),
    window = Color(0xFFFFFDF6),
    sunken = Color(0xFFE9E3CC),
    ink = Color(0xFF22232A),
    inkSoft = Color(0xFF4F5160),
    inkDim = Color(0xFF7D7F8C),
    frameInner = Color(0xFFB9B29A),
    accent = Color(0xFF2F6FDB),
    hpGood = Color(0xFF2E9F56),
    hpMid = Color(0xFFD99A12),
    hpLow = Color(0xFFD5402F),
    faint = Color(0xFF9A9AA2),
    habitats = listOf(
        Color(0xFFE5533D), // Arcade
        Color(0xFF8E5BD9), // Music Hall
        Color(0xFFD94F8A), // Theater
        Color(0xFFE8A33D), // Gallery
        Color(0xFF3B9FE0), // Plaza
        Color(0xFF6F8294), // Newsroom
        Color(0xFF3FAF6A), // Wilds
        Color(0xFFC47634), // Workshop
        Color(0xFF26AE9E), // Lab
        Color(0xFF8DBA3A), // Meadow
    ),
    isDark = false,
)

val DarkGameColors = GameColors(
    background = Color(0xFF15171E),
    window = Color(0xFF1F2330),
    sunken = Color(0xFF2A2F3E),
    ink = Color(0xFFF1EEE4),
    inkSoft = Color(0xFFC9C6BC),
    inkDim = Color(0xFF95928A),
    frameInner = Color(0xFF4A4F60),
    accent = Color(0xFF6FA3FF),
    hpGood = Color(0xFF4CC777),
    hpMid = Color(0xFFF2B93B),
    hpLow = Color(0xFFFF6B5E),
    faint = Color(0xFF6E7079),
    habitats = listOf(
        Color(0xFFFF6E57),
        Color(0xFFAE85F0),
        Color(0xFFF074A6),
        Color(0xFFF5BA5C),
        Color(0xFF62B9F2),
        Color(0xFF9AA9B8),
        Color(0xFF5CCB86),
        Color(0xFFE0975A),
        Color(0xFF4CCDBD),
        Color(0xFFA9D35A),
    ),
    isDark = true,
)

val LocalGameColors = staticCompositionLocalOf { LightGameColors }

/** 画面からは `Game.colors` で読む */
object Game {
    val colors: GameColors
        @Composable get() = LocalGameColors.current
}

@Composable
fun GameTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkGameColors else LightGameColors
    CompositionLocalProvider(LocalGameColors provides colors, content = content)
}
