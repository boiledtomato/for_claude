package com.example.zlauncher.domain.model

/**
 * プリセットのカテゴリー。Zscaler の predefined URL categories の英語名をそのまま借りている。
 * URL フィルタリング用の分類なので、アプリの分類として意味の通るものだけを並べた
 * （Adult Material のようなブロック目的の分類は入れない）。
 *
 * 作られるものはユーザー定義と同じ [AppCategory] なので、追加後は名前も色も中身も変えられる。
 */
data class PresetCategory(val name: String, val colorIndex: Int)

val PRESET_CATEGORIES: List<PresetCategory> = listOf(
    PresetCategory("Business and Economy", 0),
    PresetCategory("Information Technology", 1),
    PresetCategory("Internet Communication", 2),
    PresetCategory("Social Networking", 3),
    PresetCategory("News and Media", 4),
    PresetCategory("Streaming Media", 5),
    PresetCategory("Entertainment and Recreation", 0),
    PresetCategory("Games", 1),
    PresetCategory("Sports", 2),
    PresetCategory("Shopping and Auctions", 3),
    PresetCategory("Finance", 4),
    PresetCategory("Travel", 5),
    PresetCategory("Vehicles", 0),
    PresetCategory("Education", 1),
    PresetCategory("Reference", 2),
    PresetCategory("Health", 3),
    PresetCategory("Government and Politics", 4),
    PresetCategory("Society and Lifestyle", 5),
    PresetCategory("Restaurants and Dining", 0),
    PresetCategory("Real Estate", 1),
    PresetCategory("Job Search", 2),
    PresetCategory("Professional Services", 3),
    PresetCategory("Legal", 4),
    PresetCategory("Religion", 5),
)
