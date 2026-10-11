package com.example.zlauncher.core.ui

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitHorizontalTouchSlopOrCancellation
import androidx.compose.foundation.gestures.drag
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ViewConfiguration

/**
 * 「押したまま動かす＝運ぶ」「押したまま止める＝その場の操作」を、**1 つのハンドラで**
 * さばく。
 *
 * `combinedClickable` とドラッグ検出を重ねると、長押しの時計は別々に走る。指を動かして
 * いるあいだに長押しも成立し、運んでいる途中で取り外しモードに落ちる。どちらが先かを
 * 決められるのは、同じストリームを見ている 1 つの場所だけ。
 *
 * 判定の規則そのものは [PressGesture] に書いてある（純粋なので固定できる）。ここは
 * その規則をポインタのイベントに当てているだけ。
 *
 * タップはここでは拾わない。押した感じ（`springyClick`）と一緒に呼び出し側に残すほうが、
 * 見た目と動きがずれない。
 *
 * 座標は**ウィンドウ基準**で渡す。掴む側（右のペイン）と落とす側（左のレール）では
 * 親の座標系が違うため。
 *
 * **運び始めるのは横に動かしたときだけ。** 縦はアイコンの一覧の巻き取りに残す ― どの向き
 * でも運び始めると、4 列を超えるカテゴリーが二度と巻けなくなる。落とし先は左のレールなので、
 * 運ぶ動きは元々横向きになる。縦に動かしたぶんは一覧側が食べ、こちらは空振りに終わる。
 */
fun Modifier.appDragGesture(
    enabled: Boolean = true,
    key: Any? = null,
    onLongPress: () -> Unit,
    onDragStart: (Offset) -> Unit,
    onDrag: (Offset) -> Unit,
    onDragEnd: () -> Unit,
    onDragCancel: () -> Unit,
): Modifier = composed {
    // **remember で持つ。** 素の var にすると再合成のたびに null に戻り、onGloballyPositioned
    // は位置が変わらないかぎり呼び直されないので、運んでいる最中に座標を見失う
    // （持ち上げた瞬間に tile は必ず再合成される ― 薄くするため）
    val coordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    // pointerInput の中身は key が変わるまで作り直されない。最初の合成で捕まえた
    // ラムダを呼び続けないよう、読むのは常に最新のほう
    val longPress by rememberUpdatedState(onLongPress)
    val dragStart by rememberUpdatedState(onDragStart)
    val dragging by rememberUpdatedState(onDrag)
    val dragEnd by rememberUpdatedState(onDragEnd)
    val dragCancel by rememberUpdatedState(onDragCancel)
    this
        .onGloballyPositioned { coordinates.value = it }
        .pointerInput(enabled, key) {
            if (!enabled) return@pointerInput
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                val slopChange = awaitSlopOrLongPress(down, viewConfiguration) { longPress() }
                    ?: return@awaitEachGesture

                fun windowPoint(change: PointerInputChange): Offset =
                    coordinates.value?.localToWindow(change.position) ?: change.position

                dragStart(windowPoint(slopChange))
                val completed = drag(slopChange.id) { change ->
                    dragging(windowPoint(change))
                    change.consume()
                }
                if (completed) dragEnd() else dragCancel()
            }
        }
}

/**
 * 横のスロップを越えるのが先ならその変化を返す。長押しの時間が先に過ぎたら [onLongPress] を
 * 呼び、指が離れるまでイベントを食べてから null を返す。
 *
 * **食べるのが肝。** 残すと、長押しで取り外しモードに入ったあと指を離した瞬間に、
 * 同じストロークがタップとしても成立してアプリが起動する。
 *
 * 指がスロップに届かないまま離れた（＝タップ）場合も null。こちらは何もしない。
 */
private suspend fun AwaitPointerEventScope.awaitSlopOrLongPress(
    down: PointerInputChange,
    viewConfiguration: ViewConfiguration,
    onLongPress: () -> Unit,
): PointerInputChange? {
    // ここの withTimeoutOrNull は Compose 側のもの。ポインタを待つスコープは
    // @RestrictsSuspension なので、kotlinx の withTimeout は呼べない
    var decided = false
    val slopChange = withTimeoutOrNull(viewConfiguration.longPressTimeoutMillis) {
        // 横だけを見る。縦に動いたぶんは一覧の巻き取りが持っていき、消費された時点で
        // ここは null を返して終わる（＝縦のスワイプでは運び始めない）
        val change = awaitHorizontalTouchSlopOrCancellation(down.id) { c, _ -> c.consume() }
        // 時間切れと「指が離れた」はどちらも null なので、印を付けて見分ける
        decided = true
        change
    }
    if (decided) return slopChange

    onLongPress()
    var change: PointerInputChange? = down
    while (change != null && change.pressed) {
        val event = awaitPointerEvent()
        change = event.changes.firstOrNull { it.id == down.id }
        change?.consume()
    }
    return null
}
