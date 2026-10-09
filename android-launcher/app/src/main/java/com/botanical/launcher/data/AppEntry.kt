package com.botanical.launcher.data

import androidx.compose.ui.graphics.ImageBitmap

/** ランチャーに並べる 1 アプリ。[key] が割り当ての保存キーになる。 */
data class AppEntry(
    val label: String,
    val packageName: String,
    val className: String,
    val icon: ImageBitmap,
) {
    val key: String get() = "$packageName/$className"
}
