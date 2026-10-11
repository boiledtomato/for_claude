package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 状態コードの振り分け。**理由ごとに次の手が違う**ので、ひとまとめの「失敗」にしない。
 * 200 の経路は JSON を読むので [NotebookJsonTest] 側（Robolectric）で見る。
 */
class NotebookFetchStatusTest {

    @Test
    fun `401 asks for a new sign-in`() {
        assertEquals(NotebookFetch.NeedsSignIn, NotebookFetch.fromStatus(401, ""))
    }

    @Test
    fun `403 and 404 both mean the project cannot be used`() {
        // Google の API は権限を伏せるために 404 を返すことがある。
        // 利用者から見れば「使えない」で同じなので、同じ案内に寄せる
        assertEquals(NotebookFetch.NoAccess, NotebookFetch.fromStatus(403, ""))
        assertEquals(NotebookFetch.NoAccess, NotebookFetch.fromStatus(404, ""))
    }

    @Test
    fun `429 keeps the previous contents`() {
        assertEquals(NotebookFetch.RateLimited, NotebookFetch.fromStatus(429, ""))
    }

    @Test
    fun `server errors and anything unexpected are plain failures`() {
        assertEquals(NotebookFetch.Failed("server error 503"), NotebookFetch.fromStatus(503, ""))
        assertEquals(NotebookFetch.Failed("unexpected status 418"), NotebookFetch.fromStatus(418, ""))
    }
}
