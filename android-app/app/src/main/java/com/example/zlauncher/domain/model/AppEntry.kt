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
    /** 仕事用プロファイル（managed profile）のアプリかどうか */
    val isWorkProfile: Boolean = false,
    /** 端末にプリインストールされたアプリ。本体は消せない */
    val isSystem: Boolean = false,
    /** プリインに更新が当たっている ＝ 更新だけは剥がせる */
    val hasSystemUpdate: Boolean = false,
) {
    /** 同じパッケージが個人用と仕事用の両方に存在しうるので、キーにユーザーを含める */
    val key: String get() = "${componentName.flattenToShortString()}#${user.hashCode()}"

    /**
     * アンインストールを頼めるか。
     *
     * - プリインストールは本体を消せない。更新が当たっていれば**更新だけ**剥がせる
     * - **仕事用プロファイルは対象外。** OS のアンインストーラを開く Intent はパッケージ名しか
     *   運べず、どのユーザーのものかを指定できない。仕事用に対して投げると同じパッケージの
     *   個人用のほうが消えうるので、こちらからは出さない（App info からなら正しい側が開く）
     */
    val canUninstall: Boolean get() = !isWorkProfile && (!isSystem || hasSystemUpdate)

    /** 剥がせるのが更新だけ ＝ ラベルを「Uninstall updates」にする */
    val uninstallRemovesUpdatesOnly: Boolean get() = isSystem && hasSystemUpdate
}
