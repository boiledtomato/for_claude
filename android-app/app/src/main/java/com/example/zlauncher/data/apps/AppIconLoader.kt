package com.example.zlauncher.data.apps

import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.util.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

/**
 * アイコンのラスタライズは必ず IO で行い、結果をキャッシュする。
 * ここをメインスレッドでやるとアプリ数 × Adaptive Icon の描画で初回表示が固まる。
 */
@Singleton
class AppIconLoader @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val cache = LruCache<String, ImageBitmap>(MAX_CACHED_ICONS)

    private val iconSizePx: Int
        get() = (ICON_SIZE_DP * context.resources.displayMetrics.density).roundToInt().coerceAtLeast(1)

    fun cached(componentName: ComponentName): ImageBitmap? = cache.get(componentName.flattenToShortString())

    suspend fun load(componentName: ComponentName): ImageBitmap? {
        val key = componentName.flattenToShortString()
        cache.get(key)?.let { return it }
        return withContext(Dispatchers.IO) {
            runCatching {
                val size = iconSizePx
                val drawable = context.packageManager.getActivityIcon(componentName)
                drawable.toBitmap(width = size, height = size).asImageBitmap()
            }.onFailure {
                Log.w(TAG, "icon load failed for $componentName", it)
            }.getOrNull()?.also { cache.put(key, it) }
        }
    }

    private companion object {
        const val TAG = "AppIconLoader"
        const val MAX_CACHED_ICONS = 256
        const val ICON_SIZE_DP = 48
    }
}
