package com.botanical.launcher.ui

import androidx.compose.ui.geometry.Offset
import com.botanical.launcher.pencil.along
import com.botanical.launcher.pencil.polar
import com.botanical.launcher.pencil.rand01
import com.botanical.launcher.pencil.tendril
import kotlin.math.PI
import kotlin.math.sin

/**
 * アプリを開こうとしたときに伸びる蔦。
 *
 * 押した葉や花から蔓が這い出し、蔦形の葉を互い違いにつけながら伸びて、
 * 先についた蕾がほどけて咲き、その中にアプリが現れる。複数のアプリが
 * 割り当ててあれば、蔓は途中で枝分かれして同じ数だけ蕾をつける。
 *
 * 線を 1 本伸ばすだけでは蔦に見えない。参照図版の下半分にいる
 * ツタバギキョウ（Campanula hederacea）がそのまま手本で、
 *
 *   ・蔓はまっすぐ伸びず、左右にうねりながら進む
 *   ・葉が互い違いにつき、先へ行くほど小さい
 *   ・葉は蔓が通り過ぎてから、少し遅れて開く
 *   ・先端は巻きひげのように巻き込む
 *
 * この 4 つがそろって、はじめて「蔦が伸びた」と読める。
 *
 * 蔓は押した場所から任意の向きへ伸びるので、形を焼いておけない。
 * ここだけはその場で組み立てて線を引く。葉と花は焼いた素材を置く。
 */
class Reach(
    val originId: String,
    val origin: Offset,
    val heading: Float,
    val length: Float,
    val seed: Int,
    val appKeys: List<String>,
    val curl: Float = 1.1f,
) {
    private val forks = appKeys.size.coerceAtLeast(1)
    private val mainFraction = if (forks > 1) 0.55f else 1f

    val main: List<Offset> =
        waver(tendril(origin, heading, length * mainFraction, curl, seed), seed, length)

    val branches: List<List<Offset>> = if (forks <= 1) {
        emptyList()
    } else {
        val (fork, ang) = along(main, 0.999f)
        (0 until forks).map { i ->
            waver(
                tendril(
                    start = fork,
                    // 花どうしを離す。重なると、どれを押したのか指で決められない。
                heading = ang + (i - (forks - 1) / 2f) * 46f,
                    length = length * 0.62f,
                    curl = curl * 0.6f,
                    seed = seed + 7 * i,
                    steps = 26,
                ),
                seed + 7 * i, length * 0.55f,
            )
        }
    }

    /** 花の中心と、その花が向いている方向。 */
    val tips: List<Pair<Offset, Float>> =
        if (forks <= 1) listOf(along(main, 0.999f)) else branches.map { along(it, 0.999f) }

    /** 蔓ごとの葉。(蔓の index, 蔓上の位置 t, 左右, 素材 index, 大きさ) */
    val leaves: List<LeafOnVine> = buildLeaves()

    fun flowerCentre(index: Int, size: Float): Offset {
        val (tip, ang) = tips[index]
        return polar(tip, ang, size * 0.42f)
    }

    /** 主軸の伸び具合。枝は主軸が伸びきってから出る。 */
    fun mainGrow(grow: Float) = (grow / mainFraction).coerceAtMost(1f)

    fun branchGrow(grow: Float) = ((grow - 0.55f) / 0.45f).coerceIn(0f, 1f)

    /** 蔓の全体の伸び具合から、その蔓の伸び具合へ。[vine] が -1 なら主軸。 */
    fun growOf(vine: Int, grow: Float) =
        if (vine < 0) mainGrow(grow) else branchGrow(grow)

    private fun buildLeaves(): List<LeafOnVine> {
        if (forks <= 1) return leavesOn(-1, main, 6, 1.0f)
        // 主軸にも葉をつける。枝分かれするとき根元側が裸だと蔓に見えない。
        val out = ArrayList<LeafOnVine>(leavesOn(-1, main, 5, 1.0f))
        branches.forEachIndexed { i, p -> out += leavesOn(i, p, 4, 0.82f) }
        return out
    }

    private fun leavesOn(vine: Int, path: List<Offset>, n: Int, scale: Float):
        List<LeafOnVine> = (0 until n).map { i ->
        val t = 0.16f + 0.76f * (i / (n - 1f).coerceAtLeast(1f))
        val r = rand01(seed + vine * 31, i)
        LeafOnVine(
            vine = vine,
            t = t,
            side = if (i % 2 == 0) -1f else 1f,
            sprite = (i + vine + 1).mod(3),
            // 先へ行くほど小さい。同じ大きさで並べると模様になる。
            // 蔓の太さに対して葉が大きすぎると、蔓ではなく「葉の行列」に見える。
            scale = scale * (0.62f - 0.14f * t) * (0.85f + 0.3f * r),
        )
    }

    /**
     * 蔓を左右にうねらせる。
     *
     * tendril() は曲率を少しずつ足していくので、なめらかな渦になる。渦は
     * ぜんまいであって蔦ではない。進行方向に直交する向きへ波を足すと、
     * 「障害物をよけながら這った跡」に近づく。
     */
    private fun waver(pts: List<Offset>, seed: Int, length: Float): List<Offset> {
        val n = pts.size - 1
        if (n < 2) return pts
        val amp = length * 0.085f
        val waves = 1.6f + rand01(seed, 97) * 1.3f
        val phase = rand01(seed, 53) * 6.283f
        return pts.mapIndexed { i, p ->
            val t = i / n.toFloat()
            val j = (i + 1).coerceAtMost(n)
            val k = (i - 1).coerceAtLeast(0)
            val ang = Math.toDegrees(
                kotlin.math.atan2((pts[j].y - pts[k].y).toDouble(),
                    (pts[j].x - pts[k].x).toDouble())
            ).toFloat()
            // 根元は動かさない。付け根が動くと、生えている場所から外れる。
            val env = (t * 3f).coerceAtMost(1f) * (1f - t * 0.35f)
            polar(p, ang + 90f, (sin(t * waves * 2f * PI.toFloat() + phase) * amp * env))
        }
    }
}

/** 蔓についた 1 枚の葉。[vine] が -1 なら主軸。 */
data class LeafOnVine(
    val vine: Int,
    val t: Float,
    val side: Float,
    val sprite: Int,
    val scale: Float,
)
