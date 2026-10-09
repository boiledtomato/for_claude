package com.botanical.launcher.flora

import android.graphics.Bitmap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import org.json.JSONArray
import org.json.JSONObject

/**
 * 版面の定義。assets/flora/manifest.json が中身。
 *
 * 葉と花は 1 つずつ画像に焼いてある（ハッチングは 1 枚に 100 本以上引くので、
 * 毎フレーム描き直すには重すぎる）。焼いた画像を付け根を軸に回すことで、
 * 密度を落とさずに個別に動かす。
 *
 * 茎は形が毎フレーム変わるので画像にせず、その場で線を引く。
 */
data class Flora(
    val width: Float,
    val height: Float,
    val paper: Color,
    val stems: List<Stem>,
    val organs: List<Organ>,
    val gemma: Gemma?,
    val roots: Roots?,
    val vine: Vine?,
    val caption: String,
    val plateNo: String,
) {
    val stemById: Map<String, Stem> = stems.associateBy { it.id }

    /** タップできる器官と、アプリ一覧を開く蕾。 */
    val tapTargets: List<TapTarget> =
        organs.mapNotNull { o -> o.hit?.let { TapTarget(o.id, it.at, it.r, o.label) } } +
            listOfNotNull(gemma?.let { TapTarget(it.id, it.hit.at, it.hit.r, "Bud") })

    val bindable: List<String> = organs.mapNotNull { o -> o.hit?.let { o.id } }

    /** 画像を間引いて読んだ倍率。描くときに戻す。 */
    var sample: Int = 1

    fun target(id: String): TapTarget? = tapTargets.firstOrNull { it.id == id }

    companion object {
        val Empty = Flora(1100f, 1800f, Palette.Paper, emptyList(), emptyList(),
            null, null, null, "", "")
    }
}

data class TapTarget(val id: String, val at: Offset, val radius: Float, val label: String)

/** 揺れ。[speed] は基準周期に対する整数倍。整数でないと 1 周ごとに動きが飛ぶ。 */
data class Sway(val amp: Float, val speed: Int, val phase: Float)

data class Stem(
    val id: String,
    val p: List<Offset>,
    val w0: Float,
    val w1: Float,
    val tone: Float,
    val depth: Float,
    val sway: Sway,
)

data class Hit(val at: Offset, val r: Float)

data class Organ(
    val id: String,
    val stem: String,
    val t: Float,
    val imagePath: String,
    /** 画像左上の版面座標 */
    val off: Offset,
    /** 付け根の版面座標。回転の中心になる */
    val pivot: Offset,
    /** 焼いたときの茎の接線方向。実行時との差が回転量になる */
    val restAngle: Float,
    val sway: Sway,
    val hit: Hit?,
    val z: Int,
    val label: String,
) {
    var bitmap: Bitmap? = null
}

data class BloomFrame(val imagePath: String, val off: Offset) {
    var bitmap: Bitmap? = null
}

/** 根。土の中なので風では動かない。1 枚に焼いてそのまま置く。 */
data class Roots(val imagePath: String, val off: Offset) {
    var bitmap: Bitmap? = null
}

/** 蔦の素材。蔓の形は実行時に決まるが、葉と先の花は焼いておける。 */
data class VineSprite(val imagePath: String, val off: Offset, val reach: Float = 0f) {
    var bitmap: Bitmap? = null
}

data class Vine(
    /** 蔓につく蔦形の葉。大きい順 */
    val leaves: List<VineSprite>,
    /** 蕾がほどけて咲くまでの連続コマ */
    val bloom: List<VineSprite>,
    /** 焼いたときの花の大きさ。実行時の拡大率を決めるのに使う */
    val size: Float,
)

data class Gemma(
    val id: String,
    val stem: String,
    val t: Float,
    val pivot: Offset,
    val restAngle: Float,
    val frames: List<BloomFrame>,
    val hit: Hit,
    val sway: Sway,
)

private fun JSONArray.offset() = Offset(getDouble(0).toFloat(), getDouble(1).toFloat())

private fun JSONObject.sway() = Sway(
    amp = optDouble("amp", 0.0).toFloat(),
    speed = optInt("speed", 1),
    phase = optDouble("phase", 0.0).toFloat(),
)

private fun JSONObject.hit() = Hit(getJSONArray("at").offset(), getDouble("r").toFloat())

/** 葉か花かを id から読む。表示名に使うだけなので簡単に判定する。 */
private fun labelOf(id: String): String = when {
    id.contains("flos") -> "Flower"
    id.contains("leaf") -> "Leaf"
    else -> "Part"
}

fun parseFlora(json: String): Flora {
    val root = JSONObject(json)
    val plate = root.getJSONObject("plate")

    val stems = root.getJSONArray("stems").let { arr ->
        (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            val p = o.getJSONArray("p")
            Stem(
                id = o.getString("id"),
                p = (0 until p.length()).map { p.getJSONArray(it).offset() },
                w0 = o.getDouble("w0").toFloat(),
                w1 = o.getDouble("w1").toFloat(),
                tone = o.getDouble("tone").toFloat(),
                depth = o.getDouble("depth").toFloat(),
                sway = o.getJSONObject("sway").sway(),
            )
        }
    }

    val organs = root.getJSONArray("organs").let { arr ->
        (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            Organ(
                id = o.getString("id"),
                stem = o.getString("stem"),
                t = o.getDouble("t").toFloat(),
                imagePath = o.getString("image"),
                off = o.getJSONArray("off").offset(),
                pivot = o.getJSONArray("pivot").offset(),
                restAngle = o.getDouble("restAngle").toFloat(),
                sway = o.getJSONObject("sway").sway(),
                hit = o.optJSONObject("hit")?.hit(),
                z = o.optInt("z", i),
                label = labelOf(o.getString("id")),
            )
        }
    }.sortedBy { it.z }

    val gemma = root.optJSONObject("gemma")?.let { g ->
        val fr = g.getJSONArray("frames")
        Gemma(
            id = g.getString("id"),
            stem = g.getString("stem"),
            t = g.getDouble("t").toFloat(),
            pivot = g.getJSONArray("pivot").offset(),
            restAngle = g.getDouble("restAngle").toFloat(),
            frames = (0 until fr.length()).map {
                val f = fr.getJSONObject(it)
                BloomFrame(f.getString("image"), f.getJSONArray("off").offset())
            },
            hit = g.getJSONObject("hit").hit(),
            sway = g.getJSONObject("sway").sway(),
        )
    }

    val roots = root.optJSONObject("roots")?.let {
        Roots(it.getString("image"), it.getJSONArray("off").offset())
    }

    val vine = root.optJSONObject("vine")?.let { v ->
        fun sprites(key: String) = v.getJSONArray(key).let { arr ->
            (0 until arr.length()).map { i ->
                val o = arr.getJSONObject(i)
                VineSprite(o.getString("image"), o.getJSONArray("off").offset(),
                    o.optDouble("reach", 0.0).toFloat())
            }
        }
        Vine(sprites("leaves"), sprites("bloom"), v.optDouble("size", 92.0).toFloat())
    }

    val paperHex = plate.optString("paper", "#F6F3EC").removePrefix("#")
    return Flora(
        width = plate.getDouble("width").toFloat(),
        height = plate.getDouble("height").toFloat(),
        paper = Color(0xFF000000L or paperHex.toLong(16)),
        stems = stems,
        organs = organs,
        gemma = gemma,
        roots = roots,
        vine = vine,
        caption = plate.optString("caption", ""),
        plateNo = plate.optString("plateNo", ""),
    )
}
