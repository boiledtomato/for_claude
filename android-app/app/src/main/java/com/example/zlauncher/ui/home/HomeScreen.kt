package com.example.zlauncher.ui.home

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.data.widgets.WidgetHostController
import com.example.zlauncher.ui.home.component.AppSearchBar
import com.example.zlauncher.ui.home.component.AppTile
import com.example.zlauncher.ui.home.component.HomeWidgetItem
import com.example.zlauncher.ui.home.component.FavoritesDock
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DOCK_AREA_HEIGHT_DP = 176
private const val HOME_COLUMNS = 4

@Composable
fun HomeScreen(
    onOpenConsole: () -> Unit,
    widgetHost: WidgetHostController,
    modifier: Modifier = Modifier,
    gridState: LazyGridState = rememberLazyGridState(),
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val widgets by viewModel.widgets.collectAsStateWithLifecycle()

    // ホームでは戻る操作を無効化する。ランチャーの Activity は終了しないのが正しい
    BackHandler(enabled = true) { }

    Box(
        modifier
            .fillMaxSize()
            // 壁紙を透過させたうえでトーンを統一する
            .background(ZColors.Background.copy(alpha = 0.88f))
    ) {
        Column(Modifier.fillMaxSize().padding(WindowInsets.safeDrawing.asPaddingValues())) {
            ClockHeader(chips = state.statusChips)
            Spacer(Modifier.height(14.dp))
            AppSearchBar(
                query = state.query,
                onQueryChange = viewModel::setQuery,
                onClear = viewModel::clearQuery,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
            Spacer(Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(HOME_COLUMNS),
                state = gridState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = DOCK_AREA_HEIGHT_DP.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // ウィジェットは全幅・縦一列。検索中は結果を押し下げないよう隠す
                if (!state.isSearching) {
                    items(
                        items = widgets,
                        key = { it.appWidgetId },
                        span = { GridItemSpan(HOME_COLUMNS) },
                    ) { placement ->
                        HomeWidgetItem(
                            placement = placement,
                            controller = widgetHost,
                            onRemove = { viewModel.removeWidget(placement.appWidgetId) },
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        )
                    }
                }

                if (state.isEmptyResult) {
                    item(span = { GridItemSpan(HOME_COLUMNS) }) {
                        Text(
                            text = "「${state.query}」に一致するアプリはありません",
                            style = ZType.Body,
                            color = ZColors.TextSecondary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 24.dp),
                        )
                    }
                }

                items(state.apps, key = { it.key }) { entry ->
                    HomeAppItem(
                        entry = entry,
                        isFavorite = state.isFavorite(entry),
                        canFavorite = !entry.isWorkProfile,
                        favoritesFull = state.isFavoritesFull,
                        iconProvider = viewModel::icon,
                        onLaunch = viewModel::launch,
                        onAddFavorite = viewModel::addFavorite,
                        onRemoveFavorite = viewModel::removeFavorite,
                        onAppInfo = viewModel::openAppInfo,
                    )
                }
            }
        }

        // 下端のフェード。グリッドがドックの下に潜り込んでいることを示す
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(236.dp)
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.32f to ZColors.Background.copy(alpha = 0.93f),
                        0.54f to ZColors.Background,
                    )
                )
        )

        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ConsoleHandle(onClick = onOpenConsole)
            Spacer(Modifier.height(12.dp))
            FavoritesDock(
                favorites = state.favorites,
                slots = state.favoriteSlots,
                iconProvider = viewModel::icon,
                onLaunch = viewModel::launch,
                onRemove = viewModel::removeFavorite,
                onMove = viewModel::moveFavorite,
            )
        }
    }
}

@Composable
private fun HomeAppItem(
    entry: AppEntry,
    isFavorite: Boolean,
    canFavorite: Boolean,
    favoritesFull: Boolean,
    iconProvider: suspend (AppEntry) -> androidx.compose.ui.graphics.ImageBitmap?,
    onLaunch: (AppEntry, android.graphics.Rect?) -> Unit,
    onAddFavorite: (AppEntry) -> Unit,
    onRemoveFavorite: (AppEntry) -> Unit,
    onAppInfo: (AppEntry, android.graphics.Rect?) -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }
    var bounds by remember { mutableStateOf<android.graphics.Rect?>(null) }

    Box {
        AppTile(
            entry = entry,
            iconProvider = iconProvider,
            onClick = { rect ->
                bounds = rect
                onLaunch(entry, rect)
            },
            onLongClick = { menuOpen = true },
        )
        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
            // 仕事用プロファイルのアプリはドックに入れられない（ドックは packageName で
            // 個人用アプリを解決するため）。項目自体を出さない
            if (!canFavorite) {
                Unit
            } else if (isFavorite) {
                DropdownMenuItem(
                    text = { Text("ドックから外す", style = ZType.Body, color = ZColors.TextPrimary) },
                    onClick = { menuOpen = false; onRemoveFavorite(entry) },
                )
            } else {
                DropdownMenuItem(
                    enabled = !favoritesFull,
                    text = {
                        Text(
                            text = if (favoritesFull) "ドックが満杯です" else "ドックに追加",
                            style = ZType.Body,
                            color = if (favoritesFull) ZColors.TextDim else ZColors.AccentSoft,
                        )
                    },
                    onClick = { menuOpen = false; onAddFavorite(entry) },
                )
            }
            DropdownMenuItem(
                text = { Text("アプリ情報", style = ZType.Body, color = ZColors.TextPrimary) },
                onClick = { menuOpen = false; onAppInfo(entry, bounds) },
            )
        }
    }
}

@Composable
private fun ClockHeader(chips: List<HomeStatusChip>) {
    val time by produceState(initialValue = formatTime()) {
        while (true) {
            value = formatTime()
            delay(10_000)
        }
    }
    val date = remember { formatDate() }

    Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 6.dp)) {
        Text(text = time, style = ZType.Clock, color = ZColors.TextPrimary)
        Spacer(Modifier.height(6.dp))
        Text(text = date, style = ZType.Sub.copy(fontSize = 12.5.sp), color = ZColors.TextSecondary)
        Spacer(Modifier.height(14.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            chips.forEach { chip -> StatusChip(chip) }
        }
    }
}

@Composable
private fun StatusChip(chip: HomeStatusChip) {
    val color = LocalStatusColors.current.colorFor(chip.status)
    Row(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .border(1.dp, ZColors.Outline, RoundedCornerShape(999.dp))
            .padding(horizontal = 9.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(Modifier.size(5.dp).background(color, CircleShape))
        Text(text = chip.label, style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.TextSecondary)
    }
}

@Composable
private fun ConsoleHandle(onClick: () -> Unit) {
    Row(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(999.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 15.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(Modifier.size(8.dp).background(ZColors.AccentAlt, CircleShape))
        Text(text = "コンソール", style = ZType.Body, color = ZColors.TextPrimary)
    }
}

private fun formatTime(): String = SimpleDateFormat("H:mm", Locale.getDefault()).format(Date())

private fun formatDate(): String = SimpleDateFormat("M月d日(E)", Locale.getDefault()).format(Date())
