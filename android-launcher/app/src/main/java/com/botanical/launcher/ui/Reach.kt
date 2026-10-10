package com.botanical.launcher.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.botanical.launcher.pencil.along
import com.botanical.launcher.pencil.polar
import com.botanical.launcher.pencil.rand01
import com.botanical.launcher.pencil.tendril
import kotlin.math.PI
import kotlin.math.hypot
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
    /**
     * 指でなぞった軌跡（版面座標）。渡されたらこれをそのまま蔓の道筋にする。
     * 自動で伸ばす蔓と同じ仕組みに載せたいので、形だけ差し替える。
     */
    traced: List<Offset>? = null,
) {
    private val forks = appKeys.size.coerceAtLeast(1)
    private val tracedPath = traced?.takeIf { it.size >= 4 }
    private val mainFraction = if (tracedPath == null && forks > 1) 0.55f else 1f

    val main: List<Offset> = tracedPath
        ?: waver(tendril(origin, heading, length * mainFraction, curl, seed), seed, length)

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

    /**
     * 咲いた花の中心。[size] は描画側と同じ値、[swell] は蕾のふくらみ具合。
     * 画面に収まるかの判定と実際の描画が別々の式を持つとずれるので、
     * ここ 1 か所に置いて両方から呼ぶ。
     */
    fun bloomCentre(index: Int, size: Float, swell: Float = 1f): Offset {
        val (tip, ang) = tips[index]
        return polar(tip, ang, size * 0.40f * (0.55f + 0.45f * swell))
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


/**
 * 画面に収まる向きと長さを選んで蔦を作る。
 *
 * 押した位置だけで向きを決め打ちすると、画面端の部位から伸ばしたときに
 * 咲いた花が画面の外へ出て見切れる。候補をいくつか組み立てて、花が
 * すべて収まるものを選ぶ。どれも収まらなければ、はみ出しがいちばん
 * 少ないものを使う。
 *
 * [bounds] は版面座標での可視範囲、[flowerRadius] は咲いた花の半径。
 */
fun fittingReach(
    originId: String,
    origin: Offset,
    seed: Int,
    appKeys: List<String>,
    baseLength: Float,
    bloomSize: Float,
    flowerRadius: Float,
    bounds: Rect,
): Reach {
    // 押した位置から見て、空いている側を先に試す。
    val outward = if (origin.x < bounds.center.x)
        listOf(-38f, -72f, -14f, -104f, 14f, -90f, 42f)
    else
        listOf(-142f, -108f, -166f, -76f, 194f, -90f, 222f)
    val fit = Rect(
        bounds.left + flowerRadius, bounds.top + flowerRadius,
        bounds.right - flowerRadius, bounds.bottom - flowerRadius,
    )

    var best: Reach? = null
    var bestMiss = Float.MAX_VALUE
    for (factor in floatArrayOf(1f, 0.78f, 0.58f, 0.44f)) {
        for (heading in outward) {
            val r = Reach(originId, origin, heading, baseLength * factor, seed, appKeys)
            var miss = 0f
            for (i in r.tips.indices) {
                val c = r.bloomCentre(i, bloomSize)
                miss += maxOf(0f, fit.left - c.x) + maxOf(0f, c.x - fit.right) +
                    maxOf(0f, fit.top - c.y) + maxOf(0f, c.y - fit.bottom)
            }
            if (miss <= 0f) return r
            if (miss < bestMiss) {
                bestMiss = miss
                best = r
            }
        }
    }
    return best!!
}

/**
 * なぞった生の座標を、蔓として引ける折れ線に均す。
 *
 * 指の軌跡はそのままだと点が不均等に詰まり、小刻みに震えている。蔓として
 * 引くと地震計の記録になるので、等間隔に取り直してから移動平均で均す。
 * 植物の蔓は曲がっても滑らかで、折れない。
 *
 * [step] は版面座標での点の間隔。
 */
fun smoothTrace(raw: List<Offset>, step: Float = 14f): List<Offset> {
    if (raw.size < 2) return raw
    // 等間隔に取り直す
    val even = ArrayList<Offset>(raw.size)
    even.add(raw[0])
    var carry = 0f
    for (i in 1 until raw.size) {
        val a = even.last()
        val b = raw[i]
        var d = hypot(b.x - a.x, b.y - a.y) + carry
        if (d < step) { carry = d; continue }
        carry = 0f
        var from = a
        while (d >= step) {
            val len = hypot(b.x - from.x, b.y - from.y)
            if (len < 1e-3f) break
            val t = step / len
            from = Offset(from.x + (b.x - from.x) * t, from.y + (b.y - from.y) * t)
            even.add(from)
            d -= step
        }
    }
    if (even.size < 3) return even

    // 移動平均。両端は動かさない（付け根が浮くと生えていないように見える）
    val out = ArrayList<Offset>(even.size)
    out.add(even.first())
    for (i in 1 until even.size - 1) {
        val p = even[i - 1]
        val q = even[i]
        val r = even[i + 1]
        out.add(Offset((p.x + 2f * q.x + r.x) / 4f, (p.y + 2f * q.y + r.y) / 4f))
    }
    out.add(even.last())
    return out
}
