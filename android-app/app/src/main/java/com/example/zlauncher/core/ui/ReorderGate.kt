package com.example.zlauncher.core.ui

/**
 * 並べ替えで「入れ替えてよいか」を決める判定。
 *
 * 素朴に「つまんだ要素の中心が相手の矩形に入ったら入れ替える」と、**端に 1px 触れただけで
 * 入れ替わる**。指は真っ直ぐ動かないので、隣を通り過ぎる途中に何度も入れ替わり、離すころには
 * どこへ置いたのか分からなくなる。ここで効かせる歯止めは 3 つ:
 *
 * 1. **深さ** ([ReorderGeometry]) — 相手の中心寄りまで入らないと候補にしない
 * 2. **滞在** ([dwellMillis]) — その候補にしばらく留まって初めて確定する。通り過ぎただけの
 *    相手とは入れ替わらない
 * 3. **間隔** ([cooldownMillis]) — 1 回入れ替えたら少し待つ。連鎖して数枚ぶん飛ぶのを防ぐ
 *
 * 指を止めたままでも確定させたいので、待ち時間は [Decision.Wait] で呼び出し側に返す
 * （ポインタのイベントが来なくなっても、その時間後に再評価すれば確定できる）。
 */
internal class ReorderGate(
    private val dwellMillis: Long = DWELL_MILLIS,
    private val cooldownMillis: Long = COOLDOWN_MILLIS,
) {
    sealed interface Decision {
        /** 入れ替える */
        data class Commit(val index: Int) : Decision

        /** まだ早い。この時間後に再評価すれば確定しうる */
        data class Wait(val millis: Long) : Decision

        /** 候補が無い */
        data object None : Decision
    }

    private var pending: Int? = null
    private var pendingSince = 0L
    private var lastCommit = Long.MIN_VALUE

    /** 候補の滞在時間が満ちているか見るだけ。確定したときは内部状態も進める */
    fun offer(candidate: Int?, now: Long): Decision {
        if (candidate == null) {
            pending = null
            return Decision.None
        }
        if (candidate != pending) {
            pending = candidate
            pendingSince = now
        }
        val readyAt = maxOf(
            pendingSince + dwellMillis,
            if (lastCommit == Long.MIN_VALUE) Long.MIN_VALUE else lastCommit + cooldownMillis,
        )
        if (now >= readyAt) {
            lastCommit = now
            // 確定したら候補を空にする。続けて同じ相手を提示されても滞在時間を数え直す
            pending = null
            return Decision.Commit(candidate)
        }
        return Decision.Wait(readyAt - now)
    }

    fun reset() {
        pending = null
        lastCommit = Long.MIN_VALUE
    }

    companion object {
        /** 候補に留まる時間。短いと通りすがりで入れ替わり、長いと動かないと感じる */
        const val DWELL_MILLIS = 140L

        /** 入れ替え直後の据わり時間。退く側のアニメーションが落ち着くまでの長さに合わせる */
        const val COOLDOWN_MILLIS = 220L
    }
}

/**
 * 入れ替え先の候補を選ぶ。
 *
 * 相手の矩形を中心方向へ [INSET_FRACTION] だけ縮めたうえで、つまんだ要素の中心がその中に
 * 入っているかを見る。縮めずに判定すると、隣に少し重なった瞬間に入れ替わる。
 */
internal object ReorderGeometry {
    /**
     * 相手の矩形をどれだけ縮めるか（片側あたりの割合）。
     *
     * 0.3 は「相手の幅・高さの 3 割ぶん踏み込まないと候補にならない」という意味で、
     * 中央の 4 割だけが有効な的になる。
     */
    const val INSET_FRACTION = 0.3f

    data class Slot(
        val index: Int,
        val left: Float,
        val top: Float,
        val width: Float,
        val height: Float,
    )

    fun candidate(
        centerX: Float,
        centerY: Float,
        slots: List<Slot>,
        exclude: Int,
        insetFraction: Float = INSET_FRACTION,
    ): Int? = slots
        .filter { slot ->
            if (slot.index == exclude || slot.width <= 0f || slot.height <= 0f) return@filter false
            val insetX = slot.width * insetFraction
            val insetY = slot.height * insetFraction
            centerX >= slot.left + insetX && centerX <= slot.left + slot.width - insetX &&
                centerY >= slot.top + insetY && centerY <= slot.top + slot.height - insetY
        }
        // 的が重なることは無いはずだが、重なったときは中心に近いほうを採る
        .minByOrNull { slot ->
            val dx = centerX - (slot.left + slot.width / 2f)
            val dy = centerY - (slot.top + slot.height / 2f)
            dx * dx + dy * dy
        }
        ?.index
}
