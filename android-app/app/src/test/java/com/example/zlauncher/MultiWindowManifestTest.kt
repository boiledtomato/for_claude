package com.example.zlauncher

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * マルチウィンドウ（分割画面・フリーフォーム・DeX）で使えるままであることを宣言の側から見る。
 *
 * **崩れてもビルドもテストも lint も通る。** 壊れ方は「分割画面にこのホームを入れられない」
 * という形で実機にだけ出る。しかも直し方が逆向きに見えるので、規則として固定しておく。
 *
 * 噛み合っている宣言は 3 つ:
 *
 * 1. `resizeableActivity="true"` ― targetSdk 24 以降の既定値ではあるが、明示しておく。
 *    `false` にすると compact（電話機）では**マルチウィンドウ自体が無効**になる
 * 2. 寸法が変わったときの設定変更を自前で受ける ― 分割画面の仕切りを動かすたびに
 *    Activity が作り直されると、開いていたペインもダイアログも消える
 * 3. `<layout android:minWidth/minHeight>` を**宣言しない** ― 一見「狭すぎる窓を防ぐ」
 *    正しい手に見えるが、Android 12 以降の compact 画面ではこの最小寸法が分割画面の
 *    割り当てに収まるかどうかで可否が決まる。電話機の半分に収まらない値を書いた時点で、
 *    守ろうとしていた分割画面が消える。狭い窓は列数を減らして受ける（CATEGORY_TILE_MIN）
 *
 * `screenOrientation="nosensor"` との両立は OS 側が面倒を見る ―
 * マルチウィンドウ中は向きの指定が無視されるので、回転の固定と併存できる。
 */
class MultiWindowManifestTest {

    private val manifest = File("src/main/AndroidManifest.xml").readText()

    @Test
    fun `the activity declares itself resizeable`() {
        assertTrue(
            "resizeableActivity=false は compact 画面でマルチウィンドウを無効にする",
            manifest.contains("android:resizeableActivity=\"true\""),
        )
        assertFalse(
            "resizeableActivity を false にするとこの機能は無くなる",
            manifest.contains("android:resizeableActivity=\"false\""),
        )
    }

    @Test
    fun `resizing the window does not recreate the activity`() {
        val configChanges = Regex("android:configChanges=\"([^\"]+)\"")
            .find(manifest)
            ?.groupValues
            ?.get(1)
            .orEmpty()
            .split("|")
            .map { it.trim() }
        // 分割画面の仕切りを動かすと、この 4 つが変わる
        listOf("screenSize", "smallestScreenSize", "screenLayout", "density").forEach { change ->
            assertTrue(
                "$change を受けないと、窓の寸法が変わるたびに画面が作り直される",
                change in configChanges,
            )
        }
    }

    @Test
    fun `no minimum window size is declared`() {
        // 最小寸法を書くと、Android 12 以降の compact 画面では
        // 「その寸法が分割画面の割り当てに収まるか」で可否が決まるようになる
        assertFalse(
            "<layout minWidth/minHeight> は電話機の分割画面を消しかねない。狭い窓は UI 側で受ける",
            manifest.contains("android:minWidth") || manifest.contains("android:minHeight"),
        )
    }

    @Test
    fun `the orientation lock is the kind that multi-window can ignore`() {
        // nosensor はマルチウィンドウ中は無視される（向きは窓の寸法で決まる）。
        // locked や記憶された向きに変えると、回転の固定とマルチウィンドウの関係が変わる
        assertTrue(
            "向きの固定を変えるなら、マルチウィンドウ中の扱いも見直すこと",
            manifest.contains("android:screenOrientation=\"nosensor\""),
        )
    }
}
