package com.example.zlauncher.ui.apps.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.AppEntry
import kotlinx.coroutines.launch
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
    val scope = rememberCoroutineScope()
    var dragIndex by remember { mutableStateOf<Int?>(null) }
    // 指を離した後、掴んでいた位置から元の位置へバネで戻すあいだだけ立つ。
    // 即座に 0 に戻すとタイルがパッと飛ぶので、着地が終わるまで枠を保持する。
    var settlingIndex by remember { mutableStateOf<Int?>(null) }
    var dragDx by remember { mutableFloatStateOf(0f) }
    val settleDx = remember { Animatable(0f) }
    var slotWidth by remember { mutableFloatStateOf(1f) }
    val shape = RoundedCornerShape(20.dp)
    val borderColor by animateColorAsState(
        targetValue = if (dragIndex != null) ZColors.Accent else ZColors.Outline,
        animationSpec = ZMotion.touch(),
        label = "dockBorder",
    )

    Row(
        modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(shape)
            .background(ZColors.Surface)
            .border(1.dp, borderColor, shape)
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
                        lifted = dragIndex == slot,
                        dragDx = when (slot) {
                            dragIndex -> dragDx
                            settlingIndex -> settleDx.value
                            else -> 0f
                        },
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
                        onDragEnd = {
                            val from = dragIndex ?: return@DockTile
                            val released = dragDx
                            dragIndex = null
                            dragDx = 0f
                            settlingIndex = from
                            scope.launch {
                                settleDx.snapTo(released)
                                settleDx.animateTo(0f, ZMotion.touch())
                                settlingIndex = null
                            }
                        },
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
    lifted: Boolean,
    dragDx: Float,
    iconProvider: suspend (AppEntry) -> androidx.compose.ui.graphics.ImageBitmap?,
    onLaunch: (AppEntry, android.graphics.Rect?) -> Unit,
    onRemove: (AppEntry) -> Unit,
    onDragStart: () -> Unit,
    onDrag: (Float) -> Unit,
    onDragEnd: () -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }

    val lift by animateFloatAsState(
        targetValue = if (lifted) ZMotion.LIFT_SCALE else 1f,
        animationSpec = ZMotion.touch(),
        label = "dockLift",
    )

    Box(
        Modifier
            .zIndex(if (dragDx != 0f || lifted) 1f else 0f)
            .graphicsLayer {
                translationX = dragDx
                scaleX = lift
                scaleY = lift
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
