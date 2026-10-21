package com.example.zlauncher.domain.model.game

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 相棒の名前と姿は保存せず毎回導く。**同じ種から同じものが出ること**が崩れると、
 * 開くたびに別の生き物になる。
 */
class MonsterSpeciesTest {

    private val seeds = (0 until 200).map { "category-$it" }

    @Test
    fun `names are stable and keep their first syllable through evolution`() {
        seeds.forEach { seed ->
            val base = MonsterSpecies.name(seed, 0)
            assertEquals(base, MonsterSpecies.name(seed, 0))
            (1..2).forEach { stage ->
                val evolved = MonsterSpecies.name(seed, stage)
                assertNotEquals(base, evolved)
                assertEquals("$evolved should start like $base", base.take(2), evolved.take(2))
            }
        }
    }

    @Test
    fun `type comes from the category name, else from the seed`() {
        assertEquals("CHAT", MonsterSpecies.type("x", "Social Networking"))
        assertEquals("COIN", MonsterSpecies.type("x", "Finance"))
        // 長い語は語中でも当てる（Webmail）。"ai" のような短い語だけは語の頭に限る
        assertEquals("CHAT", MonsterSpecies.type("x", "Webmail"))
        assertEquals("VITAL", MonsterSpecies.type("x", "Sports Training"))
        assertEquals("LORE", MonsterSpecies.type("x", "AI & ML Applications"))
        assertTrue(MonsterSpecies.type("seed", "Qwerty") in MonsterSpecies.TYPES)
        assertEquals(MonsterSpecies.type("seed", "Qwerty"), MonsterSpecies.type("seed", "Qwerty"))
    }

    @Test
    fun `sprites are deterministic, mirrored, and grow with each stage`() {
        seeds.forEach { seed ->
            (0..2).forEach { stage ->
                val grid = MonsterSprite.pixels(seed, stage)
                val size = MonsterSprite.sizeFor(stage)
                assertEquals(size, grid.size)
                grid.forEach { row ->
                    assertEquals(size, row.size)
                    for (c in 0 until size) assertEquals(row[c], row[size - 1 - c])
                }
                val again = MonsterSprite.pixels(seed, stage)
                grid.indices.forEach { assertTrue(grid[it].contentEquals(again[it])) }
            }
        }
        assertTrue(MonsterSprite.sizeFor(0) < MonsterSprite.sizeFor(1))
        assertTrue(MonsterSprite.sizeFor(1) < MonsterSprite.sizeFor(2))
    }

    @Test
    fun `a sprite is one outlined body that never touches the edge`() {
        seeds.forEach { seed ->
            val grid = MonsterSprite.pixels(seed, 0)
            val n = grid.size
            val cells = grid.sumOf { row -> row.count { it == MonsterSprite.BODY || it == MonsterSprite.ACCENT || it == MonsterSprite.EYE } }
            assertTrue("$seed has a body", cells >= 8)
            assertTrue(grid.any { row -> row.any { it == MonsterSprite.OUTLINE } })
            // 輪郭の外へ体がはみ出さない（端の 1 マスは輪郭か空白だけ）
            for (i in 0 until n) {
                listOf(grid[0][i], grid[n - 1][i], grid[i][0], grid[i][n - 1]).forEach {
                    assertTrue(it == MonsterSprite.EMPTY || it == MonsterSprite.OUTLINE)
                }
            }
        }
    }

    @Test
    fun `different categories mostly look different`() {
        val shapes = seeds.map { seed -> MonsterSprite.pixels(seed, 0).joinToString("|") { it.joinToString("") } }.toSet()
        assertTrue("only ${shapes.size} distinct shapes", shapes.size > seeds.size * 0.9)
    }
}
