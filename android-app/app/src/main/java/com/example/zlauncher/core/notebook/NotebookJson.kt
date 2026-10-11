package com.example.zlauncher.core.notebook

import org.json.JSONObject

/**
 * 応答の読み取り。**Pre-GA のプレビュー API なので、欠けていても落ちない形で読む。**
 *
 * 版が上がって項目が増える / 名前が変わるのは前提。1 つ欠けたら行ごと捨てるのではなく、
 * 読めたところまでで 1 行にする ― ウィジェットが空になるより、題だけでも出るほうがいい。
 * 落とすのは id が取れなかったときだけ（開く先が決まらないので行の意味が無い）。
 *
 * `org.json` を使うのは、kotlinx.serialization のデータクラスに固めると、項目名が 1 つ
 * 変わった時点で一覧ごと例外になるため。相手がプレビューの間はこちらを緩くしておく。
 * （`org.json` は Android 同梱。単体テストでは Robolectric 下で動かす）
 */
object NotebookJson {

    /** `{"notebooks":[…]}` を読む。`notebooks` が無い＝0 件（エラーではない） */
    fun parseList(body: String): List<NotebookSummary> {
        val root = runCatching { JSONObject(body) }.getOrNull() ?: return emptyList()
        val array = root.optJSONArray("notebooks") ?: return emptyList()
        return (0 until array.length()).mapNotNull { index ->
            array.optJSONObject(index)?.let(::readNotebook)
        }
    }

    /** 1 件取得の応答。こちらは `notebooks` で包まれない */
    fun parseOne(body: String): NotebookSummary? {
        val root = runCatching { JSONObject(body) }.getOrNull() ?: return null
        return readNotebook(root)
    }

    private fun readNotebook(obj: JSONObject): NotebookSummary? {
        val metadata = obj.optJSONObject("metadata")
        val id = obj.optString("notebookId").ifBlank { idFromName(obj.optString("name")) }
        if (id.isBlank()) return null
        return NotebookSummary(
            id = id,
            title = obj.optString("title"),
            emoji = obj.optString("emoji"),
            userRole = metadata?.optString("userRole").orEmpty(),
            isShared = metadata?.optBoolean("isShared") ?: false,
            lastViewed = metadata?.optString("lastViewed").orEmpty(),
            createTime = metadata?.optString("createTime").orEmpty(),
        )
    }

    /**
     * `projects/123/locations/global/notebooks/<id>` の末尾。
     *
     * `notebookId` が欠けた応答でも開けるようにしておく ― どちらも公開されている項目なので、
     * 片方だけに頼る理由が無い。
     */
    internal fun idFromName(name: String): String =
        name.substringAfterLast("/notebooks/", missingDelimiterValue = "")
}
