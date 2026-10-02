package com.example.zlauncher.ui.console

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * レールの並べ替えの当たり判定。
 *
 * 行の高さがそろっていない前提を押さえる。名前が 2 行に折り返すカテゴリーは背が高く、
 * 「1 行ぶん = 固定値」で割ると長い名前の隣で 1 つずれる。指の下で 1 つずれるのは
 * 見ていても原因が分からない類の壊れ方なので、ここで固定しておく。
 */
class RailReorderTest {

    /** 60 / 84（2 行）/ 60 / 60 */
    private val uneven = listOf(60f, 84f, 60f, 60f)
    private val even = List(4) { 60f }

    @Test
    fun `staying within half a row does not move`() {
        assertEquals(1, RailReorder.drop(1, 25f, even).index)
        assertEquals(1, RailReorder.drop(1, -25f, even).index)
        assertEquals(0f, RailReorder.drop(1, 25f, even).consumed, 0.01f)
    }

    @Test
    fun `crossing half of the next row swaps`() {
        assertEquals(2, RailReorder.drop(1, 31f, even).index)
        assertEquals(0, RailReorder.drop(1, -31f, even).index)
    }

    @Test
    fun `the threshold is the neighbour's height, not the dragged row's`() {
        // 0 の下は 84 の行。42 を越えるまでは入れ替わらない
        assertEquals(0, RailReorder.drop(0, 41f, uneven).index)
        assertEquals(1, RailReorder.drop(0, 43f, uneven).index)
        // 逆に背の高い 1 から上へ動かすときの閾値は上の行の 60 の半分
        assertEquals(1, RailReorder.drop(1, -29f, uneven).index)
        assertEquals(0, RailReorder.drop(1, -31f, uneven).index)
    }

    @Test
    fun `consumed distance is the sum of the rows actually passed`() {
        val drop = RailReorder.drop(0, 200f, uneven)
        assertEquals(3, drop.index)
        // 84 + 60 + 60
        assertEquals(204f, drop.consumed, 0.01f)
    }

    @Test
    fun `dragging past the ends clamps`() {
        assertEquals(3, RailReorder.drop(0, 10_000f, uneven).index)
        assertEquals(0, RailReorder.drop(3, -10_000f, uneven).index)
    }

    @Test
    fun `an out of range start is left alone`() {
        assertEquals(9, RailReorder.drop(9, 500f, uneven).index)
        assertEquals(0f, RailReorder.drop(9, 500f, uneven).consumed, 0.01f)
    }

    @Test
    fun `rows on the path move by the dragged row's height, others stay`() {
        // 0 を 2 まで下ろす: 1 と 2 が 0 の高さぶん上がる
        assertEquals(-60f, RailReorder.shift(1, start = 0, target = 2, heights = uneven), 0.01f)
        assertEquals(-60f, RailReorder.shift(2, start = 0, target = 2, heights = uneven), 0.01f)
        assertEquals(0f, RailReorder.shift(3, start = 0, target = 2, heights = uneven), 0.01f)
        // 掴んでいる行そのものは shift しない（指に追従する側）
        assertEquals(0f, RailReorder.shift(0, start = 0, target = 2, heights = uneven), 0.01f)
    }

    @Test
    fun `dragging upward pushes the passed rows down`() {
        // 3 を 1 まで上げる: 1 と 2 が 3 の高さぶん下がる
        assertEquals(60f, RailReorder.shift(1, start = 3, target = 1, heights = uneven), 0.01f)
        assertEquals(60f, RailReorder.shift(2, start = 3, target = 1, heights = uneven), 0.01f)
        assertEquals(0f, RailReorder.shift(0, start = 3, target = 1, heights = uneven), 0.01f)
    }

    @Test
    fun `no shift while the drop target is where it started`() {
        (0..3).forEach { assertEquals(0f, RailReorder.shift(it, 1, 1, uneven), 0.01f) }
    }
}
