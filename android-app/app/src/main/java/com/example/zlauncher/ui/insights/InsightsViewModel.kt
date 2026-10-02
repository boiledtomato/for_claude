package com.example.zlauncher.ui.insights

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zlauncher.data.insights.InsightsExporter
import com.example.zlauncher.data.insights.InsightsRepository
import com.example.zlauncher.domain.model.AppRollup
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

/** 右ペインの表示。同じデータを、集計 → アプリ → 生ログの順に細かくして見る */
enum class InsightView(val label: String) {
    CHART("Chart"),
    APPS("Apps"),
    LOG("Log"),
}

/** ドリルダウンで開いている対象 */
sealed interface InsightDetail {
    data class Category(val id: String) : InsightDetail
    data class App(val key: String) : InsightDetail
}

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

    /** 書き出せなかった理由。null なら何も出さない */
    var exportMessage by mutableStateOf<String?>(null)
        private set

    var view by mutableStateOf(InsightView.CHART)
        private set

    var detail by mutableStateOf<InsightDetail?>(null)
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

    /** 出せるものが無いのに押せるボタンを置かない */
    val canExport: Boolean get() = report.available && filteredEntries.isNotEmpty()

    /** 絞り込みを反映したアプリ別集計。カテゴリー絞り込みと検索が効く */
    val filteredApps: List<AppRollup>
        get() {
            val needle = query.trim().lowercase(Locale.getDefault())
            return report.apps.filter { app ->
                (categoryFilter == null || app.categoryId == categoryFilter) &&
                    (
                        needle.isEmpty() ||
                            app.label.lowercase(Locale.getDefault()).contains(needle) ||
                            app.packageName.lowercase(Locale.getDefault()).contains(needle) ||
                            app.categoryName.lowercase(Locale.getDefault()).contains(needle)
                        )
            }
        }

    fun selectView(next: InsightView) {
        view = next
    }

    fun openCategoryDetail(id: String) {
        detail = InsightDetail.Category(id)
    }

    fun openAppDetail(key: String) {
        detail = InsightDetail.App(key)
    }

    fun closeDetail() {
        detail = null
    }

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
            // 対象が消えたドリルダウンは閉じる。空の詳細が残ると操作不能に見える
            when (val open = detail) {
                is InsightDetail.Category ->
                    if (next.categories.none { it.id == open.id }) detail = null
                is InsightDetail.App ->
                    if (next.apps.none { it.key == open.key }) detail = null
                null -> Unit
            }
            loading = false
        }
    }

    fun export() = viewModelScope.launch {
        val entries = filteredEntries
        if (entries.isEmpty()) {
            // ボタン自体を出していないので通常は来ないが、競合したときに黙らせない
            exportMessage = if (!report.available) {
                "Grant usage access first — there is no data to export yet."
            } else {
                "Nothing to export for the current filter."
            }
            return@launch
        }
        val intent = exporter.export(range, entries)
        if (intent == null) {
            exportMessage = "Could not write the CSV file."
        } else {
            pendingExport = intent
        }
    }

    fun consumeExport() {
        pendingExport = null
    }

    fun clearExportMessage() {
        exportMessage = null
    }
}
