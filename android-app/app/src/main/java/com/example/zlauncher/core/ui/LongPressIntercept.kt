package com.example.zlauncher.core.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed

/**
 * 長押しだけを横取りする。**普通のタップは相手（子）にそのまま通す。**
 *
 * ウィジェットは提供元アプリのビューなので、何もしなければタッチはすべて向こうに届く。
 * かといって膜をかぶせてしまうと、ウィジェットを普通に使えなくなる。そこで
 * **Initial パス**（子へ配る前）でだけ様子を見て、指が長押しの時間だけ止まっていたら
 * そこから先のイベントを自分のものにする。それより早く離せば何も消費しないので、
 * タップもスクロールも素通りする。
 *
 * ホーム画面のランチャーが普通にやっていること（長押しは置き場の操作）と同じ考え方。
 */
fun Modifier.interceptLongPress(
    enabled: Boolean = true,
    onLongPress: () -> Unit,
): Modifier = composed {
    val current by rememberUpdatedState(onLongPress)

    if (!enabled) {
        this
    } else {
        pointerInput(Unit) {
            val timeoutMillis = viewConfiguration.longPressTimeoutMillis
            val slop = viewConfiguration.touchSlop

            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)

                // 指が止まったまま時間切れになったときだけ長押し。離す・動かすと null 以外が返る
                val released = withTimeoutOrNull(timeoutMillis) {
                    var travelled = 0f
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Initial)
                        val change = event.changes.firstOrNull { it.id == down.id } ?: return@withTimeoutOrNull Unit
                        if (change.changedToUpIgnoreConsumed()) return@withTimeoutOrNull Unit
                        if (change.positionChanged()) {
                            travelled += (change.position - change.previousPosition).getDistance()
                            if (travelled > slop) return@withTimeoutOrNull Unit
                        }
                    }
                    @Suppress("UNREACHABLE_CODE")
                    Unit
                }
                if (released != null) return@awaitEachGesture

                current()

                // ここから先はこちらのもの。消費しないと、指を離した瞬間に
                // 提供元アプリがタップとして受け取ってしまう
                var pressed = true
                while (pressed) {
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                    event.changes.forEach { it.consume() }
                    pressed = event.changes.any { it.pressed }
                }
            }
        }
    }
}
