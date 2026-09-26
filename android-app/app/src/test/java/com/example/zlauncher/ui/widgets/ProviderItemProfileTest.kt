package com.example.zlauncher.ui.widgets

import android.content.ComponentName
import android.os.Process
import android.os.UserHandle
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * 一覧の 1 行が、どのプロファイルのものかを取り違えないこと。
 *
 * 同じアプリが個人用と仕事用の両方に入っていると、`provider`（ComponentName）は**完全に同じ**に
 * なる。鍵にユーザーを混ぜないと、片方を選んだだけでもう片方まで選択済みに見え、結び付ける
 * 相手も取り違える。仕事用プロファイルのある端末でしか出ないので、規則として固定しておく。
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ProviderItemProfileTest {

    private val personal: UserHandle = Process.myUserHandle()
    private val work: UserHandle = UserHandle.getUserHandleForUid(WORK_UID)

    private fun item(user: UserHandle, work: Boolean) = WidgetPickerViewModel.ProviderItem(
        provider = ComponentName("com.example.app", "com.example.app.Widget"),
        user = user,
        isWorkProfile = work,
        label = "Codes",
        appLabel = "Authenticator",
        icon = null,
        minHeightDp = 100,
        widthSpan = 2,
        sizeLabel = "140 × 100 dp",
        configure = null,
    )

    @Test
    fun `the same provider in two profiles is two different rows`() {
        assertNotEquals(
            item(personal, work = false).key,
            item(work, work = true).key,
        )
    }

    @Test
    fun `the same provider in the same profile is the same row`() {
        assertTrue(item(personal, work = false).key == item(personal, work = false).key)
    }

    @Test
    fun `searching work narrows to the work profile rows`() {
        assertTrue(item(work, work = true).matches("work"))
        assertFalse(item(personal, work = false).matches("work"))
        // 通常の検索語は今までどおり、どちらにも当たる
        assertTrue(item(work, work = true).matches("Authenticator"))
        assertTrue(item(personal, work = false).matches("Authenticator"))
    }

    private companion object {
        /** ユーザー 10（最初の仕事用プロファイルの慣習的な id）の uid */
        const val WORK_UID = 10 * 100000 + 1000
    }
}
