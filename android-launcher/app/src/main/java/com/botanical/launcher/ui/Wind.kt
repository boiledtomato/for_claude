package com.botanical.launcher.ui

import com.botanical.launcher.garden.OrganKind
import com.botanical.launcher.garden.Plate
import com.botanical.launcher.garden.PlantLayer
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

/**
 * 風の場。
 *
 * 版面は 1 枚の画像なので、素朴に撓ませると全体が同じ位相で動いてしまい、
 * 「板が揺れている」ようにしか見えない。そこで変位を
 *
 *   根元を固定した全体の撓み  +  花ごとの局所的な揺れ（位相も速さも別）
 *
 * の和として作り、メッシュの頂点ごとに評価する。花の周りだけ強く効く重みを
 * 持たせてあるので、隣り合う花が別々に頷く。
 *
 * さらに横方向へ進む波（突風）を掛けることで、風が吹き抜けていくように見せる。
 */
class WindField(
    private val layer: PlantLayer,
    organs: List<Organ>,
    private val gustTravel: Float = 2.4f,
) {
    /** 個々に揺れる対象。位置と大きさは配置矩形に対する割合。 */
    data class Organ(
        val x: Float,
        val y: Float,
        val sigma: Float,
        val amplitude: Float,
        val phase: Float,
        /** 基準周期に対する整数倍。整数でないと位相が一周するたびに動きが飛ぶ。 */
        val speed: Int,
        val bobbing: Float,
    )

    private val organs = organs.toTypedArray()

    /**
     * メッシュ頂点 ([u], [v] は配置矩形に対する割合) の変位。
     *
     * @param bloom 蕾の開き具合。蕾の周りだけ押し広げる
     * @param bloomAt 蕾の位置（割合）。null なら開花の効果なし
     */
    fun displace(
        u: Float,
        v: Float,
        phase: Float,
        gust: Float,
        nudge: Float,
        bloom: Float,
        bloomAt: Pair<Float, Float>?,
        out: FloatArray,
    ) {
        val pivotY = layer.pivot.y.coerceIn(0.05f, 1f)
        // 根元で 0、先端で 1
        val root = ((pivotY - v) / pivotY).coerceIn(0f, 1f)
        // 株全体のうねりは、花の揺れよりずっとゆっくり
        val rootSwing = sin(phase * ROOT_SPEED * layer.bendSpeed + layer.bendPhase -
            u * gustTravel * 0.5f)
        // 株全体のうねりは控えめに。これを強くすると全部が同じ位相で動いて
        // 「板が揺れている」だけになる。
        var dx = layer.bendAmplitude * ROOT_SHARE * root * root * rootSwing * gust
        var dy = 0f

        for (o in organs) {
            val ddx = (u - o.x) / o.sigma
            val ddy = (v - o.y) / (o.sigma * 1.25f)
            val d2 = ddx * ddx + ddy * ddy
            if (d2 > 9f) continue                       // 3σ の外は無視する
            val w = exp(-0.5f * d2)
            // 突風は左から右へ抜ける
            val t = phase * o.speed + o.phase - u * gustTravel
            val a = o.amplitude * w * (0.45f + 0.55f * gust) * (1f + nudge * 1.8f)
            dx += a * sin(t)
            dy += a * o.bobbing * sin(t * 2f + o.phase)
        }

        if (bloomAt != null && bloom > 0.001f) {
            // 蕾の周りを押し広げる。開いた花が出てくる前の「膨らみ」。
            val bx = u - bloomAt.first
            val by = v - bloomAt.second
            val d = kotlin.math.sqrt(bx * bx + by * by)
            val swell = exp(-0.5f * (d / 0.09f) * (d / 0.09f)) * bloom
            if (d > 1e-4f) {
                dx += bx / d * swell * 0.030f
                dy += by / d * swell * 0.030f
            }
        }

        out[0] = dx
        out[1] = dy
    }

    companion object {
        /** 株全体のうねりの速さ（基準周期に対する整数倍）。 */
        private const val ROOT_SPEED = 2

        /** 全体のうねりの取り分。残りは花ごとの揺れに回す。 */
        private const val ROOT_SHARE = 0.45f

        /**
         * ゆっくりした強弱。風は一定では吹かない。
         *
         * 倍率はすべて整数。そうしないと位相が一周したときに強さが飛ぶ。
         * 基準周期を長くとってあるので、1 倍でも十分ゆっくりしたうねりになる。
         */
        fun gust(phase: Float): Float {
            val slow = sin(phase)
            val slower = sin(phase * 3f + 1.7f)
            return (0.52f + 0.32f * slow + 0.16f * slower).coerceIn(0.10f, 1.0f)
        }

        /**
         * 版面から風の場を作る。
         *
         * 花は大きく長く揺れ、葉は小刻みに震える。実際の草はそう動く。
         */
        fun of(plate: Plate, layer: PlantLayer): WindField {
            val organs = layer.organs.map { spot ->
                val seed = spot.id.hashCode()
                val rnd = { n: Int -> abs((seed * 31 + n * 2654435761L.toInt()) % 1000) / 1000f }
                val high = (1f - spot.at.y).coerceIn(0f, 1f)   // 上にあるものほど揺れる
                val leaf = spot.kind == OrganKind.LEAF
                WindField.Organ(
                    x = spot.at.x,
                    y = spot.at.y,
                    // 広げすぎると隣の花まで一緒に動いてしまう
                    sigma = (spot.radius * 1.5f).coerceIn(0.05f, 0.22f),
                    amplitude = plate.width * 0.010f *
                        (0.45f + 0.85f * high) *
                        (if (leaf) 0.55f else 1.0f) *
                        (0.55f + 1.0f * rnd(1)),
                    phase = rnd(2) * (2 * PI).toFloat(),
                    // 葉は速く小さく、花はゆっくり大きく。基準周期の整数倍で持つ。
                    speed = if (leaf) 16 + (rnd(3) * 11).toInt() else 5 + (rnd(4) * 10).toInt(),
                    bobbing = 0.22f + 0.30f * rnd(5),
                )
            }
            return WindField(layer, organs)
        }
    }
}

/** 蕾が開くときの、花弁がほどける変形。 */
fun unfurl(p: Float, q: Float, bloom: Float, out: FloatArray) {
    // 中心からの相対位置を、縮んだ状態から本来の大きさへ広げつつ回す
    val cx = p - 0.5f
    val cy = q - 0.5f
    val scale = 0.22f + 0.78f * bloom
    val twist = (1f - bloom) * 0.9f
    val c = cos(twist)
    val s = sin(twist)
    out[0] = 0.5f + (cx * c - cy * s) * scale
    out[1] = 0.5f + (cx * s + cy * c) * scale
}
