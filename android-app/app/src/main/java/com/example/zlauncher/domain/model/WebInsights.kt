package com.example.zlauncher.domain.model

/**
 * Web Insights（カテゴリー別の通信ログ）のモデル。
 *
 * 端末が実際に返せるのは「UID 単位・時間バケット単位の受信/送信バイト数」までで、
 * URL もホスト名も取れない（それらは VPN サービスを立ててパケットを見る必要があり、
 * ランチャーの役割を大きく外れる）。したがってここでいう「ログ」は
 * 時刻 × アプリ × 回線種別 × バイト数の行であり、そのアプリが属する
 * ユーザー定義カテゴリーで束ねて見せる。
 */

/** 検索できる期間と、その中の 1 行の粒度 */
enum class InsightRange(
    val label: String,
    val windowMillis: Long,
    val bucketMillis: Long,
    /** 1 バケットの長さの表示名 */
    val bucketLabel: String,
) {
    LAST_HOUR("1H", 60L * 60_000, 5L * 60_000, "5 min"),
    LAST_DAY("24H", 24L * 60 * 60_000, 60L * 60_000, "1 hour"),

    /**
     * 週間検索。UID 別の統計はプラットフォーム側が 2 時間バケットで保持しており、
     * 保持期間も数週間しかないので、7 日を 6 時間刻みで読むのが実用上の上限。
     */
    LAST_WEEK("7D", 7L * 24 * 60 * 60_000, 6L * 60 * 60_000, "6 hours");

    val bucketCount: Int get() = (windowMillis / bucketMillis).toInt()

    /**
     * この範囲を刻んだときの各バケットの開始時刻。
     *
     * 実データからではなく範囲から作る。通信の無かった時間帯が軸から抜け落ちると、
     * 隣り合う棒が実際には離れた時刻なのに連続しているように見えてしまうため。
     * 境界に丸めるのは、更新のたびに棒が横滑りしないようにするため。
     */
    fun bucketStarts(now: Long): List<Long> {
        val start = ((now - windowMillis) / bucketMillis) * bucketMillis
        return generateSequence(start) { it + bucketMillis }.takeWhile { it < now }.toList()
    }
}

/** 回線種別。NetworkStatsManager が分けて返せるのはこの 2 つだけ */
enum class InsightTransport(val label: String) {
    WIFI("Wi-Fi"),
    MOBILE("Mobile"),
}

/** 未分類の行に使う擬似カテゴリー。実カテゴリーと同じ形で扱えるようにする */
const val UNCATEGORIZED_ID = "__uncategorized__"
const val UNCATEGORIZED_NAME = "Uncategorized"

/** 積み上げの「その他」。実カテゴリーではないので id を分けておく */
const val OTHER_ID = "__other__"

/** 色を持たないことを示す番兵。未分類と「その他」で使う */
const val NO_COLOR_INDEX = -1
const val OTHER_COLOR_INDEX = -2

/** 積み上げグラフで色を割り当てる上限。これを超えた分は Other にまとめる */
const val CHART_SERIES_LIMIT = 4

/** ログ 1 行 = 1 バケット × 1 アプリ（UID） × 1 回線種別 */
data class InsightLogEntry(
    val startMillis: Long,
    val endMillis: Long,
    val packageName: String,
    val label: String,
    val categoryId: String,
    val categoryName: String,
    val colorIndex: Int,
    val transport: InsightTransport,
    val rxBytes: Long,
    val txBytes: Long,
) {
    val totalBytes: Long get() = rxBytes + txBytes
}

/** カテゴリー単位の集計。[series] はバケット並びの合計バイト数（棒グラフ用） */
data class CategoryRollup(
    val id: String,
    val name: String,
    val colorIndex: Int,
    val rxBytes: Long,
    val txBytes: Long,
    val wifiBytes: Long,
    val mobileBytes: Long,
    val appCount: Int,
    val series: List<Long>,
) {
    val totalBytes: Long get() = rxBytes + txBytes
}

/** アプリ単位の集計。ドリルダウンで自分の時系列を出すので [series] を持つ */
data class AppRollup(
    val packageName: String,
    val label: String,
    val categoryId: String,
    val categoryName: String,
    val colorIndex: Int,
    val rxBytes: Long,
    val txBytes: Long,
    val wifiBytes: Long,
    val mobileBytes: Long,
    val series: List<Long>,
) {
    val totalBytes: Long get() = rxBytes + txBytes

    /** 一覧の識別子。Tethering のようにパッケージ名を持たない行はラベルで代用する */
    val key: String get() = packageName.ifEmpty { label }
}

/**
 * 1 回の照会結果。
 *
 * [available] が false のときは使用状況へのアクセス（Usage access）が未許可で、
 * 端末が UID 別の統計を返してくれない状態。
 */
data class WebInsightsReport(
    val range: InsightRange,
    val generatedAtMillis: Long,
    val available: Boolean,
    /** 各バケットの開始時刻。時系列グラフの X 軸 */
    val bucketStarts: List<Long>,
    val entries: List<InsightLogEntry>,
    val categories: List<CategoryRollup>,
    val apps: List<AppRollup>,
) {
    val totalRx: Long get() = categories.sumOf { it.rxBytes }
    val totalTx: Long get() = categories.sumOf { it.txBytes }
    val totalBytes: Long get() = totalRx + totalTx

    /** バケット並びの総量。時系列グラフの本体 */
    val series: List<Long>
        get() = List(bucketStarts.size) { index ->
            categories.sumOf { it.series.getOrElse(index) { 0L } }
        }

    /**
     * 積み上げグラフに出す系列。上位 [CHART_SERIES_LIMIT] 件だけを色付きで出し、
     * 残りは 1 本の「Other」にまとめる。
     *
     * 色を足して回すことはしない。7 色目以降は必ずどれかと見分けがつかなくなり、
     * 「色が違う＝別のカテゴリー」という読み方そのものが壊れるため。
     */
    fun chartSeries(): List<CategoryRollup> {
        val active = categories.filter { it.totalBytes > 0 }
        if (active.size <= CHART_SERIES_LIMIT) return active
        val head = active.take(CHART_SERIES_LIMIT)
        val tail = active.drop(CHART_SERIES_LIMIT)
        val merged = CategoryRollup(
            id = OTHER_ID,
            name = "Other (${tail.size})",
            colorIndex = OTHER_COLOR_INDEX,
            rxBytes = tail.sumOf { it.rxBytes },
            txBytes = tail.sumOf { it.txBytes },
            wifiBytes = tail.sumOf { it.wifiBytes },
            mobileBytes = tail.sumOf { it.mobileBytes },
            appCount = tail.sumOf { it.appCount },
            series = List(bucketStarts.size) { index ->
                tail.sumOf { it.series.getOrElse(index) { 0L } }
            },
        )
        return head + merged
    }

    companion object {
        fun empty(range: InsightRange, available: Boolean) = WebInsightsReport(
            range = range,
            generatedAtMillis = 0L,
            available = available,
            bucketStarts = emptyList(),
            entries = emptyList(),
            categories = emptyList(),
            apps = emptyList(),
        )
    }
}
