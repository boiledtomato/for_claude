package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * ホームに置いたウィジェット 1 件。
 *
 * 座標は持たない。ウィジェットは全幅・縦一列に、このリストの順で並ぶ。
 * 自由座標配置にするとグリッド列数が変わったときに配置が破綻するため、MVP では採らない。
 */
@Serializable
data class WidgetPlacement(
    /** AppWidgetHost が払い出した ID。削除時は必ず deleteAppWidgetId すること */
    val appWidgetId: Int,
    val heightDp: Int,
)
