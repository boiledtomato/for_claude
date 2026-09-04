package com.example.zlauncher.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.zlauncher.domain.model.CardStatus

/**
 * Material3 の ColorScheme には green/amber/red のスロットが無いので、
 * ステータス色は別トークンとして CompositionLocal で配る。
 */
@Immutable
data class StatusColors(
    val green: Color = ZColors.StatusGreen,
    val amber: Color = ZColors.StatusAmber,
    val red: Color = ZColors.StatusRed,
    val neutral: Color = ZColors.StatusNeutral,
) {
    fun colorFor(status: CardStatus): Color = when (status) {
        CardStatus.GREEN -> green
        CardStatus.AMBER -> amber
        CardStatus.RED -> red
        CardStatus.NEUTRAL, CardStatus.UNKNOWN -> neutral
    }
}

val LocalStatusColors = staticCompositionLocalOf { StatusColors() }

private val ZDarkColorScheme = darkColorScheme(
    primary = ZColors.Accent,
    onPrimary = ZColors.TextPrimary,
    secondary = ZColors.AccentAlt,
    background = ZColors.Background,
    onBackground = ZColors.TextPrimary,
    surface = ZColors.Surface,
    onSurface = ZColors.TextPrimary,
    surfaceVariant = ZColors.SurfaceHigh,
    onSurfaceVariant = ZColors.TextSecondary,
    outline = ZColors.Outline,
    outlineVariant = ZColors.OutlineStrong,
    error = ZColors.StatusRed,
)

@Composable
fun ZLauncherTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalStatusColors provides StatusColors()) {
        MaterialTheme(
            colorScheme = ZDarkColorScheme,
            typography = ZTypography,
            content = content,
        )
    }
}
