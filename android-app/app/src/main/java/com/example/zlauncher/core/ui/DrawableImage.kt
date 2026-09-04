package com.example.zlauncher.core.ui

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap

/** Drawable を Compose で描ける形に変換する。呼び出しは必ず IO で */
fun Drawable.toImageBitmap(sizePx: Int): ImageBitmap =
    toBitmap(width = sizePx, height = sizePx).asImageBitmap()
