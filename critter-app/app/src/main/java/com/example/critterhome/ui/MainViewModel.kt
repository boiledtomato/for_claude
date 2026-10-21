package com.example.critterhome.ui

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Rect
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.critterhome.CritterApplication
import com.example.critterhome.data.AppEntry
import com.example.critterhome.data.BatteryLevel
import com.example.critterhome.data.GameStore
import com.example.critterhome.data.batteryLevels
import com.example.critterhome.game.Badge
import com.example.critterhome.game.GameRules
import com.example.critterhome.game.GameState
import com.example.critterhome.game.Habitat
import com.example.critterhome.game.MonsterSpecies
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/** 画面。戻るは [MainViewModel.back] が 1 つずつ戻す */
sealed interface Screen {
    data object Home : Screen
    /** アプリ一覧。住みかで絞った状態で開くこともある */
    data class Bag(val filter: Habitat? = null) : Screen
    data class Summary(val habitat: Habitat) : Screen
    data object Box : Screen
    data object Badges : Screen
    data object Card : Screen
}

/** 住みか 1 つぶん。出会っていなければ [met] が false */
data class CompanionUi(
    val habitat: Habitat,
    val met: Boolean,
    val seed: String,
    val name: String,
    val level: Int,
    val stage: Int,
    val exp: Long,
    val expProgress: Float,
    val expToNext: Long,
    val hp: Float,
    val fainted: Boolean,
    val launches: Long,
    val metOnDay: Long,
    /** この住みかのアプリ ＝ わざ */
    val apps: List<AppEntry>,
)

data class BadgeUi(val badge: Badge, val earned: Boolean)

data class GameUi(
    val trainerLevel: Int = 1,
    val trainerProgress: Float = 0f,
    val launches: Long = 0,
    val streakDays: Int = 0,
    val bestStreak: Int = 0,
    val party: List<CompanionUi> = emptyList(),
    val box: List<CompanionUi> = emptyList(),
    val badges: List<BadgeUi> = emptyList(),
    val message: String? = null,
    val messageCount: Int = 0,
    val recent: List<AppEntry> = emptyList(),
    val apps: List<AppEntry> = emptyList(),
    /** アプリの key → 住みか */
    val habitatOf: Map<String, Habitat> = emptyMap(),
    val loaded: Boolean = false,
) {
    val companions: List<CompanionUi> get() = party + box
    fun companion(h: Habitat): CompanionUi? = companions.firstOrNull { it.habitat == h }
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val container = application as CritterApplication
    private val apps = container.apps
    private val game = container.game

    /** 日付が変わったら HP を計り直す。開きっぱなしで真夜中をまたいでも古い値を出さない */
    private val today = flow {
        while (true) {
            emit(GameStore.today())
            delay(60_000)
        }
    }.distinctUntilChanged()

    val ui: StateFlow<GameUi> = combine(game.state, apps.apps, today) { state, installed, day ->
        build(state, installed, day)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), GameUi())

    val battery: StateFlow<BatteryLevel> = batteryLevels(application)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), BatteryLevel())

    // ---- 画面の行き来 -------------------------------------------------------

    val stack = mutableStateListOf<Screen>(Screen.Home)
    val screen: Screen get() = stack.last()

    fun open(target: Screen) {
        if (stack.last() != target) stack += target
    }

    /** 戻れたら true。ホームで押された戻るは飲み込む（ホームアプリは終了しない） */
    fun back(): Boolean {
        if (stack.size <= 1) return false
        stack.removeAt(stack.lastIndex)
        return true
    }

    /** HOME キー。どこにいてもホームへ */
    fun home() {
        stack.clear()
        stack += Screen.Home
    }

    // ---- 操作 ---------------------------------------------------------------

    fun launch(entry: AppEntry, bounds: Rect? = null) {
        if (apps.launch(entry, bounds)) {
            viewModelScope.launch { game.recordLaunch(entry) }
        }
    }

    fun openAppInfo(entry: AppEntry) {
        apps.openAppInfo(entry)
    }

    fun moveApp(entry: AppEntry, to: Habitat) = viewModelScope.launch { game.moveApp(entry, to) }

    fun consumeMessage() = viewModelScope.launch { game.consumeMessage() }

    suspend fun icon(entry: AppEntry): Bitmap? = apps.icon(entry)

    private fun build(state: GameState, installed: List<AppEntry>, today: Long): GameUi {
        val habitatOf = installed.associate { it.key to GameStore.habitatOf(it, state) }
        val byHabitat = installed.groupBy { habitatOf.getValue(it.key) }
        val (party, box) = GameRules.partition(state)
        fun companion(h: Habitat): CompanionUi {
            val record = state.monsters[h.key]
            val level = record?.let { GameRules.levelFor(it.exp) } ?: 1
            val stage = GameRules.stageFor(level)
            val seed = state.seedOf(h.key)
            return CompanionUi(
                habitat = h,
                met = record != null,
                seed = seed,
                name = MonsterSpecies.name(seed, stage),
                level = level,
                stage = stage,
                exp = record?.exp ?: 0,
                expProgress = record?.let { GameRules.progress(it.exp) } ?: 0f,
                expToNext = record?.let { GameRules.expToNext(it.exp) } ?: 0,
                hp = record?.let { GameRules.hp(it, today) } ?: 0f,
                fainted = record != null && GameRules.fainted(record, today),
                launches = record?.launches ?: 0,
                metOnDay = record?.metOnDay ?: 0,
                apps = byHabitat[h].orEmpty(),
            )
        }
        val byKey = installed.associateBy { it.key }
        return GameUi(
            trainerLevel = GameRules.levelFor(state.trainerExp),
            trainerProgress = GameRules.progress(state.trainerExp),
            launches = state.launches,
            streakDays = GameRules.currentStreak(state, today),
            bestStreak = state.bestStreak,
            party = party.map(::companion),
            box = box.map(::companion),
            badges = GameRules.BADGES.map { BadgeUi(it, it.id in state.badges) },
            message = state.messages.firstOrNull(),
            messageCount = state.messages.size,
            recent = state.recent.mapNotNull { byKey[it] },
            apps = installed,
            habitatOf = habitatOf,
            loaded = true,
        )
    }
}
