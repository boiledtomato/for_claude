package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class AppSortOrder {
    /** ラベルの照合順。日本語の漢字はコードポイント順になる（読み仮名 API が無いため） */
    LABEL,

    /** インストールが新しい順。漢字の並び問題を回避したいとき用 */
    INSTALL_TIME,
}
