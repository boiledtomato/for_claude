package com.example.zlauncher.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WidgetSheetsTest {

    private fun widget(id: Int, sheet: String) =
        WidgetPlacement(appWidgetId = id, heightDp = 120, widthSpan = 2, sheetId = sheet)

    private fun ids(sheets: List<WidgetSheet>) = sheets.map { it.id }

    // ---- 移行 ---------------------------------------------------------------

    @Test
    fun `widgets placed before sheets existed land on a first sheet`() {
        val result = WidgetSheets.normalize(
            sheets = emptyList(),
            widgets = listOf(widget(1, ""), widget(2, "")),
            active = "",
            newId = { "s1" },
        )
        assertEquals(listOf("s1"), ids(result.sheets))
        assertTrue(result.widgets.all { it.sheetId == "s1" })
        assertEquals("s1", result.active)
    }

    @Test
    fun `a widget pointing at a sheet that no longer exists is taken in by the first one`() {
        val result = WidgetSheets.normalize(
            sheets = listOf(WidgetSheet("a"), WidgetSheet("b")),
            widgets = listOf(widget(1, "a"), widget(2, "gone")),
            active = "b",
            newId = { "never" },
        )
        assertEquals(listOf("a", "b"), ids(result.sheets))
        assertEquals("a", result.widgets.first { it.appWidgetId == 2 }.sheetId)
        assertEquals("b", result.active)
    }

    @Test
    fun `nothing is invented when there is nothing to place`() {
        val result = WidgetSheets.normalize(emptyList(), emptyList(), "", newId = { "s1" })
        assertTrue(result.sheets.isEmpty())
        assertEquals("", result.active)
    }

    @Test
    fun `an active sheet that disappeared falls back to the first one`() {
        val result = WidgetSheets.normalize(
            sheets = listOf(WidgetSheet("a")),
            widgets = listOf(widget(1, "a")),
            active = "gone",
            newId = { "never" },
        )
        assertEquals("a", result.active)
    }

    // ---- 空のシートの片付け -------------------------------------------------

    @Test
    fun `an empty sheet is dropped once you have left it`() {
        val sheets = listOf(WidgetSheet("a"), WidgetSheet("b"))
        val widgets = listOf(widget(1, "a"))
        assertEquals(listOf("a"), ids(WidgetSheets.pruneEmpty(sheets, widgets, keep = "a")))
    }

    @Test
    fun `the sheet you are standing on survives while it is still empty`() {
        val sheets = listOf(WidgetSheet("a"), WidgetSheet("b"))
        val widgets = listOf(widget(1, "a"))
        // b に居るあいだは、まだ 1 つも置いていなくても消さない
        assertEquals(listOf("a", "b"), ids(WidgetSheets.pruneEmpty(sheets, widgets, keep = "b")))
    }

    @Test
    fun `pruning with the sheet you are leaving destroys a sheet just added`() {
        // 追加直後は「元のシート a に立ったまま、空の b が増えた」状態。ここで keep に a を
        // 渡すと b がその場で消える ― 画面側で一度この順番になり、押しても増えず移動先も
        // 無い、という症状になった。**片付けは移動が終わってから、行き先を keep に**。
        val sheets = listOf(WidgetSheet("a"), WidgetSheet("b"))
        val widgets = listOf(widget(1, "a"))
        assertEquals(listOf("a"), ids(WidgetSheets.pruneEmpty(sheets, widgets, keep = "a")))
        // 行き先を渡せば残る
        assertEquals(listOf("a", "b"), ids(WidgetSheets.pruneEmpty(sheets, widgets, keep = "b")))
    }

    @Test
    fun `adding from an empty sheet keeps both until one is left behind`() {
        // 空の a から足した直後。どちらも空なので、keep 次第で片方しか残らない ―
        // 画面側はこの 1 回だけ片付けを飛ばして両方残し、フリックで離れたときに片付ける
        val sheets = listOf(WidgetSheet("a"), WidgetSheet("b"))
        assertEquals(listOf("b"), ids(WidgetSheets.pruneEmpty(sheets, emptyList(), keep = "b")))
        assertEquals(listOf("a"), ids(WidgetSheets.pruneEmpty(sheets, emptyList(), keep = "a")))
    }

    @Test
    fun `with no widgets at all and nowhere to stand every sheet goes`() {
        val sheets = listOf(WidgetSheet("a"), WidgetSheet("b"))
        assertTrue(WidgetSheets.pruneEmpty(sheets, emptyList(), keep = null).isEmpty())
    }

    // ---- シート内の並べ替え -------------------------------------------------

    @Test
    fun `reordering inside a sheet leaves the other sheets untouched`() {
        val widgets = listOf(
            widget(1, "a"), widget(2, "b"), widget(3, "a"), widget(4, "b"), widget(5, "a"),
        )
        // シート a の並びは 1, 3, 5 → 末尾を抜いて先頭へ差し込む（5, 1, 3）
        val moved = WidgetSheets.moveWithinSheet(widgets, "a", fromIndex = 2, toIndex = 0)

        assertEquals(listOf(5, 1, 3), WidgetSheets.widgetsOn(moved, "a").map { it.appWidgetId })
        assertEquals(listOf(2, 4), WidgetSheets.widgetsOn(moved, "b").map { it.appWidgetId })
        // 全体の長さも、どのシートが何番目の枠を使うかも変わらない
        assertEquals(widgets.size, moved.size)
        assertEquals(widgets.map { it.sheetId }, moved.map { it.sheetId })
    }

    @Test
    fun `an index outside the sheet changes nothing`() {
        val widgets = listOf(widget(1, "a"), widget(2, "b"))
        assertEquals(widgets, WidgetSheets.moveWithinSheet(widgets, "a", fromIndex = 0, toIndex = 3))
        assertEquals(widgets, WidgetSheets.moveWithinSheet(widgets, "a", fromIndex = 0, toIndex = 0))
    }

    @Test
    fun `widgetsOn keeps the stored order`() {
        val widgets = listOf(widget(3, "a"), widget(1, "b"), widget(2, "a"))
        assertEquals(listOf(3, 2), WidgetSheets.widgetsOn(widgets, "a").map { it.appWidgetId })
    }
}
