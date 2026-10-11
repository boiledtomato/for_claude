package com.example.zlauncher.core.notebook

/**
 * ノートブックを開く URL。
 *
 * **ウィジェットから開けるのはここまで。** 質問を投げる API も、プロンプトを URL に
 * 載せる方法も公開されていないので、「チャットを作る」は*狙ったノートブックを開いて
 * すぐ聞ける状態にする*ところまでになる。アプリのアイコンを置くのとは違い、
 * 1 タップで目的のノートブックの中に居るところが値打ち。
 *
 * この URL を NotebookLM / Gemini Notebook のアプリが App Link として受けているかは
 * **端末ごとに実行時に調べる**（`NotebookOpen`）。受けていればアプリ、受けていなければ
 * ブラウザ。どちらでも開けるので、こちらで決め打ちにしない。
 */
object NotebookLink {

    const val WEB_HOST = "notebooklm.google.com"

    fun notebook(id: String): String = "https://$WEB_HOST/notebook/$id"

    /** 一覧（ノートブックを選ぶところ）。id がまだ無いときの行き先 */
    fun home(): String = "https://$WEB_HOST/"
}
