package com.example.zlauncher

import android.app.Application
import android.os.StrictMode
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.example.zlauncher.data.catalog.CatalogSyncWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * WorkManager は既定の初期化を止めて Hilt のファクトリを渡す
 * （マニフェスト側で androidx.startup のプロバイダを外してある）。
 * こうしないと Worker に依存を注入できない。
 */
@HiltAndroidApp
class ZLauncherApplication : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // アイコンのラスタライズをメインスレッドでやってしまう事故を書いた瞬間に気づけるようにする
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            )
        }
        // 既に積んであれば何もしない（KEEP）。周期と初回遅延がリセットされないようにする
        CatalogSyncWorker.schedule(this)
    }
}
