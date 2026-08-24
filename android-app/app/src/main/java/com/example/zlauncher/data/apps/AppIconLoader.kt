package com.example.zlauncher.data.apps

import android.content.Context
import android.util.Log
import android.util.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import com.example.zlauncher.core.ui.toImageBitmap
import com.example.zlauncher.domain.model.AppEntry
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

/**
 * アイコンのラスタライズは必ず IO で行い、結果をキャッシュする。
 * ここをメインスレッドでやるとアプリ数 × Adaptive Icon の描画で初回表示が固まる。
 *
 * 仕事用プロファイルのアイコンは PackageManager からは引けないので LauncherApps 経由で取り、
 * バッジ（ブリーフケース）を合成する。
 */
@Singleton
class AppIconLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSource: LauncherAppsDataSource,
) {
    private val cache = LruCache<String, ImageBitmap>(MAX_CACHED_ICONS)

    private val iconSizePx: Int
        get() = (ICON_SIZE_DP * context.resources.displayMetrics.density).roundToInt().coerceAtLeast(1)

    suspend fun load(entry: AppEntry): ImageBitmap? {
        cache.get(entry.key)?.let { return it }
        return withContext(Dispatchers.IO) {
            runCatching {
                val density = context.resources.displayMetrics.densityDpi
                val raw = dataSource.activityInfo(entry.componentName, entry.user)?.getIcon(density)
                    ?: context.packageManager.getActivityIcon(entry.componentName)
                val drawable = if (entry.isWorkProfile) {
                    context.packageManager.getUserBadgedIcon(raw, entry.user)
                } else {
                    raw
                }
                drawable.toImageBitmap(iconSizePx)
            }.onFailure {
                Log.w(TAG, "icon load failed for ${entry.componentName}", it)
            }.getOrNull()?.also { cache.put(entry.key, it) }
        }
    }

    private companion object {
        const val TAG = "AppIconLoader"
        const val MAX_CACHED_ICONS = 256
        const val ICON_SIZE_DP = 48
    }
}
