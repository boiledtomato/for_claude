package com.example.zlauncher.core.ui

import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * レールの寸法を窓の幅から決める規則。
 *
 * **畳んだときの見た目を変えないことが、広げることと同じくらい大事。** 利用者からは
 * 「折りたたんでいるときは違和感がない」と報告があったので、そこを動かす理由が無い。
 * 電話機の幅で [RailMetrics.COMPACT] がそのまま出ることを固定しておく。
 */
class RailMetricsTest {

    @Test
    fun `every real phone width keeps the rail exactly as it was`() {
        // 320 は分割画面の狭いほう、430 は大きめの電話機
        listOf(320f, 360f, 392f, 411f, 430f, 480f, 599f).forEach { width ->
            assertSame(
                "窓幅 $width dp で畳んだときの寸法から外れた",
                RailMetrics.COMPACT,
                RailMetrics.forWindowWidth(width),
            )
        }
        assertEquals(84f, RailMetrics.COMPACT.widthDp, 0f)
        assertEquals(10f, RailMetrics.COMPACT.labelSp, 0f)
        assertEquals(48f, RailMetrics.COMPACT.pinIconDp, 0f)
    }

    @Test
    fun `an unfolded foldable gets the wide rail`() {
        // Galaxy Z Fold: 開いて約 830dp（横持ち）/ 約 670dp（縦持ち）
        listOf(600f, 670f, 830f, 1280f).forEach { width ->
            assertSame(
                "窓幅 $width dp で広いレールにならない",
                RailMetrics.EXPANDED,
                RailMetrics.forWindowWidth(width),
            )
        }
    }

    @Test
    fun `the wide rail is twice as wide, as asked`() {
        assertEquals(
            2f,
            RailMetrics.EXPANDED.widthDp / RailMetrics.COMPACT.widthDp,
            0.001f,
        )
    }

    @Test
    fun `text grows, but by less than the width`() {
        // 幅と同じ倍率で伸ばすと、レールの名前がペイン側の見出しより大きくなって
        // 主従が逆になる。上げるが、2 倍にはしない
        val textRatio = RailMetrics.EXPANDED.labelSp / RailMetrics.COMPACT.labelSp
        assertTrue("文字が大きくなっていない: $textRatio", textRatio > 1.2f)
        assertTrue("文字を上げすぎ: $textRatio", textRatio < 1.6f)
    }

    @Test
    fun `nothing in the wide rail is smaller than in the narrow one`() {
        // 1 つでも縮むと、開いたときに一部だけ小さいちぐはぐな見た目になる
        val c = RailMetrics.COMPACT
        val e = RailMetrics.EXPANDED
        listOf(
            "width" to (c.widthDp to e.widthDp),
            "label" to (c.labelSp to e.labelSp),
            "header" to (c.headerSp to e.headerSp),
            "chevron" to (c.chevronSp to e.chevronSp),
            "addGlyph" to (c.addGlyphSp to e.addGlyphSp),
            "themeGlyph" to (c.themeGlyphSp to e.themeGlyphSp),
            "indicator" to (c.indicatorDp to e.indicatorDp),
            "pinIcon" to (c.pinIconDp to e.pinIconDp),
            "itemPadding" to (c.itemPaddingDp to e.itemPaddingDp),
            "labelGap" to (c.labelGapDp to e.labelGapDp),
            "divider" to (c.dividerWidthDp to e.dividerWidthDp),
            "selectedBarHeight" to (c.selectedBarHeightDp to e.selectedBarHeightDp),
            "selectedBarWidth" to (c.selectedBarWidthDp to e.selectedBarWidthDp),
        ).forEach { (name, pair) ->
            val (compact, expanded) = pair
            assertTrue("$name が狭いときより小さい: $compact -> $expanded", expanded > compact)
        }
    }

    @Test
    fun `the divider never reaches the rail edge`() {
        // 区切り線は中央に短く引く印。幅いっぱいにすると枠線に見える
        assertTrue(RailMetrics.COMPACT.dividerWidthDp < RailMetrics.COMPACT.widthDp)
        assertTrue(RailMetrics.EXPANDED.dividerWidthDp < RailMetrics.EXPANDED.widthDp)
    }

    @Test
    fun `the pinned icon still leaves room inside the rail`() {
        // アイコンの左右に 6dp の余白 x2 が要る。はみ出すと切れる
        listOf(RailMetrics.COMPACT, RailMetrics.EXPANDED).forEach { m ->
            assertTrue(
                "アイコン ${m.pinIconDp} が幅 ${m.widthDp} に収まらない",
                m.pinIconDp + 12f <= m.widthDp,
            )
        }
    }

    @Test
    fun `a zero or negative width does not crash into the wide layout`() {
        // 測る前の 1 フレームは 0 で来ることがある。そこで広いレールを出すと
        // 開いた直後に一瞬レイアウトが跳ねる
        assertSame(RailMetrics.COMPACT, RailMetrics.forWindowWidth(0f))
        assertSame(RailMetrics.COMPACT, RailMetrics.forWindowWidth(-1f))
    }
}
