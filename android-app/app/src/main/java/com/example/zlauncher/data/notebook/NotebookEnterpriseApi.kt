package com.example.zlauncher.data.notebook

import android.util.Log
import com.example.zlauncher.core.notebook.NotebookEndpoint
import com.example.zlauncher.core.notebook.NotebookFetch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Gemini Notebook Enterprise の REST を 1 本だけ叩く。
 *
 * **公開されているのは「最近見た順」の一覧だけ**で、素の list は無い。既定で最大 500 件
 * 返るので [pageSize] で絞る ― ウィジェットに 500 行は出せない。
 *
 * ソース件数は**取れない**。ソースを数える API が公開されていないため（1 件を id 指定で
 * 取るものと、追加・削除しかない）、「ソース 34 件」のような表示はこの API では作れない。
 *
 * 失敗の扱いは [NotebookFetch.fromStatus] に寄せてある。状態コードの意味づけは純粋な
 * ほうでテストしてあるので、ここは通信だけに責任を持つ。
 */
@Singleton
class NotebookEnterpriseApi @Inject constructor() {

    suspend fun listNotebooks(
        config: NotebookConfig,
        accessToken: String,
        pageSize: Int = DEFAULT_PAGE_SIZE,
    ): NotebookFetch = withContext(Dispatchers.IO) {
        if (!config.isConfigured) return@withContext NotebookFetch.NotConfigured
        val url = NotebookEndpoint.listRecentlyViewed(
            endpointLocation = config.endpointLocation,
            projectNumber = config.projectNumber,
            location = config.location,
            pageSize = pageSize,
        )
        runCatching { get(url, accessToken) }
            .getOrElse { error ->
                Log.w(TAG, "notebook list failed", error)
                NotebookFetch.Failed(error.message ?: "request failed")
            }
    }

    private fun get(url: String, accessToken: String): NotebookFetch {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = TIMEOUT_MS
            readTimeout = TIMEOUT_MS
            setRequestProperty("Authorization", "Bearer $accessToken")
            setRequestProperty("Content-Type", "application/json")
        }
        return try {
            val code = connection.responseCode
            // 失敗時の本文は errorStream 側に来る。読まずに閉じると理由が残らない
            val body = (if (code in 200..299) connection.inputStream else connection.errorStream)
                ?.bufferedReader()?.use { it.readText() }.orEmpty()
            NotebookFetch.fromStatus(code, body)
        } finally {
            connection.disconnect()
        }
    }

    private companion object {
        const val TAG = "NotebookApi"
        const val TIMEOUT_MS = 15_000
        /** ウィジェットに並べられる上限。既定の 500 件を全部持ってきても出せない */
        const val DEFAULT_PAGE_SIZE = 25
    }
}
