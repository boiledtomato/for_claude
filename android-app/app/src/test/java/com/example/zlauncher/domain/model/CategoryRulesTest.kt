package com.example.zlauncher.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * カテゴリーの決まりごと。**どちらの壊れ方も画面では気付きにくい。**
 *
 * 同名のカテゴリーはレールに見分けのつかない行が 2 つ並ぶだけ、未分類の算出が崩れても
 * 「同じアプリが 2 か所に出る」だけで、エラーにはならない。規則として固定しておく。
 */
class CategoryRulesTest {

    private fun category(
        id: String,
        name: String,
        packages: List<String> = emptyList(),
        isCatchAll: Boolean = false,
    ) = AppCategory(id = id, name = name, packages = packages, isCatchAll = isCatchAll)

    // ---- 名前の重複 ---------------------------------------------------------

    @Test
    fun `the same name is refused whatever the case or spacing`() {
        val existing = listOf(category("1", "Work"))
        assertTrue(CategoryRules.nameTaken(existing, "Work"))
        assertTrue(CategoryRules.nameTaken(existing, "work"))
        assertTrue(CategoryRules.nameTaken(existing, "  WORK  "))
    }

    @Test
    fun `a different name is allowed`() {
        assertFalse(CategoryRules.nameTaken(listOf(category("1", "Work")), "Home"))
    }

    @Test
    fun `renaming does not collide with itself`() {
        val existing = listOf(category("1", "Work"), category("2", "Home"))
        // 色だけ変えて保存するときも同じ名前で通る必要がある
        assertFalse(CategoryRules.nameTaken(existing, "Work", exceptId = "1"))
        // ただし他人の名前は取れない
        assertTrue(CategoryRules.nameTaken(existing, "Home", exceptId = "1"))
    }

    @Test
    fun `an empty name is not a collision`() {
        // 空は「未入力」であって重複ではない。保存自体は別のところで止める
        assertFalse(CategoryRules.nameTaken(listOf(category("1", "Work")), "   "))
    }

    // ---- 未分類の算出 -------------------------------------------------------

    @Test
    fun `an app in no other category lands in the catch-all`() {
        val categories = listOf(
            category("1", "Work", packages = listOf("com.a")),
            category("2", CategoryRules.CATCH_ALL_NAME, isCatchAll = true),
        )
        val loose = CategoryRules.catchAllPackages(categories, listOf("com.a", "com.b", "com.c"))
        assertEquals(listOf("com.b", "com.c"), loose)
    }

    @Test
    fun `an app put in another category leaves the catch-all by itself`() {
        val installed = listOf("com.a", "com.b")
        val before = listOf(
            category("1", "Work"),
            category("2", CategoryRules.CATCH_ALL_NAME, isCatchAll = true),
        )
        assertEquals(listOf("com.a", "com.b"), CategoryRules.catchAllPackages(before, installed))

        // com.a を Work に入れただけ。未分類側には何も書いていない
        val after = listOf(
            category("1", "Work", packages = listOf("com.a")),
            category("2", CategoryRules.CATCH_ALL_NAME, isCatchAll = true),
        )
        assertEquals(listOf("com.b"), CategoryRules.catchAllPackages(after, installed))
    }

    @Test
    fun `the catch-all does not claim apps against itself`() {
        // 枠自身に packages が残っていても数に入れない（導出が唯一の正）
        val categories = listOf(
            category("2", CategoryRules.CATCH_ALL_NAME, packages = listOf("com.a"), isCatchAll = true),
        )
        assertEquals(listOf("com.a", "com.b"), CategoryRules.catchAllPackages(categories, listOf("com.a", "com.b")))
    }

    @Test
    fun `an app in several categories is claimed once`() {
        val categories = listOf(
            category("1", "Work", packages = listOf("com.a")),
            category("2", "Home", packages = listOf("com.a")),
            category("3", CategoryRules.CATCH_ALL_NAME, isCatchAll = true),
        )
        assertEquals(listOf("com.b"), CategoryRules.catchAllPackages(categories, listOf("com.a", "com.b")))
    }

    @Test
    fun `only the first catch-all counts`() {
        val categories = listOf(category("1", "A", isCatchAll = true), category("2", "B", isCatchAll = true))
        assertEquals("1", CategoryRules.catchAll(categories)?.id)
        assertEquals(null, CategoryRules.catchAll(listOf(category("1", "A"))))
    }
}
