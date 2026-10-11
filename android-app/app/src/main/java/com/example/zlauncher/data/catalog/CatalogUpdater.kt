package com.example.zlauncher.data.catalog

import android.util.Log
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.CatalogDiff
import com.example.zlauncher.domain.model.CatalogMove
import com.example.zlauncher.domain.model.CatalogRename
import com.example.zlauncher.domain.model.UrlCategoryEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

/** 更新 1 回の結果 */
sealed interface CatalogUpdateResult {
    /** 取得できたが中身は同じ */
    data class UpToDate(val revision: String) : CatalogUpdateResult

    /** 改訂を検出。[diff] は未適用のまま保存してある */
    data class Updated(val diff: CatalogDiff) : CatalogUpdateResult

    data class Failed(val reason: String) : CatalogUpdateResult
}

/**
 * Zscaler ヘルプから URL カテゴリー CSV を取り直す。
 *
 * **CSV の URL は固定できない。** ファイル名に改訂日が入っており
 * （Zscaler-Test-URL-Categories-05-21-2026.csv）、改訂のたびに変わる。
 * そこで記事本文を JSON API から取り、その中の .csv リンクを拾ってから落とす。
 * ヘルプサイトは React の SPA なので HTML を読んでも本文は入っておらず、この API 以外に道は無い。
 */
@Singleton
class CatalogUpdater @Inject constructor(
    private val repository: UrlCategoryRepository,
    private val preferences: LauncherPreferencesRepository,
) {

    suspend fun update(now: Long = System.currentTimeMillis()): CatalogUpdateResult =
        withContext(Dispatchers.IO) {
            val before = repository.currentEntries()

            val article = fetch(ARTICLE_URL) ?: return@withContext CatalogUpdateResult.Failed(
                "Could not reach the Zscaler help article."
            )
            val path = CatalogArticle.csvPath(article)
                ?: return@withContext CatalogUpdateResult.Failed(
                    "The help article no longer links a CSV."
                )
            val revision = path.substringAfterLast('/')

            val csv = fetch(HELP_ORIGIN + path) ?: return@withContext CatalogUpdateResult.Failed(
                "Could not download $revision."
            )
            val after = UrlCategoryCsv.parse(csv)
            if (after.isEmpty()) {
                // 落とせたが解釈できない = 書式が変わった。既存を壊さないよう捨てる
                return@withContext CatalogUpdateResult.Failed(
                    "$revision could not be parsed — keeping the current catalog."
                )
            }

            val diff = diff(before, after, revision, now)
            repository.store(revision, csv, now)

            preferences.update { state ->
                state.copy(
                    catalogRevision = revision,
                    catalogFetchedAtMillis = now,
                    // 変更が無いときに前回の差分を消さない。未確認のものを取りこぼさないため
                    pendingCatalogDiff = if (diff.isEmpty) state.pendingCatalogDiff else diff,
                )
            }

            if (diff.isEmpty) CatalogUpdateResult.UpToDate(revision) else CatalogUpdateResult.Updated(diff)
        }

    /**
     * 差分。安定 ID が無いので、まず鍵（大項目/小項目）で突き合わせ、
     * 残った「消えた」と「増えた」から改名・移動の候補を組み立てる。
     */
    fun diff(
        before: List<UrlCategoryEntry>,
        after: List<UrlCategoryEntry>,
        toRevision: String,
        now: Long,
        fromRevision: String = "",
    ): CatalogDiff {
        val beforeByKey = before.associateBy { it.key }
        val afterByKey = after.associateBy { it.key }

        val goneKeys = beforeByKey.keys - afterByKey.keys
        val newKeys = afterByKey.keys - beforeByKey.keys
        val gone = goneKeys.mapNotNull { beforeByKey[it] }.toMutableList()
        val fresh = newKeys.mapNotNull { afterByKey[it] }.toMutableList()

        // 1) 小項目名が同じで大項目だけ変わったもの = 移動
        val moved = mutableListOf<CatalogMove>()
        gone.toList().forEach { old ->
            val match = fresh.firstOrNull { it.category.equals(old.category, ignoreCase = true) }
            if (match != null) {
                moved += CatalogMove(entry = match, fromSuperCategory = old.superCategory)
                gone.remove(old)
                fresh.remove(match)
            }
        }

        // 2) 同じ大項目で説明文が一致するもの = 改名の候補。空の説明は根拠にならないので除く
        val renamed = mutableListOf<CatalogRename>()
        gone.toList().forEach { old ->
            if (old.description.isBlank()) return@forEach
            val match = fresh.firstOrNull {
                it.superCategory == old.superCategory && it.description == old.description
            }
            if (match != null) {
                renamed += CatalogRename(from = old, to = match)
                gone.remove(old)
                fresh.remove(match)
            }
        }

        return CatalogDiff(
            fromRevision = fromRevision,
            toRevision = toRevision,
            detectedAtMillis = now,
            added = fresh.sortedBy { it.key },
            removed = gone.sortedBy { it.key },
            renamed = renamed.sortedBy { it.from.key },
            moved = moved.sortedBy { it.entry.key },
        )
    }

    /**
     * 差分をユーザーのカテゴリーに反映する。
     *
     * 触るのは[catalogKey][com.example.zlauncher.domain.model.AppCategory.catalogKey]で
     * カタログに紐づくカテゴリーだけ。自分で作ったカテゴリーには一切手を出さない。
     * 中身（所属アプリ）も色も保つ。名前と鍵の付け替えだけを行う。
     */
    suspend fun applyPendingDiff() {
        preferences.update { state ->
            val diff = state.pendingCatalogDiff ?: return@update state
            val renames = diff.renamed.associate { it.from.key to it.to }
            val moves = diff.moved.associateBy { it.entry.category.lowercase() }

            val categories = state.categories.map { category ->
                val key = category.catalogKey ?: return@map category
                renames[key]?.let { target ->
                    return@map category.copy(name = target.category, catalogKey = target.key)
                }
                // 移動は名前が変わらないので、鍵の付け替えだけ
                moves[category.name.lowercase()]?.let { move ->
                    if (move.entry.superCategory != key.substringBefore('/')) {
                        return@map category.copy(catalogKey = move.entry.key)
                    }
                }
                category
            }
            state.copy(categories = categories, pendingCatalogDiff = null)
        }
    }

    /** 見たうえで何もしない場合。差分だけ消す */
    suspend fun dismissPendingDiff() {
        preferences.update { it.copy(pendingCatalogDiff = null) }
    }

    suspend fun pendingDiff(): CatalogDiff? = preferences.state.first().pendingCatalogDiff

    private fun fetch(url: String): String? = runCatching {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            instanceFollowRedirects = true
            setRequestProperty("Accept", "*/*")
        }
        try {
            if (connection.responseCode !in 200..299) {
                Log.w(TAG, "fetch $url -> HTTP ${connection.responseCode}")
                return null
            }
            connection.inputStream.bufferedReader().use { it.readText() }
        } finally {
            connection.disconnect()
        }
    }.onFailure { Log.w(TAG, "fetch failed: $url", it) }.getOrNull()

    private companion object {
        const val TAG = "CatalogUpdater"
        const val HELP_ORIGIN = "https://help.zscaler.com"

        /** 記事本文を返す JSON API。SPA の HTML には本文が入っていないのでこちらを使う */
        const val ARTICLE_URL =
            "$HELP_ORIGIN/zapi/fetch-data?url_alias=/zia/about-url-categories&view_type=full&_format=json"

        const val TIMEOUT_MS = 20_000
    }
}
