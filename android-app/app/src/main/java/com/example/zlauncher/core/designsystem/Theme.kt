package com.example.zlauncher.core.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.zlauncher.domain.model.CardStatus
import com.example.zlauncher.domain.model.ThemeMode

/**
 * Material3 の ColorScheme には green/amber/red のスロットが無いので、
 * ステータス色は別トークンとして CompositionLocal で配る。
 */
@Immutable
data class StatusColors(
    val green: Color,
    val amber: Color,
    val red: Color,
    val neutral: Color,
) {
    fun colorFor(status: CardStatus): Color = when (status) {
        CardStatus.GREEN -> green
        CardStatus.AMBER -> amber
        CardStatus.RED -> red
        CardStatus.NEUTRAL, CardStatus.UNKNOWN -> neutral
    }
}

val LocalStatusColors = staticCompositionLocalOf {
    ZDarkColors.let { StatusColors(it.statusGreen, it.statusAmber, it.statusRed, it.statusNeutral) }
}

private fun materialScheme(colors: ZColorScheme) = if (colors.isLight) {
    lightColorScheme(
        primary = colors.accent,
        onPrimary = Color.White,
        secondary = colors.accentAlt,
        background = colors.background,
        onBackground = colors.textPrimary,
        surface = colors.surface,
        onSurface = colors.textPrimary,
        surfaceVariant = colors.surfaceHigh,
        onSurfaceVariant = colors.textSecondary,
        outline = colors.outline,
        outlineVariant = colors.outlineStrong,
        error = colors.statusRed,
    )
} else {
    darkColorScheme(
        primary = colors.accent,
        onPrimary = colors.textPrimary,
        secondary = colors.accentAlt,
        background = colors.background,
        onBackground = colors.textPrimary,
        surface = colors.surface,
        onSurface = colors.textPrimary,
        surfaceVariant = colors.surfaceHigh,
        onSurfaceVariant = colors.textSecondary,
        outline = colors.outline,
        outlineVariant = colors.outlineStrong,
        error = colors.statusRed,
    )
}

/**
 * 配色を決めて配る。
 *
 * [mode] が [ThemeMode.SYSTEM] のときだけ端末の設定を見る。DataStore の読み込みが
 * 終わるまでは既定値（SYSTEM）で描くので、起動直後に一瞬だけ違う配色が出ることは無い。
 */
@Composable
fun ThemeMode.resolveDark(): Boolean = when (this) {
    ThemeMode.SYSTEM -> isSystemInDarkTheme()
    ThemeMode.DARK -> true
    ThemeMode.LIGHT -> false
}

@Composable
fun ZLauncherTheme(
    mode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit,
) {
    val colors = if (mode.resolveDark()) ZDarkColors else ZLightColors
    val status = remember(colors) {
        StatusColors(colors.statusGreen, colors.statusAmber, colors.statusRed, colors.statusNeutral)
    }

    CompositionLocalProvider(
        LocalZColors provides colors,
        LocalStatusColors provides status,
    ) {
        MaterialTheme(
            colorScheme = materialScheme(colors),
            typography = ZTypography,
            content = content,
        )
    }
}
