package com.example.zlauncher.ui.widgets

import android.content.Context
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import kotlin.math.hypot

/**
 * 置いたウィジェットの入れ物。**長押しだけを横取りする。**
 *
 * ウィジェットの中身は提供元アプリのビューで、タッチを自分で食べる。Compose 側の
 * ジェスチャーで拾おうとすると、View との受け渡しの層を挟むぶん端末によって取りこぼす
 * （実際に効かなかった）。Android の [onInterceptTouchEvent] は**子へ配る前に必ず親を通る**
 * 仕組みなので、ここで判定する。ホーム画面のランチャーが昔からやっているやり方。
 *
 * 判定の条件は端末の標準に合わせる ― 長押しの時間（[ViewConfiguration.getLongPressTimeout]）
 * のあいだ、指が [ViewConfiguration.getScaledTouchSlop] より動かないこと。早く離せば
 * 何も横取りしないので、普通のタップもスクロールもウィジェットにそのまま届く。
 */
class LongPressHostLayout(context: Context) : FrameLayout(context) {

    /** 長押しが成立したときに呼ぶ。合成中に差し替わるので var で持つ */
    var onLongPress: (() -> Unit)? = null

    /** Layout モードや取り外しモード中は横取りしない（そちらで別の操作をしている） */
    var interceptEnabled: Boolean = true
        set(value) {
            field = value
            if (!value) cancelPending()
        }

    private val slop = ViewConfiguration.get(context).scaledTouchSlop.toFloat()
    private var downX = 0f
    private var downY = 0f

    /** 長押しが成立したか。成立後の残りのイベントは子へ渡さない */
    private var claimed = false

    private val check = Runnable {
        claimed = true
        onLongPress?.invoke()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (!interceptEnabled) return false
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                claimed = false
                downX = ev.x
                downY = ev.y
                postDelayed(check, ViewConfiguration.getLongPressTimeout().toLong())
            }

            MotionEvent.ACTION_MOVE ->
                if (hypot(ev.x - downX, ev.y - downY) > slop) cancelPending()

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> cancelPending()
        }
        // 成立した瞬間から横取りする。それまでは false ＝ 子に配る
        return claimed
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_UP || ev.actionMasked == MotionEvent.ACTION_CANCEL) {
            cancelPending()
        }
        // 子が誰も取らなかったぶんはここへ来る。true を返しておかないと以降の
        // MOVE / UP が届かず、指を離しても待ち時間が生きたままになる
        return interceptEnabled
    }

    /**
     * 子（中でスクロールするウィジェットなど）が「親は割り込まないで」と言ってきたら、
     * 長押しの判定はやめる。ここを無視すると、スクロールしようとしただけで
     * 取り外しモードに入ってしまう。
     */
    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        if (disallowIntercept) cancelPending()
        super.requestDisallowInterceptTouchEvent(disallowIntercept)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelPending()
    }

    private fun cancelPending() {
        removeCallbacks(check)
    }
}
