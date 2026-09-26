package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * 配色の選び方。
 *
 * 既定は [SYSTEM]。ランチャーは 1 日中目に入るので、端末の夜間設定から外れて 1 つだけ
 * 明るいまま、という状態を既定にはしない。手で固定したい人のために残りの 2 つがある。
 */
@Serializable
enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK,
}
