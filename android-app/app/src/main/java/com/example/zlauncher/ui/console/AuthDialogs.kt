package com.example.zlauncher.ui.console

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.otp.Base32
import com.example.zlauncher.core.otp.OtpDraft
import com.example.zlauncher.core.otp.OtpMigration
import com.example.zlauncher.core.otp.OtpUri
import com.example.zlauncher.core.ui.springyClick

/**
 * 取り込み。
 *
 * **形式を選ばせない。** 貼られた文字列の中から `otpauth://` と
 * `otpauth-migration://`（Google Authenticator の書き出し）を拾う。1 件でも、行で並べた
 * ものでも、JSON に埋まっていても同じ操作になる。移行のたびにどれを選ぶか考えさせても、
 * 正解が分かるのは作った人だけになる。
 *
 * **読み取った中身は保存前に必ず見せる。** 移行形式は公開仕様ではないので、取り違えが
 * あり得る。ここで発行元とアカウントを見て、おかしければ入れずに閉じられるようにする。
 */
@Composable
fun OtpImportDialog(
    onDismiss: () -> Unit,
    onImport: (List<OtpDraft>) -> Unit,
) {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var manual by remember { mutableStateOf(false) }

    val drafts = remember(text) {
        if (text.isBlank()) emptyList() else OtpUri.parseAll(text) + OtpMigration.parseAll(text)
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                .padding(18.dp),
        ) {
            Text(if (manual) "Add by hand" else "Add codes", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(10.dp))

            if (manual) {
                ManualEntry(
                    onCancel = { manual = false },
                    onAdd = { draft -> onImport(listOf(draft)) },
                )
                return@Column
            }

            Text(
                "Paste an otpauth:// link, several of them, or the otpauth-migration:// link from a " +
                    "Google Authenticator export. Everything stays on this phone.",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
            )
            Spacer(Modifier.height(12.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 90.dp, max = 150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ZColors.SurfaceLow)
                    .border(1.dp, ZColors.Outline, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
            ) {
                if (text.isEmpty()) {
                    Text("otpauth://totp/…", style = ZType.Sub, color = ZColors.TextDim)
                }
                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = ZType.Sub.copy(color = ZColors.TextPrimary),
                    cursorBrush = SolidColor(ZColors.AccentAlt),
                    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                )
            }

            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SmallAction("Paste") { text = clipboardText(context) ?: text }
                SmallAction("Add by hand") { manual = true }
            }

            if (text.isNotBlank()) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = when (drafts.size) {
                        0 -> "No codes found in that text"
                        1 -> "1 code found"
                        else -> "${drafts.size} codes found"
                    },
                    style = ZType.Eyebrow,
                    color = if (drafts.isEmpty()) ZColors.StatusAmber else ZColors.AccentSoft,
                )
                Spacer(Modifier.height(6.dp))
                Column(
                    Modifier.heightIn(max = 160.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    drafts.forEach { draft ->
                        Text(
                            text = "${draft.label} · ${draft.account} · ${draft.digits} digits / ${draft.periodSeconds}s",
                            style = ZType.Sub,
                            color = ZColors.TextSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                SmallAction("Cancel", onClick = onDismiss)
                Spacer(Modifier.size(10.dp))
                SmallAction(
                    label = if (drafts.size > 1) "Add ${drafts.size}" else "Add",
                    accent = true,
                    enabled = drafts.isNotEmpty(),
                ) { onImport(drafts) }
            }
        }
    }
}

/** 手入力。書き出しが使えないサイト（鍵だけ表示されるもの）向け */
@Composable
private fun ManualEntry(onCancel: () -> Unit, onAdd: (OtpDraft) -> Unit) {
    var issuer by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("") }
    var secret by remember { mutableStateOf("") }

    val parsed = remember(secret) {
        if (secret.isBlank()) null else runCatching { Base32.decode(secret) }.getOrNull()?.takeIf { it.isNotEmpty() }
    }

    Column {
        Field("Issuer (e.g. GitHub)", issuer) { issuer = it }
        Spacer(Modifier.height(8.dp))
        Field("Account", account) { account = it }
        Spacer(Modifier.height(8.dp))
        Field("Key (base32)", secret) { secret = it }

        if (secret.isNotBlank() && parsed == null) {
            Spacer(Modifier.height(6.dp))
            Text("That key is not valid base32", style = ZType.Sub, color = ZColors.StatusAmber)
        }

        Spacer(Modifier.height(18.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            SmallAction("Back", onClick = onCancel)
            Spacer(Modifier.size(10.dp))
            SmallAction("Add", accent = true, enabled = parsed != null) {
                parsed?.let { key ->
                    onAdd(
                        OtpDraft(
                            issuer = issuer.trim(),
                            account = account.trim(),
                            secret = key,
                        )
                    )
                }
            }
        }
    }
}

/** 発行元とアカウントの付け替え。鍵には触れない */
@Composable
fun OtpRenameDialog(
    issuer: String,
    account: String,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit,
) {
    var nextIssuer by remember { mutableStateOf(issuer) }
    var nextAccount by remember { mutableStateOf(account) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                .padding(18.dp),
        ) {
            Text("Rename", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(12.dp))
            Field("Issuer", nextIssuer) { nextIssuer = it }
            Spacer(Modifier.height(8.dp))
            Field("Account", nextAccount) { nextAccount = it }
            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                SmallAction("Cancel", onClick = onDismiss)
                Spacer(Modifier.size(10.dp))
                SmallAction("Save", accent = true) { onConfirm(nextIssuer, nextAccount) }
            }
        }
    }
}

@Composable
private fun Field(placeholder: String, value: String, onValueChange: (String) -> Unit) {
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
            textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
            cursorBrush = SolidColor(ZColors.AccentAlt),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun SmallAction(
    label: String,
    accent: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.14f) else ZColors.SurfaceLow)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(enabled = enabled, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 9.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.5.sp),
            color = when {
                !enabled -> ZColors.TextDim
                accent -> ZColors.AccentSoft
                else -> ZColors.TextSecondary
            },
        )
    }
}

private fun clipboardText(context: Context): String? {
    val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return null
    return manager.primaryClip?.getItemAt(0)?.coerceToText(context)?.toString()
}
