package com.botanical.launcher.flora

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * assets/flora/ から版面と器官の画像を読む。
 *
 * 器官はもともと版面の実寸で焼いてあるので、画面がそれより小さければ
 * 間引いて読む。ランチャーは起動が遅いと体感が悪い。
 */
object FloraLoader {

    private const val DIR = "flora"

    suspend fun load(context: Context, screenWidthPx: Int): Flora = withContext(Dispatchers.IO) {
        val assets = context.assets
        val json = runCatching {
            assets.open("$DIR/manifest.json").bufferedReader().use { it.readText() }
        }.getOrNull() ?: return@withContext Flora.Empty
        val flora = runCatching { parseFlora(json) }.getOrNull() ?: return@withContext Flora.Empty

        val sample = sampleSize(flora.width.toInt(), screenWidthPx)
        flora.sample = sample
        for (organ in flora.organs) {
            organ.bitmap = decode(context, "$DIR/${organ.imagePath}", sample)
        }
        flora.gemma?.frames?.forEach { it.bitmap = decode(context, "$DIR/${it.imagePath}", sample) }
        flora
    }

    private fun decode(context: Context, path: String, sample: Int): Bitmap? {
        val options = BitmapFactory.Options().apply {
            inSampleSize = sample
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        return runCatching {
            context.assets.open(path).use { BitmapFactory.decodeStream(it, null, options) }
        }.getOrNull()
    }

    /** 表示幅を下回らない範囲で最大の 2 の冪。 */
    private fun sampleSize(plateWidth: Int, screenWidth: Int): Int {
        if (screenWidth <= 0) return 1
        var s = 1
        while (plateWidth / (s * 2) >= screenWidth) s *= 2
        return s
    }
}
