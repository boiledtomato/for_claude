package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.ui.widgets.PlacedWidgetItem

/**
 * 置いたウィジェットの面。
 *
 * **置く場所と足す場所を同じ画面にする。** 以前はホーム（全アプリのグリッド）に並び、
 * 追加はコンソールの設定行という分かれ方をしていて、どこに増えるのか読めなかった。
 */
@Composable
fun WidgetsPane(
    viewModel: ConsoleViewModel,
    widgetHost: WidgetHostController,
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val widgets by viewModel.widgets.collectAsStateWithLifecycle()

    LazyColumn(
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item(key = "add") {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = when (widgets.size) {
                        0 -> "No widgets yet"
                        1 -> "1 widget"
                        else -> "${widgets.size} widgets"
                    },
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    "Add widget",
                    style = ZType.Body.copy(fontSize = 12.5.sp),
                    color = ZColors.AccentSoft,
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(ZColors.Accent.copy(alpha = 0.14f))
                        .border(1.dp, ZColors.Accent.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                        .springyClick(onClick = onAddWidget)
                        .padding(horizontal = 16.dp, vertical = 9.dp),
                )
            }
        }

        if (widgets.isEmpty()) {
            item(key = "empty") {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(ZColors.Surface)
                        .border(1.dp, ZColors.Outline, RoundedCornerShape(14.dp))
                        .padding(horizontal = 14.dp, vertical = 22.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Nothing placed yet", style = ZType.Body, color = ZColors.TextPrimary)
                        Text(
                            "Widgets you add appear here, full width, in this order. " +
                                "Each one can be resized from its “Size” button.",
                            style = ZType.Sub,
                            color = ZColors.TextSecondary,
                        )
                    }
                }
            }
        }

        items(widgets, key = { it.appWidgetId }) { placement ->
            PlacedWidgetItem(
                placement = placement,
                controller = widgetHost,
                onRemove = { viewModel.removeWidget(placement.appWidgetId) },
                onHeightChange = { viewModel.setWidgetHeight(placement.appWidgetId, it) },
                modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
            )
        }
    }
}
