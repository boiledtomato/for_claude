package com.example.zlauncher.ui.setup

import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService

/**
 * 既定ホームアプリまわり。
 *
 * 「既定にする」ダイアログを直接出す API は無いので、ROLE_HOME のリクエスト（API 29+）か
 * ホームアプリ設定画面へ送るしかない。設定画面への導線は常設しておく（壊れたビルドを
 * 既定にしてしまったときに自力で戻れるようにするため）。
 */
object DefaultLauncher {

    /**
     * 判定は RoleManager ではなく HOME インテントの解決結果で行う。
     * RoleManager は API 29 以降にしか無く、minSdk 28 では触れられないため。
     */
    fun isDefault(context: Context): Boolean = runCatching {
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
        val resolved = context.packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        resolved?.activityInfo?.packageName == context.packageName
    }.getOrDefault(false)

    fun requestIntent(context: Context): Intent {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            RoleApi.homeRoleIntent(context)?.let { return it }
        }
        return homeSettingsIntent()
    }

    fun homeSettingsIntent(): Intent = Intent(Settings.ACTION_HOME_SETTINGS)

    @RequiresApi(Build.VERSION_CODES.Q)
    private object RoleApi {
        fun homeRoleIntent(context: Context): Intent? = runCatching {
            val roleManager = context.getSystemService<RoleManager>() ?: return null
            if (!roleManager.isRoleAvailable(RoleManager.ROLE_HOME)) return null
            roleManager.createRequestRoleIntent(RoleManager.ROLE_HOME)
        }.getOrNull()
    }
}
