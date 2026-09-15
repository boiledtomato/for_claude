package com.botanical.launcher.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.min

/**
 * 版面と実画面の対応。
 *
 * cover ではなく contain で収める。端が切れないので構図が端末に依存せず、
 * 余った紙は「台紙にマウントされた図版」として自然に見える。
 */
data class SceneTransform(val scale: Float, val dx: Float, val dy: Float) {

    fun toScreen(p: Offset): Offset = Offset(p.x * scale + dx, p.y * scale + dy)

    fun toScene(p: Offset): Offset = Offset((p.x - dx) / scale, (p.y - dy) / scale)

    fun px(designUnits: Float): Float = designUnits * scale

    companion object {
        val Identity = SceneTransform(1f, 0f, 0f)

        fun fit(size: Size, plateWidth: Float, plateHeight: Float): SceneTransform {
            if (size.width <= 0f || size.height <= 0f) return Identity
            if (plateWidth <= 0f || plateHeight <= 0f) return Identity
            val s = min(size.width / plateWidth, size.height / plateHeight)
            return SceneTransform(
                scale = s,
                dx = (size.width - plateWidth * s) / 2f,
                dy = (size.height - plateHeight * s) / 2f,
            )
        }
    }
}
