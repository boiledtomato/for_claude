package com.example.zlauncher.core.designsystem

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset

/**
 * 動きの定義を 1 か所に集約する。
 *
 * 指に追従する要素はバネ、値の更新は短いイージング、という使い分け。
 * バネの減衰は 0.8 前後にして、行き過ぎて戻る動き（跳ね返り）を残しつつ収束を速くしている。
 */
object ZMotion {
    /** 押下・つまみ上げなど、指の下で動くもの */
    fun <T> touch(): FiniteAnimationSpec<T> =
        spring(dampingRatio = 0.78f, stiffness = Spring.StiffnessMediumLow)

    /** 並べ替えでほかの要素が退く動き。少しゆっくりにして流れを見せる */
    fun <T> reflow(): FiniteAnimationSpec<T> =
        spring(dampingRatio = 0.85f, stiffness = Spring.StiffnessLow)

    /**
     * つまんだ要素を離したあと、所定の位置へ収まるまで。
     *
     * ここだけ跳ね返させない（減衰 1.0）。行き過ぎて戻る動きは「まだ動かせる」ように見えて、
     * 置いた直後の確定感を消してしまう。
     */
    fun <T> settle(): FiniteAnimationSpec<T> =
        spring(dampingRatio = 1f, stiffness = Spring.StiffnessLow)

    /** グリッドの入れ替えアニメーション用 */
    fun placement(): FiniteAnimationSpec<IntOffset> =
        spring(dampingRatio = 0.85f, stiffness = Spring.StiffnessLow)

    /** 数値・ゲージの更新。指に追従しないのでバネにしない */
    fun <T> value(): FiniteAnimationSpec<T> = tween(durationMillis = 550)

    /**
     * 削除の対象が揺れる動き。
     *
     * 「押したら消えるものが今いくつある」を、色や文字ではなく動きで見せる。片道
     * [JIGGLE_MS] で往復させ、振れ幅は [JIGGLE_DEGREES] ― これ以上大きいと、並んだ
     * アイコンの文字が読めなくなる。
     */
    const val JIGGLE_MS = 140
    const val JIGGLE_DEGREES = 1.7f

    /** 画面やペインの切り替え */
    const val TRANSITION_MS = 260

    /** 押したときの縮み */
    const val PRESS_SCALE = 0.94f

    /** つまみ上げたときの浮き上がり */
    const val LIFT_SCALE = 1.04f
}
