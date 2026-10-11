package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 宛先の組み立て。**間違えても実機では 403 か 404 にしか見えない**ので、
 * 公式ドキュメントの雛形どおりであることを文字列で固定する。
 */
class NotebookEndpointTest {

    @Test
    fun `the regional host carries the location prefix`() {
        assertEquals(
            "https://us-discoveryengine.googleapis.com/v1alpha" +
                "/projects/123456789/locations/global/notebooks:listRecentlyViewed",
            NotebookEndpoint.listRecentlyViewed("us", "123456789", "global"),
        )
        assertEquals("eu-discoveryengine.googleapis.com", NotebookEndpoint.host("eu"))
    }

    @Test
    fun `the global host has no prefix`() {
        // 雛形の文字どおり global- を付けると名前が解決しない。
        // ここが唯一の分かれ目なので、直すときもこの 1 か所
        assertEquals("discoveryengine.googleapis.com", NotebookEndpoint.host("global"))
        assertTrue(
            NotebookEndpoint.collection("global", "1", "global")
                .startsWith("https://discoveryengine.googleapis.com/"),
        )
    }

    @Test
    fun `a page size is appended as a query parameter`() {
        assertTrue(
            NotebookEndpoint.listRecentlyViewed("us", "1", "global", pageSize = 20)
                .endsWith(":listRecentlyViewed?pageSize=20"),
        )
    }

    @Test
    fun `one notebook is addressed under the collection`() {
        assertEquals(
            NotebookEndpoint.collection("us", "1", "global") + "/abc-123",
            NotebookEndpoint.notebook("us", "1", "global", "abc-123"),
        )
    }

    @Test
    fun `a project id pasted in place of the project number is rejected`() {
        // 番号ではなく ID を貼るのは一番ありがちな間違い。通すと 403 になって原因が見えない
        assertTrue(NotebookEndpoint.isValidProjectNumber("123456789012"))
        assertFalse(NotebookEndpoint.isValidProjectNumber("my-project-id"))
        assertFalse(NotebookEndpoint.isValidProjectNumber(""))
        assertFalse(NotebookEndpoint.isValidProjectNumber("123 456"))
    }

    @Test
    fun `only the documented endpoint locations are accepted`() {
        assertEquals(listOf("us", "eu", "global"), NotebookEndpoint.LOCATIONS)
        assertTrue(NotebookEndpoint.isValidEndpointLocation("eu"))
        assertFalse(NotebookEndpoint.isValidEndpointLocation("asia"))
    }
}
