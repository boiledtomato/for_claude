package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * ウィジェットに出す理由。**言い当てることがこの機能の半分。**
 *
 * 動かない理由はほぼ設定の不足で、直し方が理由ごとに違う。ひとまとめの
 * 「読み込めません」だと、プロジェクト番号の打ち間違いとライセンス未割り当てと
 * 圏外の区別がつかない。
 *
 * `Ok` の経路は JSON を読むので Robolectric 下で動かす。
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NotebookStatusTest {

    @Test
    fun `each reason names what to fix`() {
        assertTrue(
            NotebookStatus.message(NotebookFetch.NotConfigured, hasCached = false)
                .contains("project number")
        )
        assertTrue(
            NotebookStatus.message(NotebookFetch.NeedsSignIn, hasCached = false)
                .contains("allow access", ignoreCase = true)
        )
        // 403 は原因が 3 つあるので 3 つ挙げる。1 つだけ書くと残り 2 つで詰まる
        val noAccess = NotebookStatus.message(NotebookFetch.NoAccess, hasCached = false)
        assertTrue(noAccess.contains("Discovery Engine"))
        assertTrue(noAccess.contains("licence"))
        assertTrue(noAccess.contains("project number"))
    }

    @Test
    fun `a good fetch with notebooks says nothing at all`() {
        val ok = NotebookFetch.Ok(listOf(NotebookSummary(id = "a", title = "A")))
        assertEquals("", NotebookStatus.message(ok, hasCached = true))
        assertTrue(NotebookStatus.showsList(ok, hasCached = true))
    }

    @Test
    fun `an empty project is stated, not left blank`() {
        val empty = NotebookFetch.Ok(emptyList())
        assertEquals("No notebooks in this project", NotebookStatus.message(empty, hasCached = false))
    }

    @Test
    fun `a transient failure over cached contents says the contents are old`() {
        // 出せる中身があるなら、失敗そのものより「古い」ことのほうが役に立つ
        val message = NotebookStatus.message(NotebookFetch.Failed("timeout"), hasCached = true)
        assertTrue(message.contains("showing the last result"))
        assertFalse(
            NotebookStatus.message(NotebookFetch.Failed("timeout"), hasCached = false)
                .contains("showing the last result")
        )
    }

    @Test
    fun `settings problems hide the list, transient ones keep it`() {
        // 古い一覧を残したまま「権限が無い」と書くと、どちらが本当か読めない
        assertFalse(NotebookStatus.showsList(NotebookFetch.NoAccess, hasCached = true))
        assertFalse(NotebookStatus.showsList(NotebookFetch.NeedsSignIn, hasCached = true))
        assertFalse(NotebookStatus.showsList(NotebookFetch.NotConfigured, hasCached = true))
        assertTrue(NotebookStatus.showsList(NotebookFetch.RateLimited, hasCached = true))
        assertTrue(NotebookStatus.showsList(NotebookFetch.Failed("x"), hasCached = true))
    }

    @Test
    fun `before the first refresh it says so instead of looking broken`() {
        assertEquals("Not refreshed yet", NotebookStatus.message(null, hasCached = false))
        assertEquals("", NotebookStatus.message(null, hasCached = true))
    }
}
