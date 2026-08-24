package com.example.zlauncher.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.CardStatus

/**
 * すべてのダッシュボードカードの外枠。
 * 影は使わず、面の明度差（surface）と 1dp の境界線だけで階層をつくる。
 */
@Composable
fun DashboardCardScaffold(
    title: String,
    modifier: Modifier = Modifier,
    status: CardStatus = CardStatus.NEUTRAL,
    statusLabel: String? = null,
    leadingDot: Boolean = false,
    highlighted: Boolean = false,
    trailing: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val statusColors = LocalStatusColors.current
    val edge = statusEdge(status, statusColors)
    val shape = RoundedCornerShape(12.dp)

    Box(
        modifier
            .clip(shape)
            .background(if (highlighted) ZColors.SurfaceHigh else ZColors.Surface)
            .border(1.dp, if (highlighted) ZColors.Accent else ZColors.Outline, shape)
    ) {
        if (edge != null) {
            Box(Modifier.width(2.dp).fillMaxHeight().background(edge))
        }
        Column(Modifier.fillMaxWidth().padding(start = if (edge != null) 14.dp else 12.dp, top = 12.dp, end = 12.dp, bottom = 11.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                if (leadingDot) StatusDot(status)
                Text(
                    text = title,
                    style = ZType.Eyebrow,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                when {
                    trailing != null -> trailing()
                    statusLabel != null -> StatusPill(status, statusLabel)
                }
            }
            content()
        }
    }
}

/** メトリクス（大きな数値 + 小さな単位） */
@Composable
fun MetricValue(
    value: String,
    unit: String? = null,
    fontSize: androidx.compose.ui.unit.TextUnit = 28.sp,
    color: androidx.compose.ui.graphics.Color = ZColors.TextPrimary,
) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(text = value, style = ZType.Metric.copy(fontSize = fontSize), color = color)
        if (unit != null) {
            Text(
                text = unit,
                style = ZType.UnitText,
                color = ZColors.TextSecondary,
                modifier = Modifier.padding(start = 3.dp, bottom = 3.dp),
            )
        }
    }
}
