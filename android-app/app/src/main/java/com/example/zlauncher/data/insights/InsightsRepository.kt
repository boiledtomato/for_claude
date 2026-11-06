package com.example.zlauncher.data.insights

import android.app.usage.NetworkStats
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import com.example.zlauncher.data.apps.InstalledAppRepository
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import com.example.zlauncher.domain.model.AppCategory
import com.example.zlauncher.domain.model.AppRollup
import com.example.zlauncher.domain.model.CategoryRollup
import com.example.zlauncher.domain.model.InsightBucket
import com.example.zlauncher.domain.model.InsightCategories
import com.example.zlauncher.domain.model.InsightLogEntry
import com.example.zlauncher.domain.model.InsightRange
import com.example.zlauncher.domain.model.InsightTransport
import com.example.zlauncher.domain.model.WebInsightsReport
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 生の UID 別通信量を、ユーザーが作ったカテゴリーの軸に載せ替える。
 *
 * カテゴリーは「パッケージ名の集合」なので、UID → パッケージ → カテゴリーの順に引く。
 * どのカテゴリーにも属さないものは [InsightCategories.fallback] の枠にまとめる
 * （捨てない。分類していないアプリが通信の大半を占めている、という事実こそ見たいもの
 * なので）。その枠は、`Miscellaneous / Unknown` を有効にしていればそれ自身になる。
 */
@Singleton
class InsightsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val source: NetworkStatsSource,
    private val preferences: LauncherPreferencesRepository,
    private val installedApps: InstalledAppRepository,
) {

    fun isAvailable(): Boolean = source.isAvailable()

    suspend fun report(
        range: InsightRange,
        now: Long = System.currentTimeMillis(),
    ): WebInsightsReport = withContext(Dispatchers.IO) {
        if (!source.isAvailable()) return@withContext WebInsightsReport.empty(range, available = false)

        val categories = preferences.state.first().categories
        val labels = installedApps.apps.first()
            .filterNot { it.isWorkProfile }
            .associate { it.packageName to it.label }

        // 明示的な所属と、こぼれたものの置き場。規則は InsightCategories 側に置いてある
        val owners = InsightCategories.explicitOwners(categories)
        val fallback = InsightCategories.fallback(categories)

        val raw = source.query(range, now)
        val resolver = UidResolver(context, labels, owners, fallback)

        // 軸は実データではなく範囲から作る。無通信の時間帯も 0 の棒として残す
        val bucketStarts = range.bucketStarts(now)
        val bucketIndex = bucketStarts.withIndex().associate { (index, start) -> start to index }

        val entries = raw.map { usage ->
            val identity = resolver.resolve(usage.uid)
            InsightLogEntry(
                startMillis = usage.bucketStart,
                endMillis = usage.bucketEnd,
                packageName = identity.packageName,
                label = identity.label,
                categoryId = identity.bucket.id,
                categoryName = identity.bucket.name,
                colorIndex = identity.bucket.colorIndex,
                transport = usage.transport,
                rxBytes = usage.rxBytes,
                txBytes = usage.txBytes,
            )
        }.sortedWith(compareByDescending<InsightLogEntry> { it.startMillis }.thenByDescending { it.totalBytes })

        WebInsightsReport(
            range = range,
            generatedAtMillis = now,
            available = true,
            bucketStarts = bucketStarts,
            entries = entries,
            categories = rollUpCategories(entries, categories, fallback, bucketIndex, bucketStarts.size),
            apps = rollUpApps(entries, bucketIndex, bucketStarts.size),
        )
    }

    /**
     * 作ったカテゴリーは、その窓で通信が無くても 0 の行として必ず出す。
     * 一覧から消えると「カテゴリーが壊れた」ようにしか見えないため。
     */
    private fun rollUpCategories(
        entries: List<InsightLogEntry>,
        defined: List<AppCategory>,
        fallback: InsightBucket,
        bucketIndex: Map<Long, Int>,
        bucketCount: Int,
    ): List<CategoryRollup> {
        val grouped = entries.groupBy { it.categoryId }
        val measured = grouped.map { (id, rows) ->
            val series = LongArray(bucketCount)
            rows.forEach { row ->
                bucketIndex[row.startMillis]?.let { index -> series[index] += row.totalBytes }
            }
            CategoryRollup(
                id = id,
                name = rows.first().categoryName,
                colorIndex = rows.first().colorIndex,
                rxBytes = rows.sumOf { it.rxBytes },
                txBytes = rows.sumOf { it.txBytes },
                wifiBytes = rows.filter { it.transport == InsightTransport.WIFI }.sumOf { it.totalBytes },
                mobileBytes = rows.filter { it.transport == InsightTransport.MOBILE }.sumOf { it.totalBytes },
                appCount = rows.map { it.packageName }.distinct().size,
                series = series.toList(),
            )
        }
        val silent = defined
            .filterNot { category -> grouped.containsKey(category.id) }
            .map { category ->
                CategoryRollup(
                    id = category.id,
                    name = category.name,
                    colorIndex = category.colorIndex,
                    rxBytes = 0,
                    txBytes = 0,
                    wifiBytes = 0,
                    mobileBytes = 0,
                    appCount = 0,
                    series = List(bucketCount) { 0L },
                )
            }

        return InsightCategories.sort(measured + silent, defined, fallback.id)
    }

    /** キーはパッケージ名。名前しか分からない UID（Tethering など）はラベルで束ねる */
    private fun rollUpApps(
        entries: List<InsightLogEntry>,
        bucketIndex: Map<Long, Int>,
        bucketCount: Int,
    ): List<AppRollup> =
        entries.groupBy { it.packageName.ifEmpty { it.label } }
            .map { (_, rows) ->
                val series = LongArray(bucketCount)
                rows.forEach { row ->
                    bucketIndex[row.startMillis]?.let { index -> series[index] += row.totalBytes }
                }
                AppRollup(
                    packageName = rows.first().packageName,
                    label = rows.first().label,
                    categoryId = rows.first().categoryId,
                    categoryName = rows.first().categoryName,
                    colorIndex = rows.first().colorIndex,
                    rxBytes = rows.sumOf { it.rxBytes },
                    txBytes = rows.sumOf { it.txBytes },
                    wifiBytes = rows.filter { it.transport == InsightTransport.WIFI }.sumOf { it.totalBytes },
                    mobileBytes = rows.filter { it.transport == InsightTransport.MOBILE }.sumOf { it.totalBytes },
                    series = series.toList(),
                )
            }
            .sortedByDescending { it.totalBytes }
}

private data class UidIdentity(
    val packageName: String,
    val label: String,
    val bucket: InsightBucket,
)

/**
 * UID をアプリ名とカテゴリーに解決する。1 レポート内で同じ UID を何度も引くので結果を持ち回る。
 *
 * 共有 UID（`android:sharedUserId`）だと 1 UID に複数パッケージがぶら下がる。その場合は
 * ユーザーがカテゴリーに入れたパッケージを優先して代表にする。分類した側の意図に沿うため。
 */
private class UidResolver(
    context: Context,
    private val labels: Map<String, String>,
    private val owners: Map<String, InsightBucket>,
    private val fallback: InsightBucket,
) {
    private val packageManager: PackageManager = context.packageManager
    private val cache = HashMap<Int, UidIdentity>()

    fun resolve(uid: Int): UidIdentity = cache.getOrPut(uid) { compute(uid) }

    private fun compute(uid: Int): UidIdentity {
        specialLabel(uid)?.let { return unclaimed(packageName = "", label = it) }

        val packages = runCatching { packageManager.getPackagesForUid(uid) }
            .getOrNull()
            ?.toList()
            .orEmpty()

        val classified = packages.firstOrNull { owners.containsKey(it) }
        val packageName = classified ?: packages.firstOrNull()
        if (packageName == null) {
            val fallbackName = runCatching { packageManager.getNameForUid(uid) }.getOrNull()
            return unclaimed(packageName = fallbackName.orEmpty(), label = fallbackName ?: "UID $uid")
        }

        val label = labels[packageName] ?: loadLabel(packageName) ?: packageName
        val bucket = owners[packageName] ?: return unclaimed(packageName, label)
        return UidIdentity(packageName = packageName, label = label, bucket = bucket)
    }

    private fun loadLabel(packageName: String): String? = runCatching {
        packageManager.getApplicationInfo(packageName, 0).loadLabel(packageManager).toString()
    }.getOrNull()

    /** アプリに紐づかない UID。素通しすると "UID -5" のような行になって読めない */
    private fun specialLabel(uid: Int): String? = when (uid) {
        NetworkStats.Bucket.UID_TETHERING -> "Tethering"
        NetworkStats.Bucket.UID_REMOVED -> "Uninstalled apps"
        NetworkStats.Bucket.UID_ALL -> "All traffic"
        Process.SYSTEM_UID -> "Android OS"
        else -> null
    }

    /**
     * どのカテゴリーも抱えていない行。置き場に載せる ―
     * `Miscellaneous / Unknown` が有効ならそれ自身、無効なら `Uncategorized`。
     */
    private fun unclaimed(packageName: String, label: String) = UidIdentity(
        packageName = packageName,
        label = label,
        bucket = fallback,
    )
}
