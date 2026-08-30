package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * ユーザーが自分で作るカテゴリー。コンソール左のレールに並び、所属アプリを入れ子で持つ。
 *
 * 所属アプリは packageName で保持する（ドックと同じ理由: アプリ更新で
 * ComponentName のクラス名が変わっても配置が消えないようにするため）。
 */
@Serializable
data class AppCategory(
    val id: String,
    val name: String,
    /** [CATEGORY_COLORS] のインデックス */
    val colorIndex: Int = 0,
    val packages: List<String> = emptyList(),
    /**
     * Zscaler のカタログから作った場合、その項目の鍵（"大項目/小項目"）。
     * 自分で名前を決めて作ったカテゴリーでは null。
     *
     * カタログが改訂されて名前が変わったとき、どのカテゴリーを差し替えればいいかを
     * これで辿る。名前だけで突き合わせると、ユーザーが改名した瞬間に追えなくなる。
     */
    val catalogKey: String? = null,
)

/** レールでカテゴリーを見分けるための色。ステータス色とは別物として扱う */
const val CATEGORY_COLOR_COUNT = 6
