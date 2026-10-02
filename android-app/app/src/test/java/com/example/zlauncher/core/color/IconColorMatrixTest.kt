package com.example.zlauncher.core.color

import com.example.zlauncher.domain.model.ColorAdjust
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class IconColorMatrixTest {

    private val identity = floatArrayOf(
        1f, 0f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f, 0f,
        0f, 0f, 1f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    )

    /** 行列を 1 色に当てる。入力も結果も 0..1 の RGB */
    private fun apply(m: FloatArray, r: Float, g: Float, b: Float): Triple<Float, Float, Float> =
        Triple(
            m[0] * r + m[1] * g + m[2] * b + m[4],
            m[5] * r + m[6] * g + m[7] * b + m[9],
            m[10] * r + m[11] * g + m[12] * b + m[14],
        )

    @Test
    fun `no adjustment means no filter at all`() {
        assertNull(IconColorMatrix.of(ColorAdjust.NONE))
    }

    @Test
    fun `the neutral matrix is the identity`() {
        assertArrayEquals(identity, IconColorMatrix.build(1f, 1f), 0.0001f)
    }

    @Test
    fun `zero saturation turns every colour into its own grey`() {
        val m = IconColorMatrix.build(saturation = 0f, brightness = 1f)
        val (r, g, b) = apply(m, 0.2f, 0.6f, 0.9f)
        assertEquals(r, g, 0.0001f)
        assertEquals(g, b, 0.0001f)
        // 明るさは輝度そのもの
        assertEquals(0.213f * 0.2f + 0.715f * 0.6f + 0.072f * 0.9f, r, 0.0001f)
    }

    @Test
    fun `grey stays grey and keeps its level whatever the saturation`() {
        listOf(0f, 0.5f, 1f, 1.6f).forEach { s ->
            val (r, g, b) = apply(IconColorMatrix.build(s, 1f), 0.5f, 0.5f, 0.5f)
            assertEquals("saturation $s", 0.5f, r, 0.0001f)
            assertEquals("saturation $s", 0.5f, g, 0.0001f)
            assertEquals("saturation $s", 0.5f, b, 0.0001f)
        }
    }

    @Test
    fun `brightness scales every channel by the same amount`() {
        val (r, g, b) = apply(IconColorMatrix.build(1f, 1.25f), 0.4f, 0.5f, 0.6f)
        assertEquals(0.5f, r, 0.0001f)
        assertEquals(0.625f, g, 0.0001f)
        assertEquals(0.75f, b, 0.0001f)
    }

    @Test
    fun `dimming keeps the hue ordering`() {
        val (r, g, b) = apply(IconColorMatrix.build(1f, 0.7f), 0.9f, 0.5f, 0.1f)
        assertTrue(r > g && g > b)
        assertEquals(0.9f / 0.5f, r / g, 0.0001f)
    }

    @Test
    fun `raising saturation pushes a colour away from its grey`() {
        val plain = apply(IconColorMatrix.build(1f, 1f), 0.6f, 0.4f, 0.4f)
        val vivid = apply(IconColorMatrix.build(1.6f, 1f), 0.6f, 0.4f, 0.4f)
        assertTrue(vivid.first > plain.first)
        assertTrue(vivid.second < plain.second)
    }

    @Test
    fun `alpha is never touched`() {
        val m = IconColorMatrix.build(0.2f, 1.4f)
        assertArrayEquals(floatArrayOf(0f, 0f, 0f, 1f, 0f), m.copyOfRange(15, 20), 0.0001f)
        // RGB の行もアルファ列には何も足さない（3 列目が 0）
        listOf(3, 8, 13).forEach { assertEquals(0f, m[it], 0.0001f) }
    }

    @Test
    fun `out of range values are pulled back before the matrix is built`() {
        val wild = IconColorMatrix.of(ColorAdjust(saturation = 5f, lightness = 5f))!!
        val clamped = IconColorMatrix.build(
            ColorAdjust.SATURATION_RANGE.endInclusive,
            ColorAdjust.LIGHTNESS_RANGE.endInclusive,
        )
        assertArrayEquals(clamped, wild, 0.0001f)
    }

    @Test
    fun `the matrix is always 4 by 5`() {
        assertEquals(20, IconColorMatrix.build(1f, 1f).size)
    }
}
