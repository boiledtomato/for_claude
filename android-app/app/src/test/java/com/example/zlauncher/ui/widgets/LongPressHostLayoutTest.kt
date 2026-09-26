package com.example.zlauncher.ui.widgets

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

/**
 * ウィジェットの上の長押し。
 *
 * **本物のタッチの配り方で試す。** Compose のジェスチャーで拾っていたときは、部品だけの
 * テストが通っても実機で効かなかった ― テストが Compose の入り口に直接イベントを流していて、
 * View 階層での配り方（親の [android.view.ViewGroup.onInterceptTouchEvent] を通ってから子へ）
 * を再現できていなかったため。ここでは本物の [MotionEvent] を親へ投げ、子のウィジェットに
 * 届くかどうかまで見る。
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class LongPressHostLayoutTest {

    private val longPressTimeout = ViewConfiguration.getLongPressTimeout().toLong()

    /**
     * **Activity に載せてから試す。** ビューが window に付いていないと
     * [android.view.View.postDelayed] は溜められるだけで走らず、長押しの待ち時間そのものが
     * 動かない（最初これに引っかかって、テストだけが赤くなった）。
     */
    private class Harness {
        val activity: Activity = Robolectric.buildActivity(Activity::class.java).setup().get()
        val context: Context = activity
        var longPresses = 0
        var childTaps = 0

        /** ウィジェット本体の代わり。中身のビューはタッチを食べる */
        val child = View(context).apply {
            isClickable = true
            setOnClickListener { childTaps++ }
        }

        val layout = LongPressHostLayout(context).apply {
            onLongPress = { longPresses++ }
            addView(
                child,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT,
                ),
            )
        }

        init {
            activity.setContentView(layout, android.view.ViewGroup.LayoutParams(SIZE, SIZE))
            shadowOf(android.os.Looper.getMainLooper()).idle()
            layout.measure(
                View.MeasureSpec.makeMeasureSpec(SIZE, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(SIZE, View.MeasureSpec.EXACTLY),
            )
            layout.layout(0, 0, SIZE, SIZE)
        }

        fun send(action: Int, x: Float = 50f, y: Float = 50f): Boolean {
            val now = SystemClock.uptimeMillis()
            val event = MotionEvent.obtain(now, now, action, x, y, 0)
            return layout.dispatchTouchEvent(event).also { event.recycle() }
        }

        fun idle(millis: Long) {
            shadowOf(android.os.Looper.getMainLooper()).idleFor(millis, TimeUnit.MILLISECONDS)
        }

        private companion object {
            const val SIZE = 200
        }
    }

    @Test
    fun `holding still past the timeout hands the gesture to us`() {
        val harness = Harness()
        harness.send(MotionEvent.ACTION_DOWN)
        harness.idle(longPressTimeout + 50)

        assertEquals("long press did not fire", 1, harness.longPresses)

        harness.send(MotionEvent.ACTION_UP)
        harness.idle(TAP_SETTLE_MILLIS)
        assertEquals("the widget must not also see a tap", 0, harness.childTaps)
    }

    @Test
    fun `a quick tap is left to the widget`() {
        val harness = Harness()
        harness.send(MotionEvent.ACTION_DOWN)
        harness.idle(longPressTimeout / 4)
        harness.send(MotionEvent.ACTION_UP)
        harness.idle(longPressTimeout * 2)

        assertEquals("a tap must not become a long press", 0, harness.longPresses)
        assertEquals("the widget should have received the tap", 1, harness.childTaps)
    }

    @Test
    fun `moving further than the slop cancels the long press`() {
        val harness = Harness()
        val slop = ViewConfiguration.get(harness.context).scaledTouchSlop.toFloat()

        harness.send(MotionEvent.ACTION_DOWN, x = 50f, y = 50f)
        harness.send(MotionEvent.ACTION_MOVE, x = 50f, y = 50f + slop * 2)
        harness.idle(longPressTimeout * 2)

        assertEquals("a scroll must not become a long press", 0, harness.longPresses)
    }

    @Test
    fun `small wobble still counts as holding still`() {
        val harness = Harness()
        harness.send(MotionEvent.ACTION_DOWN, x = 50f, y = 50f)
        harness.send(MotionEvent.ACTION_MOVE, x = 51f, y = 51f)
        harness.idle(longPressTimeout + 50)

        assertEquals(1, harness.longPresses)
    }

    @Test
    fun `a widget that asks not to be intercepted is left alone`() {
        val harness = Harness()
        harness.send(MotionEvent.ACTION_DOWN)
        // 中でスクロールするウィジェットが出す要求
        harness.layout.requestDisallowInterceptTouchEvent(true)
        harness.idle(longPressTimeout * 2)

        assertEquals(0, harness.longPresses)
    }

    @Test
    fun `nothing is taken while the interception is switched off`() {
        val harness = Harness()
        harness.layout.interceptEnabled = false

        harness.send(MotionEvent.ACTION_DOWN)
        harness.idle(longPressTimeout * 2)
        harness.send(MotionEvent.ACTION_UP)
        // クリックは UP のあとに post される。looper を回さないと数えられない
        harness.idle(TAP_SETTLE_MILLIS)

        assertEquals(0, harness.longPresses)
        assertEquals("Layout モード中はウィジェットに素通しする", 1, harness.childTaps)
    }

    @Test
    fun `turning the interception off mid gesture drops the pending long press`() {
        val harness = Harness()
        harness.send(MotionEvent.ACTION_DOWN)
        harness.layout.interceptEnabled = false
        harness.idle(longPressTimeout * 2)

        assertEquals(0, harness.longPresses)
    }

    @Test
    fun `the gesture is claimed only after it fires`() {
        val harness = Harness()
        // 押した直後は子に配る（＝横取りしない）
        assertFalse(harness.layout.onInterceptTouchEvent(motion(MotionEvent.ACTION_DOWN)))
        harness.idle(longPressTimeout + 50)
        // 成立したあとは親が取る
        assertTrue(harness.layout.onInterceptTouchEvent(motion(MotionEvent.ACTION_MOVE)))
    }

    /** UP のあとに post されるクリックを走らせるための待ち */
    private val TAP_SETTLE_MILLIS = 50L

    private fun motion(action: Int): MotionEvent {
        val now = SystemClock.uptimeMillis()
        return MotionEvent.obtain(now, now, action, 50f, 50f, 0)
    }
}
