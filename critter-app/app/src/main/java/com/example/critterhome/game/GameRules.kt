package com.example.critterhome.game

/** バッジ 1 つ。条件は [GameState] だけで判定できるものに限る */
data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val earned: (GameState) -> Boolean,
)

/**
 * ゲームの決まりごと。**Android に触れない純粋な計算だけを置く**（`GameRulesTest`）。
 *
 * 遊び方はホームアプリの使い方そのもの:
 * - アプリを開くと、そのアプリの住みかの相棒とトレーナーに経験値が入る
 * - 住みかのアプリを初めて開いたとき、その住みかの相棒と出会う
 * - 相棒は [EVOLVE_LEVELS] で進化する
 * - しばらく開かない住みかの相棒は HP が減り、[HP_DAYS] 日でひんしになる。
 *   その住みかのアプリを 1 回開けば戻る ― 罰ではなく「最近使っていない」の目印
 */
object GameRules {
    const val MAX_LEVEL = 100
    const val BASE_EXP = 20L

    /** 同じアプリを [REPEAT_WINDOW_MS] 以内に開き直したときの経験値。連打で稼げないように */
    const val REPEAT_EXP = 2L
    const val REPEAT_WINDOW_MS = 60_000L

    /** その日最初の起動に付く、連続日数ぶんの上乗せ */
    const val STREAK_BONUS_PER_DAY = 10L
    const val MAX_STREAK_BONUS_DAYS = 7

    const val PARTY_SIZE = 6
    val EVOLVE_LEVELS = listOf(16, 36)
    const val HP_DAYS = 7
    const val MAX_MESSAGES = 20
    const val RECENT_SIZE = 8

    val BADGES: List<Badge> = listOf(
        Badge("first_step", "First Step", "Open an app from Critter Home") { it.launches >= 1 },
        Badge("regular", "Regular", "Open 100 apps") { it.launches >= 100 },
        Badge("veteran", "Veteran", "Open 1,000 apps") { it.launches >= 1_000 },
        Badge("collector", "Collector", "Meet 3 companions") { it.monsters.size >= 3 },
        Badge("full_party", "Full Party", "Meet 6 companions") { it.monsters.size >= PARTY_SIZE },
        Badge("explorer", "Explorer", "Meet a companion in every habitat") { state ->
            Habitat.entries.all { it.key in state.monsters }
        },
        Badge("evolver", "Evolver", "Evolve a companion") { it.evolutions >= 1 },
        Badge("devoted", "Devoted", "Play 7 days in a row") { it.bestStreak >= 7 },
        Badge("champion", "Champion", "Raise a companion to Lv. 50") { state ->
            state.monsters.values.any { levelFor(it.exp) >= 50 }
        },
    )

    // ---- レベル -------------------------------------------------------------

    /** そのレベルに届くのに要る累計経験値。Lv. n は n³ */
    fun expForLevel(level: Int): Long {
        val l = level.coerceIn(1, MAX_LEVEL)
        return if (l == 1) 0L else l.toLong() * l * l
    }

    fun levelFor(exp: Long): Int {
        var level = 1
        while (level < MAX_LEVEL && expForLevel(level + 1) <= exp) level++
        return level
    }

    /** 次のレベルまでの進み具合（0〜1）。上限なら 1 */
    fun progress(exp: Long): Float {
        val level = levelFor(exp)
        if (level >= MAX_LEVEL) return 1f
        val from = expForLevel(level)
        val to = expForLevel(level + 1)
        return ((exp - from).toFloat() / (to - from)).coerceIn(0f, 1f)
    }

    fun expToNext(exp: Long): Long {
        val level = levelFor(exp)
        return if (level >= MAX_LEVEL) 0L else expForLevel(level + 1) - exp
    }

    /** 進化の段階（0 / 1 / 2） */
    fun stageFor(level: Int): Int = EVOLVE_LEVELS.count { level >= it }

    // ---- HP -----------------------------------------------------------------

    /**
     * 最後に開いてからの日数で減る HP（0〜1）。
     * 時計が戻った場合（未来の日付が記録されている）は満タン扱いにする。
     */
    fun hp(record: MonsterRecord, today: Long): Float {
        val idle = (today - record.lastFedDay).coerceAtLeast(0)
        return (1f - idle.toFloat() / HP_DAYS).coerceIn(0f, 1f)
    }

    fun fainted(record: MonsterRecord, today: Long): Boolean = hp(record, today) <= 0f

    // ---- 連続日数 -----------------------------------------------------------

    fun nextStreak(lastPlayDay: Long, today: Long, streak: Int): Int = when {
        lastPlayDay < 0 -> 1
        today <= lastPlayDay -> streak.coerceAtLeast(1)
        today == lastPlayDay + 1 -> streak + 1
        else -> 1
    }

    /** 画面に出す連続日数。昨日も今日も遊んでいなければ、もう途切れている */
    fun currentStreak(state: GameState, today: Long): Int =
        if (state.lastPlayDay >= today - 1) state.streakDays else 0

    // ---- 手持ち -------------------------------------------------------------

    /**
     * 手持ち（最大 [PARTY_SIZE] 体）とボックスに分ける。手持ちは出会った順、同じ日なら
     * 住みかの並び順。まだ出会っていない住みかはボックスの後ろに回る。
     */
    fun partition(state: GameState): Pair<List<Habitat>, List<Habitat>> {
        val met = Habitat.entries
            .filter { it.key in state.monsters }
            .sortedBy { state.monsters.getValue(it.key).metOnDay }
        val unmet = Habitat.entries.filterNot { it.key in state.monsters }
        return met.take(PARTY_SIZE) to (met.drop(PARTY_SIZE) + unmet)
    }

    // ---- 起動 ---------------------------------------------------------------

    /**
     * アプリを 1 つ開いたあとの状態。起きたこと（出会い・レベルアップ・進化・バッジ）は
     * [GameState.messages] の後ろに積む。
     *
     * @param appKey 最近使ったアプリの一覧に積むキー（個人用と仕事用を分けるため packageName とは別）
     * @param habitat そのアプリの住みか
     */
    fun onLaunch(
        state: GameState,
        packageName: String,
        appKey: String,
        habitat: Habitat,
        nowMillis: Long,
        today: Long,
    ): GameState {
        val repeat = packageName == state.lastLaunchPackage &&
            nowMillis - state.lastLaunchMillis in 0 until REPEAT_WINDOW_MS
        val firstToday = state.lastPlayDay != today
        val streak = nextStreak(state.lastPlayDay, today, state.streakDays)
        val gain = when {
            repeat -> REPEAT_EXP
            firstToday -> BASE_EXP + STREAK_BONUS_PER_DAY * streak.coerceAtMost(MAX_STREAK_BONUS_DAYS)
            else -> BASE_EXP
        }

        val messages = mutableListOf<String>()
        val monsters = state.monsters.toMutableMap()
        var evolutions = state.evolutions
        val id = habitat.key
        val seed = state.seedOf(id)

        val before = monsters[id]
        if (before == null) {
            val name = MonsterSpecies.name(seed, 0)
            val whereTo = if (monsters.size < PARTY_SIZE) "joined your party" else "was sent to the BOX"
            messages += "A wild $name appeared in the ${habitat.title}! $name $whereTo."
        } else if (fainted(before, today)) {
            messages += "${MonsterSpecies.name(seed, stageFor(levelFor(before.exp)))} is back on its feet!"
        }
        val old = before ?: MonsterRecord(metOnDay = today, lastFedDay = today)
        val after = old.copy(exp = old.exp + gain, lastFedDay = today, launches = old.launches + 1)
        monsters[id] = after

        val oldLevel = levelFor(old.exp)
        val newLevel = levelFor(after.exp)
        // 出会った最初の 1 回でも数レベル上がるが、出会いの文と重ねて読ませるほどの話ではない
        if (newLevel > oldLevel && before != null) {
            messages += "${MonsterSpecies.name(seed, stageFor(oldLevel))} grew to Lv. $newLevel!"
        }
        val oldStage = stageFor(oldLevel)
        val newStage = stageFor(newLevel)
        if (newStage > oldStage) {
            val from = MonsterSpecies.name(seed, oldStage)
            val to = MonsterSpecies.name(seed, newStage)
            messages += "What? $from is evolving!"
            messages += "Congratulations! $from evolved into $to!"
            evolutions += newStage - oldStage
        }

        val trainerBefore = levelFor(state.trainerExp)
        val trainerExp = state.trainerExp + gain
        val trainerAfter = levelFor(trainerExp)
        if (trainerAfter > trainerBefore) messages += "Trainer reached Lv. $trainerAfter!"

        val next = state.copy(
            trainerExp = trainerExp,
            launches = state.launches + 1,
            monsters = monsters,
            streakDays = streak,
            bestStreak = maxOf(state.bestStreak, streak),
            lastPlayDay = maxOf(state.lastPlayDay, today),
            lastLaunchPackage = packageName,
            lastLaunchMillis = nowMillis,
            evolutions = evolutions,
            recent = (listOf(appKey) + state.recent.filter { it != appKey }).take(RECENT_SIZE),
        )
        val newBadges = BADGES.filter { it.id !in state.badges && it.earned(next) }
        newBadges.forEach { messages += "You earned the ${it.name} badge!" }

        return next.copy(
            badges = state.badges + newBadges.map { it.id },
            messages = (state.messages + messages).takeLast(MAX_MESSAGES),
        )
    }

    /** アプリの住みかを移す。自動の振り分けと同じ住みかに戻したら、上書きそのものを消す */
    fun moveApp(state: GameState, packageName: String, to: Habitat, automatic: Habitat): GameState =
        state.copy(
            habitatOverrides = if (to == automatic) {
                state.habitatOverrides - packageName
            } else {
                state.habitatOverrides + (packageName to to.key)
            },
        )
}
