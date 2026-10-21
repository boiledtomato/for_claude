package com.example.zlauncher.ui.game

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.apps.AppIconLoader
import com.example.zlauncher.data.apps.CategoryRepository
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.data.apps.LauncherAppsDataSource
import com.example.zlauncher.data.device.DeviceMetricsRepository
import com.example.zlauncher.data.game.GameRepository
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.game.Badge
import com.example.zlauncher.domain.model.game.GameRules
import com.example.zlauncher.domain.model.game.GameState
import com.example.zlauncher.domain.model.game.MonsterSpecies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 手持ちやボックスの 1 体ぶん。出会っていないカテゴリーは [met] が false */
data class MonsterUi(
    val categoryId: String,
    val categoryName: String,
    val colorIndex: Int,
    val met: Boolean,
    val name: String,
    val type: String,
    val level: Int,
    val stage: Int,
    val exp: Long,
    val expProgress: Float,
    val expToNext: Long,
    val hp: Float,
    val fainted: Boolean,
    val launches: Long,
    val metOnDay: Long,
    val apps: List<AppEntry>,
)

data class BadgeUi(val badge: Badge, val earned: Boolean)

data class GameUi(
    val trainerLevel: Int = 1,
    val trainerExp: Long = 0,
    val trainerProgress: Float = 0f,
    val launches: Long = 0,
    val streakDays: Int = 0,
    val bestStreak: Int = 0,
    val party: List<MonsterUi> = emptyList(),
    val box: List<MonsterUi> = emptyList(),
    val badges: List<BadgeUi> = emptyList(),
    /** いま出すメッセージ。無ければ null */
    val message: String? = null,
    val hasCategories: Boolean = false,
    val loaded: Boolean = false,
)

@HiltViewModel
class GameViewModel @Inject constructor(
    private val game: GameRepository,
    private val iconLoader: AppIconLoader,
    private val launcherApps: LauncherAppsDataSource,
    preferences: LauncherPreferencesRepository,
    categoryRepository: CategoryRepository,
    metricsRepository: DeviceMetricsRepository,
) : ViewModel() {

    /** 日付が変わったら HP を計り直す。開きっぱなしで真夜中をまたいでも古い値を出さない */
    private val today = flow {
        while (true) {
            emit(GameRepository.today())
            delay(60_000)
        }
    }.distinctUntilChanged()

    val ui: StateFlow<GameUi> = combine(game.state, categoryRepository.categories, today) { state, categories, day ->
        build(state, categories, day)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), GameUi())

    /** トレーナーの HP は電池残量。1 秒ごとに来るので、値が変わったときだけ流す */
    val battery: StateFlow<Pair<Int, Boolean>> = metricsRepository.metrics
        .map { it.batteryPercent to it.batteryCharging }
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0 to false)

    private var themedIcons = false

    init {
        viewModelScope.launch { preferences.state.collect { themedIcons = it.themedIcons } }
    }

    fun consumeMessage() = viewModelScope.launch { game.consumeMessage() }

    /** わざ ＝ そのカテゴリーのアプリ。使えば経験値が入る */
    fun launch(entry: AppEntry) {
        if (launcherApps.launch(entry.componentName, entry.user)) {
            viewModelScope.launch { game.recordLaunch(entry) }
        }
    }

    suspend fun icon(entry: AppEntry): ImageBitmap? = iconLoader.load(entry, themedIcons)

    private fun build(state: GameState, categories: List<CategoryWithApps>, today: Long): GameUi {
        val byId = categories.associateBy { it.id }
        val (partyIds, boxIds) = GameRules.partition(categories.map { it.id }, state)
        fun monster(id: String): MonsterUi? {
            val category = byId[id] ?: return null
            val record = state.monsters[id]
            val level = record?.let { GameRules.levelFor(it.exp) } ?: 1
            val stage = GameRules.stageFor(level)
            return MonsterUi(
                categoryId = id,
                categoryName = category.category.name,
                colorIndex = category.category.colorIndex,
                met = record != null,
                name = MonsterSpecies.name(id, stage),
                type = MonsterSpecies.type(id, category.category.name),
                level = level,
                stage = stage,
                exp = record?.exp ?: 0,
                expProgress = record?.let { GameRules.progress(it.exp) } ?: 0f,
                expToNext = record?.let { GameRules.expToNext(it.exp) } ?: 0,
                hp = record?.let { GameRules.hp(it, today) } ?: 0f,
                fainted = record != null && GameRules.fainted(record, today),
                launches = record?.launches ?: 0,
                metOnDay = record?.metOnDay ?: 0,
                apps = category.apps,
            )
        }
        return GameUi(
            trainerLevel = GameRules.levelFor(state.trainerExp),
            trainerExp = state.trainerExp,
            trainerProgress = GameRules.progress(state.trainerExp),
            launches = state.launches,
            streakDays = if (state.lastPlayDay >= today - 1) state.streakDays else 0,
            bestStreak = state.bestStreak,
            party = partyIds.mapNotNull(::monster),
            box = boxIds.mapNotNull(::monster),
            badges = GameRules.BADGES.map { BadgeUi(it, it.id in state.badges) },
            message = state.messages.firstOrNull(),
            hasCategories = categories.isNotEmpty(),
            loaded = true,
        )
    }
}
