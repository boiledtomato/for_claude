package com.example.zlauncher.data.game

import android.content.Context
import android.util.Log
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.game.GameRules
import com.example.zlauncher.domain.model.game.GameState
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ゲームの進み具合の保存先。
 *
 * **ランチャー本体の設定とは別の DataStore。** アプリを開くたびに書くので、同じ所に入れると
 * 起動のたびに設定の JSON 全体（カテゴリー・ウィジェット配置・カード配置）を書き直すことになり、
 * 書き込みの途中で落ちたときに失うものも大きい。壊れていたら最初からやり直すだけで、
 * ランチャーそのものは巻き込まない。
 */
private val Context.gameDataStore by preferencesDataStore(
    name = "game",
    corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
)

@Singleton
class GameRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferences: LauncherPreferencesRepository,
) {
    private val key = stringPreferencesKey("game_state")
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    val state: Flow<GameState> = context.gameDataStore.data.map { decode(it[key]) }

    /**
     * 開いたアプリを経験値にする。起動に**成功したときだけ**呼ぶ。
     *
     * 所属は保存済みのカテゴリーから直接求める。インストール済みアプリの Flow を待つと、
     * 誰も購読していない瞬間には空の一覧が返り、どのカテゴリーにも入っていない扱いになる。
     * 仕事用プロファイルのアプリはカテゴリーに入らないので、トレーナーの経験値だけになる。
     */
    suspend fun recordLaunch(entry: AppEntry, nowMillis: Long = System.currentTimeMillis()) {
        val categories = if (entry.isWorkProfile) {
            emptyList()
        } else {
            GameRules.categoriesOf(preferences.state.first().categories, entry.packageName)
        }
        write { GameRules.onLaunch(it, entry.packageName, categories, nowMillis, today()) }
    }

    /** メッセージウィンドウの先頭を 1 つ読み終えた */
    suspend fun consumeMessage() = write { it.copy(messages = it.messages.drop(1)) }

    private suspend fun write(transform: (GameState) -> GameState) {
        context.gameDataStore.edit { prefs ->
            prefs[key] = json.encodeToString(transform(decode(prefs[key])))
        }
    }

    private fun decode(raw: String?): GameState {
        if (raw.isNullOrBlank()) return GameState()
        return runCatching { json.decodeFromString<GameState>(raw) }
            .onFailure { Log.w(TAG, "game state decode failed, starting over", it) }
            .getOrDefault(GameState())
    }

    companion object {
        private const val TAG = "GameRepository"

        /** 端末の時刻帯での今日。日付の境目は利用者の真夜中に合わせる */
        fun today(): Long = LocalDate.now(ZoneId.systemDefault()).toEpochDay()
    }
}
