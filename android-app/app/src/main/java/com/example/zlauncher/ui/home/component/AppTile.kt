package com.example.zlauncher.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.ui.springyCombinedClick
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.AppEntry

/** ラベルは常にこの行数ぶんの高さを占める。行数が揺れるとグリッドの行が揃わない */
private const val LABEL_LINES = 2

/** アイコンの解決は描画後。ラベルだけ先に出す */
@Composable
fun rememberAppIcon(entry: AppEntry, provider: suspend (AppEntry) -> ImageBitmap?) =
    produceState<ImageBitmap?>(initialValue = null, entry.key) { value = provider(entry) }

@Composable
fun AppTile(
    entry: AppEntry,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onClick: (android.graphics.Rect?) -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    tileSize: androidx.compose.ui.unit.Dp = 60.dp,
) {
    val icon by rememberAppIcon(entry, iconProvider)
    val haptics = LocalHapticFeedback.current
    var bounds by remember { mutableStateOf<android.graphics.Rect?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                val rect = coordinates.boundsInWindow()
                bounds = android.graphics.Rect(
                    rect.left.toInt(),
                    rect.top.toInt(),
                    rect.right.toInt(),
                    rect.bottom.toInt(),
                )
            }
            .clip(RoundedCornerShape(16.dp))
            .springyCombinedClick(
                onClick = { onClick(bounds) },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongClick()
                },
            )
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AppIconTile(icon = icon, size = tileSize)
        if (showLabel) {
            Text(
                text = entry.label,
                style = ZType.AppLabel,
                color = ZColors.TextSecondary,
                minLines = LABEL_LINES,
                maxLines = LABEL_LINES,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
            )
        }
    }
}

@Composable
fun AppIconTile(
    icon: ImageBitmap?,
    size: androidx.compose.ui.unit.Dp,
    background: androidx.compose.ui.graphics.Color = ZColors.Surface,
    border: androidx.compose.ui.graphics.Color = ZColors.Outline,
) {
    val shape = RoundedCornerShape(size / 4)
    Box(
        Modifier
            .size(size)
            .clip(shape)
            .background(background)
            .border(1.dp, border, shape),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Image(bitmap = icon, contentDescription = null, modifier = Modifier.size(size * 0.66f))
        } else {
            Box(
                Modifier
                    .size(size * 0.42f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(ZColors.SurfaceHigh)
            )
        }
    }
}
