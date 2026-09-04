package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.domain.model.CatalogDiff

/**
 * URL カテゴリー一覧の改訂差分。
 *
 * **自動では当てない。** CSV に安定した ID 列が無いため、改名は「消えた」と「増えた」の
 * 対応付けからの推測でしかない。推測で勝手にユーザーのカテゴリー名を書き換えると、
 * 間違えたときに元が分からなくなる。中身を見せてから当てる。
 */
@Composable
fun CatalogUpdateDialog(
    diff: CatalogDiff,
    affectedCategoryCount: Int,
    onDismiss: () -> Unit,
    onApply: () -> Unit,
    onIgnore: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Background)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                .padding(16.dp),
        ) {
            Text("Catalog updated", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${diff.toRevision} · ${diff.changeCount} change(s)",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            Spacer(Modifier.height(14.dp))
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                if (diff.renamed.isNotEmpty()) {
                    item(key = "renamed-header") {
                        DiffHeader(
                            "Renamed",
                            "${diff.renamed.size}",
                            ZColors.StatusAmber,
                            "Guessed by matching descriptions — check before applying",
                        )
                    }
                    items(diff.renamed.size, key = { "renamed-$it" }) { index ->
                        val rename = diff.renamed[index]
                        DiffRow(
                            color = ZColors.StatusAmber,
                            title = "${rename.from.category}  →  ${rename.to.category}",
                            subtitle = rename.to.superCategory,
                        )
                    }
                }

                if (diff.moved.isNotEmpty()) {
                    item(key = "moved-header") {
                        DiffHeader("Moved", "${diff.moved.size}", ZColors.Accent, null)
                    }
                    items(diff.moved.size, key = { "moved-$it" }) { index ->
                        val move = diff.moved[index]
                        DiffRow(
                            color = ZColors.Accent,
                            title = move.entry.category,
                            subtitle = "${move.fromSuperCategory}  →  ${move.entry.superCategory}",
                        )
                    }
                }

                if (diff.added.isNotEmpty()) {
                    item(key = "added-header") {
                        DiffHeader("Added", "${diff.added.size}", ZColors.StatusGreen, null)
                    }
                    items(diff.added.size, key = { "added-$it" }) { index ->
                        val entry = diff.added[index]
                        DiffRow(
                            color = ZColors.StatusGreen,
                            title = entry.category,
                            subtitle = entry.superCategory,
                        )
                    }
                }

                if (diff.removed.isNotEmpty()) {
                    item(key = "removed-header") {
                        DiffHeader(
                            "Removed",
                            "${diff.removed.size}",
                            ZColors.StatusRed,
                            "Your categories are kept — they just no longer match the catalog",
                        )
                    }
                    items(diff.removed.size, key = { "removed-$it" }) { index ->
                        val entry = diff.removed[index]
                        DiffRow(
                            color = ZColors.StatusRed,
                            title = entry.category,
                            subtitle = entry.superCategory,
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
            Text(
                text = if (affectedCategoryCount > 0) {
                    "Applying renames $affectedCategoryCount of your categories. Apps and colours stay as they are."
                } else {
                    "None of your categories are affected — applying only clears this notice."
                },
                style = ZType.Sub,
                color = ZColors.TextSecondary,
            )

            Spacer(Modifier.height(12.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Spacer(Modifier.weight(1f))
                ActionButton("Ignore", accent = false, onClick = onIgnore)
                ActionButton("Apply", accent = true, onClick = onApply)
            }
        }
    }
}

@Composable
private fun DiffHeader(title: String, count: String, color: Color, note: String?) {
    Column(Modifier.padding(top = 10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(color))
            Text(title, style = ZType.Eyebrow, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
            Text(count, style = ZType.Sub, color = ZColors.TextDim)
        }
        if (note != null) {
            Spacer(Modifier.height(3.dp))
            Text(note, style = ZType.Sub, color = ZColors.TextDim)
        }
        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun DiffRow(color: Color, title: String, subtitle: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(9.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(9.dp))
            .padding(horizontal = 10.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(Modifier.size(3.dp).height(24.dp).clip(RoundedCornerShape(2.dp)).background(color))
        Column(Modifier.weight(1f)) {
            Text(
                title,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                subtitle,
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun ActionButton(label: String, accent: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.16f) else ZColors.Surface)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            label,
            style = ZType.Body,
            color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}

/** コンソール上部に出す帯。通知を見逃しても、ここで気付ける */
@Composable
fun CatalogUpdateBanner(diff: CatalogDiff, onReview: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.Accent.copy(alpha = 0.10f))
            .border(1.dp, ZColors.Accent.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
            .springyClick(onClick = onReview)
            .padding(horizontal = 11.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(ZColors.AccentAlt))
        Column(Modifier.weight(1f)) {
            Text("URL categories changed", style = ZType.Body, color = ZColors.TextPrimary)
            Text(
                "${diff.changeCount} change(s) in ${diff.toRevision}",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text("Review", style = ZType.Sub.copy(fontSize = 11.sp), color = ZColors.AccentSoft)
    }
}
