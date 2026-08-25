package com.example.zlauncher.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.domain.model.WidgetPlacement

/**
 * ホームに置いたウィジェット 1 件。
 *
 * ウィジェット本体はタッチを消費するので、削除操作はヘッダ行に出す
 * （ウィジェット上の長押しはウィジェット側の操作と競合する）。
 */
@Composable
fun HomeWidgetItem(
    placement: WidgetPlacement,
    controller: WidgetHostController,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val info = remember(placement.appWidgetId) { controller.providerInfo(placement.appWidgetId) }
    val shape = RoundedCornerShape(14.dp)

    Column(
        modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, shape)
            .padding(bottom = 6.dp),
    ) {
        Row(
            Modifier.fillMaxWidth().padding(start = 12.dp, end = 8.dp, top = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = info?.loadLabel(context.packageManager) ?: "Unavailable widget",
                style = ZType.Eyebrow,
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "Remove",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = onRemove)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
        if (info != null) {
            AndroidView(
                factory = { ctx -> controller.createView(ctx, placement.appWidgetId, info) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(placement.heightDp.dp)
                    .padding(horizontal = 6.dp),
            )
        } else {
            Box(
                Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    "Provider app not found",
                    style = ZType.Body,
                    color = ZColors.TextDim,
                )
            }
        }
    }
}
