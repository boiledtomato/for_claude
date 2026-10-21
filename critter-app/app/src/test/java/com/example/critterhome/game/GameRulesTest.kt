package com.example.critterhome.game

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * ゲームの決まりごと。画面からは「なんとなく育つ」ようにしか見えないので、
 * 経験値・進化・HP・連続日数・手持ちの規則をここで固定する。
 */
class GameRulesTest {

    private val day = 20_000L
    private val noon = day * 86_400_000L + 43_200_000L
    private val salted = GameState(salt = "s")

    private fun launch(
        state: GameState = salted,
        pkg: String = "app.a",
        habitat: Habitat = Habitat.ARCADE,
        millis: Long = noon,
        today: Long = day,
    ) = GameRules.onLaunch(state, pkg, "$pkg/key", habitat, millis, today)

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
        val after = launch()
        val record = after.monsters.getValue(Habitat.ARCADE.key)
        assertEquals(after.trainerExp, record.exp)
        assertEquals(day, record.metOnDay)
        assertEquals(1L, after.launches)
        assertTrue(after.messages.first().startsWith("A wild "))
        assertTrue(after.messages.first().contains("Arcade"))
        assertTrue(after.messages.any { it.contains("First Step") })
    }

    @Test
    fun `reopening the same app within a minute earns almost nothing`() {
        val first = launch()
        val again = launch(first, millis = noon + 10_000)
        assertEquals(GameRules.REPEAT_EXP, again.trainerExp - first.trainerExp)
        val later = launch(first, millis = noon + GameRules.REPEAT_WINDOW_MS)
        assertEquals(GameRules.BASE_EXP, later.trainerExp - first.trainerExp)
        val other = launch(first, pkg = "app.b", millis = noon + 10_000)
        assertEquals(GameRules.BASE_EXP, other.trainerExp - first.trainerExp)
    }

    @Test
    fun `the first launch of a day carries the streak bonus, capped`() {
        val after = launch(salted.copy(lastPlayDay = day - 1, streakDays = 3))
        assertEquals(4, after.streakDays)
        assertEquals(GameRules.BASE_EXP + GameRules.STREAK_BONUS_PER_DAY * 4, after.trainerExp)

        val long = launch(salted.copy(lastPlayDay = day - 1, streakDays = 30))
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
        // 画面上は、昨日も今日も遊んでいなければ 0
        assertEquals(0, GameRules.currentStreak(GameState(lastPlayDay = day - 2, streakDays = 5), day))
        assertEquals(5, GameRules.currentStreak(GameState(lastPlayDay = day - 1, streakDays = 5), day))
    }

    @Test
    fun `crossing an evolve level evolves and says so with both names`() {
        val key = Habitat.ARCADE.key
        val nearly = salted.copy(
            monsters = mapOf(key to MonsterRecord(exp = GameRules.expForLevel(16) - 1, lastFedDay = day)),
            lastPlayDay = day,
            streakDays = 1,
        )
        val after = launch(nearly)
        assertEquals(1, after.evolutions)
        val from = MonsterSpecies.name(nearly.seedOf(key), 0)
        val to = MonsterSpecies.name(nearly.seedOf(key), 1)
        assertTrue(after.messages.contains("$from grew to Lv. 16!"))
        assertTrue(after.messages.contains("Congratulations! $from evolved into $to!"))
        assertTrue(after.messages.any { it.contains("Evolver") })
    }

    @Test
    fun `the seventh companion goes to the box`() {
        val six = Habitat.entries.take(6).associate { it.key to MonsterRecord(lastFedDay = day) }
        val after = launch(salted.copy(monsters = six, lastPlayDay = day), habitat = Habitat.entries[6])
        assertTrue(after.messages.first().endsWith("was sent to the BOX."))
    }

    @Test
    fun `messages are capped at the newest`() {
        val after = launch(salted.copy(messages = List(GameRules.MAX_MESSAGES) { "old $it" }))
        assertEquals(GameRules.MAX_MESSAGES, after.messages.size)
        assertFalse(after.messages.contains("old 0"))
    }

    @Test
    fun `a badge is awarded once`() {
        val first = launch()
        val second = launch(first, pkg = "app.b")
        assertEquals(1, second.badges.count { it == "first_step" })
        assertFalse(second.messages.drop(first.messages.size).any { it.contains("First Step") })
    }

    @Test
    fun `explorer needs every habitat`() {
        val allButOne = Habitat.entries.dropLast(1).associate { it.key to MonsterRecord(lastFedDay = day) }
        val after = launch(salted.copy(monsters = allButOne, lastPlayDay = day), habitat = Habitat.entries.last())
        assertTrue("explorer" in after.badges)
        val notYet = launch(salted.copy(monsters = allButOne, lastPlayDay = day), habitat = Habitat.entries.first())
        assertFalse("explorer" in notYet.badges)
    }

    @Test
    fun `recent apps are newest first, unique, and capped`() {
        var state = salted
        (1..GameRules.RECENT_SIZE + 2).forEach { i -> state = launch(state, pkg = "app.$i", millis = noon + i * 100_000L) }
        state = launch(state, pkg = "app.3", millis = noon + 10_000_000L)
        assertEquals(GameRules.RECENT_SIZE, state.recent.size)
        assertEquals("app.3/key", state.recent.first())
        assertEquals(state.recent.size, state.recent.toSet().size)
    }

    // ---- HP -----------------------------------------------------------------

    @Test
    fun `hp drains over a week and a launch revives`() {
        val record = MonsterRecord(lastFedDay = day)
        assertEquals(1f, GameRules.hp(record, day), 0f)
        assertEquals(1f - 3f / GameRules.HP_DAYS, GameRules.hp(record, day + 3), 1e-6f)
        assertTrue(GameRules.fainted(record, day + GameRules.HP_DAYS))
        assertEquals(1f, GameRules.hp(record, day - 5), 0f)

        val state = salted.copy(monsters = mapOf(Habitat.ARCADE.key to record), lastPlayDay = day)
        val later = day + GameRules.HP_DAYS + 2
        val after = launch(state, today = later, millis = noon + 9 * 86_400_000L)
        assertFalse(GameRules.fainted(after.monsters.getValue(Habitat.ARCADE.key), later))
        assertTrue(after.messages.first().endsWith("is back on its feet!"))
    }

    // ---- 手持ち・住みか -----------------------------------------------------

    @Test
    fun `the party is the first six met, in the order they were met`() {
        val met = Habitat.entries.take(8).mapIndexed { i, h -> h.key to MonsterRecord(metOnDay = 100L - i) }.toMap()
        val (party, box) = GameRules.partition(GameState(monsters = met))
        assertEquals(Habitat.entries.take(8).reversed().take(6), party)
        assertEquals(Habitat.entries.take(8).reversed().drop(6) + Habitat.entries.drop(8), box)
    }

    @Test
    fun `moving an app back to its automatic habitat drops the override`() {
        val moved = GameRules.moveApp(GameState(), "app.a", Habitat.LAB, automatic = Habitat.ARCADE)
        assertEquals(mapOf("app.a" to "lab"), moved.habitatOverrides)
        val back = GameRules.moveApp(moved, "app.a", Habitat.ARCADE, automatic = Habitat.ARCADE)
        assertTrue(back.habitatOverrides.isEmpty())
    }

    @Test
    fun `the same habitat is a different companion on another device`() {
        val a = GameState(salt = "device-a").seedOf("arcade")
        val b = GameState(salt = "device-b").seedOf("arcade")
        assertTrue(a != b)
    }
}
