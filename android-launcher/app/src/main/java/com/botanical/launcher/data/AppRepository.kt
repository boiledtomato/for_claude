package com.botanical.launcher.data

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 端末にインストールされた「起動可能なアプリ」の一覧。
 *
 * パッケージの可視性は AndroidManifest の <queries> で確保しているので
 * QUERY_ALL_PACKAGES は要らない。
 */
object AppRepository {

    /** アイコンはここで一度だけビットマップ化してキャッシュする。 */
    private const val ICON_PX = 144

    suspend fun load(context: Context): List<AppEntry> = withContext(Dispatchers.IO) {
        val pm = context.packageManager
        val query = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        pm.queryIntentActivities(query, 0)
            .asSequence()
            // 自分自身はホームなので一覧には出さない
            .filter { it.activityInfo.packageName != context.packageName }
            .mapNotNull { info ->
                runCatching {
                    AppEntry(
                        label = info.loadLabel(pm).toString(),
                        packageName = info.activityInfo.packageName,
                        className = info.activityInfo.name,
                        icon = info.activityInfo.loadIcon(pm)
                            .toBitmap(ICON_PX, ICON_PX)
                            .asImageBitmap(),
                    )
                }.getOrNull()
            }
            .sortedBy { it.label.lowercase() }
            .toList()
    }

    /** 失敗しても落ちない。アンインストール済みのアプリが割り当てに残っていても同じ。 */
    fun launch(context: Context, entry: AppEntry): Boolean {
        val intent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .setComponent(ComponentName(entry.packageName, entry.className))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
        return runCatching { context.startActivity(intent) }.isSuccess
    }

    fun openAppInfo(context: Context, entry: AppEntry) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(Uri.fromParts("package", entry.packageName, null))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        runCatching { context.startActivity(intent) }
    }

    /** 端末のホームアプリ設定画面。初回起動時に案内する。 */
    fun openHomeSettings(context: Context) {
        val intent = Intent(Settings.ACTION_HOME_SETTINGS)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        runCatching { context.startActivity(intent) }
    }
}
