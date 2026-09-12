package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/** 12 カラムグリッド上でのカード幅。スマホでは HALF / FULL のみ使う */
@Serializable
enum class CardSpan(val columns: Int) {
    HALF(6),
    FULL(12),
}

/**
 * 永続化されるのはこの並び順・幅・表示可否だけ。
 * カードの中身（Composable）は [com.example.zlauncher.ui.dashboard.CardCatalog] 側にあり、保存しない。
 */
@Serializable
data class CardLayout(
    val id: String,
    val span: CardSpan,
    val visible: Boolean = true,
)
