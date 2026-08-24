package com.example.zlauncher.core.ui

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

/**
 * LazyVerticalGrid の長押しドラッグ並べ替え。
 *
 * ダッシュボードのカードとホームのドックで同じ操作感にしたいので自前で持つ。
 * 外部ライブラリに寄せると、両者で挙動がずれたときに直せる場所が無くなる。
 */
class GridReorderState internal constructor(
    private val gridState: LazyGridState,
    private val onMove: (from: Int, to: Int) -> Unit,
    private val onDragStateChange: (Boolean) -> Unit,
) {
    var draggingIndex by mutableStateOf<Int?>(null)
        private set

    var dragOffset by mutableStateOf(Offset.Zero)
        private set

    fun start(index: Int) {
        draggingIndex = index
        dragOffset = Offset.Zero
        onDragStateChange(true)
    }

    fun drag(delta: Offset) {
        val current = draggingIndex ?: return
        dragOffset += delta

        val visible = gridState.layoutInfo.visibleItemsInfo
        val info = visible.firstOrNull { it.index == current } ?: return
        val centerX = info.offset.x + dragOffset.x + info.size.width / 2f
        val centerY = info.offset.y + dragOffset.y + info.size.height / 2f

        val target = visible.firstOrNull { candidate ->
            candidate.index != current &&
                centerX >= candidate.offset.x && centerX <= candidate.offset.x + candidate.size.width &&
                centerY >= candidate.offset.y && centerY <= candidate.offset.y + candidate.size.height
        } ?: return

        onMove(current, target.index)
        // 並びが入れ替わった分だけ基準をずらす。これをしないとカードが指から逃げる
        dragOffset += Offset(
            (info.offset.x - target.offset.x).toFloat(),
            (info.offset.y - target.offset.y).toFloat(),
        )
        draggingIndex = target.index
    }

    fun end() {
        draggingIndex = null
        dragOffset = Offset.Zero
        onDragStateChange(false)
    }
}

@Composable
fun rememberGridReorderState(
    gridState: LazyGridState,
    onDragStateChange: (Boolean) -> Unit = {},
    onMove: (from: Int, to: Int) -> Unit,
): GridReorderState = remember(gridState) {
    GridReorderState(gridState, onMove, onDragStateChange)
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
