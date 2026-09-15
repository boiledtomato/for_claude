package com.example.zlauncher.core.otp

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Test

class QrLuminanceTest {

    /** 3x2 の絵。値は「行 * 10 + 列」で位置が分かるようにしてある */
    private val image = byteArrayOf(
        0, 1, 2,
        10, 11, 12,
    )

    @Test
    fun `packing keeps the image when there is no padding`() {
        assertSame(image, QrLuminance.pack(image, width = 3, height = 2, rowStride = 3))
    }

    @Test
    fun `packing drops the row padding`() {
        // 1 行 5 バイトのうち後ろ 2 バイトが余白
        val padded = byteArrayOf(
            0, 1, 2, 99, 99,
            10, 11, 12, 99, 99,
        )
        assertArrayEquals(image, QrLuminance.pack(padded, width = 3, height = 2, rowStride = 5))
    }

    @Test
    fun `packing stops instead of reading past the buffer`() {
        val truncated = byteArrayOf(0, 1, 2, 99, 99, 10, 11)
        val packed = QrLuminance.pack(truncated, width = 3, height = 2, rowStride = 5)
        assertEquals(6, packed.size)
        assertArrayEquals(byteArrayOf(0, 1, 2, 0, 0, 0), packed)
    }

    @Test
    fun `row stride narrower than the image is rejected`() {
        assertThrows(IllegalArgumentException::class.java) {
            QrLuminance.pack(image, width = 3, height = 2, rowStride = 2)
        }
    }

    @Test
    fun `no rotation returns the same bytes`() {
        val result = QrLuminance.rotate(image, 3, 2, 0)
        assertSame(image, result.bytes)
        assertEquals(3, result.width)
        assertEquals(2, result.height)
    }

    @Test
    fun `quarter turn swaps the sides and moves the first pixel to the top right`() {
        val result = QrLuminance.rotate(image, 3, 2, 90)
        assertEquals(2, result.width)
        assertEquals(3, result.height)
        // 元の左上 (0) が右上へ、左下 (10) が左上へ
        assertArrayEquals(
            byteArrayOf(
                10, 0,
                11, 1,
                12, 2,
            ),
            result.bytes,
        )
    }

    @Test
    fun `half turn reverses the image`() {
        val result = QrLuminance.rotate(image, 3, 2, 180)
        assertEquals(3, result.width)
        assertEquals(2, result.height)
        assertArrayEquals(byteArrayOf(12, 11, 10, 2, 1, 0), result.bytes)
    }

    @Test
    fun `three quarter turn is the mirror of the quarter turn`() {
        val result = QrLuminance.rotate(image, 3, 2, 270)
        assertEquals(2, result.width)
        assertEquals(3, result.height)
        assertArrayEquals(
            byteArrayOf(
                2, 12,
                1, 11,
                0, 10,
            ),
            result.bytes,
        )
    }

    @Test
    fun `four quarter turns come back to the original`() {
        var current = QrLuminance.Rotated(image, 3, 2)
        repeat(4) { current = QrLuminance.rotate(current.bytes, current.width, current.height, 90) }
        assertEquals(QrLuminance.Rotated(image, 3, 2), current)
    }

    @Test
    fun `negative and oversized angles are normalised`() {
        val left = QrLuminance.rotate(image, 3, 2, -90)
        val right = QrLuminance.rotate(image, 3, 2, 270)
        assertEquals(right, left)
        assertEquals(QrLuminance.rotate(image, 3, 2, 90), QrLuminance.rotate(image, 3, 2, 450))
    }

    @Test
    fun `an angle the camera never reports is refused`() {
        assertThrows(IllegalArgumentException::class.java) {
            QrLuminance.rotate(image, 3, 2, 45)
        }
    }
}
