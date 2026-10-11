package com.example.zlauncher.data.notebook

import com.example.zlauncher.core.notebook.NotebookEndpoint
import com.example.zlauncher.core.notebook.NotebookPipeline
import com.example.zlauncher.core.notebook.NotebookSource
import com.example.zlauncher.core.notebook.NotebookWidgetStyle
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
    /**
     * どこから読むか。**既定はパイプライン** ― 設定ゼロで動くほうを初期値にしておく。
     * Enterprise を既定にすると、置いた直後は必ず「設定してください」で止まる。
     */
    val source: NotebookSource = NotebookSource.PIPELINE,
    /** パイプラインの公開先（GitHub Pages） */
    val pipelineBaseUrl: String = NotebookPipeline.DEFAULT_BASE_URL,
    /** ノートブックが増えたときに拾うための列挙元（owner/repo） */
    val pipelineRepo: String = NotebookPipeline.DEFAULT_REPO,
    /** **プロジェクト番号**（`my-project` のような ID ではなく 123456789012） */
    val projectNumber: String = "",
    /** `us` / `eu` / `global`。REST の宛先ホストを決める */
    val endpointLocation: String = "us",
    /** リソースのロケーション。ドキュメントの例は `global` */
    val location: String = "global",
    /** 利用者が選んだ Google アカウント。表示用で、認証そのものには使わない */
    val account: String = "",
    /** ウィジェットの見た目（文字色・字体・罫線・アイコン） */
    val style: NotebookWidgetStyle = NotebookWidgetStyle(),
) {
    /**
     * 読みに行けるだけの設定があるか。
     *
     * パイプラインは URL さえあれば足りる（既定値で埋まっている）。Enterprise は
     * プロジェクト番号と宛先が要る。
     */
    val isConfigured: Boolean
        get() = when (source) {
            NotebookSource.PIPELINE -> pipelineBaseUrl.isNotBlank()
            NotebookSource.ENTERPRISE ->
                NotebookEndpoint.isValidProjectNumber(projectNumber) &&
                    NotebookEndpoint.isValidEndpointLocation(endpointLocation) &&
                    location.isNotBlank()
        }
}
