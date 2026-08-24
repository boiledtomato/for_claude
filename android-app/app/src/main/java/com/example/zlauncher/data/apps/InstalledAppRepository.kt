package com.example.zlauncher.data.apps

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import com.example.zlauncher.domain.model.AppEntry
import javax.inject.Inject
import javax.inject.Singleton

/**
 * インストール済みアプリの一覧。ラベルだけを先に返し、アイコンは [AppIconLoader] が後追いで解決する。
 * 起動直後にアイコンをまとめてラスタライズすると数百 ms 単位で固まるため、ここでは触らない。
 */
@Singleton
class InstalledAppRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSource: LauncherAppsDataSource,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val apps: Flow<List<AppEntry>> = dataSource.changes
        .map { loadApps() }
        .flowOn(Dispatchers.IO)
        .stateIn(scope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private fun loadApps(): List<AppEntry> {
        val user = dataSource.currentUser
        val pm = context.packageManager
        return dataSource.activityList(user).mapNotNull { info ->
            runCatching {
                AppEntry(
                    packageName = info.componentName.packageName,
                    componentName = info.componentName,
                    label = info.label?.toString().orEmpty().ifBlank { info.componentName.packageName },
                    user = user,
                    firstInstallTime = pm.firstInstallTimeOf(info.componentName.packageName),
                )
            }.onFailure { Log.w(TAG, "skip ${info.componentName}", it) }.getOrNull()
        }
    }

    private fun PackageManager.firstInstallTimeOf(packageName: String): Long =
        runCatching { getPackageInfo(packageName, 0).firstInstallTime }.getOrDefault(0L)

    private companion object {
        const val TAG = "InstalledAppRepository"
    }
}
