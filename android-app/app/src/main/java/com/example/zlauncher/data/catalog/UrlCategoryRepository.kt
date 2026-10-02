package com.example.zlauncher.data.catalog

import android.content.Context
import android.util.Log
import com.example.zlauncher.domain.model.UrlCategoryCatalog
import com.example.zlauncher.domain.model.UrlCategoryEntry
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 手元の URL カテゴリーカタログ。
 *
 * 出所は 2 つあり、**取り込み済みファイルがあればそちらを優先**する:
 * 1. `filesDir/catalog/url_categories.csv` — 3 か月ごとの更新で取り直したもの
 * 2. `assets/zscaler_url_categories.csv` — アプリに同梱した版
 *
 * 同梱版を必ず持たせているのは、初回起動時やオフラインでもカテゴリーを作れるようにするため。
 * 通信できないとカテゴリーが 1 つも作れない、という状態を作らない。
 */
@Singleton
class UrlCategoryRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val _catalog = MutableStateFlow(UrlCategoryCatalog.EMPTY)
    val catalog: StateFlow<UrlCategoryCatalog> = _catalog.asStateFlow()

    private val downloadedFile: File
        get() = File(File(context.filesDir, DIRECTORY).apply { mkdirs() }, FILE_NAME)

    /** 画面を開くたびに呼んでよい。読み込み済みなら何もしない */
    suspend fun ensureLoaded(): UrlCategoryCatalog {
        if (_catalog.value.entries.isNotEmpty()) return _catalog.value
        return reload()
    }

    suspend fun reload(): UrlCategoryCatalog = withContext(Dispatchers.IO) {
        val loaded = readDownloaded() ?: readBundled()
        _catalog.value = loaded
        loaded
    }

    /** 更新で取ってきた CSV を採用する。差分の適用とは独立に、本文だけ先に置き換える */
    suspend fun store(revision: String, csv: String, fetchedAtMillis: Long): UrlCategoryCatalog =
        withContext(Dispatchers.IO) {
            runCatching { downloadedFile.writeText(csv) }
                .onFailure { Log.w(TAG, "could not persist catalog", it) }
            val entries = UrlCategoryCsv.parse(csv)
            val catalog = UrlCategoryCatalog(revision, fetchedAtMillis, entries)
            _catalog.value = catalog
            catalog
        }

    /** 同梱版に戻す。取り込んだ CSV が壊れていたときの逃げ道 */
    suspend fun resetToBundled(): UrlCategoryCatalog = withContext(Dispatchers.IO) {
        runCatching { downloadedFile.delete() }
        val bundled = readBundled()
        _catalog.value = bundled
        bundled
    }

    /** 差分を取るための、現在採用中のエントリー */
    suspend fun currentEntries(): List<UrlCategoryEntry> = ensureLoaded().entries

    private fun readDownloaded(): UrlCategoryCatalog? {
        val file = downloadedFile
        if (!file.exists()) return null
        return runCatching {
            val entries = UrlCategoryCsv.parse(file.readText())
            // 空になるのは取得が壊れていた証拠。同梱版に落として気付けるようにする
            if (entries.isEmpty()) null
            else UrlCategoryCatalog(file.name, file.lastModified(), entries)
        }.onFailure { Log.w(TAG, "downloaded catalog unreadable", it) }.getOrNull()
    }

    private fun readBundled(): UrlCategoryCatalog = runCatching {
        val csv = context.assets.open(ASSET_NAME).bufferedReader().use { it.readText() }
        UrlCategoryCatalog(ASSET_NAME, 0L, UrlCategoryCsv.parse(csv))
    }.onFailure { Log.e(TAG, "bundled catalog unreadable", it) }
        .getOrDefault(UrlCategoryCatalog.EMPTY)

    private companion object {
        const val TAG = "UrlCategoryRepository"
        const val DIRECTORY = "catalog"
        const val FILE_NAME = "url_categories.csv"
        const val ASSET_NAME = "zscaler_url_categories.csv"
    }
}
