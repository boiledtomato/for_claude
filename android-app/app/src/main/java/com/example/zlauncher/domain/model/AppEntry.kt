package com.example.zlauncher.domain.model

import android.content.ComponentName
import android.os.UserHandle

/**
 * ホームに並べる 1 アプリ。
 *
 * お気に入りの保存キーは [packageName]。ComponentName を保存すると、アプリ更新で
 * ランチャーアクティビティのクラス名が変わったときに配置が消えるため。
 */
data class AppEntry(
    val packageName: String,
    val componentName: ComponentName,
    val label: String,
    val user: UserHandle,
    val firstInstallTime: Long,
) {
    val key: String get() = componentName.flattenToShortString()
}
