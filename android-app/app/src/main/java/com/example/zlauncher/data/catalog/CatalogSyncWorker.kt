package com.example.zlauncher.data.catalog

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.zlauncher.MainActivity
import com.example.zlauncher.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

/**
 * 3 か月ごとに URL カテゴリー CSV を取り直す。
 *
 * WorkManager にしているのは、間隔が長いので端末が寝ている時間をまたぐのが確実だから。
 * `AlarmManager` だと Doze で潰れ、再起動でも消える。WorkManager は再起動後に自分で復帰する。
 *
 * 失敗しても [androidx.work.ListenableWorker.Result.success] を返す。3 か月周期の仕事を
 * 数分おきに再試行させても意味が無く、次の周期で取り直せば十分なため。
 */
@HiltWorker
class CatalogSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val updater: CatalogUpdater,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        when (val result = updater.update()) {
            is CatalogUpdateResult.Updated -> {
                Log.i(TAG, "catalog updated: ${result.diff.changeCount} changes")
                notifyChanges(applicationContext, result.diff.changeCount, result.diff.toRevision)
            }
            is CatalogUpdateResult.UpToDate -> Log.i(TAG, "catalog unchanged (${result.revision})")
            is CatalogUpdateResult.Failed -> Log.w(TAG, "catalog update failed: ${result.reason}")
        }
        return Result.success()
    }

    companion object {
        private const val TAG = "CatalogSyncWorker"
        private const val WORK_NAME = "url-category-catalog-sync"
        private const val CHANNEL_ID = "catalog-updates"
        private const val NOTIFICATION_ID = 4101

        /** 3 か月ごと。初回は 1 日ずらして、インストール直後の通信を避ける */
        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<CatalogSyncWorker>(90, TimeUnit.DAYS)
                .setConstraints(
                    Constraints.Builder()
                        // 従量課金の回線で 30KB を落としに行かない
                        .setRequiredNetworkType(NetworkType.UNMETERED)
                        .build()
                )
                .setInitialDelay(1, TimeUnit.DAYS)
                .build()

            // KEEP。起動のたびに作り直すと初回遅延も 3 か月周期も毎回リセットされ、永久に走らない
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request,
            )
        }

        private fun notifyChanges(context: Context, changeCount: Int, revision: String) {
            // Android 13 以降は実行時の許可が要る。無ければ通知は諦める
            // （コンソール側にも同じ案内を出すので、気付けなくなるわけではない）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                Log.i(TAG, "notification permission not granted; console banner only")
                return
            }

            // minSdk 28 なのでチャンネルは常に必要。作成は冪等なので毎回呼んでよい
            val manager = NotificationManagerCompat.from(context)
            manager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    "URL category updates",
                    NotificationManager.IMPORTANCE_DEFAULT,
                ).apply { description = "Zscaler の URL カテゴリー一覧が改訂されたときの通知" }
            )

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val pending = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("URL categories changed")
                .setContentText("$changeCount change(s) in $revision — review in the console")
                .setStyle(
                    NotificationCompat.BigTextStyle().bigText(
                        "Zscaler published $revision with $changeCount change(s). " +
                            "Open the console to review them and update your categories."
                    )
                )
                .setContentIntent(pending)
                .setAutoCancel(true)
                .build()

            runCatching { manager.notify(NOTIFICATION_ID, notification) }
                .onFailure { Log.w(TAG, "could not post notification", it) }
        }
    }
}
