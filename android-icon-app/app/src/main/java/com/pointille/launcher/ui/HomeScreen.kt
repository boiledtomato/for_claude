package com.pointille.launcher.ui

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
import com.pointille.launcher.data.AppEntry
import com.pointille.launcher.data.AppRepository
import com.pointille.launcher.data.LayoutStore
import com.pointille.launcher.data.Salon
import com.pointille.launcher.data.Slot
import com.pointille.launcher.paint.IconSource
import com.pointille.launcher.paint.Pointillist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

/** A finished panel: the bitmap, and the colours its brush is loaded with. */
class Painting(val image: ImageBitmap, val colors: List<Color>)

/** An app on its way open, held while the screen paints over. */
private class Launch(val entry: AppEntry, val painting: Painting)

private const val WIPE_MS = 360
private const val WIPE_HOLD_MS = 300
private val LABEL = Color(0xFFECE6D9)

/**
 * Panels, painted off the main thread and kept for as long as the launcher
 * lives. Painting is idempotent and disk-cached, so asking twice is cheap; what
 * this avoids is asking on the main thread.
 */
private class Studio(private val ctx: Context, private val repo: AppRepository) {
    val panels = mutableStateMapOf<String, Painting>()
    /** Drawer items ask concurrently as they scroll in, so this must be shared-safe. */
    private val pending = java.util.Collections.newSetFromMap(java.util.concurrent.ConcurrentHashMap<String, Boolean>())
    var tiers: Map<String, Int> = emptyMap()

    suspend fun paint(entry: AppEntry) {
        if (panels.containsKey(entry.packageName)) return
        if (!pending.add(entry.packageName)) return
        panels[entry.packageName] = render(entry)
    }

    /** After the tier is changed by hand: drop what is on the wall and paint again. */
    suspend fun repaint(entry: AppEntry) {
        panels.remove(entry.packageName)
        pending.remove(entry.packageName)
        pending.add(entry.packageName)
        panels[entry.packageName] = render(entry)
    }

    private suspend fun render(entry: AppEntry): Painting = withContext(Dispatchers.Default) {
        val bmp = IconSource.painted(
            ctx, entry.cacheKey, entry.packageName, repo.icon(entry), entry.seed,
            tiers[entry.packageName] ?: IconSource.AUTO,
        )
        Painting(bmp.asImageBitmap(), Pointillist.brushColors(bmp).map { Color(it) })
    }
}

@Composable
fun HomeScreen(repo: AppRepository, store: LayoutStore) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val studio = remember { Studio(ctx, repo) }

    var all by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    var wall by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    var dock by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    var launching by remember { mutableStateOf<Launch?>(null) }
    var drawerOpen by remember { mutableStateOf(false) }
    var sheetFor by remember { mutableStateOf<AppEntry?>(null) }
    var swapFrom by remember { mutableStateOf<AppEntry?>(null) }

    fun place(wallPkgs: List<String>, dockPkgs: List<String>, byPackage: Map<String, AppEntry>) {
        wall = wallPkgs.mapNotNull(byPackage::get)
        dock = dockPkgs.mapNotNull(byPackage::get)
        store.save(wallPkgs, dockPkgs)
    }

    /** Put [b] where [a] hangs. Either may live on the wall, the palette, or neither. */
    fun swap(a: AppEntry, b: AppEntry) {
        val w = wall.map { it.packageName }.toMutableList()
        val d = dock.map { it.packageName }.toMutableList()
        fun setAt(list: MutableList<String>, i: Int, v: String) { if (i >= 0) list[i] = v }
        val aw = w.indexOf(a.packageName); val ad = d.indexOf(a.packageName)
        val bw = w.indexOf(b.packageName); val bd = d.indexOf(b.packageName)
        setAt(w, aw, b.packageName); setAt(d, ad, b.packageName)
        setAt(w, bw, a.packageName); setAt(d, bd, a.packageName)
        val byPackage = all.associateBy { it.packageName }
        place(w, d, byPackage)
    }

    LaunchedEffect(Unit) {
        val apps = repo.installedApps()
        all = apps
        val byPackage = apps.associateBy { it.packageName }
        val (wallPkgs, dockPkgs) = store.load() ?: store.seed(apps).also { store.save(it.first, it.second) }
        studio.tiers = store.tiers()
        wall = wallPkgs.mapNotNull(byPackage::get)
        dock = dockPkgs.mapNotNull(byPackage::get)
        // the wall fills in one panel at a time rather than blocking on the set
        (wall + dock).forEach { studio.paint(it) }
    }

    /** Tapping a panel while one is picked up puts them in each other's place. */
    fun onPanelTap(entry: AppEntry, p: Painting) {
        val from = swapFrom
        if (from == null) { launching = Launch(entry, p); return }
        swapFrom = null
        if (from.packageName != entry.packageName) swap(from, entry)
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                // the drag lives on the wall, not the root: on the root it
                // competes with the drawer's own scrolling
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dy -> if (dy < -18f) drawerOpen = true }
                }
        ) {
            BoxWithConstraints(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 24.dp)
            ) {
                val w = maxWidth
                val h = maxHeight
                wall.forEachIndexed { i, entry ->
                    val slot = Salon.WALL.getOrNull(i) ?: return@forEachIndexed
                    Panel(
                        entry = entry,
                        painting = studio.panels[entry.packageName],
                        slot = slot,
                        index = i,
                        wallW = w,
                        wallH = h,
                        showLabel = true,
                        pickedUp = swapFrom?.packageName == entry.packageName,
                        onLongPress = { sheetFor = entry },
                        onOpen = { p -> onPanelTap(entry, p) },
                    )
                }
            }
            PaletteDock(
                dock = dock,
                panels = studio.panels,
                pickedUp = swapFrom?.packageName,
                onLongPress = { sheetFor = it },
                onOpen = { entry, p -> onPanelTap(entry, p) },
            )
        }

        AnimatedVisibility(
            visible = drawerOpen,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            Drawer(
                apps = all,
                studio = studio,
                onLongPress = { sheetFor = it },
                onOpen = { entry ->
                    val p = studio.panels[entry.packageName] ?: return@Drawer
                    val from = swapFrom
                    if (from == null) launching = Launch(entry, p)
                    else { swapFrom = null; drawerOpen = false; swap(from, entry) }
                },
            )
        }
        swapFrom?.let { from ->
            Text(
                text = "「${from.label}」の掛け替え先を選んでください",
                color = LABEL,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 44.dp)
                    .background(Color(0xCC1A2127))
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            )
        }

        // Back closes the drawer; the activity swallows it otherwise, so without
        // this the drawer would be a trap
        BackHandler(enabled = drawerOpen) { drawerOpen = false }
        BackHandler(enabled = !drawerOpen && swapFrom != null) { swapFrom = null }

        sheetFor?.let { entry ->
            PanelSheet(
                entry = entry,
                tier = studio.tiers[entry.packageName] ?: IconSource.AUTO,
                onSwap = { sheetFor = null; swapFrom = entry },
                onTier = { next ->
                    sheetFor = null
                    store.setTier(entry.packageName, next)
                    studio.tiers = store.tiers()
                    scope.launch { studio.repaint(entry) }
                },
                onDismiss = { sheetFor = null },
            )
        }

        launching?.let { l ->
            LaunchWipe(
                painting = l.painting,
                label = l.entry.label,
                // only once the screen is covered, so the stroke and the wipe
                // both get seen before the app takes the window
                onCovered = { runCatching { ctx.startActivity(l.entry.launchIntent()) } },
                onDone = { launching = null; drawerOpen = false },
            )
        }
    }
}

/**
 * What long-pressing a panel offers: move it, or paint it a different way.
 *
 * Deliberately two choices and no more. The tier cycle is the escape hatch for
 * when the automatic pick reads badly — a wordmark that turned to mush, or a
 * colour field where the real icon would have been fine.
 */
@Composable
private fun PanelSheet(
    entry: AppEntry,
    tier: Int,
    onSwap: () -> Unit,
    onTier: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val names = mapOf(
        IconSource.AUTO to "自動",
        IconSource.HAND to "手描きのモチーフ",
        IconSource.CONVERT to "アイコンを点描に",
        IconSource.FIELDS to "色面だけ",
    )
    val next = when (tier) {
        IconSource.AUTO -> IconSource.CONVERT
        IconSource.CONVERT -> IconSource.FIELDS
        IconSource.FIELDS -> IconSource.HAND
        else -> IconSource.AUTO
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xB3000000))
            .pointerInput(Unit) { detectTapGestures { onDismiss() } }
    ) {
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xFF1A2127))
                // swallow taps, or the scrim behind would dismiss the sheet
                .pointerInput(Unit) { detectTapGestures { } }
                .padding(22.dp),
        ) {
            Text(entry.label, color = LABEL, fontSize = 17.sp)
            Text(
                "いまの描き方: ${names[tier]}",
                color = LABEL.copy(alpha = 0.6f),
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
            )
            TextButton(onClick = onSwap) { Text("場所を入れ替える", color = LABEL) }
            TextButton(onClick = { onTier(next) }) { Text("描き直す → ${names[next]}", color = LABEL) }
        }
    }
    BackHandler { onDismiss() }
}

// -------------------------------------------------------------------- panels

@Composable
private fun Panel(
    entry: AppEntry,
    painting: Painting?,
    slot: Slot,
    index: Int,
    wallW: Dp,
    wallH: Dp,
    showLabel: Boolean,
    pickedUp: Boolean,
    onLongPress: () -> Unit,
    onOpen: (Painting) -> Unit,
) {
    val side = wallW * slot.size
    Box(
        Modifier
            .offset(x = wallW * slot.x - side / 2, y = wallH * slot.y - side / 2)
            .width(side)
    ) {
        PanelBody(entry, painting, side, slot.rotation(index), showLabel, pickedUp, onLongPress, onOpen)
    }
}

@Composable
private fun PanelBody(
    entry: AppEntry,
    painting: Painting?,
    side: Dp,
    rotation: Float,
    showLabel: Boolean,
    pickedUp: Boolean,
    onLongPress: () -> Unit,
    onOpen: (Painting) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var strokes by remember(entry.packageName) { mutableStateOf(0) }
    val lift by animateFloatAsState(if (pickedUp) 1.12f else 1f, label = "lift")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .size(side)
                .scale(lift)
                .rotate(rotation)
                .pointerInput(entry.packageName, painting) {
                    detectTapGestures(
                        onPress = {
                            strokes++                       // the brush runs on touch down
                            tryAwaitRelease()
                        },
                        onLongPress = { onLongPress() },
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
                BrushStroke(it.colors, strokes, Modifier.fillMaxSize())
            }
        }
        if (showLabel) {
            Text(
                text = entry.label,
                color = LABEL.copy(alpha = if (pickedUp) 1f else 0.68f),
                fontSize = 9.5.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 5.dp),
            )
        }
    }
}

/** The palette the four thumb-reachable apps sit on, as dabs of paint. */
@Composable
private fun PaletteDock(
    dock: List<AppEntry>,
    panels: Map<String, Painting>,
    pickedUp: String?,
    onLongPress: (AppEntry) -> Unit,
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

        Canvas(Modifier.size(boxW, boxH)) {
            drawPath(palettePath(size), Color(0xFF44392B).copy(alpha = 0.55f))
        }
        dock.forEachIndexed { i, entry ->
            val slot = Salon.DOCK.getOrNull(i) ?: return@forEachIndexed
            Panel(
                entry = entry,
                painting = panels[entry.packageName],
                slot = slot,
                index = Salon.WALL.size + i,
                wallW = boxW,
                wallH = boxH,
                showLabel = false,
                pickedUp = pickedUp == entry.packageName,
                onLongPress = { onLongPress(entry) },
                onOpen = { p -> onOpen(entry, p) },
            )
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
        addOval(Rect(Offset(x(66f), y(92f)), minOf(x(15f), y(15f))))
    }
}

// -------------------------------------------------------------------- drawer

/**
 * Everything the wall does not hold. Panels here are painted only as they
 * scroll into view — a phone with 150 apps would otherwise spend a minute
 * painting before showing anything.
 */
@Composable
private fun Drawer(
    apps: List<AppEntry>,
    studio: Studio,
    onLongPress: (AppEntry) -> Unit,
    onOpen: (AppEntry) -> Unit,
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xF20D1215))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 88.dp),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(apps, key = { it.packageName }) { entry ->
                LaunchedEffect(entry.packageName) { studio.paint(entry) }
                BoxWithConstraints {
                    PanelBody(
                        entry = entry,
                        painting = studio.panels[entry.packageName],
                        side = maxWidth,
                        rotation = ((entry.seed % 9) - 4) * 0.5f,
                        showLabel = true,
                        pickedUp = false,
                        onLongPress = { onLongPress(entry) },
                        onOpen = { onOpen(entry) },
                    )
                }
            }
        }
    }
}

// ---------------------------------------------------------------- launch wipe

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
    val sweeps = remember(painting) { WipeBand.build(painting.colors) }
    var showLabel by remember { mutableStateOf(false) }

    LaunchedEffect(painting) {
        t.animateTo(1f, tween(WIPE_MS))
        showLabel = true
        onCovered()
        // the app owns the window now; drop the overlay so coming home shows
        // the wall again rather than a screen still full of paint
        delay(WIPE_HOLD_MS.toLong())
        onDone()
    }

    Box(Modifier.fillMaxSize()) {
        Canvas(Modifier.fillMaxSize()) { drawWipe(sweeps, t.value) }
        if (showLabel) {
            Text(
                text = label,
                color = Color(0xFFF6F1E4),
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

private class WipeBand(
    val yFrac: Float,
    val slope: Float,
    val lag: Float,
    val color: Color,
    val over: Float,
    val tips: FloatArray,
) {
    companion object {
        const val COUNT = 16

        fun build(colors: List<Color>): List<WipeBand> {
            val pal = colors.ifEmpty { listOf(Color(0xFFE6DFCE)) }
            val rnd = Random(29)
            return (0 until COUNT).map { i ->
                WipeBand(
                    yFrac = (i + 0.5f) / COUNT + (rnd.nextFloat() - 0.5f) * 0.3f / COUNT,
                    slope = (rnd.nextFloat() - 0.5f) * 0.07f,
                    lag = (i.toFloat() / COUNT) * 0.34f + rnd.nextFloat() * 0.13f,
                    color = pal[(i * 5 + rnd.nextInt(pal.size)) % pal.size],
                    over = 1f + rnd.nextFloat() * 0.3f,
                    tips = FloatArray(4) { rnd.nextFloat() },
                )
            }
        }
    }
}

private fun DrawScope.drawWipe(bands: List<WipeBand>, t: Float) {
    if (t <= 0f) return
    val w = size.width
    val bh = size.height / WipeBand.COUNT

    for (s in bands) {
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
