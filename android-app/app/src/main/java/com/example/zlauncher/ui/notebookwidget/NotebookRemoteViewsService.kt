package com.example.zlauncher.ui.notebookwidget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.zlauncher.R
import com.example.zlauncher.core.notebook.NotebookSummary
import com.example.zlauncher.data.notebook.NotebookCacheStore
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking

/**
 * ウィジェットの一覧に行を供給する。
 *
 * **保存済みの中身しか見ない。** [RemoteViewsService.RemoteViewsFactory.onDataSetChanged] は
 * 同期で呼ばれるので、ここで通信すると一覧が描かれるまでウィジェットが固まる。
 * 取得は [com.example.zlauncher.data.notebook.NotebookRefreshWorker] の仕事。
 *
 * 行の見た目は呼び出し側が渡す（開く用／情報用）。枠を 2 つ持たないのと同じ理由で、
 * 供給する側も 1 つにしてある。
 */
class NotebookRemoteViewsService : RemoteViewsService() {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface Dependencies {
        fun notebookCacheStore(): NotebookCacheStore
    }

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        val rowLayout = intent.getIntExtra(EXTRA_ROW_LAYOUT, R.layout.widget_notebook_row_open)
        val appWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        )
        val store = EntryPointAccessors
            .fromApplication(applicationContext, Dependencies::class.java)
            .notebookCacheStore()
        return NotebookRowFactory(packageName, rowLayout, appWidgetId, store)
    }

    companion object {
        const val EXTRA_ROW_LAYOUT = "row_layout"
    }
}

private class NotebookRowFactory(
    private val packageName: String,
    private val rowLayout: Int,
    private val appWidgetId: Int,
    private val store: NotebookCacheStore,
) : RemoteViewsService.RemoteViewsFactory {

    private var notebooks: List<NotebookSummary> = emptyList()

    override fun onCreate() = Unit

    override fun onDataSetChanged() {
        notebooks = runBlocking { store.read() }.notebooks
    }

    override fun onDestroy() {
        notebooks = emptyList()
    }

    override fun getCount(): Int = notebooks.size

    override fun getViewAt(position: Int): RemoteViews {
        // 取得と再描画のあいだで件数が減ることがある。空の行を返して落とさない
        val entry = notebooks.getOrNull(position) ?: return RemoteViews(packageName, rowLayout)
        return RemoteViews(packageName, rowLayout).apply {
            setTextViewText(R.id.row_emoji, entry.emoji.ifBlank { "📓" })
            setTextViewText(R.id.row_title, entry.displayTitle)
            // 情報用の行にだけある。開く用には無いので、触っても害が無いよう存在を前提にしない
            if (rowLayout == R.layout.widget_notebook_row_info) {
                setTextViewText(
                    R.id.row_meta,
                    NotebookWidgetText.meta(
                        userRole = entry.userRole,
                        isShared = entry.isShared,
                        lastViewed = entry.lastViewed,
                        nowMillis = System.currentTimeMillis(),
                    ),
                )
            }
            setContentDescription(R.id.row_root, entry.displayTitle)
            // 押された行がどれかを雛形へ渡す。雛形側は component を変えられないので、
            // 開く先の判断（アプリかブラウザか）は受け取った Activity が行う
            setOnClickFillInIntent(
                R.id.row_root,
                Intent().putExtra(NotebookWidgetClickActivity.EXTRA_NOTEBOOK_ID, entry.id),
            )
        }
    }

    /** null で枠組みの既定（薄い待ち行）に任せる */
    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long =
        notebooks.getOrNull(position)?.id?.hashCode()?.toLong() ?: position.toLong()

    override fun hasStableIds(): Boolean = true
}
