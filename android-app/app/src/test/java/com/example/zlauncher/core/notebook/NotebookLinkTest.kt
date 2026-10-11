package com.example.zlauncher.core.notebook

import org.junit.Assert.assertEquals
import org.junit.Test

class NotebookLinkTest {

    @Test
    fun `a notebook id becomes the url the app or the browser opens`() {
        assertEquals(
            "https://notebooklm.google.com/notebook/9f2a-1",
            NotebookLink.notebook("9f2a-1"),
        )
    }

    @Test
    fun `without an id the link goes to the notebook list`() {
        assertEquals("https://notebooklm.google.com/", NotebookLink.home())
    }
}
