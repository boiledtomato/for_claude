package com.example.zlauncher.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.AppEntry
import kotlin.math.roundToInt

/**
 * お気に入りドック。グリッドのフィルタではなく参照なので、
 * 同じアプリがグリッドとドックの両方に出るのが正しい挙動。
 */
@Composable
fun FavoritesDock(
    favorites: List<AppEntry>,
    slots: Int,
    iconProvider: suspend (AppEntry) -> androidx.compose.ui.graphics.ImageBitmap?,
    onLaunch: (AppEntry, android.graphics.Rect?) -> Unit,
    onRemove: (AppEntry) -> Unit,
    onMove: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var dragIndex by remember { mutableStateOf<Int?>(null) }
    var dragDx by remember { mutableFloatStateOf(0f) }
    var slotWidth by remember { mutableFloatStateOf(1f) }
    val shape = RoundedCornerShape(20.dp)

    Row(
        modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(shape)
            .background(ZColors.Surface)
            .border(1.dp, if (dragIndex != null) ZColors.Accent else ZColors.Outline, shape)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(slots) { slot ->
            val entry = favorites.getOrNull(slot)
            Box(
                Modifier
                    .weight(1f)
                    .onSizeChanged { if (it.width > 0) slotWidth = it.width.toFloat() },
                contentAlignment = Alignment.Center,
            ) {
                if (entry == null) {
                    EmptyDockSlot()
                } else {
                    DockTile(
                        entry = entry,
                        index = slot,
                        isDragging = dragIndex == slot,
                        dragDx = if (dragIndex == slot) dragDx else 0f,
                        iconProvider = iconProvider,
                        onLaunch = onLaunch,
                        onRemove = onRemove,
                        onDragStart = { dragIndex = slot; dragDx = 0f },
                        onDrag = { delta ->
                            val current = dragIndex ?: return@DockTile
                            dragDx += delta
                            val steps = (dragDx / slotWidth).roundToInt()
                            if (steps != 0) {
                                val target = (current + steps).coerceIn(0, favorites.lastIndex)
                                if (target != current) {
                                    onMove(current, target)
                                    dragDx -= (target - current) * slotWidth
                                    dragIndex = target
                                }
                            }
                        },
                        onDragEnd = { dragIndex = null; dragDx = 0f },
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyDockSlot() {
    val shape = RoundedCornerShape(14.dp)
    Box(
        Modifier
            .size(52.dp)
            .clip(shape)
            .background(ZColors.Accent.copy(alpha = 0.06f))
            .border(1.dp, ZColors.Accent.copy(alpha = 0.35f), shape)
    )
}

@Composable
private fun DockTile(
    entry: AppEntry,
    index: Int,
    isDragging: Boolean,
    dragDx: Float,
    iconProvider: suspend (AppEntry) -> androidx.compose.ui.graphics.ImageBitmap?,
    onLaunch: (AppEntry, android.graphics.Rect?) -> Unit,
    onRemove: (AppEntry) -> Unit,
    onDragStart: () -> Unit,
    onDrag: (Float) -> Unit,
    onDragEnd: () -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }

    Box(
        Modifier
            .zIndex(if (isDragging) 1f else 0f)
            .graphicsLayer {
                translationX = dragDx
                scaleX = if (isDragging) 1.08f else 1f
                scaleY = if (isDragging) 1.08f else 1f
            }
            .pointerInput(entry.key, index) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { onDragStart() },
                    onDrag = { change, delta ->
                        change.consume()
                        onDrag(delta.x)
                    },
                    onDragEnd = { onDragEnd() },
                    onDragCancel = { onDragEnd() },
                )
            },
    ) {
        AppTile(
            entry = entry,
            iconProvider = iconProvider,
            onClick = { bounds -> onLaunch(entry, bounds) },
            onLongClick = { menuOpen = true },
            showLabel = false,
            tileSize = 52.dp,
        )
        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
            DropdownMenuItem(
                text = { Text("Remove from dock", style = ZType.Body, color = ZColors.TextPrimary) },
                onClick = {
                    menuOpen = false
                    onRemove(entry)
                },
            )
        }
    }
}
