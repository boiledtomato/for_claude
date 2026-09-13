package com.botanical.launcher.garden

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * assets/plates/ から版面を読む。
 *
 * 図版は複数入っていて、plates/index.json がその一覧、
 * plates/<id>/plate.json が 1 枚ぶんの定義になる。
 *
 * スキャン画像は元が数千 px あるので、画面上で実際に必要な大きさまで
 * 間引いて読む。等倍で持つと数枚で数百 MB になり、ランチャーとしては即死する。
 */
object PlateLoader {

    private const val ROOT = "plates"

    suspend fun loadCatalog(context: Context): PlateCatalog = withContext(Dispatchers.IO) {
        val json = runCatching {
            context.assets.open("$ROOT/index.json").bufferedReader().use { it.readText() }
        }.getOrNull() ?: return@withContext PlateCatalog.Empty
        runCatching { parseCatalog(json) }.getOrNull() ?: PlateCatalog.Empty
    }

    suspend fun load(
        context: Context,
        plateId: String,
        screenWidthPx: Int,
    ): Plate = withContext(Dispatchers.IO) {
        val assets = context.assets
        val dir = "$ROOT/$plateId"
        val json = runCatching {
            assets.open("$dir/plate.json").bufferedReader().use { it.readText() }
        }.getOrNull() ?: return@withContext Plate.Empty

        val plate = runCatching { parsePlate(json) }.getOrNull() ?: return@withContext Plate.Empty

        // 版面 1 単位あたりの画面ピクセル数。切り抜き画像はこの解像度まであれば足りる。
        val unitPx = if (plate.width > 0f) screenWidthPx / plate.width else 1f

        for (layer in plate.plants) {
            layer.bitmap = decode(assets, dir, layer.imagePath, (layer.rect.width * unitPx).toInt())
        }
        plate.gemma?.let { gemma ->
            val targetPx = ((gemma.rect?.width ?: 200f) * unitPx).toInt()
            gemma.openBitmap = gemma.openImagePath?.let { decode(assets, dir, it, targetPx) }
        }
        plate
    }

    private fun decode(
        assets: AssetManager,
        dir: String,
        path: String,
        targetWidthPx: Int,
    ): Bitmap? {
        val full = "$dir/$path"
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        runCatching { assets.open(full).use { BitmapFactory.decodeStream(it, null, bounds) } }
            .getOrNull()
        if (bounds.outWidth <= 0) return null

        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize(bounds.outWidth, targetWidthPx)
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        return runCatching {
            assets.open(full).use { BitmapFactory.decodeStream(it, null, options) }
        }.getOrNull()
    }

    /** 表示幅を下回らない範囲で最大の 2 の冪。 */
    private fun sampleSize(sourceWidth: Int, targetWidth: Int): Int {
        if (targetWidth <= 0) return 1
        var sample = 1
        while (sourceWidth / (sample * 2) >= targetWidth) sample *= 2
        return sample
    }
}
