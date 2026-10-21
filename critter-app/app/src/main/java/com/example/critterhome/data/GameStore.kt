package com.example.critterhome.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.critterhome.game.GameRules
import com.example.critterhome.game.GameState
import com.example.critterhome.game.Habitat
import com.example.critterhome.game.Habitats
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

/**
 * 進み具合の保存先。壊れていたら最初からやり直す ― ホームアプリが起動しなくなるよりはよい。
 */
private val Context.gameDataStore by preferencesDataStore(
    name = "game",
    corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
)

class GameStore(private val context: Context) {
    private val key = stringPreferencesKey("game_state")
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    val state: Flow<GameState> = context.gameDataStore.data.map { decode(it[key]) }

    /** 開いたアプリを経験値にする。起動に**成功したときだけ**呼ぶ */
    suspend fun recordLaunch(entry: AppEntry, nowMillis: Long = System.currentTimeMillis()) = write { state ->
        val seeded = if (state.salt.isBlank()) state.copy(salt = UUID.randomUUID().toString()) else state
        val habitat = habitatOf(entry, seeded)
        GameRules.onLaunch(seeded, entry.packageName, entry.key, habitat, nowMillis, today())
    }

    suspend fun moveApp(entry: AppEntry, to: Habitat) = write { state ->
        GameRules.moveApp(state, entry.packageName, to, automatic = habitatOf(entry, GameState()))
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
        private const val TAG = "GameStore"

        fun habitatOf(entry: AppEntry, state: GameState): Habitat =
            Habitats.classify(entry.packageName, entry.label, entry.declaredCategory, state.habitatOverrides)

        /** 端末の時刻帯での今日。日付の境目は利用者の真夜中に合わせる */
        fun today(): Long = LocalDate.now(ZoneId.systemDefault()).toEpochDay()
    }
}
