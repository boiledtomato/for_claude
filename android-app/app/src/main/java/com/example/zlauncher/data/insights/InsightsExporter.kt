package com.example.zlauncher.data.insights

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.example.zlauncher.domain.model.InsightLogEntry
import com.example.zlauncher.domain.model.InsightRange
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 表示中のログを CSV に落として共有シートに渡す。
 *
 * 端末の外に出す手段を用意しないと、集計はできても持ち出せない。書き出し先は cacheDir で、
 * FileProvider 経由の一時的な read 権限しか渡さない（保存場所を汚さないため）。
 */
@Singleton
class InsightsExporter @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    /** 書き出しに成功したら共有インテントを返す。失敗したら null（呼び出し側で通知する） */
    suspend fun export(range: InsightRange, entries: List<InsightLogEntry>): Intent? =
        withContext(Dispatchers.IO) {
            runCatching {
                val stamp = FILE_STAMP.format(Date())
                val directory = File(context.cacheDir, DIRECTORY).apply { mkdirs() }
                // 同じ範囲を撮り直すたびに溜まるので、書く前に古いものを片付ける
                directory.listFiles()?.forEach { it.delete() }

                val file = File(directory, "web-insights-${range.label.lowercase(Locale.US)}-$stamp.csv")
                file.bufferedWriter().use { writer ->
                    writer.appendLine(HEADER)
                    entries.forEach { entry ->
                        writer.appendLine(
                            listOf(
                                ROW_STAMP.format(Date(entry.startMillis)),
                                ROW_STAMP.format(Date(entry.endMillis)),
                                entry.categoryName,
                                entry.label,
                                entry.packageName,
                                entry.transport.label,
                                entry.rxBytes.toString(),
                                entry.txBytes.toString(),
                                entry.totalBytes.toString(),
                            ).joinToString(",") { escape(it) }
                        )
                    }
                }

                val uri = FileProvider.getUriForFile(context, "${context.packageName}$AUTHORITY_SUFFIX", file)
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/csv"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    putExtra(Intent.EXTRA_SUBJECT, "Web Insights ${range.label}")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }.let { Intent.createChooser(it, "Export log").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
            }.getOrNull()
        }

    /** アプリ名にカンマや引用符が入るので、RFC 4180 の最小限のエスケープをする */
    private fun escape(value: String): String =
        if (value.any { it == ',' || it == '"' || it == '\n' }) {
            "\"" + value.replace("\"", "\"\"") + "\""
        } else {
            value
        }

    private companion object {
        const val DIRECTORY = "insights"
        const val AUTHORITY_SUFFIX = ".insights"
        const val HEADER = "start,end,category,app,package,transport,rx_bytes,tx_bytes,total_bytes"
        val FILE_STAMP = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US)
        val ROW_STAMP = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    }
}
