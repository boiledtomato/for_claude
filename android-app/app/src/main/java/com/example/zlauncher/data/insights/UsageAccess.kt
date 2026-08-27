package com.example.zlauncher.data.insights

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.provider.Settings
import androidx.core.content.getSystemService

/**
 * UID 別の通信量を読むための「使用状況へのアクセス」（Usage access）。
 *
 * `PACKAGE_USAGE_STATS` は signature|appop の権限で、実行時ダイアログでは出せない。
 * マニフェストに宣言したうえで、ユーザーに設定画面で許可してもらう以外に道が無い。
 * 未許可でもアプリは通常どおり動き、この画面だけが空になる。
 */
object UsageAccess {

    const val PERMISSION = "android.permission.PACKAGE_USAGE_STATS"

    fun isGranted(context: Context): Boolean = runCatching {
        val appOps = context.getSystemService<AppOpsManager>() ?: return false
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName,
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName,
            )
        }
        when (mode) {
            AppOpsManager.MODE_ALLOWED -> true
            // 既定のままなら権限側の判定に委ねる（端末によっては付与済みで DEFAULT が返る）
            AppOpsManager.MODE_DEFAULT ->
                context.checkCallingOrSelfPermission(PERMISSION) == PackageManager.PERMISSION_GRANTED
            else -> false
        }
    }.getOrDefault(false)

    /**
     * 使用状況アクセスの設定画面。アプリ個別の画面へ直接飛ぶ標準の方法は無いので、
     * 一覧を開いて自分を選んでもらう。
     */
    fun settingsIntent(): Intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
}
