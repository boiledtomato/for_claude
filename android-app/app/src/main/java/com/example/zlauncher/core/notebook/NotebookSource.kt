package com.example.zlauncher.core.notebook

import kotlinx.serialization.Serializable

/**
 * ノートブックの一覧をどこから読むか。**出せるものも、詰まったときの直し方も違う**ので、
 * 文言や失敗の扱いはこれで分岐する。
 */
@Serializable
enum class NotebookSource {

    /**
     * このリポジトリのパイプラインが公開している同期状態（GitHub Pages の JSON）。
     *
     * 認証も API キーも要らず、数 KB。**個人アカウントで動いている本物のノートブック**
     * ― `Zscaler_help_docs` などがそのまま並び、タップでそれを開ける。
     * 読めるのは「パイプラインが入れたソースの数と最終同期」まで。
     */
    PIPELINE,

    /**
     * Gemini Notebook Enterprise の REST。
     *
     * Google Cloud プロジェクトと Gemini Enterprise のライセンスが要る。見えるのは
     * **そのプロジェクトの中の**ノートブックで、上のパイプラインが作るものとは別物。
     */
    ENTERPRISE,
}
