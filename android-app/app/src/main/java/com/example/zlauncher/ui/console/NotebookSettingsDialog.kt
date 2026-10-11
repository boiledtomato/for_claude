package com.example.zlauncher.ui.console

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.notebook.NotebookDivider
import com.example.zlauncher.core.notebook.NotebookEndpoint
import com.example.zlauncher.core.notebook.NotebookFont
import com.example.zlauncher.core.notebook.NotebookPipeline
import com.example.zlauncher.core.notebook.NotebookSource
import com.example.zlauncher.core.notebook.NotebookTextColor
import com.example.zlauncher.core.notebook.NotebookWidgetStyle
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.data.notebook.NotebookCache
import com.example.zlauncher.data.notebook.NotebookConfig

/**
 * ノートブックのウィジェットの設定。
 *
 * **ここで入れるのは「どこを読むか」だけで、資格情報は入れさせない。** アクセストークンは
 * 使うたびに取り直す（寿命 1 時間）ので、貼り付ける欄も保存する場所も無い。
 *
 * 前提が重いので、何が要るかを画面に書いてある ― ここは利用者の Google Cloud 側の
 * 用意が揃うまで必ず失敗する機能で、「動かない」と「設定がまだ」を取り違えると
 * 直し方にたどり着けない。
 */
@Composable
fun NotebookSettingsDialog(
    config: NotebookConfig,
    cache: NotebookCache,
    message: String?,
    onSave: (NotebookConfig) -> Unit,
    /** 見た目だけの変更。保存を待たずその場で効かせる（通信は要らない） */
    onStyle: (NotebookWidgetStyle) -> Unit,
    onAuthorize: () -> Unit,
    onRefresh: () -> Unit,
    onClearMessage: () -> Unit,
    onDismiss: () -> Unit,
) {
    var source by remember(config.source) { mutableStateOf(config.source) }
    var baseUrl by remember(config.pipelineBaseUrl) { mutableStateOf(config.pipelineBaseUrl) }
    var projectNumber by remember(config.projectNumber) { mutableStateOf(config.projectNumber) }
    var endpointLocation by remember(config.endpointLocation) { mutableStateOf(config.endpointLocation) }
    var location by remember(config.location) { mutableStateOf(config.location) }

    val numberLooksWrong = source == NotebookSource.ENTERPRISE &&
        projectNumber.isNotBlank() &&
        !NotebookEndpoint.isValidProjectNumber(projectNumber)

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                // 前提の説明が長いので、縦の短い窓でもボタンに届くようにする
                .verticalScroll(rememberScrollState())
                .padding(18.dp),
        ) {
            Text("Notebook widgets", style = ZType.Title, color = ZColors.TextPrimary)

            Spacer(Modifier.height(14.dp))
            FieldLabel("Read from")
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Chip("Pipeline", source == NotebookSource.PIPELINE) {
                    source = NotebookSource.PIPELINE
                }
                Chip("Enterprise", source == NotebookSource.ENTERPRISE) {
                    source = NotebookSource.ENTERPRISE
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = when (source) {
                    NotebookSource.PIPELINE ->
                        "The notebooks this repository's pipeline keeps up to date, read from " +
                            "its published sync state. No sign-in, nothing to set up. Shows how " +
                            "many sources each notebook holds and when it was last synced; " +
                            "tapping a row opens that notebook."
                    NotebookSource.ENTERPRISE ->
                        "Gemini Notebook Enterprise (formerly NotebookLM Enterprise). Needs a " +
                            "Google Cloud project and a licence — see below. It lists the " +
                            "notebooks in that project, which are not the same ones the " +
                            "pipeline maintains."
                },
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            if (source == NotebookSource.PIPELINE) {
                Spacer(Modifier.height(12.dp))
                FieldLabel("Published URL")
                Spacer(Modifier.height(6.dp))
                TextInput(
                    value = baseUrl,
                    placeholder = NotebookPipeline.DEFAULT_BASE_URL,
                    numeric = false,
                    onValueChange = { baseUrl = it },
                )
            }

            if (source == NotebookSource.ENTERPRISE) {
            Spacer(Modifier.height(14.dp))
            FieldLabel("Project number")
            Spacer(Modifier.height(6.dp))
            TextInput(
                value = projectNumber,
                placeholder = "123456789012",
                numeric = true,
                onValueChange = { projectNumber = it },
            )
            if (numberLooksWrong) {
                Spacer(Modifier.height(6.dp))
                // 一番ありがちな間違い。通すと 403 になって原因が見えない
                Text(
                    "That looks like a project ID. Use the project number — digits only.",
                    style = ZType.Sub,
                    color = ZColors.Danger,
                )
            }

            Spacer(Modifier.height(12.dp))
            FieldLabel("API endpoint")
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                NotebookEndpoint.LOCATIONS.forEach { option ->
                    Chip(option, option == endpointLocation) { endpointLocation = option }
                }
            }

            Spacer(Modifier.height(12.dp))
            FieldLabel("Resource location")
            Spacer(Modifier.height(6.dp))
            TextInput(
                value = location,
                placeholder = "global",
                numeric = false,
                onValueChange = { location = it },
            )

            Spacer(Modifier.height(14.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(ZColors.SurfaceLow)
                    .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 11.dp),
            ) {
                Column {
                    Text("What this needs", style = ZType.Eyebrow, color = ZColors.TextSecondary)
                    Spacer(Modifier.height(6.dp))
                    // 全部そろわないと必ず失敗する。先に並べて見せておく
                    Text(
                        "· A Google Cloud project with the Discovery Engine API enabled\n" +
                            "· A Gemini Enterprise (or Education Premium) licence on your account\n" +
                            "· An Android OAuth client in that project for this app's package and " +
                            "signing certificate\n" +
                            "The API is a pre-GA preview, so Google can change it without notice.",
                        style = ZType.Sub,
                        color = ZColors.TextDim,
                    )
                }
            }
            }

            Spacer(Modifier.height(16.dp))
            Text("Appearance", style = ZType.Eyebrow, color = ZColors.TextSecondary)
            Spacer(Modifier.height(4.dp))
            // 押したその場で効かせる。保存を待たせる理由が無い（中身は取り直さない）
            Text(
                "Applies to the placed widgets straight away.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            Spacer(Modifier.height(10.dp))
            FieldLabel("Text colour")
            Spacer(Modifier.height(6.dp))
            ChipRow(
                options = NotebookTextColor.entries,
                selected = config.style.textColor,
                label = { it.name.lowercase().replaceFirstChar(Char::uppercase) },
                onSelect = { onStyle(config.style.copy(textColor = it)) },
            )

            Spacer(Modifier.height(12.dp))
            FieldLabel("Font")
            Spacer(Modifier.height(6.dp))
            ChipRow(
                options = NotebookFont.entries,
                selected = config.style.font,
                label = { it.name.lowercase().replaceFirstChar(Char::uppercase) },
                onSelect = { onStyle(config.style.copy(font = it)) },
            )
            Spacer(Modifier.height(4.dp))
            // 端末に必ずある総称名だけ。同梱フォントは RemoteViews へ確実に渡せない
            Text(
                "Generic families only — a bundled font cannot be handed to a widget reliably.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            Spacer(Modifier.height(12.dp))
            FieldLabel("Line under each row")
            Spacer(Modifier.height(6.dp))
            ChipRow(
                options = NotebookDivider.entries,
                selected = config.style.divider,
                label = { it.name.lowercase().replaceFirstChar(Char::uppercase) },
                onSelect = { onStyle(config.style.copy(divider = it)) },
            )

            Spacer(Modifier.height(12.dp))
            FieldLabel("Icons")
            Spacer(Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Chip("On", config.style.showIcons) {
                    onStyle(config.style.copy(showIcons = true))
                }
                Chip("Off", !config.style.showIcons) {
                    onStyle(config.style.copy(showIcons = false))
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "Vector marks, matched from each notebook's name — a book for Microsoft Learn, " +
                    "a shield for the Zscaler docs, a speech bubble for the community, a tag " +
                    "for release updates. Off falls back to the emoji.",
                style = ZType.Sub,
                color = ZColors.TextDim,
            )

            if (cache.status.isNotBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(cache.status, style = ZType.Sub, color = ZColors.TextSecondary)
            }
            message?.let { text ->
                Spacer(Modifier.height(10.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(ZColors.SurfaceLow)
                        .springyClick(onClick = onClearMessage)
                        .padding(horizontal = 11.dp, vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text, style = ZType.Sub, color = ZColors.TextSecondary, modifier = Modifier.weight(1f))
                    Text("Dismiss", style = ZType.Sub, color = ZColors.AccentSoft)
                }
            }

            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // サインインが要るのは Enterprise だけ。パイプラインには押す意味が無い
                if (source == NotebookSource.ENTERPRISE) {
                    DialogButton("Allow access", accent = false, onClick = onAuthorize)
                }
                Spacer(Modifier.weight(1f))
                DialogButton("Refresh", accent = false, onClick = onRefresh)
                DialogButton(
                    label = "Save",
                    accent = true,
                    enabled = source == NotebookSource.PIPELINE || !numberLooksWrong,
                ) {
                    onSave(
                        config.copy(
                            source = source,
                            pipelineBaseUrl = baseUrl.trim().ifBlank { NotebookPipeline.DEFAULT_BASE_URL },
                            projectNumber = projectNumber.trim(),
                            endpointLocation = endpointLocation,
                            location = location.trim(),
                        )
                    )
                    onDismiss()
                }
            }
        }
    }
}

/** 同じ形の選択行が 4 つ続くので、1 つにまとめる */
@Composable
private fun <T> ChipRow(
    options: List<T>,
    selected: T,
    label: (T) -> String,
    onSelect: (T) -> Unit,
) {
    // 選択肢が 4-5 個あって 1 行に収まらないことがある。折り返す
    FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        options.forEach { option ->
            Chip(label(option), option == selected) { onSelect(option) }
        }
    }
}

@Composable
private fun FieldLabel(label: String) {
    Text(label, style = ZType.Eyebrow, color = ZColors.TextSecondary)
}

@Composable
private fun TextInput(
    value: String,
    placeholder: String,
    numeric: Boolean,
    onValueChange: (String) -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 11.dp),
    ) {
        if (value.isEmpty()) {
            Text(placeholder, style = ZType.Body, color = ZColors.TextDim)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (numeric) KeyboardType.Number else KeyboardType.Text,
            ),
            textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
            cursorBrush = SolidColor(ZColors.AccentAlt),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun Chip(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (selected) ZColors.Accent.copy(alpha = 0.16f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (selected) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 7.dp),
    ) {
        Text(
            label,
            style = ZType.Body,
            color = if (selected) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}
