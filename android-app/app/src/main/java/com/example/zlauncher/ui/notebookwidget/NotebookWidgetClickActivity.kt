package com.example.zlauncher.ui.notebookwidget

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

/**
 * ウィジェットの行を押したときの受け。**画面は出さない。**
 *
 * 間に挟むのは、一覧の雛形 `PendingIntent` では行ごとに宛先アプリを変えられないため。
 * 「アプリが受けていればアプリ、受けていなければブラウザ」という判断
 * （[NotebookOpen]）は押された時点でしかできないので、ここで解決して渡す。
 */
class NotebookWidgetClickActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notebookId = intent?.getStringExtra(EXTRA_NOTEBOOK_ID).orEmpty()
        runCatching { startActivity(NotebookOpen.intent(this, notebookId)) }
            .onFailure { error ->
                Log.w(TAG, "could not open notebook", error)
                // ブラウザも専用アプリも無い端末。黙って終わると押し間違いに見える
                Toast.makeText(this, "No app can open this notebook", Toast.LENGTH_SHORT).show()
            }
        finish()
    }

    companion object {
        private const val TAG = "NotebookWidgetClick"
        const val EXTRA_NOTEBOOK_ID = "notebook_id"
    }
}
