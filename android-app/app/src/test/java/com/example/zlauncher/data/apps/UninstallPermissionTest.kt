package com.example.zlauncher.data.apps

import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * アンインストールの導線と、それに要る権限の宣言がそろっているかを見る。
 *
 * **この組み合わせが崩れても、ビルドもテストも lint も通る。** 壊れ方は実機でしか出ず、
 * しかも例外にならない ― `REQUEST_DELETE_PACKAGES` を宣言していないと、targetSdk 28 以降では
 * システムのアンインストーラが起動したそばから終了し、呼び出し側からは成功に見える。
 * 実際に「Uninstall を押しても何も起きない」という形で一度出したので、規則として固定する。
 */
class UninstallPermissionTest {

    private val manifest = File("src/main/AndroidManifest.xml").readText()
    private val dataSource =
        File("src/main/java/com/example/zlauncher/data/apps/LauncherAppsDataSource.kt").readText()

    @Test
    fun `the uninstall intent is still the one the permission covers`() {
        // ACTION_DELETE / ACTION_UNINSTALL_PACKAGE はどちらも同じアンインストーラに届き、
        // 同じ権限で門番される。別の手段（PackageInstaller.uninstall）に変えるなら、
        // こちらは installer of record か DELETE_PACKAGES が要るので、その時は作り直し
        assertTrue(
            "アンインストールの起動方法を変えたなら、要る権限も見直すこと",
            dataSource.contains("Intent.ACTION_DELETE") ||
                dataSource.contains("Intent.ACTION_UNINSTALL_PACKAGE"),
        )
    }

    @Test
    fun `the manifest declares the permission that uninstall needs`() {
        assertTrue(
            "REQUEST_DELETE_PACKAGES が無いと、アンインストーラは何も出さずに終わる",
            manifest.contains("android.permission.REQUEST_DELETE_PACKAGES"),
        )
    }

    @Test
    fun `deleting packages outright is never requested`() {
        // DELETE_PACKAGES は署名権限で、宣言しても一般アプリには付かない。
        // 付いたつもりで無確認の削除を書くと、動かないうえに危ない
        assertTrue(
            "DELETE_PACKAGES は署名権限。消すのは OS に任せる",
            !manifest.contains("android.permission.DELETE_PACKAGES\""),
        )
    }
}
