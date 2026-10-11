package com.example.zlauncher.ui.console

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * レールのどの行の上で指を離したか。**外すと消える種類の操作**の入口なので、
 * 隣の行を拾ったり、持ってきた枠自身を拾ったりしないことを固定しておく。
 *
 * 座標はウィンドウ基準（レール幅 84dp ＝ 84px として、行を縦に並べたもの）。
 */
class DropTargetsTest {

    private val rail = mapOf(
        "work" to Rect(0f, 100f, 84f, 180f),
        "social" to Rect(0f, 180f, 84f, 260f),
        "news" to Rect(0f, 260f, 84f, 340f),
    )

    @Test
    fun `a point inside a row picks that row`() {
        assertEquals("social", DropTargets.hit(rail, Offset(40f, 200f), exclude = "work"))
    }

    @Test
    fun `the category the app came from is never a target`() {
        // 自分の上に落としても何も起きない。確認だけ出て中身が変わらないのが一番困る
        assertNull(DropTargets.hit(rail, Offset(40f, 140f), exclude = "work"))
    }

    @Test
    fun `dropping right of the rail hits nothing`() {
        assertNull(DropTargets.hit(rail, Offset(300f, 200f), exclude = "work"))
    }

    @Test
    fun `dropping above the first row hits nothing`() {
        assertNull(DropTargets.hit(rail, Offset(40f, 40f), exclude = null))
    }

    @Test
    fun `the boundary belongs to the lower row`() {
        // Rect.contains は上端を含み下端を含まない。隣り合う行で二重に当たらない
        assertEquals("social", DropTargets.hit(rail, Offset(40f, 180f), exclude = null))
    }

    @Test
    fun `no targets registered means no drop`() {
        assertNull(DropTargets.hit(emptyMap(), Offset(40f, 200f), exclude = null))
    }
}
