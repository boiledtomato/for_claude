package com.example.zlauncher.core.color

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ColorMathTest {

    private val white = 0xFFFFFFFF.toInt()
    private val black = 0xFF000000.toInt()
    private val zscalerBlue = 0xFF2E7DF6.toInt()

    @Test
    fun `neutral adjustment returns the very same colour`() {
        assertEquals(zscalerBlue, ColorMath.adjust(zscalerBlue, saturation = 1f, lightness = 1f))
    }

    @Test
    fun `zero saturation leaves a grey`() {
        val grey = ColorMath.adjust(zscalerBlue, saturation = 0f, lightness = 1f)
        val r = (grey shr 16) and 0xFF
        val g = (grey shr 8) and 0xFF
        val b = grey and 0xFF
        assertEquals(r, g)
        assertEquals(g, b)
    }

    @Test
    fun `alpha survives an adjustment`() {
        val translucent = 0x80123456.toInt()
        val adjusted = ColorMath.adjust(translucent, saturation = 1.4f, lightness = 0.7f)
        assertEquals(0x80, (adjusted ushr 24) and 0xFF)
    }

    @Test
    fun `raising lightness brightens and lowering it darkens`() {
        val brighter = ColorMath.adjust(zscalerBlue, saturation = 1f, lightness = 1.3f)
        val darker = ColorMath.adjust(zscalerBlue, saturation = 1f, lightness = 0.7f)
        assertTrue(ColorMath.luminance(brighter) > ColorMath.luminance(zscalerBlue))
        assertTrue(ColorMath.luminance(darker) < ColorMath.luminance(zscalerBlue))
    }

    @Test
    fun `hue is left alone`() {
        val before = ColorMath.toHsl(zscalerBlue)[0]
        val after = ColorMath.toHsl(ColorMath.adjust(zscalerBlue, saturation = 0.6f, lightness = 1.2f))[0]
        assertEquals(before, after, 1.0f)
    }

    @Test
    fun `white and black stay put at the ends`() {
        assertEquals(white, ColorMath.adjust(white, saturation = 1.5f, lightness = 1.5f))
        assertEquals(black, ColorMath.adjust(black, saturation = 1.5f, lightness = 1.5f))
    }

    @Test
    fun `hsl survives a round trip`() {
        val hsl = ColorMath.toHsl(zscalerBlue)
        assertEquals(zscalerBlue, ColorMath.fromHsl(hsl[0], hsl[1], hsl[2], 0xFF))
    }

    @Test
    fun `contrast matches the WCAG extremes`() {
        assertEquals(21f, ColorMath.contrast(black, white), 0.05f)
        assertEquals(1f, ColorMath.contrast(white, white), 0.001f)
    }

    @Test
    fun `text that already reads is returned untouched`() {
        assertEquals(black, ColorMath.ensureContrast(black, white, minRatio = 4.5f))
    }

    @Test
    fun `text on a light surface is pushed darker until it reads`() {
        val paleOnWhite = 0xFFE8EEF6.toInt()
        val fixed = ColorMath.ensureContrast(paleOnWhite, white, minRatio = 4.5f)
        assertTrue(ColorMath.contrast(fixed, white) >= 4.5f)
        assertTrue(ColorMath.luminance(fixed) < ColorMath.luminance(paleOnWhite))
    }

    @Test
    fun `text on a dark surface is pushed lighter until it reads`() {
        val nearBlackSurface = 0xFF0A0E17.toInt()
        val murkyText = 0xFF1F2A3C.toInt()
        val fixed = ColorMath.ensureContrast(murkyText, nearBlackSurface, minRatio = 4.5f)
        assertTrue(ColorMath.contrast(fixed, nearBlackSurface) >= 4.5f)
        assertTrue(ColorMath.luminance(fixed) > ColorMath.luminance(murkyText))
    }

    @Test
    fun `the guard keeps the hue while fixing the contrast`() {
        val surface = 0xFFF4F7FB.toInt()
        val washedOut = 0xFFBFD4F2.toInt()
        val fixed = ColorMath.ensureContrast(washedOut, surface, minRatio = 4.5f)
        assertEquals(ColorMath.toHsl(washedOut)[0], ColorMath.toHsl(fixed)[0], 2.0f)
    }

    @Test
    fun `an impossible request returns the best it can reach`() {
        // 白い面の上で 21:1 は黒しか無い。届かなくても、一番遠い色を返して黙って諦めない
        val fixed = ColorMath.ensureContrast(0xFFCCCCCC.toInt(), white, minRatio = 21f)
        assertTrue(ColorMath.contrast(fixed, white) > ColorMath.contrast(0xFFCCCCCC.toInt(), white))
    }
}
