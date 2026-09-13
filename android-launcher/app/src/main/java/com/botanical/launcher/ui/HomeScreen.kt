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
import com.botanical.launcher.flora.Flora
import com.botanical.launcher.flora.FloraLoader
import com.botanical.launcher.flora.TapTarget
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.hypot

/** 風の基準周期。葉や花はこの整数倍の速さで揺れる。 */
private const val SWAY_PERIOD_MS = 22_000

/** 描き直しの速さ。手描きアニメと同じで、滑らかにしすぎると CG に見える。 */
private const val BOIL_FPS = 11

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = remember { BindingStore(context) }

    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.roundToPx()
    }

    var flora by remember { mutableStateOf(Flora.Empty) }
    var apps by remember { mutableStateOf<List<AppEntry>>(emptyList()) }
    val appsByKey = remember(apps) { apps.associateBy { it.key } }
    val bindings = remember { mutableStateMapOf<String, List<String>>() }

    var canvasSize by remember { mutableStateOf(Size.Zero) }
    val transform = remember(canvasSize, flora) {
        SceneTransform.fit(canvasSize, flora.width, flora.height)
    }

    var drawerMode by remember { mutableStateOf<DrawerMode?>(null) }
    var showSettings by remember { mutableStateOf(false) }
    var showLabels by remember { mutableStateOf(store.captionsVisible) }
    var showHitAreas by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        bindings.putAll(store.load())
        flora = FloraLoader.load(context, screenWidthPx)
        val slots = flora.bindable
        if (apps.isNotEmpty() && slots.isNotEmpty()) {
            bindings.clear()
            bindings.putAll(store.seedIfNeeded("flora", apps, slots))
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        scope.launch {
            val loaded = AppRepository.load(context)
            apps = loaded
            val slots = flora.bindable
            if (slots.isNotEmpty()) {
                bindings.clear()
                bindings.putAll(store.seedIfNeeded("flora", loaded, slots))
            }
        }
    }

    // 風の位相は滑らかに、線の描き直しは 11 コマ / 秒
    val phase by rememberInfiniteTransition(label = "wind").animateFloat(
        initialValue = 0f,
        targetValue = (2 * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(SWAY_PERIOD_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "phase",
    )
    var boil by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L / BOIL_FPS)
            boil++
        }
    }

    // 蕾の開き（アプリ一覧）
    val bloom = remember { Animatable(0f) }
    val drawerOpen = drawerMode != null
    LaunchedEffect(drawerOpen) {
        bloom.animateTo(
            targetValue = if (drawerOpen) 1f else 0f,
            animationSpec = tween(if (drawerOpen) 700 else 420, easing = FastOutSlowInEasing),
        )
    }

    // アプリを開くときに伸びる蔓
    var reach by remember { mutableStateOf<Reach?>(null) }
    val reachGrow = remember { Animatable(0f) }
    val reachBloom = remember { Animatable(0f) }

    fun clearReach() {
        reach = null
        scope.launch {
            reachGrow.snapTo(0f)
            reachBloom.snapTo(0f)
        }
    }

    fun startReach(target: TapTarget, keys: List<String>, launchWhenOpen: Boolean) {
        val heading = if (target.at.x < flora.width / 2f) -142f else -38f
        reach = Reach(
            originId = target.id,
            origin = target.at,
            heading = heading,
            length = flora.width * 0.34f,
            seed = target.id.hashCode(),
            appKeys = keys,
        )
        scope.launch {
            reachGrow.snapTo(0f)
            reachBloom.snapTo(0f)
            reachGrow.animateTo(1f, tween(460, easing = FastOutSlowInEasing))
            reachBloom.animateTo(1f, tween(380, easing = FastOutSlowInEasing))
            if (launchWhenOpen) {
                keys.firstOrNull()?.let { k -> appsByKey[k]?.let { AppRepository.launch(context, it) } }
                delay(240)
                clearReach()
            }
        }
    }

    fun assign(target: TapTarget) {
        drawerMode = DrawerMode.Assign(target.id, target.label, bindings[target.id].orEmpty())
    }

    fun activate(target: TapTarget) {
        if (target.id == flora.gemma?.id) {
            drawerMode = DrawerMode.Browse
            return
        }
        val keys = bindings[target.id].orEmpty().filter { appsByKey.containsKey(it) }
        when {
            keys.isEmpty() -> assign(target)
            // 1 つなら伸びた蔓の先で花が開き、そのまま起動する
            keys.size == 1 -> startReach(target, keys, launchWhenOpen = true)
            // 複数なら枝分かれして並ぶので、どれかを選んでもらう
            else -> startReach(target, keys, launchWhenOpen = false)
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        drawerMode = null
        clearReach()
    }

    Box(Modifier.fillMaxSize()) {
        FloraCanvas(
            flora = flora,
            transform = transform,
            phase = { phase },
            boil = { boil },
            bloom = { bloom.value },
            reach = { reach },
            reachGrow = { reachGrow.value },
            reachBloom = { reachBloom.value },
            bindings = bindings,
            appsByKey = appsByKey,
            showLabels = showLabels,
            showHitAreas = showHitAreas,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .onSizeChanged { canvasSize = Size(it.width.toFloat(), it.height.toFloat()) }
                .pointerInput(transform, flora, reach) {
                    detectTapGestures(
                        onTap = { p ->
                            val open = reach
                            // 咲いている花のどれかを押したら、そのアプリを起動
                            if (open != null && open.appKeys.size > 1) {
                                val picked = pickFlower(open, transform, p)
                                if (picked != null) {
                                    appsByKey[open.appKeys[picked]]
                                        ?.let { AppRepository.launch(context, it) }
                                    clearReach()
                                    return@detectTapGestures
                                }
                                clearReach()
                                return@detectTapGestures
                            }
                            hitTest(flora, transform, p)?.let { activate(it) }
                        },
                        onLongPress = { p ->
                            clearReach()
                            val hit = hitTest(flora, transform, p)
                            if (hit == null) {
                                showSettings = true
                            } else if (hit.id == flora.gemma?.id) {
                                drawerMode = DrawerMode.Browse
                            } else {
                                assign(hit)
                            }
                        },
                    )
                },
        )

        val mode = drawerMode
        if (bloom.value > 0.004f) {
            val budAnchor = remember(transform, flora) {
                flora.gemma?.let { transform.toScreen(it.hit.at) } ?: Offset.Zero
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
                                (budAnchor.x / size.width).coerceIn(0f, 1f),
                                (budAnchor.y / size.height).coerceIn(0f, 1f),
                            )
                        }
                    },
            ) {
                AppDrawer(
                    apps = apps,
                    mode = mode ?: DrawerMode.Browse,
                    onLaunch = { app ->
                        AppRepository.launch(context, app)
                        drawerMode = null
                    },
                    onConfirmAssign = { keys ->
                        (mode as? DrawerMode.Assign)?.let { m ->
                            store.put(m.organId, keys)
                            if (keys.isEmpty()) bindings.remove(m.organId)
                            else bindings[m.organId] = keys
                        }
                        drawerMode = null
                    },
                    onAppInfo = { AppRepository.openAppInfo(context, it) },
                    onDismiss = { drawerMode = null },
                )
            }
        }

        if (showSettings) {
            SettingsDialog(
                labelsVisible = showLabels,
                onToggleLabels = {
                    showLabels = it
                    store.captionsVisible = it
                },
                hitAreasVisible = showHitAreas,
                onToggleHitAreas = { showHitAreas = it },
                onClearAll = {
                    flora.bindable.forEach { id ->
                        store.remove(id)
                        bindings.remove(id)
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

    BackHandler(enabled = drawerOpen || reach != null) {
        if (reach != null) clearReach() else drawerMode = null
    }
}

/**
 * 画面座標から器官を引く。
 *
 * 器官は風で動いているが、振れ幅は判定円の半径に比べて十分小さい。
 * 静止位置で判定して体感上の破綻はない。
 */
private fun hitTest(flora: Flora, t: SceneTransform, p: Offset): TapTarget? {
    if (t.scale <= 0f) return null
    val q = t.toScene(p)
    var best: TapTarget? = null
    var bestD = Float.MAX_VALUE
    for (target in flora.tapTargets) {
        val d = hypot(q.x - target.at.x, q.y - target.at.y)
        if (d <= target.radius && d < bestD) {
            bestD = d
            best = target
        }
    }
    return best
}

/** 咲いている花のどれを押したか。 */
private fun pickFlower(reach: Reach, t: SceneTransform, p: Offset): Int? {
    val size = 108f
    var best: Int? = null
    var bestD = Float.MAX_VALUE
    for (i in reach.tips.indices) {
        val c = t.toScreen(reach.flowerCentre(i, size))
        val d = hypot(p.x - c.x, p.y - c.y)
        if (d <= size * 0.7f * t.scale && d < bestD) {
            bestD = d
            best = i
        }
    }
    return best
}
