package com.example.zlauncher.ui.notebookwidget

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.net.toUri
import com.example.zlauncher.core.notebook.NotebookLink

/**
 * ノートブックを開く Intent。
 *
 * **アプリが受けるかどうかは端末で調べる。** NotebookLM / Gemini Notebook のアプリが
 * `notebooklm.google.com` を App Link として受けているかは、版と端末の設定（利用者が
 * 「このリンクを開く」を切っていることもある）次第。決め打ちで `setPackage` すると、
 * 受けていない端末では**何も起きないボタン**になる ― ホーム画面でそれが一番困る。
 *
 * なので: 解決できるアプリがあればそれ、無ければ既定のブラウザ。どちらでも開ける。
 */
object NotebookOpen {

    /** そのノートブックへ。id が空なら一覧へ */
    fun intent(context: Context, notebookId: String): Intent {
        val url = if (notebookId.isBlank()) NotebookLink.home() else NotebookLink.notebook(notebookId)
        val view = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return resolvedApp(context, view)?.let { packageName ->
            Intent(view).setPackage(packageName)
        } ?: view
    }

    /**
     * このリンクを受け持つ**ブラウザ以外の**アプリ。
     *
     * ブラウザも `ACTION_VIEW` に応えるので、素の問い合わせでは見分けられない。
     * `MATCH_DEFAULT_ONLY` を付けず全件見て、自分で選べるブラウザ群を引いて残るものを採る。
     */
    private fun resolvedApp(context: Context, view: Intent): String? {
        val packageManager = context.packageManager
        val handlers = packageManager.queryIntentActivities(view, 0)
            .mapNotNull { it.activityInfo?.packageName }
            .toSet()
        if (handlers.isEmpty()) return null
        // 「どんな http でも開く」もの＝ブラウザ。これを除くと専用アプリだけが残る
        val anyHttp = Intent(Intent.ACTION_VIEW, "https://example.invalid/".toUri())
        val browsers = packageManager.queryIntentActivities(anyHttp, 0)
            .mapNotNull { it.activityInfo?.packageName }
            .toSet()
        return (handlers - browsers).firstOrNull()
    }

    /** 端末にこのリンクを開けるものが 1 つも無い（ブラウザも無い）ら、押させない */
    fun canOpen(context: Context, notebookId: String): Boolean =
        intent(context, notebookId).resolveActivity(context.packageManager) != null ||
            context.packageManager.queryIntentActivities(
                intent(context, notebookId),
                PackageManager.MATCH_DEFAULT_ONLY,
            ).isNotEmpty()
}
