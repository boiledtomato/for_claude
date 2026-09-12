package com.example.zlauncher.data.prefs

import android.content.Context
import android.util.Log
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 設定ファイルが壊れてホームが起動しない、という事態を避けるため
 * corruptionHandler で既定値に落とす。デコード失敗も同様に既定値へ。
 */
private val Context.launcherDataStore by preferencesDataStore(
    name = "launcher",
    corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
)

@Singleton
class LauncherPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val stateKey = stringPreferencesKey("launcher_state")
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    val state: Flow<LauncherState> = context.launcherDataStore.data.map { prefs ->
        decode(prefs[stateKey])
    }

    suspend fun update(transform: (LauncherState) -> LauncherState) {
        context.launcherDataStore.edit { prefs ->
            val current = decode(prefs[stateKey])
            prefs[stateKey] = json.encodeToString(transform(current))
        }
    }

    private fun decode(raw: String?): LauncherState {
        if (raw.isNullOrBlank()) return LauncherState()
        return runCatching { json.decodeFromString<LauncherState>(raw).migrate() }
            .onFailure { Log.w(TAG, "state decode failed, falling back to defaults", it) }
            .getOrDefault(LauncherState())
    }

    private companion object {
        const val TAG = "LauncherPreferences"
    }
}
