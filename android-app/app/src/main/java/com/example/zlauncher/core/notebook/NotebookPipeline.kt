package com.example.zlauncher.core.notebook

import org.json.JSONObject

/**
 * パイプラインが公開している同期状態から一覧を作る。
 *
 * **認証が要らないのが値打ち。** `sync_notebooklm.py` が書く
 * `data/<name>_sync_state.json` は GitHub Pages にそのまま載っていて、どれも数 KB。
 * ノートブック 1 つにつき `notebook.id` / `notebook.title` / `sources` の件数 /
 * `synced_at` が入っている ― ウィジェットの 1 行に必要なものが全部そろう。
 *
 * **id が入っているので、タップで本物のノートブックを開ける。** Enterprise API では
 * 見えない個人アカウント側のノートブックに、ここからなら届く。
 *
 * Android に依存しないので、組み立てと読み取りはそのままテストにかけられる。
 */
object NotebookPipeline {

    /** 公開先。設定で変えられるが、既定はこのリポジトリの Pages */
    const val DEFAULT_BASE_URL = "https://boiledtomato.github.io/for_claude"

    /** 一覧の取得元。公開リポジトリなので認証不要（60 回/時の範囲で足りる） */
    const val DEFAULT_REPO = "boiledtomato/for_claude"

    /** 同期状態が置かれているディレクトリ */
    const val STATE_DIR = "data"

    /**
     * 一覧が引けなかったときに使う既定。
     *
     * **これだけに頼らない。** パイプラインにノートブックが増えても気付けないので、
     * 本筋は [contentsUrl] での列挙。ここは GitHub API が落ちた / 回数を使い切った
     * ときに、ウィジェットが空にならないための保険。
     */
    val FALLBACK_STATE_FILES = listOf(
        "notebooklm_sync_state.json",
        "community_notebooklm_sync_state.json",
        "release_notes_notebooklm_sync_state.json",
        "mslearn_entra_notebooklm_sync_state.json",
    )

    fun stateUrl(baseUrl: String, fileName: String): String =
        "${baseUrl.trimEnd('/')}/$STATE_DIR/$fileName"

    fun contentsUrl(repo: String): String =
        "https://api.github.com/repos/${repo.trim('/')}/contents/$STATE_DIR"

    /**
     * GitHub の contents API の応答から、同期状態のファイル名だけを拾う。
     *
     * 名前で選ぶ ― ディレクトリや他の JSON（`articles.json` など）が混ざっているため。
     * 並びは応答のまま（API は名前順で返す）。
     */
    fun parseStateFileNames(body: String): List<String> {
        val array = runCatching { org.json.JSONArray(body) }.getOrNull() ?: return emptyList()
        return (0 until array.length()).mapNotNull { index ->
            val entry = array.optJSONObject(index) ?: return@mapNotNull null
            val name = entry.optString("name")
            name.takeIf {
                entry.optString("type") == "file" &&
                    it.endsWith("_sync_state.json")
            }
        }
    }

    /**
     * 1 つぶんの同期状態を 1 行にする。
     *
     * `notebook.id` が無いものは捨てる ― 開く先が決まらない行は押せても何も起きない。
     * 題が無いときはファイル名から作る（`notebooklm_sync_state.json` → `notebooklm`）。
     */
    fun parseState(body: String, fileName: String = ""): NotebookSummary? {
        val root = runCatching { JSONObject(body) }.getOrNull() ?: return null
        val notebook = root.optJSONObject("notebook") ?: return null
        val id = notebook.optString("id")
        if (id.isBlank()) return null
        return NotebookSummary(
            id = id,
            title = notebook.optString("title").ifBlank { titleFromFileName(fileName) },
            // sources は {ファイル名: {...}} の形。件数がそのままソース数
            sourceCount = root.optJSONObject("sources")?.length() ?: 0,
            syncedAt = root.optString("synced_at"),
        )
    }

    internal fun titleFromFileName(fileName: String): String =
        fileName.removeSuffix(".json")
            .removeSuffix("_sync_state")
            .removeSuffix("_notebooklm")
            .ifBlank { "" }
}
