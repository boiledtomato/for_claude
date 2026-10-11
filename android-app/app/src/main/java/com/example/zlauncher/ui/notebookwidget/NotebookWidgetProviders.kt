package com.example.zlauncher.ui.notebookwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import android.widget.RemoteViews
import com.example.zlauncher.MainActivity
import com.example.zlauncher.R
import com.example.zlauncher.data.notebook.NotebookCacheStore
import com.example.zlauncher.data.notebook.NotebookRefreshWorker
import com.example.zlauncher.data.prefs.LauncherPreferencesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 * ノートブックのウィジェット 2 種。**枠と更新の仕掛けは同じで、行の作りだけが違う。**
 *
 * - [NotebookOpenWidget] … 押すとそのノートブックを開く（「すぐ聞ける状態にする」側）
 * - [NotebookInfoWidget] … 権限・共有・最後に見た日時まで出す（「情報を見る」側）
 *
 * **描くのは保存済みの中身だけ。** ウィジェットの更新は短い時間しか与えられないので、
 * ここで通信はしない。取得は [NotebookRefreshWorker] が行い、終わったらこちらへ
 * 「作り直せ」と伝える。押した瞬間に固まるウィジェットを作らないための切り分け。
 */
abstract class NotebookWidget : AppWidgetProvider() {

    protected abstract val rowLayout: Int
    protected abstract val headerTitle: String

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface Dependencies {
        fun notebookCacheStore(): NotebookCacheStore
        fun launcherPreferences(): LauncherPreferencesRepository
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        val dependencies = EntryPointAccessors
            .fromApplication(context.applicationContext, Dependencies::class.java)
        // DataStore からの 1 回読み。ウィジェット更新は既に別スレッドで呼ばれている
        val cache = runBlocking { dependencies.notebookCacheStore().read() }
        val style = runBlocking { dependencies.launcherPreferences().state.first() }.notebook.style
        val now = System.currentTimeMillis()

        appWidgetIds.forEach { id ->
            val views = RemoteViews(context.packageName, R.layout.widget_notebooks).apply {
                val refreshed = NotebookWidgetText.refreshedAt(cache.fetchedAtMillis, now)
                setTextViewText(
                    R.id.widget_title,
                    NotebookRowStyle.styled(
                        context = context,
                        text = if (refreshed.isBlank()) headerTitle else "$headerTitle · $refreshed",
                        style = style,
                        dim = true,
                    ),
                )
                setTextViewText(R.id.widget_refresh, "↻")
                setContentDescription(R.id.widget_refresh, "Refresh notebooks")

                if (cache.status.isBlank()) {
                    setViewVisibility(R.id.widget_status, android.view.View.GONE)
                } else {
                    setViewVisibility(R.id.widget_status, android.view.View.VISIBLE)
                    setTextViewText(
                        R.id.widget_status,
                        NotebookRowStyle.styled(context, cache.status, style, dim = true),
                    )
                }

                // 理由を出している（＝設定が足りない）ときは行を出さない。
                // 古い一覧を残したまま「権限が無い」と書くと、どちらが本当か読めない
                if (cache.showsList) {
                    setViewVisibility(R.id.widget_list, android.view.View.VISIBLE)
                    setRemoteAdapter(R.id.widget_list, collectionIntent(context, id))
                    setEmptyView(R.id.widget_list, R.id.widget_empty)
                    setTextViewText(R.id.widget_empty, "No notebooks to show")
                } else {
                    setViewVisibility(R.id.widget_list, android.view.View.GONE)
                    setViewVisibility(R.id.widget_empty, android.view.View.GONE)
                }

                setPendingIntentTemplate(R.id.widget_list, rowTemplate(context))
                setOnClickPendingIntent(R.id.widget_refresh, refreshIntent(context))
                // 設定が足りないときは、見出しを押せば設定画面へ行けるようにする
                if (!cache.showsList) {
                    setOnClickPendingIntent(R.id.widget_status, settingsIntent(context))
                }
            }
            appWidgetManager.updateAppWidget(id, views)
            appWidgetManager.notifyAppWidgetViewDataChanged(id, R.id.widget_list)
        }
    }

    override fun onEnabled(context: Context) {
        // 1 つめが置かれた時点で定期更新を始める
        NotebookRefreshWorker.schedule(context)
        NotebookRefreshWorker.runNow(context)
    }

    override fun onDisabled(context: Context) {
        // 最後の 1 つが外れたら止める。誰も見ていない一覧を取り続ける理由が無い
        NotebookRefreshWorker.cancel(context)
    }

    private fun collectionIntent(context: Context, appWidgetId: Int): Intent =
        Intent(context, NotebookRemoteViewsService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            putExtra(NotebookRemoteViewsService.EXTRA_ROW_LAYOUT, rowLayout)
            // RemoteAdapter は Intent の中身で区別されない（extras は見ない）ので、
            // ウィジェットごとに別物だと分かる data を持たせる。
            // 付けないと 2 つ置いたとき後から置いたほうが前のものを使い回す
            data = "zlauncher://notebooks/$appWidgetId/$rowLayout".toUri()
        }

    private fun rowTemplate(context: Context): PendingIntent =
        PendingIntent.getActivity(
            context,
            REQUEST_ROW,
            Intent(context, NotebookWidgetClickActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE,
        )

    private fun refreshIntent(context: Context): PendingIntent =
        PendingIntent.getBroadcast(
            context,
            REQUEST_REFRESH,
            Intent(context, javaClass).setAction(ACTION_REFRESH),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

    private fun settingsIntent(context: Context): PendingIntent =
        PendingIntent.getActivity(
            context,
            REQUEST_SETTINGS,
            Intent(context, MainActivity::class.java)
                .setAction(ACTION_OPEN_NOTEBOOK_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_REFRESH) {
            NotebookRefreshWorker.runNow(context)
            return
        }
        super.onReceive(context, intent)
    }

    companion object {
        const val ACTION_REFRESH = "com.example.zlauncher.notebook.REFRESH"
        const val ACTION_OPEN_NOTEBOOK_SETTINGS = "com.example.zlauncher.notebook.SETTINGS"
        private const val REQUEST_ROW = 2001
        private const val REQUEST_REFRESH = 2002
        private const val REQUEST_SETTINGS = 2003

        /** 取得が終わったあと、置かれている両方のウィジェットに作り直させる */
        fun refreshAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context) ?: return
            listOf(NotebookOpenWidget::class.java, NotebookInfoWidget::class.java).forEach { type ->
                val ids = manager.getAppWidgetIds(ComponentName(context, type))
                if (ids.isEmpty()) return@forEach
                context.sendBroadcast(
                    Intent(context, type)
                        .setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                        .putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                )
            }
        }
    }
}

/** 押すと開く側。行は絵文字と題だけ */
class NotebookOpenWidget : NotebookWidget() {
    override val rowLayout = R.layout.widget_notebook_row_open
    override val headerTitle = "Notebooks"
}

/** 情報を見る側。行に権限・共有・最後に見た日時を足す */
class NotebookInfoWidget : NotebookWidget() {
    override val rowLayout = R.layout.widget_notebook_row_info
    override val headerTitle = "Notebook info"
}
