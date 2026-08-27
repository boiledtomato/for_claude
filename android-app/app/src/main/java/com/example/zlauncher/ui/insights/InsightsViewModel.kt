package com.example.zlauncher.ui.insights

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.insights.InsightsExporter
import com.example.zlauncher.data.insights.InsightsRepository
import com.example.zlauncher.domain.model.InsightLogEntry
import com.example.zlauncher.domain.model.InsightRange
import com.example.zlauncher.domain.model.WebInsightsReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/** ログ一覧の描画上限。7 日ぶんは数千行になりうるので、絞り込みを促す */
private const val MAX_LOG_ROWS = 400

@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val repository: InsightsRepository,
    private val exporter: InsightsExporter,
) : ViewModel() {

    var range by mutableStateOf(InsightRange.LAST_DAY)
        private set

    var report by mutableStateOf(WebInsightsReport.empty(InsightRange.LAST_DAY, available = true))
        private set

    var loading by mutableStateOf(false)
        private set

    var query by mutableStateOf("")
        private set

    /** null なら全カテゴリー */
    var categoryFilter by mutableStateOf<String?>(null)
        private set

    /** 時系列の棒をタップしたときの絞り込み。バケット開始時刻 */
    var bucketFilter by mutableStateOf<Long?>(null)
        private set

    /** 共有シートを一度だけ開くための受け渡し。消費したら [consumeExport] で戻す */
    var pendingExport by mutableStateOf<Intent?>(null)
        private set

    var exportFailed by mutableStateOf(false)
        private set

    private var loadJob: Job? = null

    /** 絞り込み後のログ。CSV もこの結果を書き出す（見えているものと一致させる） */
    val filteredEntries: List<InsightLogEntry>
        get() {
            val needle = query.trim().lowercase(Locale.getDefault())
            return report.entries.filter { entry ->
                (categoryFilter == null || entry.categoryId == categoryFilter) &&
                    (bucketFilter == null || entry.startMillis == bucketFilter) &&
                    (
                        needle.isEmpty() ||
                            entry.label.lowercase(Locale.getDefault()).contains(needle) ||
                            entry.packageName.lowercase(Locale.getDefault()).contains(needle) ||
                            entry.categoryName.lowercase(Locale.getDefault()).contains(needle)
                        )
            }
        }

    val visibleEntries: List<InsightLogEntry> get() = filteredEntries.take(MAX_LOG_ROWS)

    val truncated: Boolean get() = filteredEntries.size > MAX_LOG_ROWS

    fun selectRange(next: InsightRange) {
        if (next == range) return
        range = next
        bucketFilter = null
        refresh()
    }

    fun updateQuery(next: String) {
        query = next
    }

    fun toggleCategoryFilter(id: String?) {
        categoryFilter = if (categoryFilter == id) null else id
    }

    fun toggleBucketFilter(start: Long?) {
        bucketFilter = if (bucketFilter == start) null else start
    }

    fun clearFilters() {
        query = ""
        categoryFilter = null
        bucketFilter = null
    }

    /**
     * 画面に戻るたびに呼ぶ。設定画面で許可を付けて戻ってきた場合、
     * ここで拾わないと「未許可」の表示のままになる。
     */
    fun refresh(force: Boolean = false) {
        if (loading && !force) return
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            loading = true
            val next = repository.report(range)
            report = next
            // カテゴリーが消えた／範囲を変えて対象が無くなった絞り込みは外す
            if (categoryFilter != null && next.categories.none { it.id == categoryFilter }) {
                categoryFilter = null
            }
            if (bucketFilter != null && bucketFilter !in next.bucketStarts) {
                bucketFilter = null
            }
            loading = false
        }
    }

    fun export() = viewModelScope.launch {
        val entries = filteredEntries
        if (entries.isEmpty()) {
            exportFailed = true
            return@launch
        }
        val intent = exporter.export(range, entries)
        if (intent == null) exportFailed = true else pendingExport = intent
    }

    fun consumeExport() {
        pendingExport = null
    }

    fun clearExportError() {
        exportFailed = false
    }
}
