package com.example.zlauncher.core.notebook

/**
 * Gemini Notebook Enterprise（旧 NotebookLM Enterprise）の REST の宛先。
 *
 * **Android に依存しないので、組み立てた URL をそのままテストで固定できる。** ここを
 * 間違えると実機では 404 か 403 にしか見えず、設定を疑う時間だけが増える。
 *
 * 公式ドキュメントの雛形:
 * ```
 * https://ENDPOINT_LOCATION-discoveryengine.googleapis.com
 *   /v1alpha/projects/PROJECT_NUMBER/locations/LOCATION/notebooks
 * ```
 * - `ENDPOINT_LOCATION` は `us` / `eu` / `global`
 * - `PROJECT_NUMBER` は**プロジェクト番号**（プロジェクト ID ではない）
 * - API は Pre-GA のプレビュー。版が上がれば `v1alpha` も動く前提で 1 か所に置く
 *
 * `global` のときだけ接頭辞を付けない。Discovery Engine の共通規則で、グローバルの
 * 宛先は無印の `discoveryengine.googleapis.com` になる（雛形の文字どおり
 * `global-` を付けると名前が解決しない）。もしここが違っていたとしても、直すのは
 * [host] の 1 行だけで済む。
 */
object NotebookEndpoint {

    const val API_VERSION = "v1alpha"

    /** 置ける宛先。設定画面の選択肢もこれを使う */
    val LOCATIONS = listOf("us", "eu", "global")

    /** プロジェクト番号は数字だけ。プロジェクト ID を貼られると 403 になるので入口で弾く */
    fun isValidProjectNumber(value: String): Boolean =
        value.isNotEmpty() && value.all { it.isDigit() }

    fun isValidEndpointLocation(value: String): Boolean = value in LOCATIONS

    fun host(endpointLocation: String): String =
        if (endpointLocation == "global") {
            "discoveryengine.googleapis.com"
        } else {
            "$endpointLocation-discoveryengine.googleapis.com"
        }

    /** `…/notebooks` まで。この下に `:listRecentlyViewed` や `/<id>` が付く */
    fun collection(endpointLocation: String, projectNumber: String, location: String): String =
        "https://${host(endpointLocation)}/$API_VERSION" +
            "/projects/$projectNumber/locations/$location/notebooks"

    /**
     * 一覧。**「最近見た順」しか公開されていない**（素の list は無い）。既定で最大 500 件
     * 返るが、ウィジェットが並べられる数ではないので [pageSize] で絞る。
     */
    fun listRecentlyViewed(
        endpointLocation: String,
        projectNumber: String,
        location: String,
        pageSize: Int? = null,
    ): String {
        val base = collection(endpointLocation, projectNumber, location) + ":listRecentlyViewed"
        return if (pageSize == null) base else "$base?pageSize=$pageSize"
    }

    fun notebook(
        endpointLocation: String,
        projectNumber: String,
        location: String,
        notebookId: String,
    ): String = collection(endpointLocation, projectNumber, location) + "/$notebookId"

    /** 要求するスコープ。読むだけでも Cloud の API なのでこれになる */
    const val OAUTH_SCOPE = "https://www.googleapis.com/auth/cloud-platform"
}
