package com.example.zlauncher.data.catalog

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * 同梱している実物の CSV でパーサーを確かめる。
 *
 * 作り物のサンプルではなく実ファイルを読むのは、この CSV の壊れ方が独特なため
 * （BOM、列名末尾の空白、説明文中のカンマ、Class 列に注記文だけを入れた行）。
 * 取り込みが静かに空になったり ATP が紛れ込んだりすると、カテゴリー機能ごと壊れる。
 */
class UrlCategoryCsvTest {

    private val csv: String by lazy {
        // 単体テストの作業ディレクトリはモジュール直下（app/）
        File("src/main/assets/zscaler_url_categories.csv").readText()
    }

    @Test
    fun `parses the bundled catalog`() {
        val entries = UrlCategoryCsv.parse(csv)
        assertTrue("expected a substantial catalog, got ${entries.size}", entries.size > 100)
        assertTrue(entries.all { it.superCategory.isNotBlank() })
        assertTrue(entries.all { it.category.isNotBlank() })
    }

    @Test
    fun `drops ATP categories`() {
        val entries = UrlCategoryCsv.parse(csv)
        assertFalse(
            "Advanced Security is ATP-only and must not be offered as a launcher category",
            entries.any { it.superCategory.equals("Advanced Security", ignoreCase = true) },
        )
        assertFalse(entries.any { it.className.equals("Advanced Security Risk", ignoreCase = true) })
        // 落としているのは 13 件だけで、他は残っていること
        assertTrue(entries.size > 100)
    }

    @Test
    fun `drops the note rows that carry no category`() {
        val entries = UrlCategoryCsv.parse(csv)
        assertFalse(
            entries.any { it.superCategory.startsWith("Note:") || it.className.startsWith("Note:") },
        )
    }

    @Test
    fun `keeps commas inside quoted descriptions`() {
        val entries = UrlCategoryCsv.parse(csv)
        val entertainment = entries.first { it.category == "Entertainment" }
        assertEquals("Entertainment/Recreation", entertainment.superCategory)
        assertTrue(
            "quoted field was split on its commas: ${entertainment.description}",
            entertainment.description.contains(","),
        )
        assertTrue(entertainment.description.endsWith("."))
    }

    @Test
    fun `groups sub-categories under their super-category`() {
        val entries = UrlCategoryCsv.parse(csv)
        val groups = entries.groupBy { it.superCategory }
        assertTrue("expected many super-categories, got ${groups.size}", groups.size > 20)
        // 大項目 1 つに小項目が複数ぶら下がる構造になっていること
        assertTrue(groups.values.any { it.size > 5 })
        assertTrue(groups.keys.contains("Information Technology"))
    }

    @Test
    fun `keys are unique so they can identify a category`() {
        val keys = UrlCategoryCsv.parse(csv).map { it.key }
        assertEquals(keys.size, keys.toSet().size)
    }

    @Test
    fun `handles escaped quotes and CRLF`() {
        val sample = "\uFEFFClass,Super-Category,Category ,Description,Test URL\r\n" +
            "Business Use,IT,Web Hosting,\"Sites that say \"\"hello\"\", and more\",http://x\r\n" +
            "Note: something entirely different.,,,,\r\n"
        val entries = UrlCategoryCsv.parse(sample)
        assertEquals(1, entries.size)
        assertEquals("Web Hosting", entries[0].category)
        assertEquals("Sites that say \"hello\", and more", entries[0].description)
    }

    @Test
    fun `returns empty for content that is not the expected csv`() {
        assertTrue(UrlCategoryCsv.parse("<html><body>404</body></html>").isEmpty())
        assertTrue(UrlCategoryCsv.parse("").isEmpty())
    }
}
