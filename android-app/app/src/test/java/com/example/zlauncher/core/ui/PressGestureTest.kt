package com.example.zlauncher.core.ui

import com.example.zlauncher.core.ui.PressGesture.Outcome
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 1 つのアイコンに「起動」「取り外し」「別カテゴリーへ移動」の 3 つが載っているので、
 * 分かれ道を取り違えると、運ぶつもりがアプリを起動する / 消すつもりが運び始める。
 *
 * 端末の値に近いところ（スロップ 8px、長押し 500ms）で固定しておく。
 */
class PressGestureTest {

    private val slop = 8f
    private val longPress = 500L

    /** moved は横の距離。縦は一覧の巻き取りに渡すので、ここには入ってこない */
    private fun decide(moved: Float, elapsed: Long, lifted: Boolean) =
        PressGesture.decide(moved, elapsed, lifted, slop, longPress)

    @Test
    fun `moving sideways before the long press wins is a drag`() {
        assertEquals(Outcome.DRAG, decide(moved = 20f, elapsed = 120, lifted = false))
    }

    @Test
    fun `holding still past the timeout is a long press`() {
        assertEquals(Outcome.LONG_PRESS, decide(moved = 2f, elapsed = 520, lifted = false))
    }

    @Test
    fun `slop must be exceeded, not merely reached`() {
        // ちょうどスロップぶんの揺れは指の震え。ここで運び始めると起動できなくなる
        assertEquals(Outcome.PENDING, decide(moved = slop, elapsed = 100, lifted = false))
    }

    @Test
    fun `a short press that lifts is a tap`() {
        assertEquals(Outcome.TAP, decide(moved = 3f, elapsed = 90, lifted = true))
    }

    @Test
    fun `still undecided while the finger is down and has not moved`() {
        assertEquals(Outcome.PENDING, decide(moved = 0f, elapsed = 10, lifted = false))
    }

    @Test
    fun `moving after the long press has already fired is not a drag`() {
        // 取り外しモードに入ったあとに指が動いても、運び始めてはいけない
        assertEquals(Outcome.LONG_PRESS, decide(moved = 40f, elapsed = 600, lifted = false))
    }

    @Test
    fun `lifting after a long move still reads as a drag, never a tap`() {
        assertEquals(Outcome.DRAG, decide(moved = 40f, elapsed = 200, lifted = true))
    }
}
