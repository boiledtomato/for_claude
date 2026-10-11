package com.example.zlauncher.data.insights

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.example.zlauncher.domain.model.InsightRange
import com.example.zlauncher.domain.model.InsightTransport
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

/** 集計前の 1 行。UID はまだアプリ名に解決していない */
data class RawUsage(
    val bucketStart: Long,
    val bucketEnd: Long,
    val transport: InsightTransport,
    val uid: Int,
    val rxBytes: Long,
    val txBytes: Long,
)

/**
 * `NetworkStatsManager` を時間バケットで読む。
 *
 * `querySummary` は指定区間を 1 つに畳んで UID ごとに返すだけなので、時系列が欲しければ
 * 区間を刻んで呼ぶしかない。7 日 × 6 時間 = 28 区間 × 回線 2 種で 56 回の照会になる。
 * 端末内のデータベースを引くだけとはいえ安くはないので、必ず IO で回して結果をキャッシュする。
 *
 * subscriberId には必ず null を渡す。API 29 以降、一般アプリが実際の値を渡すと
 * SecurityException になる（null は「全キャリア」の意味で受け付けられる）。
 */
@Singleton
class NetworkStatsSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun isAvailable(): Boolean = UsageAccess.isGranted(context)

    suspend fun query(range: InsightRange, now: Long): List<RawUsage> = withContext(Dispatchers.IO) {
        val manager = context.getSystemService<NetworkStatsManager>() ?: return@withContext emptyList()
        val rows = mutableListOf<RawUsage>()

        range.bucketStarts(now).forEach { windowStart ->
            coroutineContext.ensureActive()
            val windowEnd = minOf(windowStart + range.bucketMillis, now)
            for (transport in InsightTransport.entries) {
                collect(manager, transport, windowStart, windowEnd, rows)
            }
        }
        rows
    }

    private fun collect(
        manager: NetworkStatsManager,
        transport: InsightTransport,
        windowStart: Long,
        windowEnd: Long,
        into: MutableList<RawUsage>,
    ) {
        // 権限が外れた・端末が未対応など、失敗の形が多いので握って空に落とす
        val stats = runCatching {
            manager.querySummary(transport.legacyType, null, windowStart, windowEnd)
        }.getOrNull() ?: return

        // 同じ UID が set / tag / metered 違いで複数バケットに分かれて出てくるのでまとめる
        val perUid = HashMap<Int, LongArray>()
        try {
            val bucket = NetworkStats.Bucket()
            while (stats.hasNextBucket()) {
                if (!stats.getNextBucket(bucket)) break
                val rx = bucket.rxBytes
                val tx = bucket.txBytes
                if (rx <= 0 && tx <= 0) continue
                val totals = perUid.getOrPut(bucket.uid) { LongArray(2) }
                totals[0] += rx
                totals[1] += tx
            }
        } catch (_: Throwable) {
            // 途中で失敗しても、そこまでに読めた分は使う
        } finally {
            runCatching { stats.close() }
        }

        perUid.forEach { (uid, totals) ->
            into += RawUsage(
                bucketStart = windowStart,
                bucketEnd = windowEnd,
                transport = transport,
                uid = uid,
                rxBytes = totals[0],
                txBytes = totals[1],
            )
        }
    }
}

/**
 * `NetworkStatsManager` は今も旧来の `ConnectivityManager.TYPE_*` を受け取る。
 * NetworkCapabilities を取る新しい入口は用意されていないので、非推奨のまま使う。
 */
@Suppress("DEPRECATION")
private val InsightTransport.legacyType: Int
    get() = when (this) {
        InsightTransport.WIFI -> ConnectivityManager.TYPE_WIFI
        InsightTransport.MOBILE -> ConnectivityManager.TYPE_MOBILE
    }
