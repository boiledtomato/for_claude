package com.example.zlauncher.core.notebook

/**
 * ウィジェットに出す一行。**理由を言い当てることがこの機能の半分。**
 *
 * この機能が動かない理由はほぼ設定の不足で、しかも直し方が理由ごとに違う。
 * 「読み込めません」とだけ出ると、プロジェクト番号の打ち間違いとライセンス未割り当てと
 * 圏外の区別がつかず、利用者は何を直せばいいのか分からない。
 *
 * 前回の内容が残っているかどうかで言い方を変える ― 残っているなら「古いものを出している」、
 * 無いなら「まだ何も出せない」。純粋なのでそのままテストにかけられる。
 */
object NotebookStatus {

    /**
     * @param state 直近の取得結果。まだ一度も試していなければ null
     * @param hasCached 前回の一覧が残っているか
     * @return 出す文。空文字なら何も出さない（一覧だけ見せる）
     */
    fun message(state: NotebookFetch?, hasCached: Boolean): String = when (state) {
        null -> if (hasCached) "" else "Not refreshed yet"
        is NotebookFetch.Ok ->
            if (state.notebooks.isEmpty()) "No notebooks in this project" else ""
        NotebookFetch.NotConfigured -> "Set your project number in Console settings"
        NotebookFetch.NeedsSignIn -> "Tap to allow access to your Google account"
        NotebookFetch.NoAccess ->
            "No access — check the Discovery Engine API, your Gemini Enterprise licence, " +
                "and the project number"
        NotebookFetch.RateLimited -> stale(hasCached, "Rate limited by the preview API")
        is NotebookFetch.Failed -> stale(hasCached, "Could not reach the API")
    }

    /** 出せる中身があるなら、失敗そのものより「古い」ことを伝えるほうが役に立つ */
    private fun stale(hasCached: Boolean, reason: String): String =
        if (hasCached) "$reason — showing the last result" else reason

    /** 一覧を出していいか。駄目な理由のときは行を消して文だけ出す */
    fun showsList(state: NotebookFetch?, hasCached: Boolean): Boolean = when (state) {
        NotebookFetch.NotConfigured, NotebookFetch.NeedsSignIn, NotebookFetch.NoAccess -> false
        else -> hasCached || (state as? NotebookFetch.Ok)?.notebooks?.isNotEmpty() == true
    }
}
