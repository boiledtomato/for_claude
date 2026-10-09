package com.botanical.launcher.flora

import androidx.compose.ui.graphics.Color

/**
 * 図版の色。参照した Anne Pratt の多色石版（Pl.134）から実測した値で、
 * tools/flora/palette.py と同じものを持つ。手で選ぶと必ず彩度が上がりすぎる。
 *
 * 実物の青はくすんだスレートブルー、緑は黄みの強いサップグリーン、
 * 輪郭線は黒ではなく暗い茶緑。色数の少ない石版の渋さがここにある。
 */
object Palette {
    val Paper = Color(0xFFF0EBE2)
    val PaperDeep = Color(0xFFE8E2D6)
    val PaperShade = Color(0xFFD8D0C0)
    val Cream = Color(0xFFF6F1E8)

    val Ink = Color(0xFF231D1A)
    val InkSoft = Color(0xFF364531)

    val Blue = Color(0xFF95B1B6)
    val BlueHi = Color(0xFFC6D8DC)
    val BlueDeep = Color(0xFF597B88)
    val BlueInk = Color(0xFF3A5666)

    val Crimson = Color(0xFF7D1327)
    val CrimsonHi = Color(0xFFB26062)

    val Yellow = Color(0xFFCAB533)
    val YellowDeep = Color(0xFFB1921B)

    // 明るい緑に紙の白を混ぜると彩度が落ちて灰緑になる。参照図版の葉は
    // いちばん明るいところ以外、どこも彩度 0.63〜0.71 を保っている。
    val GreenLight = Color(0xFFA3BC66)
    val GreenHi = Color(0xFF7AA23C)
    val Green = Color(0xFF659130)
    val GreenDeep = Color(0xFF466B22)
    val GreenShade = Color(0xFF2B4C16)
    val Stem = Color(0xFF709838)
    val StemDeep = Color(0xFF3E611E)
}
