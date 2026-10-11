package com.example.zlauncher.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.example.zlauncher.core.color.IconColorMatrix
import com.example.zlauncher.domain.model.ColorAdjust

/**
 * アプリのアイコンに掛ける色味の指定。
 *
 * 配色トークン（[LocalZColors]）とは**別に持つ**。こちらが相手にするのは自分で描いていない
 * 絵 ― 各アプリが配布しているアイコンで、色の意味も明るさもばらばらなので、面や文字と
 * 同じ係数で動かす理由が無い。
 *
 * 値は CompositionLocal で配る。アイコンを描く場所は [com.example.zlauncher.ui.apps.component.AppIconTile]
 * に集まっているので、呼び出し側は何も変えずに済む。
 */
val LocalIconAdjust = staticCompositionLocalOf { ColorAdjust.NONE }

/**
 * いまの指定に対応する [ColorFilter]。調整なしのときは null を返す ―
 * 素通しのフィルタを噛ませても結果は同じで、レイヤーが 1 枚増えるだけ。
 *
 * 行列は指定が変わったときだけ組み直す。アイコンは 1 画面に何十枚も出るので、
 * 再構成のたびに 20 個の float を作らない。
 */
@Composable
fun rememberIconColorFilter(adjust: ColorAdjust = LocalIconAdjust.current): ColorFilter? =
    remember(adjust) {
        IconColorMatrix.of(adjust)?.let { ColorFilter.colorMatrix(ColorMatrix(it)) }
    }
