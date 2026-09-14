package com.example.zlauncher.ui.console

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.Canvas
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZMotion
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick
import com.example.zlauncher.core.ui.springyCombinedClick
import com.example.zlauncher.data.otp.OtpRepository

/**
 * 認証コード（TOTP）の一覧。
 *
 * **認証アプリを開かずに済ませるための面。** 他アプリの画面を埋め込む方法は Android に
 * 無いので（埋め込めるのは相手がウィジェットとして差し出したものだけ）、コードの計算
 * そのものをこちらに持っている。
 *
 * 共有鍵は暗号化して保管し、**この面を開いている間だけ**復号する。コードの表示に生体認証は
 * 挟まない（利用者の選択）。そのぶん、開いている間はスクリーンショットと画面録画を止める。
 */
@Composable
fun AuthPane(
    viewModel: ConsoleViewModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val codes by viewModel.otpCodes.collectAsStateWithLifecycle()
    val status by viewModel.otpStatus.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    var importing by remember { mutableStateOf(false) }
    var editing by remember { mutableStateOf<OtpCodeUi?>(null) }
    var deleting by remember { mutableStateOf<OtpCodeUi?>(null) }
    var copied by remember { mutableStateOf<String?>(null) }
    var discarding by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { viewModel.loadOtp() }

    // 開いている間だけ、画面の撮影と録画を止める（コードが常に出ている面なので）。
    // 面を離れるときに鍵をメモリから落とすのも、同じ後始末としてここでやる
    DisposableEffect(Unit) {
        val window = (context as? Activity)?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            viewModel.unloadOtp()
        }
    }

    val visible = remember(codes, query) { codes.filter { it.matches(query) } }

    Column(modifier.fillMaxSize()) {
        Row(
            Modifier.fillMaxWidth().padding(start = 12.dp, end = 16.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SearchField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.weight(1f),
            )
            AuthChip("Add", accent = true, onClick = { importing = true })
        }

        when {
            status == OtpRepository.Status.LOCKED -> AuthNotice(
                title = "Locked",
                body = "The codes are sealed while the device is locked. Unlock the phone and open this pane again.",
            )

            // 読めないまま書かせない。ここで足すと残っている保管庫を上書きしてしまう
            status == OtpRepository.Status.UNREADABLE -> AuthNotice(
                title = "Codes cannot be read",
                body = "The key that unlocks them is gone — this happens when the app's data is cleared or " +
                    "the app is reinstalled. The stored file is kept and nothing new can be added until " +
                    "you start over, so it is not overwritten by accident.",
                action = "Start over" to { discarding = true },
            )

            codes.isEmpty() -> AuthNotice(
                title = "No codes yet",
                body = "Add them with “Add”: paste one or more otpauth:// links, or a Google Authenticator " +
                    "export link. Nothing leaves the phone — the keys are encrypted on this device.",
            )

            visible.isEmpty() -> AuthNotice(
                title = "Nothing matches",
                body = "No issuer or account contains “$query”.",
            )

            else -> LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 12.dp, end = 16.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(visible, key = { it.id }) { code ->
                    CodeCard(
                        code = code,
                        copied = copied == code.id,
                        onCopy = {
                            copyCode(context, code)
                            copied = code.id
                        },
                        onEdit = { editing = code },
                        onDelete = { deleting = code },
                        modifier = Modifier.animateItem(placementSpec = ZMotion.placement()),
                    )
                }
            }
        }
    }

    // コピーした印は数秒で戻す
    LaunchedEffect(copied) {
        if (copied != null) {
            kotlinx.coroutines.delay(1_600)
            copied = null
        }
    }

    if (importing) {
        OtpImportDialog(
            onDismiss = { importing = false },
            onImport = { drafts ->
                viewModel.addOtp(drafts)
                importing = false
            },
        )
    }

    editing?.let { target ->
        OtpRenameDialog(
            issuer = target.issuer,
            account = target.account,
            onDismiss = { editing = null },
            onConfirm = { issuer, account ->
                viewModel.renameOtp(target.id, issuer, account)
                editing = null
            },
        )
    }

    if (discarding) {
        ConfirmDialog(
            title = "Start over",
            message = "Delete the unreadable file and begin with an empty list? The codes inside it cannot " +
                "be recovered either way — this only clears the way for new ones.",
            onConfirm = {
                viewModel.discardOtpVault()
                discarding = false
            },
            onDismiss = { discarding = false },
        )
    }

    deleting?.let { target ->
        ConfirmDialog(
            title = "Remove this code",
            message = "Remove “${target.label}” from ZLauncher? The key is deleted from this phone. " +
                "If it is not saved anywhere else, you will lose access to that account.",
            onConfirm = {
                viewModel.removeOtp(listOf(target.id))
                deleting = null
            },
            onDismiss = { deleting = null },
        )
    }
}

@Composable
private fun CodeCard(
    code: OtpCodeUi,
    copied: Boolean,
    onCopy: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(14.dp)
    val identity = ZColors.CategoryColors[code.colorIndex % ZColors.CategoryColors.size]

    Row(
        modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ZColors.Surface)
            .border(1.dp, if (copied) ZColors.Accent else ZColors.Outline, shape)
            .springyCombinedClick(onClick = onCopy, onLongClick = onEdit)
            .padding(start = 12.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(Modifier.size(8.dp).clip(RoundedCornerShape(4.dp)).background(identity))

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = if (code.issuer.isBlank()) code.account else code.issuer,
                style = ZType.Body,
                color = ZColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (code.issuer.isNotBlank() && code.account.isNotBlank()) {
                Text(
                    text = code.account,
                    style = ZType.Sub,
                    color = ZColors.TextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = if (copied) "Copied" else code.grouped,
                style = ZType.Title.copy(
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 1.sp,
                ),
                color = if (copied) ZColors.AccentSoft else ZColors.TextPrimary,
            )
        }

        CountdownRing(fraction = code.fraction, seconds = code.secondsRemaining, color = identity)
        AuthChip("✕", accent = false, onClick = onDelete)
    }
}

/**
 * 残り時間の輪。
 *
 * 切り替わった瞬間だけ跳ね返さずに一気に戻す ― 直線で 1 秒かけて戻すと、コードが変わった
 * のに輪が逆回りしているように見える。
 */
@Composable
private fun CountdownRing(fraction: Float, seconds: Int, color: Color) {
    val sweep = remember { Animatable(fraction) }
    LaunchedEffect(fraction) {
        if (fraction > sweep.value) {
            sweep.snapTo(fraction)
        } else {
            sweep.animateTo(fraction, tween(durationMillis = 1_000, easing = LinearEasing))
        }
    }

    val track = ZColors.OutlineStrong
    Box(Modifier.size(34.dp), contentAlignment = Alignment.Center) {
        Canvas(Modifier.fillMaxSize()) {
            val stroke = 3.dp.toPx()
            val inset = stroke / 2f
            val arcSize = Size(size.width - stroke, size.height - stroke)
            drawArc(
                color = track,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = Stroke(width = stroke),
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * sweep.value.coerceIn(0f, 1f),
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = arcSize,
                style = Stroke(width = stroke),
            )
        }
        Text("$seconds", style = ZType.Eyebrow, color = ZColors.TextSecondary)
    }
}

@Composable
private fun SearchField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .clip(RoundedCornerShape(999.dp))
            .background(ZColors.SurfaceLow)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(999.dp))
            .padding(horizontal = 14.dp, vertical = 9.dp),
    ) {
        if (value.isEmpty()) {
            Text("Search issuer or account", style = ZType.Sub, color = ZColors.TextDim)
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
private fun AuthNotice(title: String, body: String, action: Pair<String, () -> Unit>? = null) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 16.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(ZColors.Surface)
            .border(1.dp, ZColors.Outline, RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 20.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, style = ZType.Body, color = ZColors.TextPrimary)
            Text(body, style = ZType.Sub, color = ZColors.TextSecondary)
            action?.let { (label, onClick) ->
                Spacer(Modifier.height(6.dp))
                AuthChip(label, accent = true, onClick = onClick)
            }
        }
    }
}

@Composable
private fun AuthChip(label: String, accent: Boolean, onClick: () -> Unit) {
    Box(
        Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(if (accent) ZColors.Accent.copy(alpha = 0.14f) else ZColors.SurfaceHigh)
            .border(
                1.dp,
                if (accent) ZColors.Accent.copy(alpha = 0.5f) else ZColors.Outline,
                RoundedCornerShape(999.dp),
            )
            .springyClick(onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 8.dp),
    ) {
        Text(
            label,
            style = ZType.Body.copy(fontSize = 12.sp),
            color = if (accent) ZColors.AccentSoft else ZColors.TextSecondary,
        )
    }
}

/**
 * クリップボードへ。
 *
 * Android 13 以降は「機密」の印を付ける。付けないと、貼り付け候補のプレビューに 6 桁が
 * そのまま出て、画面を見た人に読まれる。
 */
private fun copyCode(context: Context, code: OtpCodeUi) {
    val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
    val clip = ClipData.newPlainText("code", code.raw)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        clip.description.extras = PersistableBundle().apply {
            putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
        }
    }
    manager.setPrimaryClip(clip)
}

private fun OtpCodeUi.matches(query: String): Boolean {
    val needle = query.trim()
    if (needle.isEmpty()) return true
    return issuer.contains(needle, ignoreCase = true) || account.contains(needle, ignoreCase = true)
}

private val OtpCodeUi.label: String get() = if (issuer.isBlank()) account else issuer
