package com.example.zlauncher.core.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import com.example.zlauncher.core.designsystem.ZMotion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * LazyVerticalGrid の長押しドラッグ並べ替え。
 *
 * 入れ替えの判定は [ReorderGate] に任せる ― 以前は相手の矩形に中心が入った瞬間に
 * 入れ替えていたので、隣を通り過ぎるだけで何度も入れ替わっていた。
 *
 * 指を離したあとも位置をバネで戻す（[settlingIndex]）。即座にゼロへ戻すと、
 * つまんでいた要素が最後にカクッと飛んで没入感が切れるため。
 */
class GridReorderState internal constructor(
    private val gridState: LazyGridState,
    private val scope: CoroutineScope,
    private val onMove: (from: Int, to: Int) -> Unit,
    private val onDragStateChange: (Boolean) -> Unit,
) {
    var draggingIndex by mutableStateOf<Int?>(null)
        private set

    /** 指を離してから元位置に収まるまでの間だけ入る */
    var settlingIndex by mutableStateOf<Int?>(null)
        private set

    private var rawOffset by mutableStateOf(Offset.Zero)
    private val settleOffset = Animatable(Offset.Zero, Offset.VectorConverter)
    private val gate = ReorderGate()
    /** 指が止まっても滞在時間を数え切れるように、待ち時間ぶんの再評価を予約する */
    private var pendingCheck: Job? = null

    /** つまみ上げ中か、収まり中の要素 */
    val activeIndex: Int? get() = draggingIndex ?: settlingIndex

    val dragOffset: Offset
        get() = if (draggingIndex != null) rawOffset else settleOffset.value

    fun isActive(index: Int): Boolean = activeIndex == index

    fun start(index: Int) {
        pendingCheck?.cancel()
        gate.reset()
        scope.launch { settleOffset.snapTo(Offset.Zero) }
        settlingIndex = null
        draggingIndex = index
        rawOffset = Offset.Zero
        onDragStateChange(true)
    }

    fun drag(delta: Offset) {
        if (draggingIndex == null) return
        rawOffset += delta
        evaluate()
    }

    private fun evaluate() {
        val current = draggingIndex ?: return
        pendingCheck?.cancel()

        val visible = gridState.layoutInfo.visibleItemsInfo
        val info = visible.firstOrNull { it.index == current } ?: return
        val centerX = info.offset.x + rawOffset.x + info.size.width / 2f
        val centerY = info.offset.y + rawOffset.y + info.size.height / 2f
        val slots = visible.map { item ->
            ReorderGeometry.Slot(
                index = item.index,
                left = item.offset.x.toFloat(),
                top = item.offset.y.toFloat(),
                width = item.size.width.toFloat(),
                height = item.size.height.toFloat(),
            )
        }

        when (val decision = gate.offer(ReorderGeometry.candidate(centerX, centerY, slots, current), now())) {
            is ReorderGate.Decision.Commit -> commit(current, decision.index, visible)
            is ReorderGate.Decision.Wait -> {
                pendingCheck = scope.launch {
                    delay(decision.millis)
                    evaluate()
                }
            }
            ReorderGate.Decision.None -> Unit
        }
    }

    private fun commit(
        from: Int,
        to: Int,
        visible: List<LazyGridItemInfo>,
    ) {
        val info = visible.firstOrNull { it.index == from } ?: return
        val target = visible.firstOrNull { it.index == to } ?: return
        onMove(from, to)
        // 並びが入れ替わった分だけ基準をずらす。これをしないとカードが指から逃げる
        rawOffset += Offset(
            (info.offset.x - target.offset.x).toFloat(),
            (info.offset.y - target.offset.y).toFloat(),
        )
        draggingIndex = to
    }

    fun end() {
        pendingCheck?.cancel()
        gate.reset()
        val released = draggingIndex ?: return
        val from = rawOffset
        draggingIndex = null
        settlingIndex = released
        onDragStateChange(false)
        scope.launch {
            settleOffset.snapTo(from)
            settleOffset.animateTo(Offset.Zero, ZMotion.settle())
            if (settlingIndex == released) settlingIndex = null
        }
    }

    private fun now(): Long = System.nanoTime() / 1_000_000
}

@Composable
fun rememberGridReorderState(
    gridState: LazyGridState,
    onDragStateChange: (Boolean) -> Unit = {},
    onMove: (from: Int, to: Int) -> Unit,
): GridReorderState {
    val scope = rememberCoroutineScope()
    return remember(gridState) { GridReorderState(gridState, scope, onMove, onDragStateChange) }
}

fun Modifier.reorderableItem(state: GridReorderState, index: Int, enabled: Boolean = true): Modifier =
    if (!enabled) this else pointerInput(index, enabled) {
        detectDragGesturesAfterLongPress(
            onDragStart = { state.start(index) },
            onDrag = { change, delta ->
                change.consume()
                state.drag(delta)
            },
            onDragEnd = { state.end() },
            onDragCancel = { state.end() },
        )
    }

/**
 * Lazy リストの中に自前で並べた要素の、長押しドラッグ並べ替え。
 *
 * [GridReorderState] は LazyGrid の `visibleItemsInfo` から位置を読めるが、ウィジェットの面は
 * 「行の中に幅の違う要素が並ぶ」ので、Lazy の 1 項目 = 1 行になり、その中の位置は Lazy からは
 * 見えない。そこで各要素に自分の位置を申告させる（[reorderableSlot]）。
 *
 * 判定は [GridReorderState] と同じ [ReorderGate] を通す ― 面ごとに手触りが違うのは避ける。
 */
class ListReorderState internal constructor(
    private val scope: CoroutineScope,
    private val onMove: (from: Int, to: Int) -> Unit,
) {
    private val slots = mutableStateMapOf<Int, ReorderGeometry.Slot>()
    private val gate = ReorderGate()
    private var pendingCheck: Job? = null

    /** 現在の要素数。消えた要素の古い位置を候補にしないために要る */
    var count: Int = 0

    var draggingIndex by mutableStateOf<Int?>(null)
        private set

    var settlingIndex by mutableStateOf<Int?>(null)
        private set

    private var rawOffset by mutableStateOf(Offset.Zero)
    private val settleOffset = Animatable(Offset.Zero, Offset.VectorConverter)

    val activeIndex: Int? get() = draggingIndex ?: settlingIndex

    val dragOffset: Offset
        get() = if (draggingIndex != null) rawOffset else settleOffset.value

    fun isActive(index: Int): Boolean = activeIndex == index

    internal fun report(slot: ReorderGeometry.Slot) {
        slots[slot.index] = slot
    }

    fun start(index: Int) {
        pendingCheck?.cancel()
        gate.reset()
        scope.launch { settleOffset.snapTo(Offset.Zero) }
        settlingIndex = null
        draggingIndex = index
        rawOffset = Offset.Zero
    }

    fun drag(delta: Offset) {
        if (draggingIndex == null) return
        rawOffset += delta
        evaluate()
    }

    private fun evaluate() {
        val current = draggingIndex ?: return
        pendingCheck?.cancel()

        val own = slots[current] ?: return
        val centerX = own.left + rawOffset.x + own.width / 2f
        val centerY = own.top + rawOffset.y + own.height / 2f
        val live = slots.values.filter { it.index in 0 until count }

        when (val decision = gate.offer(ReorderGeometry.candidate(centerX, centerY, live, current), now())) {
            is ReorderGate.Decision.Commit -> {
                val target = slots[decision.index] ?: return
                onMove(current, decision.index)
                // 入れ替えたぶん基準をずらす。これをしないと要素が指から逃げる
                rawOffset += Offset(own.left - target.left, own.top - target.top)
                draggingIndex = decision.index
            }
            is ReorderGate.Decision.Wait -> {
                pendingCheck = scope.launch {
                    delay(decision.millis)
                    evaluate()
                }
            }
            ReorderGate.Decision.None -> Unit
        }
    }

    fun end() {
        pendingCheck?.cancel()
        gate.reset()
        val released = draggingIndex ?: return
        val from = rawOffset
        draggingIndex = null
        settlingIndex = released
        scope.launch {
            settleOffset.snapTo(from)
            settleOffset.animateTo(Offset.Zero, ZMotion.settle())
            if (settlingIndex == released) settlingIndex = null
        }
    }

    private fun now(): Long = System.nanoTime() / 1_000_000
}

@Composable
fun rememberListReorderState(onMove: (from: Int, to: Int) -> Unit): ListReorderState {
    val scope = rememberCoroutineScope()
    return remember { ListReorderState(scope, onMove) }
}

/**
 * 自分の位置を申告し、長押しで並べ替えを始める。
 *
 * 位置はウィンドウ基準で取る。Lazy の項目・行・重みつきの枠と入れ子になるので、
 * 親のどれか 1 つを基準にすると、行が変わった瞬間に座標系が変わってしまう。
 */
fun Modifier.reorderableSlot(
    state: ListReorderState,
    index: Int,
): Modifier = this.onGloballyPositioned { coordinates ->
    val position = coordinates.positionInWindow()
    state.report(
        ReorderGeometry.Slot(
            index = index,
            left = position.x,
            top = position.y,
            width = coordinates.size.width.toFloat(),
            height = coordinates.size.height.toFloat(),
        )
    )
}

/**
 * 長押しで並べ替えを始める口。
 *
 * 位置の申告（[reorderableSlot]）と分けてあるのは、置き場所が違うから ―
 * 位置は枠そのもの、掴む口は中身の上にかぶせた膜（ウィジェットにタッチを渡さないための
 * もの）に付ける。同じ修飾子にまとめると、膜の大きさが枠の位置として申告されてしまう。
 */
fun Modifier.reorderableHandle(
    state: ListReorderState,
    index: Int,
    enabled: Boolean = true,
): Modifier = if (!enabled) this else this.pointerInput(index, enabled) {
    detectDragGesturesAfterLongPress(
        onDragStart = { state.start(index) },
        onDrag = { change, delta ->
            change.consume()
            state.drag(delta)
        },
        onDragEnd = { state.end() },
        onDragCancel = { state.end() },
    )
}
