package com.example.zlauncher.core.notebook

import kotlinx.serialization.Serializable

/**
 * 一覧に 1 行として出せるだけのノートブック情報。
 *
 * **公開されている項目だけを持つ。** Gemini Notebook Enterprise の REST で読めるのは
 * `name` / `title` / `notebookId` / `emoji` と `metadata` の
 * `userRole` / `isShared` / `isShareable` / `lastViewed` / `createTime` まで。
 *
 * **ソース件数は入っていない。** ソースを数える API が公開されていない（1 件を id 指定で
 * 取る `sources/<id>` と、追加・削除しかない）ため、「ソース 34 件」のような表示は
 * この API では作れない。無いものを 0 件と出すと「消えた」と読めるので、持たない。
 */
@Serializable
data class NotebookSummary(
    val id: String,
    val title: String,
    val emoji: String = "",
    /** `OWNER` / `READER` など。共有されたノートブックを見分ける手がかり */
    val userRole: String = "",
    val isShared: Boolean = false,
    /** RFC3339 の文字列のまま持つ。整形は表示側でやる */
    val lastViewed: String = "",
    val createTime: String = "",
    /**
     * パイプラインが入れたソースの数。**-1 は「分からない」**で、0 件とは違う。
     *
     * Enterprise API からは取れない（数える API が公開されていない）ので、そちらでは
     * -1 のまま。0 と区別しないと「ソースが消えた」と読めてしまう。
     */
    val sourceCount: Int = -1,
    /** パイプラインが最後に同期した時刻（RFC3339）。Enterprise 側では空 */
    val syncedAt: String = "",
) {
    /** 題が空のノートブックもあるので、一覧で空行にならないようにする */
    val displayTitle: String get() = title.ifBlank { "Untitled notebook" }
}
