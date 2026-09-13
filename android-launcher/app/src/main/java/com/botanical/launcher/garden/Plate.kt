package com.botanical.launcher.garden

import android.graphics.Bitmap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

/**
 * 版面の定義。19 世紀の石版画スキャンから切り抜いた植物を、
 * 1 枚の紙の上に並べたもの。形は assets/plate/plate.json が持つ。
 *
 * 座標系は「版面座標」で、[width] x [height] の固定平面。
 * 実画面へはこの平面ごと拡大縮小して収める（SceneTransform）。
 */
data class Plate(
    val width: Float,
    val height: Float,
    val number: String,
    val paper: Color,
    val paperDeep: Color,
    val backgroundImage: String?,
    val frame: Boolean,
    val plants: List<PlantLayer>,
    val gemma: Gemma?,
) {
    val organs: List<Pair<PlantLayer, OrganSpot>> =
        plants.flatMap { p -> p.organs.map { p to it } }

    /** アプリを割り当てられる器官（蕾を除く）。 */
    val bindableOrgans: List<Pair<PlantLayer, OrganSpot>> =
        organs.filter { it.second.id != gemma?.organId }

    /** タップ判定の対象。割り当て可能な器官＋アプリ一覧を開く蕾。 */
    val tapTargets: List<Pair<PlantLayer, OrganSpot>> = organs

    fun organ(id: String): Pair<PlantLayer, OrganSpot>? =
        organs.firstOrNull { it.second.id == id }

    companion object {
        val Empty = Plate(
            width = 1000f, height = 2100f, number = "", paper = Color(0xFFF3EAD3),
            paperDeep = Color(0xFFE5D8B6), backgroundImage = null, frame = false,
            plants = emptyList(), gemma = null,
        )
    }
}

/**
 * 1 株ぶんの切り抜き画像とその配置。
 *
 * @param rect 版面上での配置矩形
 * @param pivot 画像内での「根元」。ここを固定して上ほど大きく撓ませる（0..1 の割合）
 * @param bendAmplitude 撓みの最大横移動量（版面単位）
 * @param bendSpeed 全体位相に対する整数倍。整数でないと 1 周ごとに動きが飛ぶ
 */
data class PlantLayer(
    val id: String,
    val latin: String,
    val number: String,
    val imagePath: String,
    val rect: Rect,
    val pivot: Offset,
    val bendAmplitude: Float,
    val bendSpeed: Int,
    val bendPhase: Float,
    val captionAt: Offset?,
    val organs: List<OrganSpot>,
) {
    var bitmap: Bitmap? = null
}

enum class OrganKind { LEAF, FLOWER, FRUIT, BUD }

/**
 * タップできる部位。切り抜き画像の中の円で表す。
 *
 * @param at 画像内の中心（0..1 の割合）
 * @param radius 判定半径。画像の幅に対する割合
 */
data class OrganSpot(
    val id: String,
    val kind: OrganKind,
    val at: Offset,
    val radius: Float,
    val label: String,
) {
    /** 版面座標での中心。 */
    fun center(layer: PlantLayer): Offset = Offset(
        layer.rect.left + at.x * layer.rect.width,
        layer.rect.top + at.y * layer.rect.height,
    )

    /** 版面座標での判定半径。 */
    fun radius(layer: PlantLayer): Float = radius * layer.rect.width
}

/**
 * アプリ一覧を開く蕾。
 *
 * 開花は「蕾を消して花を描く」のではなく、[rect] に開いた花を重ねて蕾を覆う。
 * 図版から蕾だけを消すと背後の茎まで欠けるので、上書きの方が破綻しない。
 */
data class Gemma(
    val organId: String,
    val openImagePath: String?,
    val rect: Rect?,
) {
    var openBitmap: Bitmap? = null
}

/** plate.json を読む。Android 同梱の org.json だけで足りるので依存は増やさない。 */
fun parsePlate(json: String): Plate {
    val root = JSONObject(json)
    val plateObj = root.getJSONObject("plate")
    val gemmaObj = root.optJSONObject("gemma")

    val plants = root.getJSONArray("plants").let { arr ->
        (0 until arr.length()).map { i ->
            val o = arr.getJSONObject(i)
            val id = o.getString("id")
            val r = o.getJSONArray("rect")
            val pivot = o.optJSONArray("pivot")
            val bend = o.optJSONObject("bend")
            val caption = o.optJSONArray("captionAt")
            PlantLayer(
                id = id,
                latin = o.optString("latin", ""),
                number = o.optString("number", ""),
                imagePath = o.getString("image"),
                rect = Rect(
                    left = r.getDouble(0).toFloat(),
                    top = r.getDouble(1).toFloat(),
                    right = (r.getDouble(0) + r.getDouble(2)).toFloat(),
                    bottom = (r.getDouble(1) + r.getDouble(3)).toFloat(),
                ),
                pivot = Offset(
                    pivot?.getDouble(0)?.toFloat() ?: 0.5f,
                    pivot?.getDouble(1)?.toFloat() ?: 1f,
                ),
                bendAmplitude = bend?.optDouble("amplitude", 12.0)?.toFloat() ?: 12f,
                bendSpeed = bend?.optInt("speed", 1) ?: 1,
                bendPhase = bend?.optDouble("phase", 0.0)?.toFloat() ?: 0f,
                captionAt = caption?.let {
                    Offset(it.getDouble(0).toFloat(), it.getDouble(1).toFloat())
                },
                organs = o.optJSONArray("organs").let { organs ->
                    if (organs == null) emptyList() else (0 until organs.length()).map { j ->
                        val g = organs.getJSONObject(j)
                        val at = g.getJSONArray("at")
                        OrganSpot(
                            id = "$id/${g.getString("id")}",
                            kind = when (g.optString("kind", "leaf")) {
                                "flower" -> OrganKind.FLOWER
                                "fruit" -> OrganKind.FRUIT
                                "bud" -> OrganKind.BUD
                                else -> OrganKind.LEAF
                            },
                            at = Offset(at.getDouble(0).toFloat(), at.getDouble(1).toFloat()),
                            radius = g.optDouble("radius", 0.14).toFloat(),
                            label = g.optString("label", ""),
                        )
                    }
                },
            )
        }
    }

    return Plate(
        width = plateObj.optDouble("width", 1000.0).toFloat(),
        height = plateObj.optDouble("height", 2100.0).toFloat(),
        number = plateObj.optString("number", ""),
        paper = parseColor(plateObj.optString("paper", "#F3EAD3")),
        paperDeep = parseColor(plateObj.optString("paperDeep", "#E5D8B6")),
        backgroundImage = plateObj.optString("background").ifBlank { null },
        frame = plateObj.optBoolean("frame", false),
        plants = plants,
        gemma = gemmaObj?.let {
            val gr = it.optJSONArray("rect")
            Gemma(
                organId = it.getString("organ"),
                openImagePath = it.optString("openImage").ifBlank { null },
                rect = gr?.let { r ->
                    Rect(
                        left = r.getDouble(0).toFloat(),
                        top = r.getDouble(1).toFloat(),
                        right = (r.getDouble(0) + r.getDouble(2)).toFloat(),
                        bottom = (r.getDouble(1) + r.getDouble(3)).toFloat(),
                    )
                },
            )
        },
    )
}

private fun parseColor(hex: String): Color {
    val cleaned = hex.removePrefix("#")
    val v = cleaned.toLong(16)
    return if (cleaned.length <= 6) Color(0xFF000000L or v) else Color(v)
}
