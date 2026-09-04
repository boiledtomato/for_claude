package com.example.zlauncher.domain.model

/**
 * カードのステータス。色だけで意味を伝えないため、必ず [label] を併記して描画する。
 */
enum class CardStatus {
    GREEN,
    AMBER,
    RED,
    NEUTRAL,
    UNKNOWN,
}
