package com.botanical.launcher.ui

import androidx.compose.ui.geometry.Offset
import com.botanical.launcher.pencil.along
import com.botanical.launcher.pencil.polar
import com.botanical.launcher.pencil.tendril

/**
 * アプリを開こうとしたときに伸びる蔓。
 *
 * 押した葉や花から蔓が伸び、先で花が開き、その中にアプリが現れる。
 * 複数のアプリが割り当ててあれば、蔓は途中で枝分かれして同じ数だけ花をつける。
 *
 * 蔓は押した場所から任意の向きへ伸びるので、焼いた素材では表せない。
 * ここだけはその場で組み立てて線を引く。
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
        tendril(origin, heading, length * mainFraction, curl, seed)

    val branches: List<List<Offset>> = if (forks <= 1) {
        emptyList()
    } else {
        val (fork, ang) = along(main, 0.999f)
        (0 until forks).map { i ->
            tendril(
                start = fork,
                heading = ang + (i - (forks - 1) / 2f) * 34f,
                length = length * 0.55f,
                curl = curl * 0.6f,
                seed = seed + 7 * i,
                steps = 26,
            )
        }
    }

    /** 花の中心と、その花が向いている方向。 */
    val tips: List<Pair<Offset, Float>> =
        if (forks <= 1) listOf(along(main, 0.999f)) else branches.map { along(it, 0.999f) }

    fun flowerCentre(index: Int, size: Float): Offset {
        val (tip, ang) = tips[index]
        return polar(tip, ang, size * 0.42f)
    }

    /** 主軸の伸び具合。枝は主軸が伸びきってから出る。 */
    fun mainGrow(grow: Float) = (grow / mainFraction).coerceAtMost(1f)

    fun branchGrow(grow: Float) = ((grow - 0.55f) / 0.45f).coerceIn(0f, 1f)
}
