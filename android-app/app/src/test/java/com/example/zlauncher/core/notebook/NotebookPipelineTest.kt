package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.File

/**
 * パイプラインが公開している同期状態の読み取り。
 *
 * **本物のファイルで読む。** `data/<name>_sync_state.json` は `sync_notebooklm.py` が
 * 書いているもので、このアプリの外（Python 側）で形が決まる。作り物の JSON だけで
 * 固めると、あちらが項目名を変えたときにウィジェットが黙って空になる ―
 * 気付けるのは実機でだけ、という一番困る壊れ方になる。
 *
 * リポジトリのルートから読むので、パスはテストの作業ディレクトリ（`app/`）基準。
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class NotebookPipelineTest {

    private val dataDir = File("../../data")

    private fun stateFiles(): List<File> =
        dataDir.listFiles { file -> file.name.endsWith("_sync_state.json") }?.sorted().orEmpty()

    @Test
    fun `every published state file still parses into a row`() {
        val files = stateFiles()
        assertTrue(
            "同期状態のファイルが 1 つも無い。パイプラインの出力場所が変わった可能性がある",
            files.isNotEmpty(),
        )
        files.forEach { file ->
            val summary = NotebookPipeline.parseState(file.readText(), file.name)
            assertTrue("${file.name} が読めない", summary != null)
            summary!!
            assertTrue("${file.name}: id が空だと開けない", summary.id.isNotBlank())
            assertTrue("${file.name}: 題が空", summary.displayTitle.isNotBlank())
            assertTrue("${file.name}: ソース数が負", summary.sourceCount >= 0)
            // 同期時刻は「いつの情報か」を出す唯一の手がかり
            assertTrue(
                "${file.name}: synced_at が読めない形",
                NotebookWidgetTextParsable(summary.syncedAt),
            )
        }
    }

    /** 相対時刻に直せる形か。直せない値は画面から黙って消えるので、ここで見る */
    private fun NotebookWidgetTextParsable(value: String): Boolean =
        value.isNotBlank() && runCatching { java.time.Instant.parse(value) }.isSuccess

    @Test
    fun `the notebooks the pipeline maintains come through by name`() {
        val titles = stateFiles().mapNotNull {
            NotebookPipeline.parseState(it.readText(), it.name)?.displayTitle
        }
        // 実際に運用している 2 つ。消えていたらパイプラインか読み取りのどちらかが壊れている
        assertTrue("Zscaler_help_docs が出てこない: $titles", titles.contains("Zscaler_help_docs"))
        assertTrue("Zscaler_community が出てこない: $titles", titles.contains("Zscaler_community"))
    }

    @Test
    fun `a state file without a notebook id is dropped, not shown blank`() {
        // 開く先が決まらない行は押しても何も起きない。出さないほうが正しい
        assertNull(NotebookPipeline.parseState("""{"sources":{"a":{}},"synced_at":"x"}""", "a.json"))
        assertNull(NotebookPipeline.parseState("""{"notebook":{}}""", "a.json"))
        assertNull(NotebookPipeline.parseState("not json", "a.json"))
    }

    @Test
    fun `a missing title falls back to the file name`() {
        val summary = NotebookPipeline.parseState(
            """{"notebook":{"id":"abc"},"sources":{}}""",
            "community_notebooklm_sync_state.json",
        )
        assertEquals("community", summary?.displayTitle)
        assertEquals(0, summary?.sourceCount)
    }

    @Test
    fun `the listing picks only the state files`() {
        // 本物の contents API の応答の形。data/ には articles.json なども並んでいる
        val body = """
            [
              {"name":"articles.json","path":"data/articles.json","type":"file"},
              {"name":"community_notebooklm_sync_state.json","type":"file"},
              {"name":"notebooklm_sync_state.json","type":"file"},
              {"name":"help_docs_index.json","type":"file"},
              {"name":"somedir","type":"dir"},
              {"name":"mslearn_entra_notebooklm_sync_state.json","type":"file"}
            ]
        """.trimIndent()
        assertEquals(
            listOf(
                "community_notebooklm_sync_state.json",
                "notebooklm_sync_state.json",
                "mslearn_entra_notebooklm_sync_state.json",
            ),
            NotebookPipeline.parseStateFileNames(body),
        )
    }

    @Test
    fun `a directory entry named like a state file is not fetched`() {
        assertTrue(
            NotebookPipeline.parseStateFileNames(
                """[{"name":"x_sync_state.json","type":"dir"}]"""
            ).isEmpty()
        )
    }

    @Test
    fun `a broken listing is empty, so the known set takes over`() {
        assertTrue(NotebookPipeline.parseStateFileNames("").isEmpty())
        assertTrue(NotebookPipeline.parseStateFileNames("{}").isEmpty())
        assertTrue(NotebookPipeline.parseStateFileNames("nonsense").isEmpty())
    }

    @Test
    fun `the fallback set matches what is actually published`() {
        // 列挙できなかったときの保険。本物とずれていたら保険にならない
        val published = stateFiles().map { it.name }.toSet()
        NotebookPipeline.FALLBACK_STATE_FILES.forEach { name ->
            assertTrue("既定の一覧にある $name が公開されていない", name in published)
        }
    }

    @Test
    fun `urls are built without doubling the separator`() {
        assertEquals(
            "https://example.github.io/repo/data/notebooklm_sync_state.json",
            NotebookPipeline.stateUrl("https://example.github.io/repo", "notebooklm_sync_state.json"),
        )
        // 末尾のスラッシュを足して貼る人が居る
        assertEquals(
            "https://example.github.io/repo/data/a.json",
            NotebookPipeline.stateUrl("https://example.github.io/repo/", "a.json"),
        )
        assertEquals(
            "https://api.github.com/repos/owner/name/contents/data",
            NotebookPipeline.contentsUrl("owner/name"),
        )
    }
}
