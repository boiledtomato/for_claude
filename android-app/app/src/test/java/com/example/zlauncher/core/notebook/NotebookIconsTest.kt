package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 題からどの絵を出すか。**順番が意味を持つ**ので、重なる題で固定する。
 *
 * 題はパイプラインが付けたもの（`Zscaler_community` など）で、こちらの都合では変えられない。
 * `Zscaler_community` は「zscaler」にも「community」にも当たるため、広いほうを先に見ると
 * フォーラムのノートブックに公式ドキュメントの盾が付く ― 間違いだが、エラーにはならない。
 */
class NotebookIconsTest {

    @Test
    fun `the notebooks the pipeline actually publishes get the right mark`() {
        assertEquals(NotebookKind.LEARN, NotebookIcons.kindFor("MSLearn_entra"))
        assertEquals(NotebookKind.SHIELD, NotebookIcons.kindFor("Zscaler_help_docs"))
        assertEquals(NotebookKind.COMMUNITY, NotebookIcons.kindFor("Zscaler_community"))
        assertEquals(NotebookKind.UPDATES, NotebookIcons.kindFor("Zscaler_updates"))
    }

    @Test
    fun `the narrower match wins over the broader one`() {
        // どちらも "zscaler" を含む。先に広いほうを見ると両方とも盾になる
        assertEquals(NotebookKind.COMMUNITY, NotebookIcons.kindFor("Zscaler_community"))
        assertEquals(NotebookKind.UPDATES, NotebookIcons.kindFor("Zscaler_release_notes"))
    }

    @Test
    fun `matching ignores case`() {
        assertEquals(NotebookKind.LEARN, NotebookIcons.kindFor("MSLEARN_ENTRA"))
        assertEquals(NotebookKind.SHIELD, NotebookIcons.kindFor("zscaler_help_docs"))
    }

    @Test
    fun `an unknown notebook gets the plain mark, never a wrong one`() {
        // 増えたときに全部 GENERIC に落ちるのは許容。間違った絵を出すより良い
        assertEquals(NotebookKind.GENERIC, NotebookIcons.kindFor("My_research"))
        assertEquals(NotebookKind.GENERIC, NotebookIcons.kindFor(""))
    }

    @Test
    fun `the product notebooks are recognised by product name too`() {
        assertEquals(NotebookKind.SHIELD, NotebookIcons.kindFor("ZIA_notes"))
        assertEquals(NotebookKind.SHIELD, NotebookIcons.kindFor("zpa"))
    }

    @Test
    fun `the defaults are the ones that need no setup`() {
        val style = NotebookWidgetStyle()
        // 既定で罫線もアイコンも出す。出ないほうを既定にすると、機能に気付かれない
        assertEquals(NotebookDivider.SOLID, style.divider)
        assertEquals(true, style.showIcons)
        assertEquals(NotebookTextColor.DEFAULT, style.textColor)
        assertEquals(NotebookFont.SANS, style.font)
    }

    @Test
    fun `every font names a family the system always has`() {
        // 同梱フォントは RemoteViews へ確実に渡せない。総称名だけに絞ってある
        val generic = setOf("sans-serif", "sans-serif-condensed", "serif", "monospace")
        NotebookFont.entries.forEach { font ->
            assertEquals("${font.name} は総称名ではない", true, font.family in generic)
        }
    }
}
