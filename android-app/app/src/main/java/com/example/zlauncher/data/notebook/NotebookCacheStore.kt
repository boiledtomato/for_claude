package com.example.zlauncher.data.notebook

import android.content.Context
import android.util.Log
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.zlauncher.core.notebook.NotebookSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ウィジェットが読む、最後に取れた一覧。
 *
 * **出す文まで一緒に保存する。** ウィジェットは `RemoteViews` なので、描くときに
 * 計算や注入をしたくない ― 取得のたびに [com.example.zlauncher.core.notebook.NotebookStatus]
 * で決めた文をそのまま置いておき、ウィジェット側は並べるだけにする。
 *
 * **ランチャー本体の設定とは別の DataStore。** 同じ所に入れると、更新のたびに設定の
 * JSON 全体を書き直すことになる（ウィジェットの更新は定期的に走る）。
 */
@Serializable
data class NotebookCache(
    val notebooks: List<NotebookSummary> = emptyList(),
    val fetchedAtMillis: Long = 0L,
    /** 出す一行。空なら一覧だけ見せる */
    val status: String = "",
    val showsList: Boolean = true,
    /** 同意がまだ。ウィジェットのタップ先を設定画面に向ける */
    val needsConsent: Boolean = false,
)

private val Context.notebookDataStore by preferencesDataStore(
    name = "notebook_cache",
    corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
)

@Singleton
class NotebookCacheStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val key = stringPreferencesKey("notebook_cache")
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    val cache: Flow<NotebookCache> = context.notebookDataStore.data.map { decode(it[key]) }

    suspend fun read(): NotebookCache = cache.first()

    suspend fun write(transform: (NotebookCache) -> NotebookCache) {
        context.notebookDataStore.edit { prefs ->
            prefs[key] = json.encodeToString(transform(decode(prefs[key])))
        }
    }

    private fun decode(raw: String?): NotebookCache {
        if (raw.isNullOrBlank()) return NotebookCache()
        return runCatching { json.decodeFromString<NotebookCache>(raw) }
            .onFailure { Log.w(TAG, "notebook cache decode failed", it) }
            .getOrDefault(NotebookCache())
    }

    private companion object { const val TAG = "NotebookCache" }
}
