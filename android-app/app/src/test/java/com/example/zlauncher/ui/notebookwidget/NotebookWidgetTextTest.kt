package com.example.zlauncher.ui.notebookwidget

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ウィジェットに出す文字。**欠けた項目で行が壊れないこと**が肝 ―
 * 相手は Pre-GA のプレビューで、`metadata` ごと無い応答が来る。
 */
class NotebookWidgetTextTest {

    /** 2026-10-11T00:00:00Z */
    private val now = 1_791_676_800_000L

    private fun hoursAgo(hours: Int) = now - hours * 3_600_000L

    @Test
    fun `all three parts are joined`() {
        val meta = NotebookWidgetText.meta(
            userRole = "OWNER",
            isShared = true,
            lastViewed = iso(hoursAgo(3)),
            nowMillis = now,
        )
        assertEquals("Owner · Shared · Opened 3h ago", meta)
    }

    @Test
    fun `nothing known leaves the second line empty, not a row of separators`() {
        // metadata ごと無い応答。" ·  · " のような行を出さない
        assertEquals("", NotebookWidgetText.meta("", isShared = false, lastViewed = "", nowMillis = now))
    }

    @Test
    fun `only the known parts appear`() {
        assertEquals("Reader", NotebookWidgetText.meta("READER", false, "", now))
        assertEquals("Shared", NotebookWidgetText.meta("", true, "", now))
    }

    @Test
    fun `the api constant is not shouted at the user`() {
        assertEquals("Owner", NotebookWidgetText.role("OWNER"))
        assertEquals("Reader", NotebookWidgetText.role("READER"))
    }

    @Test
    fun `elapsed time is spoken in the largest useful unit`() {
        assertEquals("just now", NotebookWidgetText.relative(iso(now - 30_000), now))
        assertEquals("5m ago", NotebookWidgetText.relative(iso(now - 300_000), now))
        assertEquals("2h ago", NotebookWidgetText.relative(iso(hoursAgo(2)), now))
        assertEquals("3d ago", NotebookWidgetText.relative(iso(hoursAgo(24 * 3)), now))
        assertEquals("2mo ago", NotebookWidgetText.relative(iso(hoursAgo(24 * 61)), now))
    }

    @Test
    fun `a clock skew into the future is not shown as negative`() {
        // 端末の時計がずれているだけで「-5m ago」と出すのは意味が無い
        assertNull(NotebookWidgetText.relative(iso(now + 600_000), now))
    }

    @Test
    fun `unreadable timestamps are left out rather than printed raw`() {
        assertNull(NotebookWidgetText.relative("", now))
        assertNull(NotebookWidgetText.relative("whenever", now))
        assertNull(NotebookWidgetText.relative("2026-13-45T99:99:99Z", now))
    }

    @Test
    fun `fractional seconds and offsets both parse`() {
        // 同じ API が両方の形を返しうる。どちらでも落とさない
        assertTrue(NotebookWidgetText.parseMillis("2026-10-09T02:11:00.123456Z") != null)
        assertTrue(NotebookWidgetText.parseMillis("2026-10-09T02:11:00+09:00") != null)
        assertTrue(NotebookWidgetText.parseMillis("2026-10-09T02:11:00Z") != null)
    }

    @Test
    fun `the header says when it was updated, and says nothing before the first fetch`() {
        assertEquals("", NotebookWidgetText.refreshedAt(0L, now))
        assertEquals("Updated 4h ago", NotebookWidgetText.refreshedAt(hoursAgo(4), now))
    }

    private fun iso(millis: Long): String = java.time.Instant.ofEpochMilli(millis).toString()
}
