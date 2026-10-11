package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * 応答の読み取り。相手は Pre-GA のプレビューなので、**項目が欠けても一覧が空にならない**
 * ことが肝。`org.json` は Android 同梱なので Robolectric 下で動かす。
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NotebookJsonTest {

    private val documented = """
        {
          "notebooks": [
            {
              "name": "projects/123/locations/global/notebooks/9f2a-1",
              "title": "Zscaler help docs",
              "notebookId": "9f2a-1",
              "emoji": "📘",
              "metadata": {
                "userRole": "OWNER",
                "isShared": false,
                "isShareable": true,
                "lastViewed": "2026-10-09T02:11:00Z",
                "createTime": "2026-08-01T20:46:42Z"
              }
            },
            {
              "name": "projects/123/locations/global/notebooks/9f2a-2",
              "title": "Zscaler community",
              "notebookId": "9f2a-2",
              "emoji": "💬",
              "metadata": { "userRole": "READER", "isShared": true }
            }
          ]
        }
    """.trimIndent()

    @Test
    fun `the documented shape is read in full`() {
        val list = NotebookJson.parseList(documented)
        assertEquals(2, list.size)
        val first = list.first()
        assertEquals("9f2a-1", first.id)
        assertEquals("Zscaler help docs", first.title)
        assertEquals("📘", first.emoji)
        assertEquals("OWNER", first.userRole)
        assertEquals(false, first.isShared)
        assertEquals("2026-10-09T02:11:00Z", first.lastViewed)
        assertEquals("2026-08-01T20:46:42Z", first.createTime)
        assertEquals(true, list[1].isShared)
        // 日時の無い行もそのまま 1 行として残す
        assertEquals("", list[1].lastViewed)
    }

    @Test
    fun `a row missing everything but its name still opens`() {
        // 版が上がって notebookId が消えても、name の末尾から開ける
        val list = NotebookJson.parseList(
            """{"notebooks":[{"name":"projects/1/locations/global/notebooks/only-name"}]}"""
        )
        assertEquals(1, list.size)
        assertEquals("only-name", list.first().id)
        assertEquals("Untitled notebook", list.first().displayTitle)
    }

    @Test
    fun `a row with no id at all is dropped, not shown blank`() {
        // 開く先が決まらない行は押せても何も起きない。出さないほうが正しい
        val list = NotebookJson.parseList("""{"notebooks":[{"title":"no id here"}]}""")
        assertTrue(list.isEmpty())
    }

    @Test
    fun `one bad row does not take the good ones with it`() {
        val list = NotebookJson.parseList(
            """{"notebooks":[{"title":"bad"},{"notebookId":"good","title":"fine"}]}"""
        )
        assertEquals(listOf("good"), list.map { it.id })
    }

    @Test
    fun `an empty or broken body is zero notebooks, not a crash`() {
        assertTrue(NotebookJson.parseList("").isEmpty())
        assertTrue(NotebookJson.parseList("not json at all").isEmpty())
        assertTrue(NotebookJson.parseList("{}").isEmpty())
        assertTrue(NotebookJson.parseList("""{"notebooks":[]}""").isEmpty())
    }

    @Test
    fun `a single notebook response is not wrapped`() {
        val one = NotebookJson.parseOne("""{"notebookId":"solo","title":"Just one"}""")
        assertEquals("solo", one?.id)
        assertNull(NotebookJson.parseOne("{}"))
    }

    @Test
    fun `a 200 goes through the parser`() {
        val ok = NotebookFetch.fromStatus(200, documented) as NotebookFetch.Ok
        assertEquals(2, ok.notebooks.size)
    }
}
