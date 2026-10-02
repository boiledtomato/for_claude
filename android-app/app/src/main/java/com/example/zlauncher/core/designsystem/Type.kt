package com.example.zlauncher.core.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * コンソール系ダッシュボードのタイポ階層。
 * 数値は等幅 + tabular figures（tnum）で桁を揃える。ここが崩れると一気に安く見える。
 */
object ZType {
    /** カード見出し。11sp・トラッキング広め・二次色で使う */
    val Eyebrow = TextStyle(
        fontSize = 11.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.10.em,
    )

    /** 大きなメトリクス。サイズは呼び出し側で copy(fontSize = …) する */
    val Metric = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.02).em,
        fontFeatureSettings = "tnum",
    )

    val UnitText = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 12.sp,
        fontFeatureSettings = "tnum",
    )

    val Sub = TextStyle(
        fontSize = 10.5.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.04.em,
    )

    val Body = TextStyle(fontSize = 12.sp, lineHeight = 17.sp)

    val AppLabel = TextStyle(fontSize = 11.5.sp, lineHeight = 15.sp, letterSpacing = 0.02.em)

    val Clock = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 46.sp,
        lineHeight = 48.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.02).em,
        fontFeatureSettings = "tnum",
    )

    val Title = TextStyle(fontSize = 17.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 0.02.em)
}

internal val ZTypography = Typography(
    titleMedium = ZType.Title,
    bodyMedium = ZType.Body,
    labelSmall = ZType.Sub,
)
