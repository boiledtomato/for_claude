package com.botanical.launcher.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.botanical.launcher.data.AppEntry
import com.botanical.launcher.data.AppRepository
import com.botanical.launcher.data.BindingStore
import com.botanical.launcher.garden.OrganSpot
import com.botanical.launcher.garden.Plate
import com.botanical.launcher.garden.PlantLayer
import com.botanical.launcher.garden.PlateCatalog
import com.botanical.launcher.garden.PlateLoader
import kotlinx.coroutines.launch
import kotlin.math.PI

/** 撓みの周期。長めにとって「気づくと動いている」程度にする。 */
private const val SWAY_PERIOD_MS = 13_000

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = remember { BindingStore(context) }

    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.roundToPx()
    }

    var catalog by remember { mutableStateOf(PlateCatalog.Empty) }
    var plateId by remember { mutableStateOf(store.selectedPlateId ?: "") }
    var plate by remember { mutableStateOf(Plate.Empty) }
    var apps by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    val appsByKey = remember(apps) { apps.associateBy { it.key } }
    val bindings = remember { mutableStateMapOf<String, String>() }

    var canvasSize by remember { mutableStateOf(Size.Zero) }
    val transform = remember(canvasSize, plate) {
        SceneTransform.fit(canvasSize, plate.width, plate.height)
    }

    var drawerMode by remember { mutableStateOf<DrawerMode?>(null) }
    var showSettings by remember { mutableStateOf(false) }
    var showCaptions by remember { mutableStateOf(store.captionsVisible) }
    var showHitAreas by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        bindings.putAll(store.load())
        catalog = PlateLoader.loadCatalog(context)
        plateId = catalog.resolve(store.selectedPlateId)
    }

    // 図版が決まったら（切り替えたときも）読み直す
    LaunchedEffect(plateId) {
        if (plateId.isBlank()) return@LaunchedEffect
        plate = PlateLoader.load(context, plateId, screenWidthPx)
        val slots = plate.bindableOrgans.map { it.second.id }
        if (apps.isNotEmpty() && slots.isNotEmpty()) {
            bindings.clear()
            bindings.putAll(store.seedIfNeeded(plateId, apps, slots))
        }
    }

    // インストール・アンインストールを拾うため、前面に戻るたびに読み直す
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        scope.launch {
            val loaded = AppRepository.load(context)
            apps = loaded
            val slots = plate.bindableOrgans.map { it.second.id }
            if (plateId.isNotBlank() && slots.isNotEmpty()) {
                bindings.clear()
                bindings.putAll(store.seedIfNeeded(plateId, loaded, slots))
            }
        }
    }

    // アプリを起動したら一覧は畳んでおく（戻ってきたとき版面から始まる）
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) { drawerMode = null }

    val swayPhase by rememberInfiniteTransition(label = "sway").animateFloat(
        initialValue = 0f,
        targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(SWAY_PERIOD_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "phase",
    )

    val bloom = remember { Animatable(0f) }
    val drawerOpen = drawerMode != null
    LaunchedEffect(drawerOpen) {
        bloom.animateTo(
            targetValue = if (drawerOpen) 1f else 0f,
            animationSpec = tween(if (drawerOpen) 680 else 420, easing = FastOutSlowInEasing),
        )
    }

    // タップされた株を一度だけ大きく揺らす
    var nudgedPlantId by remember { mutableStateOf<String?>(null) }
    var nudgeTick by remember { mutableIntStateOf(0) }
    val nudge = remember { Animatable(0f) }
    LaunchedEffect(nudgeTick) {
        if (nudgeTick == 0) return@LaunchedEffect
        nudge.snapTo(1f)
        nudge.animateTo(0f, tween(900, easing = FastOutSlowInEasing))
    }

    fun activate(layer: PlantLayer, organ: OrganSpot) {
        nudgedPlantId = layer.id
        nudgeTick++
        if (organ.id == plate.gemma?.organId) {
            drawerMode = DrawerMode.Browse
            return
        }
        val app = bindings[organ.id]?.let { appsByKey[it] }
        if (app != null) {
            AppRepository.launch(context, app)
        } else {
            drawerMode = DrawerMode.Assign(organ.id, organ.label)
        }
    }

    Box(Modifier.fillMaxSize()) {
        PlateCanvas(
            plate = plate,
            transform = transform,
            phase = { swayPhase },
            bloom = { bloom.value },
            nudgedPlantId = { nudgedPlantId },
            nudgeAmount = { nudge.value },
            bindings = bindings,
            appsByKey = appsByKey,
            showCaptions = showCaptions,
            showHitAreas = showHitAreas,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .onSizeChanged { canvasSize = Size(it.width.toFloat(), it.height.toFloat()) }
                .pointerInput(transform, plate) {
                    detectTapGestures(
                        onTap = { p ->
                            hitTest(plate, transform, p)?.let { (layer, organ) ->
                                activate(layer, organ)
                            }
                        },
                        onLongPress = { p ->
                            val hit = hitTest(plate, transform, p)
                            if (hit == null) {
                                showSettings = true
                            } else {
                                val (layer, organ) = hit
                                nudgedPlantId = layer.id
                                nudgeTick++
                                drawerMode = if (organ.id == plate.gemma?.organId) {
                                    DrawerMode.Browse
                                } else {
                                    DrawerMode.Assign(organ.id, organ.label)
                                }
                            }
                        },
                    )
                },
        )

        // 一覧は蕾の位置から咲き広がるように拡大して現れる
        val mode = drawerMode
        if (bloom.value > 0.004f) {
            val budAnchor = remember(transform, plate) {
                plate.gemma?.rect?.center?.let { transform.toScreen(it) } ?: Offset.Zero
            }
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val s = 0.16f + 0.84f * bloom.value
                        scaleX = s
                        scaleY = s
                        alpha = (bloom.value * 1.8f).coerceAtMost(1f)
                        if (size.width > 0f && size.height > 0f) {
                            transformOrigin = TransformOrigin(
                                pivotFractionX = (budAnchor.x / size.width).coerceIn(0f, 1f),
                                pivotFractionY = (budAnchor.y / size.height).coerceIn(0f, 1f),
                            )
                        }
                    },
            ) {
                AppDrawer(
                    apps = apps,
                    mode = mode ?: DrawerMode.Browse,
                    onPick = { app ->
                        when (val m = mode) {
                            is DrawerMode.Assign -> {
                                store.put(m.organId, app.key)
                                bindings[m.organId] = app.key
                                drawerMode = null
                            }
                            else -> {
                                AppRepository.launch(context, app)
                                drawerMode = null
                            }
                        }
                    },
                    onClearBinding = {
                        (mode as? DrawerMode.Assign)?.let {
                            store.remove(it.organId)
                            bindings.remove(it.organId)
                        }
                        drawerMode = null
                    },
                    onAppInfo = { AppRepository.openAppInfo(context, it) },
                    onDismiss = { drawerMode = null },
                )
            }
        }

        if (showSettings) {
            PlateSettingsDialog(
                plates = catalog.plates,
                selectedPlateId = plateId,
                onSelectPlate = { id ->
                    if (id != plateId) {
                        store.selectedPlateId = id
                        plateId = id
                    }
                    showSettings = false
                },
                captionsVisible = showCaptions,
                onToggleCaptions = {
                    showCaptions = it
                    store.captionsVisible = it
                },
                hitAreasVisible = showHitAreas,
                onToggleHitAreas = { showHitAreas = it },
                onClearAll = {
                    plate.bindableOrgans.forEach { (_, organ) ->
                        store.remove(organ.id)
                        bindings.remove(organ.id)
                    }
                    showSettings = false
                },
                onOpenHomeSettings = {
                    AppRepository.openHomeSettings(context)
                    showSettings = false
                },
                onDismiss = { showSettings = false },
            )
        }
    }

    BackHandler(enabled = drawerOpen) { drawerMode = null }
}

/**
 * 画面座標から器官を引く。
 *
 * 器官は撓んで動いているが、振れ幅は版面幅の 1.5% ほどで、判定円の半径に比べて
 * 十分小さい。静止位置で判定して体感上の破綻はない。
 */
private fun hitTest(
    plate: Plate,
    transform: SceneTransform,
    screenPoint: Offset,
): Pair<PlantLayer, OrganSpot>? {
    if (transform.scale <= 0f) return null
    val p = transform.toScene(screenPoint)
    var best: Pair<PlantLayer, OrganSpot>? = null
    var bestDistance = Float.MAX_VALUE
    for ((layer, organ) in plate.tapTargets) {
        val c = organ.center(layer)
        val r = organ.radius(layer)
        val dx = p.x - c.x
        val dy = p.y - c.y
        val d2 = dx * dx + dy * dy
        if (d2 <= r * r && d2 < bestDistance) {
            bestDistance = d2
            best = layer to organ
        }
    }
    return best
}
