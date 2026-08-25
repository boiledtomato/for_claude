package com.example.zlauncher.ui.console

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.core.ui.springyCombinedClick
import com.example.zlauncher.data.apps.CategoryWithApps
import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.ui.home.component.AppIconTile
import com.example.zlauncher.ui.home.component.rememberAppIcon

private const val CATEGORY_COLUMNS = 4

/** カテゴリーに入れ子にしたアプリの一覧 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryPane(
    category: CategoryWithApps,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onLaunch: (AppEntry) -> Unit,
    onRemoveApp: (String) -> Unit,
    onPickApps: () -> Unit,
    onEditCategory: () -> Unit,
    onDeleteCategory: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = ZColors.CategoryColors[category.category.colorIndex % ZColors.CategoryColors.size]
    var menuOpen by remember { mutableStateOf(false) }

    Column(modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 16.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(Modifier.size(10.dp).clip(CircleShape).background(color))
            Text(
                text = "${category.apps.size} apps",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                modifier = Modifier.weight(1f),
            )
            ActionChip("Select apps", accent = true, onClick = onPickApps)
            Box {
                ActionChip("⋯", accent = false, onClick = { menuOpen = true })
                DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                    DropdownMenuItem(
                        text = { Text("Rename & recolor", style = ZType.Body, color = ZColors.TextPrimary) },
                        onClick = { menuOpen = false; onEditCategory() },
                    )
                    DropdownMenuItem(
                        text = { Text("Delete category", style = ZType.Body, color = ZColors.StatusRed) },
                        onClick = { menuOpen = false; onDeleteCategory() },
                    )
                }
            }
        }

        if (category.apps.isEmpty()) {
            EmptyCategory(onPickApps)
            return@Column
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(CATEGORY_COLUMNS),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 8.dp, end = 12.dp, bottom = 28.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(category.apps, key = { it.key }) { entry ->
                CategoryAppTile(
                    modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                    entry = entry,
                    iconProvider = iconProvider,
                    onLaunch = { onLaunch(entry) },
                    onRemove = { onRemoveApp(entry.packageName) },
                )
            }
            item(span = { GridItemSpan(CATEGORY_COLUMNS) }) {
                Spacer(Modifier.height(6.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategoryAppTile(
    modifier: Modifier = Modifier,
    entry: AppEntry,
    iconProvider: suspend (AppEntry) -> ImageBitmap?,
    onLaunch: () -> Unit,
    onRemove: () -> Unit,
) {
    val icon by rememberAppIcon(entry, iconProvider)
    var menuOpen by remember { mutableStateOf(false) }

    Box(modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .springyCombinedClick(onClick = onLaunch, onLongClick = { menuOpen = true })
                .padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AppIconTile(icon = icon, size = 56.dp)
            Text(
                text = entry.label,
                style = ZType.AppLabel,
                color = ZColors.TextSecondary,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
            )
        }
        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
            DropdownMenuItem(
                text = { Text("Remove from category", style = ZType.Body, color = ZColors.TextPrimary) },
                onClick = { menuOpen = false; onRemove() },
            )
        }
    }
}

@Composable
private fun EmptyCategory(onPickApps: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(40.dp))
        Text("No apps yet", style = ZType.Body, color = ZColors.TextSecondary)
        Spacer(Modifier.height(14.dp))
        ActionChip("Select apps", accent = true, onClick = onPickApps)
    }
}

@Composable
private fun ActionChip(label: String, accent: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.12f) else ZColors.Surface)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.45f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 7.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.sp),
            color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}
