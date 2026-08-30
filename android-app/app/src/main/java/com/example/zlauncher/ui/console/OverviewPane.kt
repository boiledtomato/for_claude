package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.rememberGridReorderState
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.core.ui.reorderableItem
import com.example.zlauncher.domain.model.AppSortOrder
import com.example.zlauncher.domain.model.CardLayout
import com.example.zlauncher.domain.model.CardSpan
import com.example.zlauncher.ui.console.card.CardContext
import com.example.zlauncher.ui.setup.DefaultLauncher

private const val GRID_COLUMNS = 12

@Composable
fun OverviewPane(
    viewModel: ConsoleViewModel,
    snapshot: ConsoleSnapshot,
    onAddWidget: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val layout by viewModel.layout.collectAsStateWithLifecycle()
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()
    val themedIcons by viewModel.themedIconsEnabled.collectAsStateWithLifecycle()
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

    val cardContext = CardContext(
        snapshot = snapshot,
        iconProvider = viewModel::icon,
        onOpenSettings = { action ->
            runCatching { context.startActivity(android.content.Intent(action)) }
        },
        onLaunchApp = { entry -> viewModel.launch(entry) },
    )

    Box(modifier.fillMaxSize()) {
        if (isEditing) {
            Column {
                EditHint()
                CardGrid(
                    gridState = gridState,
                    reorderState = reorderState,
                    visibleCards = visibleCards,
                    hiddenCards = hiddenCards,
                    isEditing = true,
                    cardContext = cardContext,
                    sortOrder = sortOrder,
                    themedIcons = themedIcons,
                    viewModel = viewModel,
                    onAddWidget = onAddWidget,
                )
            }
        } else {
            CardGrid(
                gridState = gridState,
                reorderState = reorderState,
                visibleCards = visibleCards,
                hiddenCards = hiddenCards,
                isEditing = false,
                cardContext = cardContext,
                sortOrder = sortOrder,
                themedIcons = themedIcons,
                viewModel = viewModel,
                onAddWidget = onAddWidget,
            )
        }
    }
}

@Composable
private fun CardGrid(
    gridState: androidx.compose.foundation.lazy.grid.LazyGridState,
    reorderState: com.example.zlauncher.core.ui.GridReorderState,
    visibleCards: List<CardLayout>,
    hiddenCards: List<CardLayout>,
    isEditing: Boolean,
    cardContext: CardContext,
    sortOrder: AppSortOrder,
    themedIcons: Boolean,
    viewModel: ConsoleViewModel,
    onAddWidget: () -> Unit,
) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COLUMNS),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp, top = 2.dp, bottom = 28.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(
            items = visibleCards,
            key = { _, card -> card.id },
            span = { _, card -> GridItemSpan(card.span.columns) },
        ) { index, card ->
            val definition = CardCatalog[card.id]
            // 未知の ID（アプリ更新でカードが消えた等）は描画しない
            if (definition != null) {
                val isDragging = reorderState.isActive(index)
                val lifted = reorderState.draggingIndex == index
                val lift by animateFloatAsState(
                    targetValue = if (lifted) ZMotion.LIFT_SCALE else 1f,
                    animationSpec = ZMotion.touch(),
                    label = "cardLift",
                )
                val tilt by animateFloatAsState(
                    targetValue = if (lifted) -1.2f else 0f,
                    animationSpec = ZMotion.touch(),
                    label = "cardTilt",
                )
                Column(
                    Modifier
                        // 並べ替えは編集モードに入らなくても常に効く
                        .animateItem(placementSpec = ZMotion.placement())
                        .zIndex(if (isDragging) 1f else 0f)
                        .graphicsLayer {
                            translationX = if (isDragging) reorderState.dragOffset.x else 0f
                            translationY = if (isDragging) reorderState.dragOffset.y else 0f
                            scaleX = lift
                            scaleY = lift
                            rotationZ = tilt
                        }
                        .reorderableItem(reorderState, index)
                ) {
                    Box {
                        Box(Modifier.alpha(if (isEditing) 0.92f else 1f)) {
                            definition.content(cardContext)
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
                IconStyleRow(themed = themedIcons, onSelect = viewModel::setThemedIcons)
                Spacer(Modifier.height(8.dp))
                SettingsRow(label = "Add widget", onClick = onAddWidget)
                Spacer(Modifier.height(8.dp))
                // 自動更新は 3 か月周期なので、待たずに確かめたいときの導線を置く
                SettingsRow(
                    label = if (viewModel.checkingCatalog) {
                        "Checking URL categories…"
                    } else {
                        "Check URL category updates"
                    },
                    onClick = viewModel::checkCatalogNow,
                )
                viewModel.catalogMessage?.let { message ->
                    Spacer(Modifier.height(6.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(ZColors.SurfaceLow)
                            .springyClick(onClick = viewModel::clearCatalogMessage)
                            .padding(horizontal = 11.dp, vertical = 9.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(message, style = ZType.Sub, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
                        Text("Dismiss", style = ZType.Sub, color = ZColors.AccentSoft)
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Catalog: ${viewModel.catalogRevision.ifBlank { "bundled" }} · refreshed every 90 days",
                    style = ZType.Sub,
                    color = ZColors.TextDim,
                )
                Spacer(Modifier.height(8.dp))
                SettingsRow(
                    label = if (DefaultLauncher.isDefault(context)) {
                        "Open home app settings"
                    } else {
                        "Set as default home"
                    },
                    onClick = {
                        runCatching { context.startActivity(DefaultLauncher.requestIntent(context)) }
                    },
                )
                if (isEditing) {
                    Spacer(Modifier.height(8.dp))
                    SettingsRow(label = "Reset card layout", onClick = viewModel::resetLayout)
                }
            }
        }
    }
}

@Composable
private fun EditHint() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 16.dp)
            .padding(bottom = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ZColors.Accent.copy(alpha = 0.05f))
            .border(1.dp, ZColors.Outline, RoundedCornerShape(8.dp))
            .padding(horizontal = 11.dp, vertical = 8.dp),
    ) {
        Text(
            "Drag any card to reorder · − hides it · chips resize it",
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
            .springyClick(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Text(label, style = ZType.Body.copy(fontSize = 13.sp), color = ZColors.TextSecondary)
    }
}

@Composable
private fun SpanChips(card: CardLayout, onSelect: (CardSpan) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Chip("Half", card.span == CardSpan.HALF) { onSelect(CardSpan.HALF) }
        Chip("Full", card.span == CardSpan.FULL) { onSelect(CardSpan.FULL) }
    }
}

@Composable
private fun Chip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) ZColors.Accent.copy(alpha = 0.12f) else Color.Transparent)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.5f) else ZColors.StatusNeutral,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
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
        Text("Hidden cards", style = ZType.Eyebrow, color = ZColors.TextSecondary)
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
                    .springyClick { onShow(card.id) }
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(Modifier.size(6.dp).clip(CircleShape).background(ZColors.StatusNeutral))
                Text(definition.title, style = ZType.Body, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
                Text("＋", style = ZType.Body, color = ZColors.TextSecondary)
            }
        }
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
        Text("App order", style = ZType.Body, color = ZColors.TextPrimary)
        Spacer(Modifier.height(9.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Chip("Name", current == AppSortOrder.LABEL) { onSelect(AppSortOrder.LABEL) }
            Chip("Install date", current == AppSortOrder.INSTALL_TIME) { onSelect(AppSortOrder.INSTALL_TIME) }
        }
    }
}

/**
 * アイコンの見た目。テーマ調はモノクロレイヤーを持つアプリ（Android 13 以降）にだけ効く。
 * 対応していないアプリは通常のアイコンのまま出す。
 */
@Composable
private fun IconStyleRow(themed: Boolean, onSelect: (Boolean) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 11.dp),
    ) {
        Text("Icon style", style = ZType.Body, color = ZColors.TextPrimary)
        Spacer(Modifier.height(4.dp))
        Text(
            "Themed affects apps with a monochrome layer (Android 13+)",
            style = ZType.Sub,
            color = ZColors.TextDim,
        )
        Spacer(Modifier.height(9.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Chip("Default", !themed) { onSelect(false) }
            Chip("Themed", themed) { onSelect(true) }
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
            .springyClick(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, style = ZType.Body, color = ZColors.TextPrimary, modifier = Modifier.weight(1f))
        Text("›", style = ZType.Body, color = ZColors.TextSecondary)
    }
}
