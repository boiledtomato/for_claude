package com.example.zlauncher.core.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 並べ替えの判定。端末を触らないと分からない手触りの部分なので、境界を数字で固定しておく。
 */
class ReorderGateTest {

    private fun slot(index: Int, left: Float, top: Float) =
        ReorderGeometry.Slot(index = index, left = left, top = top, width = 100f, height = 100f)

    private val slots = listOf(slot(0, 0f, 0f), slot(1, 100f, 0f), slot(2, 0f, 100f))

    // ---- 深さ ---------------------------------------------------------------

    /** これが以前の壊れ方。隣に少し重なっただけで入れ替わっていた */
    @Test
    fun `touching the edge of a neighbour is not enough`() {
        assertNull(ReorderGeometry.candidate(centerX = 105f, centerY = 50f, slots = slots, exclude = 0))
    }

    @Test
    fun `reaching the middle of a neighbour selects it`() {
        assertEquals(1, ReorderGeometry.candidate(centerX = 150f, centerY = 50f, slots = slots, exclude = 0))
    }

    @Test
    fun `the boundary of the inset target is exactly three tenths in`() {
        // 幅 100 の相手の左端は 100、内側 30% は 130
        assertNull(ReorderGeometry.candidate(129.9f, 50f, slots, exclude = 0))
        assertEquals(1, ReorderGeometry.candidate(130f, 50f, slots, exclude = 0))
    }

    @Test
    fun `the dragged item itself is never a candidate`() {
        assertNull(ReorderGeometry.candidate(centerX = 50f, centerY = 50f, slots = slots, exclude = 0))
    }

    @Test
    fun `a gap between items selects nothing`() {
        assertNull(ReorderGeometry.candidate(centerX = 50f, centerY = 250f, slots = slots, exclude = 0))
    }

    // ---- 滞在 ---------------------------------------------------------------

    @Test
    fun `a candidate must be held before it commits`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)

        assertTrue(gate.offer(1, now = 0) is ReorderGate.Decision.Wait)
        assertTrue(gate.offer(1, now = 99) is ReorderGate.Decision.Wait)
        assertEquals(ReorderGate.Decision.Commit(1), gate.offer(1, now = 100))
    }

    /** 隣を通り過ぎただけでは入れ替わらない ― 候補が変われば滞在時間は数え直し */
    @Test
    fun `passing over a neighbour on the way does not commit`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)

        assertTrue(gate.offer(1, now = 0) is ReorderGate.Decision.Wait)
        assertTrue(gate.offer(2, now = 40) is ReorderGate.Decision.Wait)
        assertTrue(gate.offer(2, now = 80) is ReorderGate.Decision.Wait)
        assertEquals(ReorderGate.Decision.Commit(2), gate.offer(2, now = 140))
    }

    @Test
    fun `leaving every target clears the pending candidate`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)

        gate.offer(1, now = 0)
        assertEquals(ReorderGate.Decision.None, gate.offer(null, now = 50))
        // 戻ってきても、滞在は数え直しになる
        assertTrue(gate.offer(1, now = 60) is ReorderGate.Decision.Wait)
        assertEquals(ReorderGate.Decision.Commit(1), gate.offer(1, now = 160))
    }

    // ---- 間隔 ---------------------------------------------------------------

    /** 1 回入れ替えた直後に次が走ると、数枚ぶん飛んでどこへ置いたか分からなくなる */
    @Test
    fun `a second swap waits for the cooldown even after dwelling`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)
        gate.offer(1, now = 0)
        assertEquals(ReorderGate.Decision.Commit(1), gate.offer(1, now = 100))

        assertTrue(gate.offer(2, now = 110) is ReorderGate.Decision.Wait)
        // 滞在（110+100=210）は満ちても、据わり時間（100+200=300）が残る
        assertEquals(ReorderGate.Decision.Wait(90), gate.offer(2, now = 210))
        assertEquals(ReorderGate.Decision.Commit(2), gate.offer(2, now = 300))
    }

    /** 指が止まっても確定できるよう、待ち時間は呼び出し側に返す */
    @Test
    fun `the wait reports how long is left`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)

        assertEquals(ReorderGate.Decision.Wait(100), gate.offer(1, now = 0))
        assertEquals(ReorderGate.Decision.Wait(60), gate.offer(1, now = 40))
    }

    @Test
    fun `the first candidate of a new drag is not held back by the previous cooldown`() {
        val gate = ReorderGate(dwellMillis = 100, cooldownMillis = 200)
        gate.offer(1, now = 0)
        gate.offer(1, now = 100)

        gate.reset()

        // 滞在は数え直しになるが、前のドラッグの据わり時間は持ち越さない
        assertEquals(ReorderGate.Decision.Wait(100), gate.offer(1, now = 200))
        assertEquals(ReorderGate.Decision.Commit(1), gate.offer(1, now = 300))
    }

    /** 既定値そのものも固定する。触り心地を変えるときは、ここを見て変える */
    @Test
    fun `defaults stay deliberate`() {
        assertEquals(140L, ReorderGate.DWELL_MILLIS)
        assertEquals(220L, ReorderGate.COOLDOWN_MILLIS)
        assertEquals(0.3f, ReorderGeometry.INSET_FRACTION)
    }
}
