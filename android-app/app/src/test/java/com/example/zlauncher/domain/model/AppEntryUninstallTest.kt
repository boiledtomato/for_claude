package com.example.zlauncher.domain.model

import android.content.ComponentName
import android.os.Process
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * 長押しメニューに「Uninstall」を出してよいかの判定。
 *
 * ここを間違えると**別のアプリが消える**ので、規則として固定しておく。とくに仕事用
 * プロファイル: OS のアンインストーラを開く Intent はパッケージ名しか運べず、どのユーザーの
 * ものかを指定できない。仕事用のつもりで投げると同じパッケージの個人用が消えうる。
 */
@RunWith(RobolectricTestRunner::class)
// UserHandle を作るためだけに Robolectric を使う。SDK は他のテストに合わせる
@Config(sdk = [34])
class AppEntryUninstallTest {

    private fun entry(
        isWorkProfile: Boolean = false,
        isSystem: Boolean = false,
        hasSystemUpdate: Boolean = false,
    ) = AppEntry(
        packageName = "com.example.app",
        componentName = ComponentName("com.example.app", "com.example.app.Main"),
        label = "App",
        user = Process.myUserHandle(),
        firstInstallTime = 0L,
        isWorkProfile = isWorkProfile,
        isSystem = isSystem,
        hasSystemUpdate = hasSystemUpdate,
    )

    @Test
    fun `an ordinary installed app can be uninstalled`() {
        val app = entry()
        assertTrue(app.canUninstall)
        assertFalse(app.uninstallRemovesUpdatesOnly)
    }

    @Test
    fun `a preinstalled app cannot be uninstalled`() {
        assertFalse(entry(isSystem = true).canUninstall)
    }

    @Test
    fun `a preinstalled app with updates offers to remove just the updates`() {
        val app = entry(isSystem = true, hasSystemUpdate = true)
        assertTrue(app.canUninstall)
        assertTrue(app.uninstallRemovesUpdatesOnly)
    }

    @Test
    fun `a work profile app is never offered`() {
        // 個人用なら消せる条件（プリインでない）でも、仕事用というだけで出さない
        assertFalse(entry(isWorkProfile = true).canUninstall)
        assertFalse(entry(isWorkProfile = true, isSystem = true, hasSystemUpdate = true).canUninstall)
    }
}
