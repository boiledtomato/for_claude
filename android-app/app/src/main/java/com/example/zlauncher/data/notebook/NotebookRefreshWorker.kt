package com.example.zlauncher.data.notebook

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.zlauncher.ui.notebookwidget.NotebookWidget
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

/**
 * ウィジェットに出す一覧を取り直す。
 *
 * **ウィジェットの更新そのものでは通信しない。** `AppWidgetProvider.onUpdate` に与えられる
 * 時間は短く、そこで HTTP を待つと「固まったウィジェット」になる。取得はこちらで行い、
 * 保存してから作り直させる、という順にしてある。
 *
 * 失敗しても `success` を返す。取れない理由はほぼ設定の不足（ライセンス・プロジェクト番号）
 * で、数分おきに再試行しても直らないため ― 理由は保存してウィジェットに出す。
 */
@HiltWorker
class NotebookRefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: NotebookRepository,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val state = runCatching { repository.refresh() }
            .onFailure { Log.w(TAG, "notebook refresh failed", it) }
            .getOrNull()
        Log.i(TAG, "notebook refresh: $state")
        NotebookWidget.refreshAll(applicationContext)
        return Result.success()
    }

    companion object {
        private const val TAG = "NotebookRefresh"
        private const val PERIODIC = "notebook-refresh"
        private const val ONE_SHOT = "notebook-refresh-now"

        /**
         * 置かれている間だけ回す。間隔はウィジェットの実用と、プレビュー API への
         * 遠慮の折り合い ― 1 時間に 1 回より細かくしても、ノートブックの題や共有状態が
         * それより速く変わることはまず無い。
         */
        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<NotebookRefreshWorker>(1, TimeUnit.HOURS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                PERIODIC,
                ExistingPeriodicWorkPolicy.KEEP,
                request,
            )
        }

        /** 更新ボタン、設定を変えた直後、ウィジェットを置いた直後 */
        fun runNow(context: Context) {
            val request = OneTimeWorkRequestBuilder<NotebookRefreshWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
            WorkManager.getInstance(context)
                // 連打しても 1 本に畳む。押すたびに積むと、戻ってくる順で古い結果が勝つ
                .enqueueUniqueWork(ONE_SHOT, ExistingWorkPolicy.REPLACE, request)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(PERIODIC)
        }
    }
}
