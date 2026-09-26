package com.example.zlauncher.core.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.zlauncher.core.color.ColorMath
import com.example.zlauncher.domain.model.ColorAdjust

/**
 * 利用者の彩度・明度の指定を配色一式に掛ける。
 *
 * **文字だけは掛けっぱなしにしない。** 面と文字に同じ係数を掛けると、両方が同じ側へ寄って
 * 字が読めなくなる。ここでは面を先に作り、文字はその面に対する比を測り直して、
 * 足りなければ明度だけを引き離す（[ColorMath.ensureContrast]）。色相と彩度は動かさない
 * ので、利用者が選んだ色味は残る。
 *
 * 既定（1.0 / 1.0）のときは何もせずそのまま返す ― 既定の配色は `tools/palette_check.py`
 * で実測した値で、ここで触ると測った意味がなくなる。
 */
fun ZColorScheme.adjusted(adjust: ColorAdjust): ZColorScheme {
    val safe = adjust.normalized()
    if (safe.isNeutral) return this

    fun shift(color: Color): Color =
        Color(ColorMath.adjust(color.toArgb(), safe.saturation, safe.lightness))

    val newBackground = shift(background)
    val newSurface = shift(surface)
    val newSurfaceHigh = shift(surfaceHigh)
    val newSurfaceLow = shift(surfaceLow)
    val surfaces = listOf(newBackground, newSurface, newSurfaceHigh, newSurfaceLow)

    /**
     * 文字を、**一番不利な面**に対して直す。カードの面はどれも同じ側にあるので、
     * 最悪の 1 枚で通れば他でも通る。
     */
    fun text(color: Color, minRatio: Float): Color {
        val shifted = shift(color).toArgb()
        val worst = surfaces.minBy { ColorMath.contrast(shifted, it.toArgb()) }
        return Color(ColorMath.ensureContrast(shifted, worst.toArgb(), minRatio))
    }

    val newDanger = shift(danger)

    return copy(
        background = newBackground,
        surface = newSurface,
        surfaceHigh = newSurfaceHigh,
        surfaceLow = newSurfaceLow,
        outline = shift(outline),
        outlineStrong = shift(outlineStrong),
        textPrimary = text(textPrimary, TEXT_PRIMARY_RATIO),
        textSecondary = text(textSecondary, TEXT_SECONDARY_RATIO),
        textDim = text(textDim, TEXT_DIM_RATIO),
        accent = shift(accent),
        accentAlt = shift(accentAlt),
        accentSoft = text(accentSoft, TEXT_SECONDARY_RATIO),
        statusGreen = shift(statusGreen),
        statusAmber = shift(statusAmber),
        statusRed = shift(statusRed),
        statusNeutral = shift(statusNeutral),
        violet = shift(violet),
        danger = newDanger,
        // 危険な操作のボタンは、地の色が動いたぶん文字を測り直す
        onDanger = Color(
            ColorMath.ensureContrast(
                shift(onDanger).toArgb(),
                newDanger.toArgb(),
                DANGER_LABEL_RATIO,
            )
        ),
        categoryColors = categoryColors.map(::shift),
        categoryChartColors = categoryChartColors.map(::shift),
        chartOther = shift(chartOther),
    )
}

/** 本文。WCAG AA の 4.5:1 */
private const val TEXT_PRIMARY_RATIO = 4.5f

/** 補助の文字。本文より小さいが、読ませる文字なので 4.0 は残す */
private const val TEXT_SECONDARY_RATIO = 4.0f

/** 添え字・単位。大きめに出る場所にしか使わないので 3.0 */
private const val TEXT_DIM_RATIO = 3.0f

/**
 * 危険な操作のボタンの文字。**3.0 は妥協ではなく、この文字の基準**。
 *
 * WCAG の 4.5:1 は本文の基準で、大きい文字には 3:1 が適用される。ここが乗るのは画面
 * 最下部の「Remove from category」の帯だけで、素の配色でも 3.53:1（白 × ピンク）。
 * 4.5 を要求すると、利用者が何も動かしていないのに文字色が変わってしまう。
 */
private const val DANGER_LABEL_RATIO = 3.0f
