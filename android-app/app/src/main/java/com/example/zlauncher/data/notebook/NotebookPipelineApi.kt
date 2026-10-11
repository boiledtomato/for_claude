package com.example.zlauncher.data.notebook

import android.util.Log
import com.example.zlauncher.core.notebook.NotebookFetch
import com.example.zlauncher.core.notebook.NotebookPipeline
import com.example.zlauncher.core.notebook.NotebookSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

/**
 * パイプラインが公開している同期状態を読む。**認証なし、数 KB、API キーも不要。**
 *
 * 2 段で取る:
 *
 * 1. GitHub の contents API で `data/` を列挙し、`*_sync_state.json` を拾う
 * 2. それぞれを GitHub Pages から取って 1 行にする
 *
 * **1 が失敗しても諦めない。** 回数制限（未認証 60 回/時）や API の不調で列挙できない
 * ことはあるので、そのときは既知の一覧で 2 だけ進める。ノートブックが増えたときに
 * 拾えるのは 1 のほうなので、既定の一覧は保険であって本筋ではない。
 *
 * 2 は**並行に取る**。4 つを順に待つと、1 つ遅いだけで全体が遅くなる。
 */
@Singleton
class NotebookPipelineApi @Inject constructor() {

    suspend fun listNotebooks(config: NotebookConfig): NotebookFetch = withContext(Dispatchers.IO) {
        val fileNames = discover(config).ifEmpty { NotebookPipeline.FALLBACK_STATE_FILES }
        val summaries = coroutineScope {
            fileNames.map { name ->
                async { readState(config.pipelineBaseUrl, name) }
            }.awaitAll()
        }.filterNotNull()

        if (summaries.isNotEmpty()) {
            NotebookFetch.Ok(summaries.sortedBy { it.displayTitle.lowercase() })
        } else {
            // 1 つも読めなかった。URL 違いか圏外か ― どちらも「届かない」で同じ案内
            NotebookFetch.Failed("no notebook state could be read")
        }
    }

    /** 列挙。失敗は空リストで返し、呼び出し側が既定の一覧にそのまま落ちる */
    private fun discover(config: NotebookConfig): List<String> = runCatching {
        val body = get(NotebookPipeline.contentsUrl(config.pipelineRepo)) ?: return emptyList()
        NotebookPipeline.parseStateFileNames(body)
    }.onFailure { Log.i(TAG, "state listing unavailable, using the known set", it) }
        .getOrDefault(emptyList())

    private fun readState(baseUrl: String, fileName: String): NotebookSummary? = runCatching {
        val body = get(NotebookPipeline.stateUrl(baseUrl, fileName)) ?: return null
        NotebookPipeline.parseState(body, fileName)
    }.onFailure { Log.w(TAG, "could not read $fileName", it) }.getOrNull()

    /** 2xx の本文、それ以外は null。どちらも落とさない（1 つ欠けても残りは出す） */
    private fun get(url: String): String? {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            // GitHub API は User-Agent を要求する。付けないと 403 になる
            setRequestProperty("User-Agent", "ZLauncher")
            setRequestProperty("Accept", "application/json")
        }
        return try {
            if (connection.responseCode in 200..299) {
                connection.inputStream.bufferedReader().use { it.readText() }
            } else {
                Log.i(TAG, "$url -> ${connection.responseCode}")
                null
            }
        } finally {
            connection.disconnect()
        }
    }

    private companion object {
        const val TAG = "NotebookPipeline"
        const val TIMEOUT_MS = 15_000
    }
}
