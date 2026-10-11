package com.example.zlauncher.data.notebook

import com.example.zlauncher.core.notebook.NotebookDivider
import com.example.zlauncher.core.notebook.NotebookSource
import com.example.zlauncher.data.prefs.LauncherState
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 既に端末に保存されている設定を読み直せるか。
 *
 * **ここが外れると、更新しても新機能が出ない。** 利用者の端末には `style` も `source` も
 * 無かった頃の JSON が入っている。欠けた項目が既定値に落ちないと、「アイコンを出す」が
 * false のまま動き続ける ― 例外は出ないので、実機で「更新したのに変わらない」という
 * 形でしか気付けない。
 */
class NotebookConfigMigrationTest {

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    @Test
    fun `a config saved before the style existed gets the new defaults`() {
        // Enterprise しか無かった頃に保存された形
        val old = """{"projectNumber":"123","endpointLocation":"us","location":"global"}"""
        val config = json.decodeFromString<NotebookConfig>(old)

        assertEquals("123", config.projectNumber)
        // 既定で罫線もアイコンも出る。出ないほうに倒れると、機能が無いのと同じ
        assertEquals(NotebookDivider.SOLID, config.style.divider)
        assertTrue(config.style.showIcons)
        // 読み元も、設定の要らないほうが既定
        assertEquals(NotebookSource.PIPELINE, config.source)
    }

    @Test
    fun `an entirely absent notebook section still yields a usable config`() {
        // ウィジェットを入れる前から使っている端末の形
        val state = json.decodeFromString<LauncherState>("""{"version":1}""")
        assertEquals(NotebookSource.PIPELINE, state.notebook.source)
        assertTrue(state.notebook.style.showIcons)
        assertEquals(NotebookDivider.SOLID, state.notebook.style.divider)
    }

    @Test
    fun `unknown keys from a newer build do not break an older read`() {
        // 先に進んだ版で保存された JSON を読み戻しても落ちない
        val future = """{"projectNumber":"1","somethingNew":42,"style":{"showIcons":false}}"""
        val config = json.decodeFromString<NotebookConfig>(future)
        assertEquals(false, config.style.showIcons)
        // 書かれていない項目はやはり既定
        assertEquals(NotebookDivider.SOLID, config.style.divider)
    }
}
