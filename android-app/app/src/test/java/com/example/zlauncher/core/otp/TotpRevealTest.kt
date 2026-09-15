package com.example.zlauncher.core.otp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ホーム画面に出しているあいだの時刻の扱い。
 *
 * ここが狂うと、切り替わったあとの古いコードを出し続ける（認証側が 1 つ前まで通すので、
 * 「たまに通らない」という一番厄介な形で出る）。
 */
class TotpRevealTest {

    @Test
    fun `window closes 30 seconds after the tap`() {
        val now = 1_700_000_000_000L
        assertEquals(now + 30_000L, TotpReveal.windowEnd(now))
        assertTrue(TotpReveal.isRevealed(now + 29_999L, TotpReveal.windowEnd(now)))
        assertFalse(TotpReveal.isRevealed(now + 30_000L, TotpReveal.windowEnd(now)))
    }

    @Test
    fun `hidden when nothing was ever revealed`() {
        assertFalse(TotpReveal.isRevealed(1L, 0L))
    }

    @Test
    fun `code expires at the next period boundary`() {
        // 周期のちょうど頭
        assertEquals(30_000L, TotpReveal.codeExpiresAt(0L))
        // 1 秒過ぎたところ
        assertEquals(30_000L, TotpReveal.codeExpiresAt(1_000L))
        // 境目そのものは「今」ではなく次の境目
        assertEquals(60_000L, TotpReveal.codeExpiresAt(30_000L))
    }

    @Test
    fun `code expiry follows a non default period`() {
        assertEquals(60_000L, TotpReveal.codeExpiresAt(1_000L, periodSeconds = 60))
        assertEquals(15_000L, TotpReveal.codeExpiresAt(14_000L, periodSeconds = 15))
    }

    @Test
    fun `next tick is the code boundary when it comes first`() {
        // 周期の頭で押した: 30 秒の窓と 30 秒後の境目が重なるので、どちらでも同じ時刻
        val now = 0L
        assertEquals(30_000L, TotpReveal.nextTickAt(now, TotpReveal.windowEnd(now)))

        // 周期の途中で押した: 先に来るのは境目のほう
        val mid = 20_000L
        assertEquals(30_000L, TotpReveal.nextTickAt(mid, TotpReveal.windowEnd(mid)))
    }

    @Test
    fun `next tick is the window end when the code outlives it`() {
        // 窓を短く切った状態（境目の直後に押し直したあとなど）
        val now = 1_000L
        assertEquals(5_000L, TotpReveal.nextTickAt(now, windowEndMillis = 5_000L))
    }

    @Test
    fun `no tick once the window is closed`() {
        assertNull(TotpReveal.nextTickAt(nowMillis = 10_000L, windowEndMillis = 10_000L))
        assertNull(TotpReveal.nextTickAt(nowMillis = 10_001L, windowEndMillis = 10_000L))
    }

    @Test
    fun `tick never lands in the past`() {
        val now = 12_345L
        val tick = TotpReveal.nextTickAt(now, TotpReveal.windowEnd(now))!!
        assertTrue(tick > now)
    }
}
