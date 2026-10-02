package com.example.zlauncher.domain.model

import kotlinx.serialization.Serializable

/**
 * Zscaler の定義済み URL カテゴリー。
 *
 * 出典は ZIA ヘルプの「About URL Categories」に貼られている CSV で、
 * 列は Class / Super-Category / Category / Description / Test URL。
 * 大項目（Super-Category）の下に小項目（Category）がぶら下がる 2 階層。
 */
@Serializable
data class UrlCategoryEntry(
    /** 大項目。例: "Information Technology" */
    val superCategory: String,
    /** 小項目。例: "Web Hosting"。カテゴリー名として使うのはこちら */
    val category: String,
    val description: String,
    /** CSV の Class 列。"Business Use" など、用途の大分類 */
    val className: String,
) {
    /**
     * カタログ内での識別子。
     *
     * CSV に安定した ID 列が無い（2023 年版にはあった Cloud API の enum 値が現行版には無い）ので、
     * 大項目 + 小項目の組み合わせで代用する。名前が変われば別物になるため、改訂の突き合わせは
     * この鍵の一致だけでは足りず、差分側で「消えた + 増えた」の対応付けを見る必要がある。
     */
    val key: String get() = "$superCategory/$category"
}

/**
 * 追加ダイアログで選んだ 1 件。**色は選んだ時点で決める。**
 *
 * 以前は作成後に自動で割り当てていたが、15 スロットあると「どれが何色になったか」は
 * 作ってレールを見るまで分からず、直すには 1 つずつ編集を開く必要があった。
 */
data class CatalogPick(
    val entry: UrlCategoryEntry,
    val colorIndex: Int,
)

/** 大項目 1 つと、その下の小項目 */
data class UrlCategoryGroup(
    val superCategory: String,
    val className: String,
    val entries: List<UrlCategoryEntry>,
)

/**
 * カタログ 1 版分。[revision] は取得元 CSV のファイル名で、Zscaler が改訂日を入れてくるので
 * そのまま版の識別に使える（例: Zscaler-Test-URL-Categories-05-21-2026.csv）。
 */
@Serializable
data class UrlCategoryCatalog(
    val revision: String,
    val fetchedAtMillis: Long,
    val entries: List<UrlCategoryEntry>,
) {
    fun groups(): List<UrlCategoryGroup> = entries
        .groupBy { it.superCategory }
        .map { (superCategory, list) ->
            UrlCategoryGroup(
                superCategory = superCategory,
                className = list.first().className,
                entries = list.sortedBy { it.category },
            )
        }
        .sortedBy { it.superCategory }

    companion object {
        val EMPTY = UrlCategoryCatalog(revision = "", fetchedAtMillis = 0L, entries = emptyList())
    }
}

/** 改訂の差分。ユーザーに見せて、適用するかどうかを決めてもらう */
@Serializable
data class CatalogDiff(
    val fromRevision: String,
    val toRevision: String,
    val detectedAtMillis: Long,
    val added: List<UrlCategoryEntry> = emptyList(),
    val removed: List<UrlCategoryEntry> = emptyList(),
    /**
     * 名前が変わったと思われる組。
     *
     * CSV に安定 ID が無いので、改名は「消えた」と「増えた」の対で推測するしかない。
     * 同じ大項目で説明文が一致するものだけを候補にしており、**確定ではない**。
     * だから黙って差し替えず、必ずユーザーに確認させる。
     */
    val renamed: List<CatalogRename> = emptyList(),
    /** 大項目が移動した小項目 */
    val moved: List<CatalogMove> = emptyList(),
) {
    val isEmpty: Boolean
        get() = added.isEmpty() && removed.isEmpty() && renamed.isEmpty() && moved.isEmpty()

    val changeCount: Int get() = added.size + removed.size + renamed.size + moved.size
}

@Serializable
data class CatalogRename(val from: UrlCategoryEntry, val to: UrlCategoryEntry)

@Serializable
data class CatalogMove(val entry: UrlCategoryEntry, val fromSuperCategory: String)
