package com.example.zlauncher.domain.model.game

import com.example.zlauncher.domain.model.AppCategory
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ゲームの決まりごと。画面からは「なんとなく育つ」ようにしか見えないので、
 * 経験値・進化・HP・連続日数・所属の規則をここで固定する。
 */
class GameRulesTest {

    private val day = 20_000L
    private val noon = day * 86_400_000L + 43_200_000L

    private fun launch(
        state: GameState,
        pkg: String = "app.a",
        ids: List<String> = listOf("cat"),
        millis: Long = noon,
        today: Long = day,
    ) = GameRules.onLaunch(state, pkg, ids, millis, today)

    // ---- レベル -------------------------------------------------------------

    @Test
    fun `level n needs n cubed exp`() {
        assertEquals(1, GameRules.levelFor(0))
        assertEquals(1, GameRules.levelFor(7))
        assertEquals(2, GameRules.levelFor(8))
        assertEquals(15, GameRules.levelFor(16L * 16 * 16 - 1))
        assertEquals(16, GameRules.levelFor(16L * 16 * 16))
        assertEquals(GameRules.MAX_LEVEL, GameRules.levelFor(Long.MAX_VALUE / 2))
    }

    @Test
    fun `progress and exp to next agree at the boundaries`() {
        assertEquals(0f, GameRules.progress(GameRules.expForLevel(10)), 0f)
        assertEquals(GameRules.expForLevel(11) - GameRules.expForLevel(10), GameRules.expToNext(GameRules.expForLevel(10)))
        assertEquals(1f, GameRules.progress(GameRules.expForLevel(GameRules.MAX_LEVEL)), 0f)
        assertEquals(0L, GameRules.expToNext(GameRules.expForLevel(GameRules.MAX_LEVEL)))
    }

    @Test
    fun `stages change at the evolve levels`() {
        assertEquals(0, GameRules.stageFor(15))
        assertEquals(1, GameRules.stageFor(16))
        assertEquals(1, GameRules.stageFor(35))
        assertEquals(2, GameRules.stageFor(36))
    }

    // ---- 起動 ---------------------------------------------------------------

    @Test
    fun `first launch meets the companion and feeds trainer and companion alike`() {
        val after = launch(GameState())
        val record = after.monsters.getValue("cat")
        assertEquals(after.trainerExp, record.exp)
        assertEquals(day, record.metOnDay)
        assertEquals(1L, after.launches)
        assertTrue(after.messages.first().startsWith("A wild "))
        assertTrue(after.messages.any { it.contains("First Step") })
    }

    @Test
    fun `an app in no category only feeds the trainer`() {
        val after = launch(GameState(), ids = emptyList())
        assertTrue(after.monsters.isEmpty())
        assertTrue(after.trainerExp > 0)
    }

    @Test
    fun `reopening the same app within a minute earns almost nothing`() {
        val first = launch(GameState())
        val again = launch(first, millis = noon + 10_000)
        assertEquals(GameRules.REPEAT_EXP, again.trainerExp - first.trainerExp)
        val later = launch(first, millis = noon + GameRules.REPEAT_WINDOW_MS)
        assertEquals(GameRules.BASE_EXP, later.trainerExp - first.trainerExp)
        val other = launch(first, pkg = "app.b", millis = noon + 10_000)
        assertEquals(GameRules.BASE_EXP, other.trainerExp - first.trainerExp)
    }

    @Test
    fun `the first launch of a day carries the streak bonus, capped`() {
        val state = GameState(lastPlayDay = day - 1, streakDays = 3)
        val after = launch(state)
        assertEquals(4, after.streakDays)
        assertEquals(GameRules.BASE_EXP + GameRules.STREAK_BONUS_PER_DAY * 4, after.trainerExp)

        val long = launch(GameState(lastPlayDay = day - 1, streakDays = 30))
        assertEquals(
            GameRules.BASE_EXP + GameRules.STREAK_BONUS_PER_DAY * GameRules.MAX_STREAK_BONUS_DAYS,
            long.trainerExp,
        )
    }

    @Test
    fun `streak continues, holds, or restarts`() {
        assertEquals(1, GameRules.nextStreak(-1, day, 0))
        assertEquals(5, GameRules.nextStreak(day, day, 5))
        assertEquals(6, GameRules.nextStreak(day - 1, day, 5))
        assertEquals(1, GameRules.nextStreak(day - 2, day, 5))
        // 時計が戻っても切らない
        assertEquals(5, GameRules.nextStreak(day + 1, day, 5))
    }

    @Test
    fun `crossing an evolve level evolves and says so with both names`() {
        val nearly = GameState(
            monsters = mapOf("cat" to MonsterRecord(exp = GameRules.expForLevel(16) - 1, lastFedDay = day)),
            lastPlayDay = day,
            streakDays = 1,
        )
        val after = launch(nearly)
        assertEquals(1, after.evolutions)
        val from = MonsterSpecies.name("cat", 0)
        val to = MonsterSpecies.name("cat", 1)
        assertTrue(after.messages.contains("$from grew to Lv. 16!"))
        assertTrue(after.messages.contains("Congratulations! $from evolved into $to!"))
        assertTrue(after.messages.any { it.contains("Evolver") })
    }

    @Test
    fun `the seventh companion goes to the box`() {
        val six = (1..6).associate { "c$it" to MonsterRecord(lastFedDay = day) }
        val after = launch(GameState(monsters = six, lastPlayDay = day), ids = listOf("c7"))
        assertTrue(after.messages.first().endsWith("was sent to the BOX."))
    }

    @Test
    fun `messages are capped at the newest`() {
        val full = GameState(messages = List(GameRules.MAX_MESSAGES) { "old $it" })
        val after = launch(full)
        assertEquals(GameRules.MAX_MESSAGES, after.messages.size)
        assertFalse(after.messages.contains("old 0"))
    }

    @Test
    fun `a badge is awarded once`() {
        val first = launch(GameState())
        val second = launch(first, pkg = "app.b")
        assertEquals(1, second.badges.count { it == "first_step" })
        assertFalse(second.messages.drop(first.messages.size).any { it.contains("First Step") })
    }

    // ---- HP -----------------------------------------------------------------

    @Test
    fun `hp drains over a week and a launch revives`() {
        val record = MonsterRecord(lastFedDay = day)
        assertEquals(1f, GameRules.hp(record, day), 0f)
        assertEquals(1f - 3f / GameRules.HP_DAYS, GameRules.hp(record, day + 3), 1e-6f)
        assertTrue(GameRules.fainted(record, day + GameRules.HP_DAYS))
        assertEquals(1f, GameRules.hp(record, day - 5), 0f)

        val state = GameState(monsters = mapOf("cat" to record), lastPlayDay = day)
        val later = day + GameRules.HP_DAYS + 2
        val after = launch(state, today = later, millis = noon + 9 * 86_400_000L)
        assertFalse(GameRules.fainted(after.monsters.getValue("cat"), later))
        assertTrue(after.messages.first().endsWith("is back on its feet!"))
    }

    // ---- 所属 ---------------------------------------------------------------

    @Test
    fun `membership follows the stored lists, and the catch-all takes the rest`() {
        val categories = listOf(
            AppCategory(id = "a", name = "A", packages = listOf("x", "y")),
            AppCategory(id = "b", name = "B", packages = listOf("y")),
            AppCategory(id = "misc", name = "Misc", isCatchAll = true),
        )
        assertEquals(listOf("a", "b"), GameRules.categoriesOf(categories, "y"))
        assertEquals(listOf("misc"), GameRules.categoriesOf(categories, "z"))
        assertEquals(emptyList<String>(), GameRules.categoriesOf(categories.take(2), "z"))
    }

    @Test
    fun `the party is the first six met companions in rail order`() {
        val ids = (1..9).map { "c$it" }
        val met = ids.filter { it != "c2" }.associateWith { MonsterRecord() }
        val (party, box) = GameRules.partition(ids, GameState(monsters = met))
        assertEquals(listOf("c1", "c3", "c4", "c5", "c6", "c7"), party)
        assertEquals(listOf("c8", "c9", "c2"), box)
    }
}
