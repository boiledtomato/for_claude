package com.example.zlauncher.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 通信ログをどのカテゴリーに載せるか。
 *
 * **実機で出た不具合がここにある。** `Miscellaneous / Unknown`（未分類を集める枠）を
 * 有効にしていても、Insights のログは `Uncategorized` という別名で記録していた。
 * あの枠の所属は保存されず導出なので、[AppCategory.packages] から作った対応表には
 * 1 件も載らず、全部こぼれていたため ― エラーは出ず、ログの名前を読んで初めて気付く
 * 壊れ方なので、規則として固定しておく。
 */
class InsightCategoriesTest {

    private val mail = AppCategory(id = "mail", name = "Mail", colorIndex = 2, packages = listOf("com.mail"))
    private val chat = AppCategory(id = "chat", name = "Chat", colorIndex = 5, packages = listOf("com.chat"))
    private val catchAll = AppCategory(
        id = "misc",
        name = CategoryRules.CATCH_ALL_NAME,
        colorIndex = 7,
        isCatchAll = true,
    )

    @Test
    fun `with the catch-all on, loose traffic lands in it, not in Uncategorized`() {
        val fallback = InsightCategories.fallback(listOf(mail, chat, catchAll))

        assertEquals("misc", fallback.id)
        assertEquals("Miscellaneous / Unknown", fallback.name)
        // レールと同じ色で出る。無彩色のままだと、あの枠だけ別物に見える
        assertEquals(7, fallback.colorIndex)
        assertFalse("Uncategorized が残っている", fallback.id == UNCATEGORIZED_ID)
        assertFalse(fallback.name == UNCATEGORIZED_NAME)
    }

    @Test
    fun `a renamed catch-all keeps its own name in the log`() {
        // 利用者が改名できる枠なので、定数ではなく保存されている名前を使う
        val renamed = catchAll.copy(name = "Everything else")
        assertEquals("Everything else", InsightCategories.fallback(listOf(mail, renamed)).name)
    }

    @Test
    fun `without the catch-all there is still somewhere to put loose traffic`() {
        // 捨てると「分類していないアプリが通信の大半」という事実が見えなくなる
        val fallback = InsightCategories.fallback(listOf(mail, chat))
        assertEquals(UNCATEGORIZED_ID, fallback.id)
        assertEquals(UNCATEGORIZED_NAME, fallback.name)
        assertEquals(NO_COLOR_INDEX, fallback.colorIndex)
        assertEquals(InsightCategories.UNCATEGORIZED, fallback)
    }

    @Test
    fun `the catch-all never claims a package explicitly`() {
        // 所属は導出。古い保存データが packages を持っていても、ここでは無視する
        val stale = catchAll.copy(packages = listOf("com.mail", "com.loose"))
        val owners = InsightCategories.explicitOwners(listOf(mail, chat, stale))

        assertEquals("mail", owners["com.mail"]?.id)
        assertNull("導出の枠が明示的に抱えてしまっている", owners["com.loose"])
        assertTrue(owners.values.none { it.id == "misc" })
    }

    @Test
    fun `an app in two categories is counted once, in the first`() {
        // 両方に計上するとバイト数が二重になる
        val both = chat.copy(packages = listOf("com.mail", "com.chat"))
        val owners = InsightCategories.explicitOwners(listOf(mail, both))
        assertEquals("mail", owners["com.mail"]?.id)
        assertEquals("chat", owners["com.chat"]?.id)
    }

    @Test
    fun `the fallback bucket sorts last, however it is named`() {
        // 置き場は普通いちばん太い。先頭に来ると自分で作ったカテゴリーが下へ押し出される
        val defined = listOf(mail, chat, catchAll)
        val sorted = InsightCategories.sort(
            listOf(
                rollup("misc", 9_000),
                rollup("chat", 300),
                rollup("mail", 7_000),
            ),
            defined,
            fallbackId = "misc",
        )
        assertEquals(listOf("mail", "chat", "misc"), sorted.map { it.id })
    }

    @Test
    fun `the pseudo bucket also sorts last when the catch-all is off`() {
        val sorted = InsightCategories.sort(
            listOf(rollup(UNCATEGORIZED_ID, 9_000), rollup("mail", 10)),
            listOf(mail, chat),
            fallbackId = UNCATEGORIZED_ID,
        )
        assertEquals(listOf("mail", UNCATEGORIZED_ID), sorted.map { it.id })
    }

    @Test
    fun `categories with no traffic keep the order they have on the rail`() {
        val sorted = InsightCategories.sort(
            listOf(rollup("chat", 0), rollup("mail", 0)),
            listOf(mail, chat),
            fallbackId = "misc",
        )
        assertEquals(listOf("mail", "chat"), sorted.map { it.id })
    }

    private fun rollup(id: String, bytes: Long) = CategoryRollup(
        id = id,
        name = id,
        colorIndex = 0,
        rxBytes = bytes,
        txBytes = 0,
        wifiBytes = bytes,
        mobileBytes = 0,
        appCount = 1,
        series = listOf(bytes),
    )
}
