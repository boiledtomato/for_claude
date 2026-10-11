package com.example.zlauncher.data.notebook

import com.example.zlauncher.core.notebook.NotebookEndpoint
import kotlinx.serialization.Serializable

/**
 * Gemini Notebook Enterprise を読むのに要る設定。
 *
 * **資格情報はここに入れない。** 持つのはどのプロジェクトのどの宛先かだけで、
 * アクセストークンは使うたびに [NotebookAuth] が取り直す（寿命 1 時間）。
 * サービスアカウントの鍵を端末に置く形には**しない** ― ランチャーに恒久的な
 * 資格情報を抱えさせる話になるので、取り消せるユーザー同意だけで済ませる。
 */
@Serializable
data class NotebookConfig(
    /** **プロジェクト番号**（`my-project` のような ID ではなく 123456789012） */
    val projectNumber: String = "",
    /** `us` / `eu` / `global`。REST の宛先ホストを決める */
    val endpointLocation: String = "us",
    /** リソースのロケーション。ドキュメントの例は `global` */
    val location: String = "global",
    /** 利用者が選んだ Google アカウント。表示用で、認証そのものには使わない */
    val account: String = "",
) {
    val isConfigured: Boolean
        get() = NotebookEndpoint.isValidProjectNumber(projectNumber) &&
            NotebookEndpoint.isValidEndpointLocation(endpointLocation) &&
            location.isNotBlank()
}
