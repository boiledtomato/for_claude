package com.example.zlauncher.data.catalog

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * 記事応答から CSV リンクを拾えることを固定する。
 *
 * 実機で「The help article no longer links a CSV」が出ていた原因がここ。
 * ヘルプ API は本文を JSON 文字列として返すので、リンクは `\/downloads\/…`、
 * 引用符は `"` の形で届く。素の `/downloads/` を探すと**必ず外れる**。
 * 下の入力は実際の応答から切り出した並びそのままにしてある。
 */
class CatalogArticleTest {

    @Test
    fun `finds the csv link in the escaped json body`() {
        val body = """{\u0022content\u0022:\u0022<a class=\u0022download\u0022 href=\u0022\/downloads\/zia\/""" +
            """documentation-knowledgebase\/policies\/url-filtering\/about-url-categories\/""" +
            """Zscaler-Test-URL-Categories-05-21-2026.csv\u0022>CSV<\/a>\u0022}"""
        assertEquals(
            "/downloads/zia/documentation-knowledgebase/policies/url-filtering/" +
                "about-url-categories/Zscaler-Test-URL-Categories-05-21-2026.csv",
            CatalogArticle.csvPath(body),
        )
    }

    @Test
    fun `revision is the file name, so it changes with the csv`() {
        val body = """href=\u0022\/downloads\/x\/Zscaler-Test-URL-Categories-09-01-2027.csv\u0022"""
        assertEquals(
            "Zscaler-Test-URL-Categories-09-01-2027.csv",
            CatalogArticle.csvPath(body)?.substringAfterLast('/'),
        )
    }

    @Test
    fun `still finds a plain unescaped link`() {
        val body = """<a href="/downloads/zia/about-url-categories/Categories.csv">CSV</a>"""
        assertEquals("/downloads/zia/about-url-categories/Categories.csv", CatalogArticle.csvPath(body))
    }

    @Test
    fun `stops at the closing quote and does not swallow markup`() {
        val body = """href=\u0022\/downloads\/a\/b.csv\u0022 title=\u0022c.csv\u0022"""
        assertEquals("/downloads/a/b.csv", CatalogArticle.csvPath(body))
    }

    @Test
    fun `returns null when the article carries no csv`() {
        assertNull(CatalogArticle.csvPath("""{"content":"<p>No downloads here.<\/p>"}"""))
        assertNull(CatalogArticle.csvPath(""))
        // .csv 以外の添付は拾わない
        assertNull(CatalogArticle.csvPath("""href=\u0022\/downloads\/a\/b.pdf\u0022"""))
    }
}
