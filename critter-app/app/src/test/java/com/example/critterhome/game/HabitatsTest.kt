package com.example.critterhome.game

import android.content.pm.ApplicationInfo
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 住みかの振り分け。外れても何も起きず「変な所に住んでいる」だけなので、ここで固定する。
 */
class HabitatsTest {

    private fun classify(pkg: String, label: String, category: Int = ApplicationInfo.CATEGORY_UNDEFINED) =
        Habitats.classify(pkg, label, category)

    @Test
    fun `a declared category wins when it is specific`() {
        assertEquals(Habitat.ARCADE, classify("com.example.foo", "Foo", ApplicationInfo.CATEGORY_GAME))
        assertEquals(Habitat.GALLERY, classify("com.example.foo", "Foo", ApplicationInfo.CATEGORY_IMAGE))
        assertEquals(Habitat.WILDS, classify("com.example.foo", "Foo", ApplicationInfo.CATEGORY_MAPS))
    }

    @Test
    fun `broad declared categories look at the words first`() {
        assertEquals(Habitat.LAB, classify("com.android.chrome", "Chrome", ApplicationInfo.CATEGORY_PRODUCTIVITY))
        assertEquals(Habitat.WORKSHOP, classify("com.example.thing", "Thing", ApplicationInfo.CATEGORY_PRODUCTIVITY))
        assertEquals(Habitat.LAB, classify("com.example.thing", "Thing", ApplicationInfo.CATEGORY_ACCESSIBILITY))
    }

    @Test
    fun `words from the package and the label`() {
        assertEquals(Habitat.PLAZA, classify("jp.naver.line.android", "LINE"))
        assertEquals(Habitat.MUSIC, classify("com.google.android.apps.youtube.music", "YouTube Music"))
        assertEquals(Habitat.THEATER, classify("com.google.android.youtube", "YouTube"))
        assertEquals(Habitat.WILDS, classify("com.google.android.apps.maps", "Maps"))
        assertEquals(Habitat.PLAZA, classify("com.google.android.gm", "Gmail"))
        assertEquals(Habitat.WORKSHOP, classify("com.microsoft.teams", "Teams"))
        assertEquals(Habitat.LAB, classify("com.android.settings", "Settings"))
    }

    @Test
    fun `short words only match whole words`() {
        // "line" が "online"、"map" が "bitmap"、"tv" が "tvk" に当たらない
        assertEquals(Habitat.MEADOW, classify("com.example.onlinebank", "Online Bank"))
        assertEquals(Habitat.MEADOW, classify("com.example.bitmapper", "Bitmapper"))
        assertEquals(Habitat.MEADOW, classify("com.example.tvkanji", "Tvkanji"))
    }

    @Test
    fun `unknown apps live in the meadow`() {
        assertEquals(Habitat.MEADOW, classify("com.example.qwerty", "Qwerty"))
    }

    @Test
    fun `a user override beats everything, and an unknown key is ignored`() {
        val moved = mapOf("com.android.chrome" to Habitat.ARCADE.key)
        assertEquals(Habitat.ARCADE, Habitats.classify("com.android.chrome", "Chrome", ApplicationInfo.CATEGORY_UNDEFINED, moved))
        val stale = mapOf("com.android.chrome" to "gone")
        assertEquals(Habitat.LAB, Habitats.classify("com.android.chrome", "Chrome", ApplicationInfo.CATEGORY_UNDEFINED, stale))
    }
}
