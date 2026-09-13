package com.botanical.launcher.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.garden.Palette
import kotlin.math.roundToInt

private const val PER_ROW = 4

/**
 * 1 つの部位に複数のアプリが入っているときに、その場で開く小さな束。
 *
 * 画面全体を覆うアプリ一覧とは別物で、押した花のすぐ脇に出す。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Posy(
    apps: List<AppEntry>,
    label: String,
    anchor: Offset,
    onPick: (AppEntry) -> Unit,
    onEdit: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier
            .fillMaxSize()
            // 束の外をタップしたら閉じる
            .pointerInput(Unit) { detectTapGestures { onDismiss() } },
    ) {
        var card by remember { mutableStateOf(IntSize.Zero) }
        val boundsW = constraints.maxWidth
        val boundsH = constraints.maxHeight
        val margin = with(androidx.compose.ui.platform.LocalDensity.current) { 12.dp.toPx() }
        val gap = with(androidx.compose.ui.platform.LocalDensity.current) { 16.dp.toPx() }

        Column(
            modifier = Modifier
                .onSizeChanged { card = it }
                .offset {
                    val x = (anchor.x - card.width / 2f)
                        .coerceIn(margin, (boundsW - card.width - margin).coerceAtLeast(margin))
                    // 下に入らなければ花の上に出す
                    val below = anchor.y + gap
                    val y = if (below + card.height + margin <= boundsH) {
                        below
                    } else {
                        (anchor.y - gap - card.height).coerceAtLeast(margin)
                    }
                    IntOffset(x.roundToInt(), y.roundToInt())
                }
                .clip(RoundedCornerShape(14.dp))
                .background(Palette.Paper)
                .drawBehind {
                    drawRoundRect(
                        color = Palette.Ink.copy(alpha = 0.45f),
                        style = Stroke(width = 2f),
                        cornerRadius = CornerRadius(36f, 36f),
                    )
                }
                .padding(horizontal = 12.dp, vertical = 10.dp)
                // 束の中のタップは閉じる側へ渡さない
                .pointerInput(Unit) { detectTapGestures { } },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = label.ifBlank { "この部位" },
                    modifier = Modifier.weight(1f, fill = false),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = Palette.InkSoft,
                    ),
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    text = "編集",
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .combinedClickable(onClick = onEdit)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 12.sp,
                        color = Palette.Green1,
                    ),
                )
            }
            Spacer(Modifier.height(6.dp))
            for (row in apps.chunked(PER_ROW)) {
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    for (app in row) {
                        PosyItem(app, onPick)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PosyItem(app: AppEntry, onPick: (AppEntry) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .combinedClickable(onClick = { onPick(app) })
            .padding(vertical = 6.dp, horizontal = 2.dp),
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(50.dp)) {
            Box(
                Modifier.fillMaxSize().drawBehind {
                    val r = size.minDimension / 2f
                    val c = Offset(size.width / 2f, size.height / 2f)
                    drawCircle(Palette.Cream, radius = r, center = c)
                    drawCircle(
                        color = Palette.Ink.copy(alpha = 0.5f),
                        radius = r,
                        center = c,
                        style = Stroke(width = 1.5f),
                    )
                },
            )
            Image(
                bitmap = app.icon,
                contentDescription = app.label,
                modifier = Modifier.size(32.dp),
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = app.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 10.sp,
                color = Palette.Ink,
            ),
        )
    }
}
