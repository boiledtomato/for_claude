package com.impasto.launcher.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import com.impasto.launcher.data.AppEntry
import com.impasto.launcher.data.AppRepository
import com.impasto.launcher.data.LayoutStore
import com.impasto.launcher.data.Salon
import com.impasto.launcher.data.Slot
import com.impasto.launcher.paint.OilPainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/** A finished painting: the bitmap, and the colours its brush is loaded with. */
class Painting(val image: ImageBitmap, val colors: List<Color>)

/** An app on its way open, held while the screen paints over. */
private class Launch(val entry: AppEntry, val painting: Painting)

private const val WIPE_MS = 360
private const val WIPE_HOLD_MS = 300

@Composable
fun HomeScreen(repo: AppRepository, store: LayoutStore) {
    val ctx = LocalContext.current

    var wall by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    var dock by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    val paintings = remember { mutableStateMapOf<String, Painting>() }

    var launching by remember { mutableStateOf<Launch?>(null) }

    LaunchedEffect(Unit) {
        val apps = repo.installedApps()
        val byPackage = apps.associateBy { it.packageName }
        val (wallPkgs, dockPkgs) = store.load() ?: store.seed(apps).also { store.save(it.first, it.second) }
        wall = wallPkgs.mapNotNull(byPackage::get)
        dock = dockPkgs.mapNotNull(byPackage::get)

        // paint on a background thread, one at a time, so the wall fills in
        // rather than blocking on the whole set
        (wall + dock).forEachIndexed { i, entry ->
            val shape = (Salon.WALL + Salon.DOCK).getOrNull(i)?.shape ?: OilPainter.SHAPE_SQUIRCLE
            paint(ctx, repo, entry, shape)?.let { paintings[entry.packageName] = it }
        }
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            BoxWithConstraints(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 24.dp)
            ) {
                val wallW = maxWidth
                val wallH = maxHeight
                wall.forEachIndexed { i, entry ->
                    val slot = Salon.WALL.getOrNull(i) ?: return@forEachIndexed
                    PaintingSlot(
                        entry = entry,
                        painting = paintings[entry.packageName],
                        slot = slot,
                        index = i,
                        wallW = wallW,
                        wallH = wallH,
                        showLabel = true,
                    ) { p -> launching = Launch(entry, p) }
                }
            }

            PaletteDock(dock, paintings) { entry, p -> launching = Launch(entry, p) }
        }

        launching?.let { l ->
            LaunchWipe(
                painting = l.painting,
                label = l.entry.label,
                // only once the screen is covered, so the stroke and the wipe
                // both get seen before the app takes the window
                onCovered = { runCatching { ctx.startActivity(l.entry.launchIntent()) } },
                onDone = { launching = null },
            )
        }
    }
}

private suspend fun paint(
    ctx: Context,
    repo: AppRepository,
    entry: AppEntry,
    shape: Float,
): Painting? = withContext(Dispatchers.Default) {
    val icon = repo.icon(entry) ?: return@withContext null
    val bmp: Bitmap = OilPainter.paint(ctx, entry.cacheKey, icon, entry.seed, shape)
    Painting(bmp.asImageBitmap(), OilPainter.brushColors(bmp).map { Color(it) })
}

@Composable
private fun PaintingSlot(
    entry: AppEntry,
    painting: Painting?,
    slot: Slot,
    index: Int,
    wallW: Dp,
    wallH: Dp,
    showLabel: Boolean,
    onOpen: (Painting) -> Unit,
) {
    val side = wallW * slot.size
    val scope = rememberCoroutineScope()
    var strokes by remember { mutableStateOf(0) }

    Box(
        Modifier
            .offset(x = wallW * slot.x - side / 2, y = wallH * slot.y - side / 2)
            .width(side)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(side)
                    .rotate(slot.rotation(index))
                    .pointerInput(entry.packageName, painting) {
                        detectTapGestures(
                            onPress = {
                                strokes++                 // the brush runs on touch down
                                tryAwaitRelease()
                            },
                            onTap = {
                                val p = painting ?: return@detectTapGestures
                                scope.launch {
                                    delay(STROKE_MS.toLong())   // let the stroke finish first
                                    onOpen(p)
                                }
                            },
                        )
                    }
            ) {
                painting?.let {
                    Image(
                        bitmap = it.image,
                        contentDescription = entry.label,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit,
                    )
                    BrushStroke(
                        colors = it.colors,
                        trigger = strokes,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
            if (showLabel) {
                Text(
                    text = entry.label,
                    color = Color(0xFFECE6D9).copy(alpha = 0.68f),
                    fontSize = 9.5.sp,
                    letterSpacing = 0.1.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 5.dp),
                )
            }
        }
    }
}

/** The palette the four thumb-reachable apps sit on, as dabs of paint. */
@Composable
private fun PaletteDock(
    dock: List<AppEntry>,
    paintings: Map<String, Painting>,
    onOpen: (AppEntry, Painting) -> Unit,
) {
    BoxWithConstraints(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(bottom = 20.dp)
    ) {
        val boxW = maxWidth
        val boxH = boxW * (132f / 340f)

        Canvas(Modifier.size(width = boxW, height = boxH)) {
            drawPath(palettePath(size), Color(0xFF44392B).copy(alpha = 0.55f))
        }

        dock.forEachIndexed { i, entry ->
            val slot = Salon.DOCK.getOrNull(i) ?: return@forEachIndexed
            PaintingSlot(
                entry = entry,
                painting = paintings[entry.packageName],
                slot = slot,
                index = Salon.WALL.size + i,
                wallW = boxW,
                wallH = boxH,
                showLabel = false,
            ) { p -> onOpen(entry, p) }
        }
    }
}

/** The blob, with the thumb hole punched through by even-odd fill. */
private fun palettePath(size: Size): Path {
    val sx = size.width / 340f
    val sy = size.height / 132f
    fun x(v: Float) = v * sx
    fun y(v: Float) = v * sy

    return Path().apply {
        fillType = PathFillType.EvenOdd
        moveTo(x(24f), y(60f))
        cubicTo(x(24f), y(18f), x(92f), y(6f), x(170f), y(6f))
        cubicTo(x(248f), y(6f), x(316f), y(18f), x(316f), y(60f))
        cubicTo(x(316f), y(102f), x(250f), y(128f), x(170f), y(128f))
        cubicTo(x(90f), y(128f), x(24f), y(102f), x(24f), y(60f))
        close()
        addOval(
            androidx.compose.ui.geometry.Rect(
                Offset(x(66f), y(92f)),
                radius = minOf(x(15f), y(15f)),
            )
        )
    }
}

/**
 * The screen is painted over in the opened app's own colours: sixteen broad
 * sweeps with round ends and a dry leading edge, staggered top to bottom.
 */
@Composable
private fun LaunchWipe(
    painting: Painting,
    label: String,
    onCovered: () -> Unit,
    onDone: () -> Unit,
) {
    val t = remember { Animatable(0f) }
    val sweeps = remember(painting) { Sweep.build(painting.colors) }
    var showLabel by remember { mutableStateOf(false) }

    LaunchedEffect(painting) {
        t.animateTo(1f, tween(WIPE_MS))
        showLabel = true
        onCovered()
        // the app now owns the window; drop the overlay so coming home shows
        // the wall again rather than a screen still full of paint
        delay(WIPE_HOLD_MS.toLong())
        onDone()
    }

    Box(Modifier.fillMaxSize()) {
        Canvas(Modifier.fillMaxSize()) { drawSweeps(sweeps, t.value) }
        if (showLabel) {
            Text(
                text = label,
                color = Color(0xFFF6F1E4),
                fontSize = 20.sp,
                letterSpacing = 4.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

private class Sweep(
    val yFrac: Float,
    val slope: Float,
    val lag: Float,
    val color: Color,
    val over: Float,
    val tips: FloatArray,
) {
    companion object {
        const val BANDS = 16

        fun build(colors: List<Color>): List<Sweep> {
            val pal = colors.ifEmpty { listOf(Color(0xFFE6DFCE)) }
            val rnd = Random(29)
            return (0 until BANDS).map { i ->
                Sweep(
                    yFrac = (i + 0.5f) / BANDS + (rnd.nextFloat() - 0.5f) * 0.3f / BANDS,
                    slope = (rnd.nextFloat() - 0.5f) * 0.07f,
                    lag = (i.toFloat() / BANDS) * 0.34f + rnd.nextFloat() * 0.13f,
                    color = pal[(i * 5 + rnd.nextInt(pal.size)) % pal.size],
                    over = 1f + rnd.nextFloat() * 0.3f,
                    tips = FloatArray(4) { rnd.nextFloat() },
                )
            }
        }
    }
}

private fun DrawScope.drawSweeps(sweeps: List<Sweep>, t: Float) {
    if (t <= 0f) return
    val w = size.width
    val bh = size.height / Sweep.BANDS

    for (s in sweeps) {
        var k = ((t - s.lag) / (1f - s.lag).coerceAtLeast(0.0001f)).coerceIn(0f, 1f)
        k = k * k * (3f - 2f * k)
        val len = k * w * 1.3f
        if (len <= 2f) continue

        val y = s.yFrac * size.height
        val y2 = y + len * s.slope
        drawLine(
            color = s.color,
            start = Offset(-40f, y),
            end = Offset(len - 34f, y2),
            strokeWidth = bh * 1.6f * s.over,
            cap = StrokeCap.Round,
        )
        // the loaded brush runs dry at the leading edge
        s.tips.forEachIndexed { j, tip ->
            val off = (j / 3f - 0.5f) * bh * 1.1f
            drawLine(
                color = s.color.copy(alpha = 0.85f - j * 0.12f),
                start = Offset(len - 46f, y2 + off),
                end = Offset(len - 34f + tip * 46f, y2 + off * 1.15f),
                strokeWidth = bh * 0.2f,
                cap = StrokeCap.Round,
            )
        }
    }
}
