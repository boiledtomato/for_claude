package com.example.zlauncher.ui.notebookwidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.zlauncher.R
import com.example.zlauncher.core.notebook.NotebookSummary
import com.example.zlauncher.core.notebook.NotebookWidgetStyle
import com.example.zlauncher.data.notebook.NotebookCacheStore
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
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
        fun launcherPreferences(): LauncherPreferencesRepository
    }

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        val rowLayout = intent.getIntExtra(EXTRA_ROW_LAYOUT, R.layout.widget_notebook_row_open)
        val appWidgetId = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        )
        val dependencies = EntryPointAccessors
            .fromApplication(applicationContext, Dependencies::class.java)
        return NotebookRowFactory(
            context = applicationContext,
            packageName = packageName,
            rowLayout = rowLayout,
            appWidgetId = appWidgetId,
            store = dependencies.notebookCacheStore(),
            preferences = dependencies.launcherPreferences(),
        )
    }

    companion object {
        const val EXTRA_ROW_LAYOUT = "row_layout"
    }
}

private class NotebookRowFactory(
    private val context: Context,
    private val packageName: String,
    private val rowLayout: Int,
    private val appWidgetId: Int,
    private val store: NotebookCacheStore,
    private val preferences: LauncherPreferencesRepository,
) : RemoteViewsService.RemoteViewsFactory {

    private var notebooks: List<NotebookSummary> = emptyList()
    private var style = NotebookWidgetStyle()

    override fun onCreate() = Unit

    /** 更新 1 回につき 1 度しか呼ばれないので、設定を読むのもここで済ませる */
    override fun onDataSetChanged() {
        notebooks = runBlocking { store.read() }.notebooks
        style = runBlocking { preferences.state.first() }.notebook.style
    }

    override fun onDestroy() {
        notebooks = emptyList()
    }

    override fun getCount(): Int = notebooks.size

    override fun getViewAt(position: Int): RemoteViews {
        // 取得と再描画のあいだで件数が減ることがある。空の行を返して落とさない
        val entry = notebooks.getOrNull(position) ?: return RemoteViews(packageName, rowLayout)
        return RemoteViews(packageName, rowLayout).apply {
            // アイコンは題から決める。切っているときは絵文字に戻す
            if (style.showIcons) {
                setViewVisibility(R.id.row_icon, android.view.View.VISIBLE)
                setViewVisibility(R.id.row_emoji, android.view.View.GONE)
                setImageViewResource(R.id.row_icon, NotebookRowStyle.iconFor(entry.displayTitle))
            } else {
                setViewVisibility(R.id.row_icon, android.view.View.GONE)
                setViewVisibility(R.id.row_emoji, android.view.View.VISIBLE)
                setTextViewText(R.id.row_emoji, entry.emoji.ifBlank { "📓" })
            }

            // 最後の行の下には引かない。一覧の末尾に取り残された線になる
            val isLast = position == notebooks.lastIndex
            val divider = if (isLast) null else NotebookRowStyle.dividerRes(style.divider)
            if (divider == null) {
                setViewVisibility(R.id.row_divider, android.view.View.GONE)
            } else {
                setViewVisibility(R.id.row_divider, android.view.View.VISIBLE)
                setImageViewResource(R.id.row_divider, divider)
            }

            setTextViewText(
                R.id.row_title,
                NotebookRowStyle.styled(context, entry.displayTitle, style),
            )
            // 情報用の行にだけある。開く用には無いので、触っても害が無いよう存在を前提にしない
            if (rowLayout == R.layout.widget_notebook_row_info) {
                setTextViewText(
                    R.id.row_meta,
                    NotebookWidgetText.meta(
                        userRole = entry.userRole,
                        isShared = entry.isShared,
                        lastViewed = entry.lastViewed,
                        sourceCount = entry.sourceCount,
                        syncedAt = entry.syncedAt,
                        nowMillis = System.currentTimeMillis(),
                    ).let { NotebookRowStyle.styled(context, it, style, dim = true) },
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
