package com.example.zlauncher.core.ui

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 狭い窓で列を減らす計算。**全画面の見た目を 1 枚も変えないこと**が同じくらい大事なので、
 * 実在する端末幅と、マルチウィンドウで実際に出る窓幅の両方を並べて固定する。
 *
 * 面の幅は: カテゴリー面 = 窓幅 − 84dp（レール） − 20dp（余白）、
 * ドロワー = 窓幅 − 20dp。
 */
class TileGridTest {

    private fun categoryColumns(windowDp: Float) =
        TileGrid.columns(windowDp - 84f - 20f)

    private fun drawerColumns(windowDp: Float) =
        TileGrid.columns(windowDp - 20f)

    @Test
    fun `real phone widths keep the four columns they have today`() {
        // ここが崩れると、分割画面のために全画面の並びを変えたことになる。
        // 360dp は 1 列 62.5dp ― 56dp のアイコンがぎりぎり収まる側に居る
        listOf(360f, 392f, 411f, 430f, 480f).forEach { width ->
            assertEquals("窓幅 $width dp のカテゴリー面", 4, categoryColumns(width))
            assertEquals("窓幅 $width dp のドロワー", 4, drawerColumns(width))
        }
    }

    @Test
    fun `a narrow window drops columns instead of squeezing icons`() {
        // 4 列固定だと窓幅 240dp で 1 列 32.5dp。56dp のアイコンが重なる
        assertEquals(2, categoryColumns(240f))
        assertEquals(3, categoryColumns(320f))
        assertEquals(3, drawerColumns(240f))
    }

    @Test
    fun `columns never reach zero`() {
        // フリーフォームの窓は端まで細くできる。0 列でグリッドを作ると落ちる
        assertEquals(1, categoryColumns(180f))
        assertEquals(1, TileGrid.columns(0f))
        assertEquals(1, TileGrid.columns(-50f))
        assertEquals(1, TileGrid.columns(100f, minCellDp = 0f))
    }

    @Test
    fun `a wide window does not add columns`() {
        // 上限が無いと、タブレットや DeX の広い窓で 6 列・8 列に増えてしまう。
        // 増やすかどうかは別の判断なので、ここでは今の見た目を保つ
        assertEquals(TileGrid.MAX_COLUMNS, drawerColumns(1280f))
        assertEquals(TileGrid.MAX_COLUMNS, categoryColumns(1280f))
    }

    @Test
    fun `the gap between columns is counted`() {
        // 隙間を無視すると 1 列ぶん多く数えて、最後の列がはみ出す
        assertEquals(2, TileGrid.columns(122f, gapDp = 2f, minCellDp = 60f))
        assertEquals(1, TileGrid.columns(119f, gapDp = 2f, minCellDp = 60f))
    }
}
