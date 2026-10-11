package com.example.zlauncher.core.notebook

/**
 * 取得の結果。**「失敗」を 1 つにまとめない。**
 *
 * この機能が動かない理由はほぼ設定の不足で、しかも理由ごとに次の手が違う。
 * ウィジェットに「読み込めません」とだけ出ると、プロジェクト番号の誤りと
 * ライセンス未割り当てと圏外の区別がつかず、直せない。
 */
sealed interface NotebookFetch {

    data class Ok(val notebooks: List<NotebookSummary>) : NotebookFetch

    /** プロジェクト番号などが未入力。設定へ送る */
    data object NotConfigured : NotebookFetch

    /** Google アカウントの許可が無い / 期限切れ（401）。サインインし直させる */
    data object NeedsSignIn : NotebookFetch

    /**
     * 認証は通ったが使わせてもらえない（403）。Discovery Engine API が無効、
     * Gemini Enterprise のライセンス未割り当て、プロジェクト番号違いのどれか。
     */
    data object NoAccess : NotebookFetch

    /** プレビュー API の絞り込み（429）。次の更新まで前回の内容を出す */
    data object RateLimited : NotebookFetch

    /** 通信できなかった / 想定しない応答 */
    data class Failed(val reason: String) : NotebookFetch

    companion object {

        /**
         * HTTP の状態コードを上の区別へ。**純粋なので固定できる。**
         *
         * 404 は 403 と同じ扱いにする ― プロジェクト番号や宛先が違うとき、Google の API は
         * 権限の有無を伏せるために 404 を返すことがあり、利用者から見れば「使えない」で同じ。
         */
        fun fromStatus(code: Int, body: String): NotebookFetch = when {
            code == 200 -> Ok(NotebookJson.parseList(body))
            code == 401 -> NeedsSignIn
            code == 403 || code == 404 -> NoAccess
            code == 429 -> RateLimited
            code in 500..599 -> Failed("server error $code")
            else -> Failed("unexpected status $code")
        }
    }
}
