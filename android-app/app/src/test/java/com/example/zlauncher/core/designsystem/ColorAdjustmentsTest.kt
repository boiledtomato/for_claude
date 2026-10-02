package com.example.zlauncher.core.designsystem

import androidx.compose.ui.graphics.toArgb
import com.example.zlauncher.core.color.ColorMath
import com.example.zlauncher.domain.model.ColorAdjust
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 「つまみをどこに動かしても字が読める」ことを、目ではなく数字で確かめる。
 *
 * 配色の調整は利用者が自由に動かせるので、組み合わせは無数にある。**面と文字が同じ側へ
 * 寄って字が消える**のがこの機能の唯一の壊れ方なので、範囲の全域を機械的に当たる。
 */
class ColorAdjustmentsTest {

    private val schemes = listOf("dark" to ZDarkColors, "light" to ZLightColors)

    private fun grid(): List<ColorAdjust> {
        val saturations = listOf(0f, 0.4f, 1f, 1.3f, 1.6f)
        val lightnesses = listOf(0.6f, 0.8f, 1f, 1.2f, 1.4f)
        return saturations.flatMap { s -> lightnesses.map { l -> ColorAdjust(s, l) } }
    }

    @Test
    fun `the default setting is left completely alone`() {
        // 既定の配色は palette_check.py で実測した値。ここで触ると測った意味がなくなる
        assertSame(ZDarkColors, ZDarkColors.adjusted(ColorAdjust.NONE))
        assertSame(ZLightColors, ZLightColors.adjusted(ColorAdjust.NONE))
    }

    @Test
    fun `body text keeps 4,5 to 1 against every surface, everywhere in the range`() {
        forEachCombination { name, adjust, colors ->
            val surfaces = listOf(colors.background, colors.surface, colors.surfaceHigh, colors.surfaceLow)
            surfaces.forEach { surface ->
                val ratio = ColorMath.contrast(colors.textPrimary.toArgb(), surface.toArgb())
                assertTrue(
                    "$name $adjust: body text only reaches ${"%.2f".format(ratio)}:1",
                    ratio >= 4.5f - TOLERANCE,
                )
            }
        }
    }

    @Test
    fun `secondary and dim text stay above their floors`() {
        forEachCombination { name, adjust, colors ->
            val surfaces = listOf(colors.background, colors.surface, colors.surfaceHigh, colors.surfaceLow)
            surfaces.forEach { surface ->
                val secondary = ColorMath.contrast(colors.textSecondary.toArgb(), surface.toArgb())
                val dim = ColorMath.contrast(colors.textDim.toArgb(), surface.toArgb())
                assertTrue("$name $adjust: secondary text at ${"%.2f".format(secondary)}:1", secondary >= 4.0f - TOLERANCE)
                assertTrue("$name $adjust: dim text at ${"%.2f".format(dim)}:1", dim >= 3.0f - TOLERANCE)
            }
        }
    }

    @Test
    fun `the danger button keeps its label readable`() {
        // 大きい文字なので基準は 3:1（素の暗い配色で 3.53:1 の白 × ピンク）。
        // 4.5 を課すと、何も動かしていない既定の見た目まで変わってしまう
        forEachCombination { name, adjust, colors ->
            val ratio = ColorMath.contrast(colors.onDanger.toArgb(), colors.danger.toArgb())
            assertTrue("$name $adjust: danger label at ${"%.2f".format(ratio)}:1", ratio >= 3.0f - TOLERANCE)
        }
    }

    @Test
    fun `the category slots keep their count and pairing`() {
        forEachCombination { name, adjust, colors ->
            assertEquals("$name $adjust", 15, colors.categoryColors.size)
            assertEquals("$name $adjust", 15, colors.categoryChartColors.size)
        }
    }

    @Test
    fun `out of range settings are pulled back before they are used`() {
        val wild = ColorAdjust(saturation = 9f, lightness = 9f).normalized()
        assertEquals(ColorAdjust.SATURATION_RANGE.endInclusive, wild.saturation, 0.0001f)
        assertEquals(ColorAdjust.LIGHTNESS_RANGE.endInclusive, wild.lightness, 0.0001f)

        // 丸めは adjusted() の中でも効く ― 範囲外の値がそのまま掛からないこと
        val clamped = ZDarkColors.adjusted(ColorAdjust(saturation = 9f, lightness = 9f))
        val expected = ZDarkColors.adjusted(ColorAdjust.LIGHTNESS_RANGE.endInclusive.let {
            ColorAdjust(ColorAdjust.SATURATION_RANGE.endInclusive, it)
        })
        assertEquals(expected.surface, clamped.surface)
    }

    @Test
    fun `zero saturation leaves every surface grey`() {
        listOf(ZDarkColors, ZLightColors).forEach { base ->
            val colors = base.adjusted(ColorAdjust(saturation = 0f, lightness = 1f))
            val argb = colors.surface.toArgb()
            val r = (argb shr 16) and 0xFF
            val g = (argb shr 8) and 0xFF
            val b = argb and 0xFF
            assertEquals(r, g)
            assertEquals(g, b)
        }
    }

    private fun forEachCombination(block: (String, ColorAdjust, ZColorScheme) -> Unit) {
        schemes.forEach { (name, base) ->
            grid().forEach { adjust -> block(name, adjust, base.adjusted(adjust)) }
        }
    }

    /** 明度の刻みが 1/100 なので、最後の 1 段ぶんの誤差は許す */
    private companion object {
        const val TOLERANCE = 0.05f
    }
}
