package com.example.zlauncher.ui.widgets

import com.example.zlauncher.domain.model.WidgetPlacement
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 行の詰め方と「本来の幅」の算出。
 *
 * どちらも見た目にしか出ない計算なので、端末を見ないと分からない類の壊れ方をする。
 */
class WidgetFlowTest {

    private fun widget(id: Int, span: Int) = WidgetPlacement(appWidgetId = id, heightDp = 120, widthSpan = span)

    @Test
    fun `two half width widgets share one row`() {
        val rows = WidgetFlow.rows(listOf(widget(1, 2), widget(2, 2)))

        assertEquals(1, rows.size)
        assertEquals(listOf(1, 2), rows[0].widgets.map { it.appWidgetId })
        assertEquals(0, rows[0].freeSpan)
    }

    @Test
    fun `a widget that does not fit starts the next row`() {
        val rows = WidgetFlow.rows(listOf(widget(1, 3), widget(2, 2)))

        assertEquals(2, rows.size)
        assertEquals(listOf(1), rows[0].widgets.map { it.appWidgetId })
        assertEquals(1, rows[0].freeSpan)
        assertEquals(listOf(2), rows[1].widgets.map { it.appWidgetId })
        assertEquals(2, rows[1].freeSpan)
    }

    /** 後ろのものを繰り上げて隙間を埋めない。並び順は利用者のもの */
    @Test
    fun `a later narrow widget is not pulled up into an earlier gap`() {
        val rows = WidgetFlow.rows(listOf(widget(1, 3), widget(2, 4), widget(3, 1)))

        assertEquals(3, rows.size)
        assertEquals(listOf(1), rows[0].widgets.map { it.appWidgetId })
        assertEquals(listOf(2), rows[1].widgets.map { it.appWidgetId })
        assertEquals(listOf(3), rows[2].widgets.map { it.appWidgetId })
    }

    @Test
    fun `four single column widgets fill exactly one row`() {
        val rows = WidgetFlow.rows((1..4).map { widget(it, 1) })

        assertEquals(1, rows.size)
        assertEquals(4, rows[0].widgets.size)
        assertEquals(0, rows[0].freeSpan)
    }

    /** 壊れた保存値が来ても行が消えたり無限に伸びたりしない */
    @Test
    fun `spans outside the grid are clamped instead of dropping the widget`() {
        val rows = WidgetFlow.rows(listOf(widget(1, 0), widget(2, 99)))

        assertEquals(2, rows.size)
        assertEquals(3, rows[0].freeSpan)
        assertEquals(0, rows[1].freeSpan)
    }

    @Test
    fun `no widgets means no rows`() {
        assertEquals(emptyList<WidgetFlow.Row>(), WidgetFlow.rows(emptyList()))
    }

    /**
     * minWidth は「70dp × セル数 − 30dp」で申告される。30 を足し戻さないと
     * 2×1 のウィジェットが 1 列と判定されて潰れる。
     */
    @Test
    fun `natural span follows the declared cell width`() {
        assertEquals(1, WidgetPlacement.spanForWidthDp(40))
        assertEquals(2, WidgetPlacement.spanForWidthDp(110))
        assertEquals(3, WidgetPlacement.spanForWidthDp(180))
        assertEquals(4, WidgetPlacement.spanForWidthDp(250))
    }

    @Test
    fun `natural span rounds up and stays inside the grid`() {
        assertEquals(2, WidgetPlacement.spanForWidthDp(100))
        assertEquals(4, WidgetPlacement.spanForWidthDp(600))
        assertEquals(4, WidgetPlacement.spanForWidthDp(0))
    }

    /** 幅を持たない既存の保存データは、今までどおり全幅で読み込まれる */
    @Test
    fun `a placement without a stored span defaults to full width`() {
        assertEquals(WidgetPlacement.COLUMNS, WidgetPlacement(appWidgetId = 7, heightDp = 120).widthSpan)
    }
}
