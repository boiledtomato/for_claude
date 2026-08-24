package com.example.zlauncher.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.rememberGridReorderState
import com.example.zlauncher.core.ui.reorderableItem
import com.example.zlauncher.data.dashboard.DashboardSnapshot
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import com.example.zlauncher.ui.setup.DefaultLauncher

private const val GRID_COLUMNS = 12

@Composable
fun DashboardScreen(
    onBack: () -> Unit,
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val snapshotResult by viewModel.snapshot.collectAsStateWithLifecycle()
    val layout by viewModel.layout.collectAsStateWithLifecycle()
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()
    val isEditing = viewModel.isEditing
    val context = LocalContext.current

    val gridState = rememberLazyGridState()
    val reorderState = rememberGridReorderState(
        gridState = gridState,
        onDragStateChange = viewModel::onDragStateChange,
        onMove = viewModel::move,
    )

    val visibleCards = remember(layout) { layout.filter { it.visible } }
    val hiddenCards = remember(layout) { layout.filterNot { it.visible } }
    val snapshot = snapshotResult?.getOrNull()

    Box(modifier.fillMaxSize().background(ZColors.Background)) {
        Column(Modifier.fillMaxSize().padding(WindowInsets.safeDrawing.asPaddingValues())) {
            DashboardTopBar(
                updatedAt = snapshot?.updatedAt.orEmpty(),
                isEditing = isEditing,
                onBack = onBack,
                onToggleEdit = { viewModel.setEditMode(!isEditing) },
            )

            if (isEditing) {
                EditHint()
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(GRID_COLUMNS),
                state = gridState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 28.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (snapshotResult?.isFailure == true) {
                    item(span = { GridItemSpan(GRID_COLUMNS) }) { DataErrorCard() }
                }

                if (snapshot != null) {
                    itemsIndexed(
                        items = visibleCards,
                        key = { _, card -> card.id },
                        span = { _, card -> GridItemSpan(card.span.columns) },
                    ) { index, card ->
                        val definition = CardCatalog[card.id]
                        // 未知の ID（アプリ更新でカードが消えた等）は描画しない
                        if (definition != null) {
                            val isDragging = reorderState.draggingIndex == index
                            Column(
                                Modifier
                                    .zIndex(if (isDragging) 1f else 0f)
                                    .graphicsLayer {
                                        if (isDragging) {
                                            translationX = reorderState.dragOffset.x
                                            translationY = reorderState.dragOffset.y
                                            rotationZ = -1.5f
                                        }
                                    }
                                    .reorderableItem(reorderState, index, enabled = isEditing)
                            ) {
                                Box {
                                    Box(Modifier.alpha(if (isEditing) 0.92f else 1f)) {
                                        definition.content(snapshot)
                                    }
                                    if (isEditing) {
                                        EditChrome(
                                            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                                            onHide = { viewModel.toggleVisibility(card.id) },
                                        )
                                    }
                                }
                                if (isEditing) {
                                    Spacer(Modifier.height(6.dp))
                                    SpanChips(card = card, onSelect = { viewModel.setSpan(card.id, it) })
                                }
                            }
                        }
                    }
                }

                if (isEditing && hiddenCards.isNotEmpty()) {
                    item(span = { GridItemSpan(GRID_COLUMNS) }) {
                        HiddenCardsSection(hiddenCards, onShow = viewModel::toggleVisibility)
                    }
                }

                item(span = { GridItemSpan(GRID_COLUMNS) }) {
                    Column {
                        Spacer(Modifier.height(4.dp))
                        SortOrderRow(current = sortOrder, onSelect = viewModel::setSortOrder)
                        Spacer(Modifier.height(8.dp))
                        SettingsRow(label = "ウィジェットを追加", onClick = onAddWidget)
                        Spacer(Modifier.height(8.dp))
                        SettingsRow(
                            label = if (DefaultLauncher.isDefault(context)) {
                                "ホームアプリ設定を開く"
                            } else {
                                "このアプリを既定のホームにする"
                            },
                            onClick = {
                                runCatching { context.startActivity(DefaultLauncher.requestIntent(context)) }
                            },
                        )
                        if (isEditing) {
                            Spacer(Modifier.height(8.dp))
                            SettingsRow(label = "カード配置を既定に戻す", onClick = viewModel::resetLayout)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DashboardTopBar(
    updatedAt: String,
    isEditing: Boolean,
    onBack: () -> Unit,
    onToggleEdit: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onBack),
            contentAlignment = Alignment.Center,
        ) {
            Text("‹", style = ZType.Title.copy(fontSize = 22.sp), color = ZColors.TextSecondary)
        }
        Column(Modifier.weight(1f)) {
            Text(if (isEditing) "カードを編集" else "コンソール", style = ZType.Title, color = ZColors.TextPrimary)
            if (updatedAt.isNotEmpty()) {
                Text("最終更新 $updatedAt", style = ZType.Sub, color = ZColors.TextSecondary)
            }
        }
        Box(
            Modifier
                .clip(RoundedCornerShape(999.dp))
                .background(if (isEditing) ZColors.Accent.copy(alpha = 0.12f) else ZColors.Surface)
                .border(
                    1.dp,
                    if (isEditing) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                    RoundedCornerShape(999.dp),
                )
                .clickable(onClick = onToggleEdit)
                .padding(horizontal = 14.dp, vertical = 7.dp),
        ) {
            Text(
                text = if (isEditing) "完了" else "編集",
                style = ZType.Body,
                color = if (isEditing) ZColors.AccentSoft else ZColors.TextSecondary,
            )
        }
    }
}

@Composable
private fun EditHint() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ZColors.Accent.copy(alpha = 0.05f))
            .border(1.dp, ZColors.Outline, RoundedCornerShape(8.dp))
            .padding(horizontal = 11.dp, vertical = 8.dp),
    ) {
        Text(
            "長押しでドラッグして並べ替え・目のアイコンで表示を切り替え",
            style = ZType.Sub.copy(fontSize = 11.sp),
            color = ZColors.TextSecondary,
        )
    }
}

@Composable
private fun EditChrome(modifier: Modifier = Modifier, onHide: () -> Unit) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        ChromeButton(label = "−", onClick = onHide)
        ChromeButton(label = "⠿", onClick = {})
    }
}

@Composable
private fun ChromeButton(label: String, onClick: () -> Unit) {
    Box(
        Modifier
            .size(26.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(ZColors.SurfaceHigh)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(7.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = ZType.Body.copy(fontSize = 13.sp), color = ZColors.TextSecondary)
    }
}

@Composable
private fun SpanChips(card: CardLayout, onSelect: (CardSpan) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        SpanChip("1/2 幅", card.span == CardSpan.HALF) { onSelect(CardSpan.HALF) }
        SpanChip("全幅", card.span == CardSpan.FULL) { onSelect(CardSpan.FULL) }
    }
}

@Composable
private fun SpanChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) ZColors.Accent.copy(alpha = 0.12f) else androidx.compose.ui.graphics.Color.Transparent)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.5f) else ZColors.StatusNeutral,
                RoundedCornerShape(999.dp),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 4.dp),
    ) {
        Text(
            label,
            style = ZType.Sub.copy(fontSize = 10.5.sp),
            color = if (selected) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}

@Composable
private fun HiddenCardsSection(hidden: List<CardLayout>, onShow: (String) -> Unit) {
    Column(Modifier.fillMaxWidth().padding(top = 10.dp)) {
        Text("非表示のカード", style = ZType.Eyebrow, color = ZColors.TextSecondary)
        Spacer(Modifier.height(9.dp))
        hidden.forEach { card ->
            val definition = CardCatalog[card.id] ?: return@forEach
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ZColors.SurfaceLow)
                    .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
                    .clickable { onShow(card.id) }
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(Modifier.size(6.dp).background(ZColors.StatusNeutral, CircleShape))
                Text(definition.title, style = ZType.Body, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
                Text("＋", style = ZType.Body, color = ZColors.TextSecondary)
            }
        }
    }
}

@Composable
private fun DataErrorCard() {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(12.dp))
            .padding(14.dp),
    ) {
        Text("データを取得できませんでした", style = ZType.Body, color = ZColors.TextSecondary)
    }
}

/**
 * アプリの並び順。「名前順」は読み仮名 API が無いため漢字がコードポイント順になる。
 * その回避策として「インストール日時順」を並べて置いている。
 */
@Composable
private fun SortOrderRow(current: AppSortOrder, onSelect: (AppSortOrder) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 11.dp),
    ) {
        Text("アプリの並び順", style = ZType.Body, color = ZColors.TextPrimary)
        Spacer(Modifier.height(9.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            SpanChip("名前順", current == AppSortOrder.LABEL) { onSelect(AppSortOrder.LABEL) }
            SpanChip("インストール日時順", current == AppSortOrder.INSTALL_TIME) { onSelect(AppSortOrder.INSTALL_TIME) }
        }
    }
}

@Composable
private fun SettingsRow(label: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, style = ZType.Body, color = ZColors.TextPrimary, modifier = Modifier.weight(1f))
        Text("›", style = ZType.Body, color = ZColors.TextSecondary)
    }
}

