package com.example.zlauncher.data.apps

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.util.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.createBitmap
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

    suspend fun load(entry: AppEntry, themed: Boolean = false): ImageBitmap? {
        val key = if (themed) entry.key + THEMED_SUFFIX else entry.key
        cache.get(key)?.let { return it }
        return withContext(Dispatchers.IO) {
            runCatching {
                val density = context.resources.displayMetrics.densityDpi
                val raw = dataSource.activityInfo(entry.componentName, entry.user)?.getIcon(density)
                    ?: context.packageManager.getActivityIcon(entry.componentName)

                // モノクロレイヤーを持つアプリだけテーマ色に寄せる。無ければ通常のアイコン
                val monochrome = if (themed) monochromeOf(raw) else null
                if (monochrome != null) {
                    renderMonochrome(monochrome, iconSizePx)
                } else {
                    val drawable = if (entry.isWorkProfile) {
                        context.packageManager.getUserBadgedIcon(raw, entry.user)
                    } else {
                        raw
                    }
                    drawable.toImageBitmap(iconSizePx)
                }
            }.onFailure {
                Log.w(TAG, "icon load failed for ${entry.componentName}", it)
            }.getOrNull()?.also { cache.put(key, it) }
        }
    }

    private fun monochromeOf(drawable: Drawable): Drawable? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return null
        if (drawable !is AdaptiveIconDrawable) return null
        return runCatching {
            drawable.monochrome?.mutate()?.apply { setTint(THEMED_TINT) }
        }.getOrNull()
    }

    /**
     * Adaptive Icon のレイヤーは 108 のうち内側 72 だけが見える前提で描かれている。
     * 外側 25% を切り落として可視領域をタイルいっぱいに合わせる。
     */
    private fun renderMonochrome(monochrome: Drawable, size: Int): ImageBitmap {
        val bitmap = createBitmap(size, size)
        val canvas = Canvas(bitmap)
        val inset = (size * 0.25f).toInt()
        monochrome.setBounds(-inset, -inset, size + inset, size + inset)
        monochrome.draw(canvas)
        return bitmap.asImageBitmap()
    }

    private companion object {
        const val TAG = "AppIconLoader"
        const val MAX_CACHED_ICONS = 256
        const val ICON_SIZE_DP = 48
        const val THEMED_SUFFIX = "#themed"
        /** ZColors.AccentAlt と同じ値。data 層から designsystem を参照しないよう定数で持つ */
        const val THEMED_TINT = 0xFF22D3EE.toInt()
    }
}
