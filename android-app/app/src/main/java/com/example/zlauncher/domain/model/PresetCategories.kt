package com.example.zlauncher.domain.model

/**
 * プリセットのカテゴリー。Zscaler の URL カテゴリー（スーパーカテゴリー）の切り口に沿って、
 * アプリの分類として意味が通るものだけを日本語名で並べたもの。
 * ユーザー定義のカテゴリーと同じ [AppCategory] になるので、作ったあとは自由に編集・削除できる。
 */
data class PresetCategory(val name: String, val colorIndex: Int)

val PRESET_CATEGORIES: List<PresetCategory> = listOf(
    PresetCategory("ビジネス・経済", 0),
    PresetCategory("情報技術", 1),
    PresetCategory("ニュース・メディア", 2),
    PresetCategory("コミュニケーション", 3),
    PresetCategory("ソーシャル", 4),
    PresetCategory("ショッピング", 5),
    PresetCategory("金融", 0),
    PresetCategory("旅行", 1),
    PresetCategory("交通・車両", 2),
    PresetCategory("教育・辞典", 3),
    PresetCategory("健康・医療", 4),
    PresetCategory("エンタメ", 5),
    PresetCategory("ゲーム", 0),
    PresetCategory("音楽・動画", 1),
    PresetCategory("スポーツ", 2),
    PresetCategory("行政・公共", 3),
    PresetCategory("生活・社会", 4),
    PresetCategory("飲食", 5),
    PresetCategory("求人・キャリア", 0),
    PresetCategory("セキュリティ", 1),
)
