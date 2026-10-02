package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.graphics.ImageBitmap
import com.example.zlauncher.core.designsystem.LocalIconAdjust
import com.example.zlauncher.core.designsystem.LocalZColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZDarkColors
import com.example.zlauncher.core.designsystem.ZLightColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.designsystem.adjusted
import com.example.zlauncher.core.designsystem.resolveDark
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.ui.apps.component.AppIconTile
import com.example.zlauncher.ui.apps.component.rememberAppIcon
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.ColorAdjust
import com.example.zlauncher.domain.model.ThemeMode
import kotlin.math.roundToInt

/**
 * 配色の調整。明暗の切り替えと、彩度・明度の微調整をひとつにまとめた面。
 *
 * **つまみを動かしている間は保存しない。** DataStore へ書くのは指を離したときだけで、
 * 動かしている間の見た目は、この面の中の見本（[Preview]）で確かめてもらう。1 ピクセル
 * ごとに書くと設定ファイルへの書き込みが数十回走る。
 */
@Composable
fun AppearanceDialog(
    themeMode: ThemeMode,
    adjust: ColorAdjust,
    iconAdjust: ColorAdjust,
    sampleApps: List<AppEntry>,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onThemeMode: (ThemeMode) -> Unit,
    onAdjust: (ColorAdjust) -> Unit,
    onIconAdjust: (ColorAdjust) -> Unit,
    onDismiss: () -> Unit,
) {
    var saturation by remember(adjust) { mutableFloatStateOf(adjust.saturation) }
    var lightness by remember(adjust) { mutableFloatStateOf(adjust.lightness) }
    val pending = ColorAdjust(saturation, lightness)

    var iconSaturation by remember(iconAdjust) { mutableFloatStateOf(iconAdjust.saturation) }
    var iconLightness by remember(iconAdjust) { mutableFloatStateOf(iconAdjust.lightness) }
    val pendingIcon = ColorAdjust(iconSaturation, iconLightness)

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                // 2 組のつまみと見本 2 つで縦に長い。小さい画面では下のボタンまで
                // 届かなくなるので、はみ出すぶんはスクロールさせる
                .verticalScroll(rememberScrollState())
                .padding(18.dp),
        ) {
            Text("Appearance", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(14.dp))
            SectionLabel("Interface")
            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ThemeMode.entries.forEach { mode ->
                    ModeChip(
                        label = when (mode) {
                            ThemeMode.SYSTEM -> "Auto"
                            ThemeMode.LIGHT -> "Light"
                            ThemeMode.DARK -> "Dark"
                        },
                        selected = mode == themeMode,
                        onClick = { onThemeMode(mode) },
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            AdjustSlider(
                label = "Saturation",
                value = saturation,
                range = ColorAdjust.SATURATION_RANGE,
                onValueChange = { saturation = it },
                onCommit = { onAdjust(pending) },
            )
            Spacer(Modifier.height(10.dp))
            AdjustSlider(
                label = "Brightness",
                value = lightness,
                range = ColorAdjust.LIGHTNESS_RANGE,
                onValueChange = { lightness = it },
                onCommit = { onAdjust(pending) },
            )

            Spacer(Modifier.height(14.dp))
            Preview(themeMode = themeMode, adjust = pending)

            Spacer(Modifier.height(20.dp))
            SectionLabel("App icons")
            Spacer(Modifier.height(4.dp))
            Text(
                "Only the icons the apps ship — the interface colours above are not touched.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )
            Spacer(Modifier.height(8.dp))
            AdjustSlider(
                label = "Saturation",
                value = iconSaturation,
                range = ColorAdjust.SATURATION_RANGE,
                onValueChange = { iconSaturation = it },
                onCommit = { onIconAdjust(pendingIcon) },
            )
            Spacer(Modifier.height(10.dp))
            AdjustSlider(
                label = "Brightness",
                value = iconLightness,
                range = ColorAdjust.LIGHTNESS_RANGE,
                onValueChange = { iconLightness = it },
                onCommit = { onIconAdjust(pendingIcon) },
            )
            Spacer(Modifier.height(12.dp))
            IconPreview(apps = sampleApps, iconProvider = iconProvider, adjust = pendingIcon)

            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                DialogButton("Reset", accent = false) {
                    saturation = ColorAdjust.NONE.saturation
                    lightness = ColorAdjust.NONE.lightness
                    iconSaturation = ColorAdjust.NONE.saturation
                    iconLightness = ColorAdjust.NONE.lightness
                    onAdjust(ColorAdjust.NONE)
                    onIconAdjust(ColorAdjust.NONE)
                }
                Spacer(Modifier.size(10.dp))
                DialogButton("Done", accent = true, onClick = onDismiss)
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, style = ZType.Eyebrow, color = ZColors.TextSecondary)
}

/**
 * 実際に入っているアプリのアイコンで見せる。**作り物の見本では確かめられない** ―
 * 効き具合はアイコンごとの元の色に左右されるので、自分の端末の絵で見るしかない。
 *
 * [AppIconTile] は [LocalIconAdjust] を読むので、ここではその値を差し替えるだけで
 * つまみに追従する（保存前の値で描ける）。
 */
@Composable
private fun IconPreview(
    apps: List<AppEntry>,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    adjust: ColorAdjust,
) {
    if (apps.isEmpty()) return
    CompositionLocalProvider(LocalIconAdjust provides adjust) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            apps.forEach { entry ->
                val icon by rememberAppIcon(entry, iconProvider)
                AppIconTile(icon = icon, size = 44.dp, background = ZColors.SurfaceLow)
            }
        }
    }
}

@Composable
private fun AdjustSlider(
    label: String,
    value: Float,
    range: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit,
    onCommit: () -> Unit,
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(label, style = ZType.Sub, color = ZColors.TextSecondary)
        Spacer(Modifier.size(8.dp))
        Text(
            "${(value * 100).roundToInt()}%",
            style = ZType.Sub.copy(fontFamily = FontFamily.Monospace),
            color = ZColors.TextDim,
        )
    }
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = range,
        onValueChangeFinished = onCommit,
        colors = SliderDefaults.colors(
            thumbColor = ZColors.Accent,
            activeTrackColor = ZColors.Accent,
            inactiveTrackColor = ZColors.Outline,
        ),
    )
}

/**
 * 見本。**文字を必ず載せる** ― 明度を動かしたときに効いてくるのは面の色ではなく、
 * 面の上の字が読めるかどうかなので。
 */
@Composable
private fun Preview(themeMode: ThemeMode, adjust: ColorAdjust) {
    val base = if (themeMode.resolveDark()) ZDarkColors else ZLightColors
    val colors = remember(base, adjust) { base.adjusted(adjust) }

    CompositionLocalProvider(LocalZColors provides colors) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(colors.background)
                .border(1.dp, colors.outline, RoundedCornerShape(14.dp))
                .padding(12.dp),
        ) {
            Text("Preview", style = ZType.Eyebrow, color = colors.textDim)
            Spacer(Modifier.height(6.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(colors.surface)
                    .border(1.dp, colors.outline, RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Column {
                    Text("Downloads", style = ZType.Eyebrow, color = colors.textSecondary)
                    Text("48.2", style = ZType.Metric.copy(fontSize = 22.sp), color = colors.textPrimary)
                    Text("MB · last hour", style = ZType.Sub, color = colors.textDim)
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                colors.categoryColors.take(8).forEach { swatch ->
                    Box(
                        Modifier
                            .size(18.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(swatch),
                    )
                }
            }
        }
    }
}

@Composable
private fun ModeChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) ZColors.Accent.copy(alpha = 0.16f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.55f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.5.sp),
            color = if (selected) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}
