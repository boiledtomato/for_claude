package com.example.zlauncher.ui.console

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.otp.OtpDraft
import com.example.zlauncher.core.otp.OtpMigration
import com.example.zlauncher.core.otp.OtpUri
import com.example.zlauncher.core.otp.QrLuminance
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.common.HybridBinarizer
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.concurrent.Executors

/**
 * QR を読んで登録する。
 *
 * **移行の実際の作業がこれ。** ほかの認証アプリから鍵を取り出す方法は無いので、各サービスの
 * 二要素認証の設定で QR を出し直し、それをここで読む。1 枚読んでも閉じない ― 登録が何十件も
 * あるときに、1 件ごとに開き直させない。
 *
 * 読み取りは端末の中だけで完結する（CameraX + zxing）。絵はどこにも保存せず、解析した
 * コマはその場で捨てる。
 */
@Composable
fun QrScanDialog(
    onDismiss: () -> Unit,
    onImport: (List<OtpDraft>) -> Unit,
) {
    val context = LocalContext.current
    var granted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
        )
    }
    var refused by remember { mutableStateOf(false) }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
        granted = it
        refused = !it
    }
    LaunchedEffect(Unit) {
        if (!granted) permission.launch(Manifest.permission.CAMERA)
    }

    val found = remember { mutableStateListOf<OtpDraft>() }
    val texts = remember { MutableSharedFlow<String>(extraBufferCapacity = 16) }

    // 読み取りは解析用のスレッドから届く。Compose の状態はそこから触れないので、
    // flow を 1 枚挟んで composition 側で受ける
    LaunchedEffect(Unit) {
        texts.collect { text -> collect(found, text) }
    }

    Dialog(onDismissRequest = onDismiss) {
        SecureDialogWindow()
        Column(
            Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(ZColors.Surface)
                .border(1.dp, ZColors.OutlineStrong, RoundedCornerShape(18.dp))
                .padding(18.dp),
        ) {
            Text("Scan QR codes", style = ZType.Title, color = ZColors.TextPrimary)
            Spacer(Modifier.height(10.dp))
            Text(
                "Open the two-factor settings of each service, choose to add an authenticator app, " +
                    "and point the camera at the QR code it shows. Several in a row is fine.",
                style = ZType.Sub,
                color = ZColors.TextSecondary,
            )
            Spacer(Modifier.height(12.dp))

            when {
                granted -> CameraBox(onText = { texts.tryEmit(it) })
                refused -> Notice(
                    "Camera access was refused. Grant it in Settings → Apps → ZLauncher, " +
                        "or paste the otpauth:// link instead."
                )
                else -> Notice("Waiting for camera access…")
            }

            Spacer(Modifier.height(12.dp))
            Text(
                text = when (found.size) {
                    0 -> "Nothing scanned yet"
                    1 -> "1 code scanned"
                    else -> "${found.size} codes scanned"
                },
                style = ZType.Eyebrow,
                color = if (found.isEmpty()) ZColors.TextDim else ZColors.AccentSoft,
            )
            if (found.isNotEmpty()) {
                Spacer(Modifier.height(6.dp))
                Column(
                    Modifier.heightIn(max = 120.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    found.forEach { draft ->
                        Text(
                            "${draft.label} · ${draft.account}",
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
                    label = if (found.size > 1) "Add ${found.size}" else "Add",
                    accent = true,
                    enabled = found.isNotEmpty(),
                ) { onImport(found.toList()) }
            }
        }
    }
}

/** 同じ QR を何コマも読むので、同じ鍵とアカウントの組は 1 件しか足さない */
private fun collect(found: SnapshotStateList<OtpDraft>, text: String) {
    val drafts = OtpUri.parseAll(text) + OtpMigration.parseAll(text)
    drafts.forEach { draft ->
        val known = found.any {
            it.secret.contentEquals(draft.secret) && it.account == draft.account
        }
        if (!known) found.add(draft)
    }
}

@Composable
private fun CameraBox(onText: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val executor = remember { Executors.newSingleThreadExecutor() }
    val view = remember {
        PreviewView(context).apply {
            // TextureView で描く。FLAG_SECURE を立てた窓の中の SurfaceView は、
            // 端末によって真っ黒のまま出ることがある
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
    }

    DisposableEffect(Unit) {
        val future = ProcessCameraProvider.getInstance(context)
        future.addListener({
            val provider = runCatching { future.get() }.getOrNull() ?: return@addListener
            val preview = Preview.Builder().build().also { it.surfaceProvider = view.surfaceProvider }
            val analysis = ImageAnalysis.Builder()
                // 溜めない。読めなかったコマを順番に処理しても意味が無い
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also { it.setAnalyzer(executor, QrAnalyzer(onText)) }
            runCatching {
                provider.unbindAll()
                provider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    analysis,
                )
            }.onFailure { Log.w(TAG, "camera could not be started (${it.javaClass.simpleName})") }
        }, ContextCompat.getMainExecutor(context))

        onDispose {
            runCatching { future.get().unbindAll() }
            executor.shutdown()
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(14.dp))
            .background(ZColors.SurfaceLow),
    ) {
        AndroidView(factory = { view }, modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun Notice(message: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(ZColors.SurfaceLow)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(message, style = ZType.Sub, color = ZColors.TextSecondary)
    }
}

/**
 * 1 コマぶんの読み取り。
 *
 * 明るさの取り方を 2 通り試す ― 画面に出た QR は照明と反射で明暗が偏りやすく、
 * [HybridBinarizer] だけだと読めない場面がそこそこ出る。
 */
private class QrAnalyzer(private val onText: (String) -> Unit) : ImageAnalysis.Analyzer {

    private val reader = MultiFormatReader().apply {
        setHints(
            mapOf(
                DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE),
                DecodeHintType.TRY_HARDER to true,
            )
        )
    }

    override fun analyze(image: ImageProxy) {
        try {
            val plane = image.planes[0]
            val buffer = plane.buffer
            val bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)

            val packed = QrLuminance.pack(bytes, image.width, image.height, plane.rowStride)
            val rotated = QrLuminance.rotate(
                packed,
                image.width,
                image.height,
                image.imageInfo.rotationDegrees,
            )
            val source = PlanarYUVLuminanceSource(
                rotated.bytes,
                rotated.width,
                rotated.height,
                0,
                0,
                rotated.width,
                rotated.height,
                false,
            )

            val text = decode(BinaryBitmap(HybridBinarizer(source)))
                ?: decode(BinaryBitmap(GlobalHistogramBinarizer(source)))
            if (text != null) onText(text)
        } catch (error: Exception) {
            // 1 コマ読めなくても次が来る。中身は出さない（読み取れた文字列は鍵そのもの）
            Log.w(TAG, "frame could not be read (${error.javaClass.simpleName})")
        } finally {
            image.close()
        }
    }

    private fun decode(bitmap: BinaryBitmap): String? = try {
        reader.decodeWithState(bitmap).text
    } catch (_: Exception) {
        null
    } finally {
        reader.reset()
    }
}

/**
 * ダイアログは**別の窓**なので、面に立てた `FLAG_SECURE` が効かない。
 * 鍵そのものが出る窓なので、ここでも撮影と録画を止める。
 */
@Composable
internal fun SecureDialogWindow() {
    val view = androidx.compose.ui.platform.LocalView.current
    DisposableEffect(view) {
        val window = (view.parent as? androidx.compose.ui.window.DialogWindowProvider)?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        onDispose { }
    }
}

private const val TAG = "QrScan"
