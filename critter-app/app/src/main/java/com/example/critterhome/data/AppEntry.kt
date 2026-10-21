package com.example.critterhome.data

import android.content.ComponentName
import android.os.UserHandle

/**
 * 起動できるアプリ 1 つ。
 *
 * 同じパッケージが個人用と仕事用の両方にありうるので、[key] にはユーザーも含める。
 */
data class AppEntry(
    val packageName: String,
    val componentName: ComponentName,
    val label: String,
    val user: UserHandle,
    val isWorkProfile: Boolean,
    /** アプリが自分で宣言した分類（`ApplicationInfo.category`）。住みかの振り分けに使う */
    val declaredCategory: Int,
) {
    val key: String get() = "${componentName.flattenToShortString()}#${user.hashCode()}"
}
