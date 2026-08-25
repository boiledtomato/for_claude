package com.example.zlauncher.core.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import com.example.zlauncher.core.designsystem.ZMotion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * LazyVerticalGrid の長押しドラッグ並べ替え。
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

    /** つまみ上げ中か、収まり中の要素 */
    val activeIndex: Int? get() = draggingIndex ?: settlingIndex

    val dragOffset: Offset
        get() = if (draggingIndex != null) rawOffset else settleOffset.value

    fun isActive(index: Int): Boolean = activeIndex == index

    fun start(index: Int) {
        scope.launch { settleOffset.snapTo(Offset.Zero) }
        settlingIndex = null
        draggingIndex = index
        rawOffset = Offset.Zero
        onDragStateChange(true)
    }

    fun drag(delta: Offset) {
        val current = draggingIndex ?: return
        rawOffset += delta

        val visible = gridState.layoutInfo.visibleItemsInfo
        val info = visible.firstOrNull { it.index == current } ?: return
        val centerX = info.offset.x + rawOffset.x + info.size.width / 2f
        val centerY = info.offset.y + rawOffset.y + info.size.height / 2f

        val target = visible.firstOrNull { candidate ->
            candidate.index != current &&
                centerX >= candidate.offset.x && centerX <= candidate.offset.x + candidate.size.width &&
                centerY >= candidate.offset.y && centerY <= candidate.offset.y + candidate.size.height
        } ?: return

        onMove(current, target.index)
        // 並びが入れ替わった分だけ基準をずらす。これをしないとカードが指から逃げる
        rawOffset += Offset(
            (info.offset.x - target.offset.x).toFloat(),
            (info.offset.y - target.offset.y).toFloat(),
        )
        draggingIndex = target.index
    }

    fun end() {
        val released = draggingIndex ?: return
        val from = rawOffset
        draggingIndex = null
        settlingIndex = released
        onDragStateChange(false)
        scope.launch {
            settleOffset.snapTo(from)
            settleOffset.animateTo(Offset.Zero, ZMotion.touch())
            if (settlingIndex == released) settlingIndex = null
        }
    }
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
