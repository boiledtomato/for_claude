package com.example.zlauncher.data.apps

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 自動判別の当たり・外れを固定する。
 *
 * この手の語彙照合は**静かに壊れる**。短い語を部分一致で当てると、たとえば "ad" が
 * "android" に当たって全アプリが広告カテゴリーに吸い込まれる。画面を見ても
 * 「なんとなく多いな」としか思えないので、境界はここで押さえておく。
 */
class AppMatchingTest {

    private fun hay(packageName: String, label: String) = AppMatching.haystack(packageName, label)

    @Test
    fun `short keywords only match a whole segment`() {
        val android = hay("com.android.settings", "Settings")
        // "ad" が "android" に当たってはいけない
        assertFalse(AppMatching.matches("ad", android))
        // 区切りとして独立していれば当たる
        assertTrue(AppMatching.matches("line", hay("jp.naver.line.android", "LINE")))
    }

    @Test
    fun `long keywords may match inside a segment`() {
        assertTrue(AppMatching.matches("spotify", hay("com.spotify.music", "Spotify")))
        assertTrue(AppMatching.matches("youtube", hay("com.google.android.youtube", "YouTube")))
        assertFalse(AppMatching.matches("spotify", hay("com.example.notes", "Notes")))
    }

    @Test
    fun `keyword tables resolve by category name, case-insensitively`() {
        assertTrue(AppMatching.keywordsFor("Music and Audio Streaming").contains("spotify"))
        assertTrue(AppMatching.keywordsFor("  finance  ").contains("bank"))
        // 表に無いカテゴリーは空。存在しない語を当てにいかない
        assertTrue(AppMatching.keywordsFor("Non Categorizable").isEmpty())
    }

    @Test
    fun `android app categories map to zscaler sub-categories`() {
        assertTrue(AppMatching.androidCategoriesFor("Social Networking").isNotEmpty())
        assertTrue(AppMatching.androidCategoriesFor("Online and Other Games").isNotEmpty())
        assertTrue(AppMatching.androidCategoriesFor("Anonymizer").isEmpty())
    }

    @Test
    fun `generic words never become evidence`() {
        // 全部が汎用語の説明文からは、根拠になる語が 1 つも残らないのが正しい。
        // ここで何か残ると、その語が全アプリに当たって提案が総なめになる
        val generic = AppMatching.meaningfulTokens(
            "Sites that provide information on the internet for users and other content"
        )
        assertTrue("generic-only text yielded $generic", generic.isEmpty())

        // 中身のある語は残ること。落としすぎると今度は何も提案できなくなる
        val real = AppMatching.meaningfulTokens(
            "Sites that promote gambling, lotteries and casino games for users"
        )
        assertTrue(real.contains("gambling"))
        assertTrue(real.contains("casino"))
        listOf("the", "and", "for", "sites", "users").forEach {
            assertFalse("'$it' should be filtered", real.contains(it))
        }
    }

    @Test
    fun `two-letter and short noise is dropped from tokens`() {
        val tokens = AppMatching.meaningfulTokens("My TV a b Player 4K")
        assertFalse(tokens.contains("tv"))
        assertFalse(tokens.contains("a"))
        assertTrue(tokens.contains("player"))
    }

    @Test
    fun `well known apps land on the category a person would pick`() {
        val cases = listOf(
            Triple("com.spotify.music", "Spotify", "Music and Audio Streaming"),
            Triple("com.google.android.youtube", "YouTube", "Video Streaming"),
            Triple("com.facebook.katana", "Facebook", "Social Networking"),
            Triple("com.Slack", "Slack", "Online Chat"),
            Triple("us.zoom.videomeetings", "Zoom", "Web Conferencing"),
            Triple("com.google.android.gm", "Gmail", "Webmail"),
            Triple("com.amazon.mShop.android.shopping", "Amazon Shopping", "Online Shopping"),
            Triple("jp.ne.paypay.android.app", "PayPay", "Finance"),
            Triple("com.github.android", "GitHub", "Developer Tools"),
            Triple("com.deepl.mobiletranslator", "DeepL", "Translators"),
            Triple("com.linkedin.android", "LinkedIn", "Job/Employment Search"),
            Triple("com.openai.chatgpt", "ChatGPT", "Generative AI and ML Applications"),
        )
        cases.forEach { (pkg, label, category) ->
            val keywords = AppMatching.keywordsFor(category)
            val haystack = hay(pkg, label)
            assertTrue(
                "$label should match $category",
                keywords.any { AppMatching.matches(it, haystack) },
            )
        }
    }

    @Test
    fun `a plain utility app is not swept into unrelated categories`() {
        val calculator = hay("com.android.calculator2", "Calculator")
        listOf("Social Networking", "Finance", "Video Streaming", "Online Shopping", "Advertising")
            .forEach { category ->
                assertFalse(
                    "Calculator should not match $category",
                    AppMatching.keywordsFor(category).any { AppMatching.matches(it, calculator) },
                )
            }
    }

    @Test
    fun `haystack splits package separators`() {
        assertEquals(
            listOf("jp", "co", "rakuten", "android"),
            AppMatching.haystack("jp.co.rakuten.android", "").take(4),
        )
    }
}
