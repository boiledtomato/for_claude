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
import com.example.zlauncher.domain.model.InsightLogEntry
import com.example.zlauncher.domain.model.InsightRange
import com.example.zlauncher.domain.model.UNCATEGORIZED_ID
import com.example.zlauncher.domain.model.UNCATEGORIZED_NAME
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
 * どのカテゴリーにも属さないものは [UNCATEGORIZED_ID] にまとめる（捨てない。
 * 分類していないアプリが通信の大半を占めている、という事実こそ見たいものなので）。
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

        // パッケージ → 最初に一致したカテゴリー。1 アプリが複数カテゴリーに入っていても二重計上しない
        val categoryOfPackage = HashMap<String, Triple<String, String, Int>>()
        categories.forEach { category ->
            category.packages.forEach { pkg ->
                categoryOfPackage.getOrPut(pkg) {
                    Triple(category.id, category.name, category.colorIndex)
                }
            }
        }

        val raw = source.query(range, now)
        val resolver = UidResolver(context, labels, categoryOfPackage)

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
                categoryId = identity.categoryId,
                categoryName = identity.categoryName,
                colorIndex = identity.colorIndex,
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
            categories = rollUpCategories(entries, categories, bucketIndex, bucketStarts.size),
            apps = rollUpApps(entries),
        )
    }

    /**
     * 作ったカテゴリーは、その窓で通信が無くても 0 の行として必ず出す。
     * 一覧から消えると「カテゴリーが壊れた」ようにしか見えないため。
     */
    private fun rollUpCategories(
        entries: List<InsightLogEntry>,
        defined: List<AppCategory>,
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
                    appCount = 0,
                    series = List(bucketCount) { 0L },
                )
            }

        // 通信量の多い順。未分類は最後に固定して、作ったカテゴリーが上に来るようにする
        val rank = defined.withIndex().associate { (index, category) -> category.id to index }
        return (measured + silent).sortedWith(
            compareBy<CategoryRollup> { it.id == UNCATEGORIZED_ID }
                .thenByDescending { it.totalBytes }
                .thenBy { rank[it.id] ?: Int.MAX_VALUE }
        )
    }

    private fun rollUpApps(entries: List<InsightLogEntry>): List<AppRollup> =
        entries.groupBy { it.packageName }
            .map { (packageName, rows) ->
                AppRollup(
                    packageName = packageName,
                    label = rows.first().label,
                    categoryId = rows.first().categoryId,
                    categoryName = rows.first().categoryName,
                    colorIndex = rows.first().colorIndex,
                    rxBytes = rows.sumOf { it.rxBytes },
                    txBytes = rows.sumOf { it.txBytes },
                )
            }
            .sortedByDescending { it.totalBytes }
}

private data class UidIdentity(
    val packageName: String,
    val label: String,
    val categoryId: String,
    val categoryName: String,
    val colorIndex: Int,
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
    private val categoryOfPackage: Map<String, Triple<String, String, Int>>,
) {
    private val packageManager: PackageManager = context.packageManager
    private val cache = HashMap<Int, UidIdentity>()

    fun resolve(uid: Int): UidIdentity = cache.getOrPut(uid) { compute(uid) }

    private fun compute(uid: Int): UidIdentity {
        specialLabel(uid)?.let { return uncategorized(packageName = "", label = it) }

        val packages = runCatching { packageManager.getPackagesForUid(uid) }
            .getOrNull()
            ?.toList()
            .orEmpty()

        val classified = packages.firstOrNull { categoryOfPackage.containsKey(it) }
        val packageName = classified ?: packages.firstOrNull()
        if (packageName == null) {
            val fallback = runCatching { packageManager.getNameForUid(uid) }.getOrNull()
            return uncategorized(packageName = fallback.orEmpty(), label = fallback ?: "UID $uid")
        }

        val label = labels[packageName] ?: loadLabel(packageName) ?: packageName
        val category = categoryOfPackage[packageName]
            ?: return uncategorized(packageName, label)
        return UidIdentity(
            packageName = packageName,
            label = label,
            categoryId = category.first,
            categoryName = category.second,
            colorIndex = category.third,
        )
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

    private fun uncategorized(packageName: String, label: String) = UidIdentity(
        packageName = packageName,
        label = label,
        categoryId = UNCATEGORIZED_ID,
        categoryName = UNCATEGORIZED_NAME,
        colorIndex = UNCATEGORIZED_COLOR_INDEX,
    )

    private companion object {
        /** ZColors.CategoryColors の範囲外を指して、無彩色で描かせる */
        const val UNCATEGORIZED_COLOR_INDEX = -1
    }
}
