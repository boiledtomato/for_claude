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
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
/** 蔦が伸びきるまで。葉が 1 枚ずつ開く間があるので、短いと見えない。 */
private const val REACH_GROW_MS = 720

/** 伸びきってから咲きはじめるまでの間。先端の蕾を見せる一拍。 */
private const val REACH_HOLD_MS = 220L

/** 蕾がほどけて咲くまで。18 コマを送る。 */
private const val REACH_BLOOM_MS = 620

private const val BOIL_FPS = 11

/** 咲いた花を 1 つ送るのに要る横移動（px）。 */
private const val ROTATE_STEP_PX = 90f

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val store = remember { BindingStore(context) }

    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.roundToPx()
    }

    // 折りたたみ端末では、閉じた表画面と開いた内側画面で割り当てを分ける。
    val displayScope = BindingStore.Scope.of(LocalConfiguration.current.screenWidthDp)

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

    // 画面の区分が変わったら（折りたたみを開閉したら）割り当てを読み直す。
    LaunchedEffect(displayScope) {
        bindings.clear()
        bindings.putAll(store.load(displayScope))
        flora = FloraLoader.load(context, screenWidthPx)
        val slots = flora.bindable
        if (apps.isNotEmpty() && slots.isNotEmpty()) {
            bindings.clear()
            bindings.putAll(store.seedIfNeeded(displayScope, "flora", apps, slots))
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        scope.launch {
            val loaded = AppRepository.load(context)
            apps = loaded
            val slots = flora.bindable
            if (slots.isNotEmpty()) {
                bindings.clear()
                bindings.putAll(store.seedIfNeeded(displayScope, "flora", loaded, slots))
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
    // 咲いた花の並びをいくつずらして見せるか。登録数が咲かせられる数を
    // 超えたとき、指で回して隠れているアプリを出すのに使う。
    var fanOffset by remember { mutableIntStateOf(0) }
    val reachGrow = remember { Animatable(0f) }
    val reachBloom = remember { Animatable(0f) }

    fun clearReach() {
        reach = null
        fanOffset = 0
        scope.launch {
            reachGrow.snapTo(0f)
            reachBloom.snapTo(0f)
        }
    }

    fun startReach(target: TapTarget, keys: List<String>, launchWhenOpen: Boolean) {
        // 咲いた花が画面からはみ出さない向きを選ぶ。押した位置だけで
        // 向きを決めると、画面端の部位から伸ばしたとき花が見切れる。
        val tl = transform.toScene(Offset.Zero)
        val br = transform.toScene(Offset(canvasSize.width, canvasSize.height))
        val bloomSize = bloomSizeOf(flora)
        reach = fittingReach(
            originId = target.id,
            origin = target.at,
            seed = target.id.hashCode(),
            appKeys = keys,
            baseLength = flora.width * 0.42f,
            bloomSize = bloomSize,
            flowerRadius = bloomSize * 1.15f,
            bounds = Rect(tl.x, tl.y, br.x, br.y),
        )
        scope.launch {
            fanOffset = 0
            reachGrow.snapTo(0f)
            reachBloom.snapTo(0f)
            // 蔦が這い、葉を開きながら伸びる。急がせると「線が飛んだ」だけに見える。
            reachGrow.animateTo(1f, tween(REACH_GROW_MS, easing = FastOutSlowInEasing))
            // 伸びきったところで一拍おく。ここで先端の蕾が見える。
            // 間を置かずに咲かせると、蕾があったことに気づけない。
            delay(REACH_HOLD_MS)
            reachBloom.animateTo(1f, tween(REACH_BLOOM_MS, easing = FastOutSlowInEasing))
            if (launchWhenOpen) {
                delay(180)
                keys.firstOrNull()?.let { k -> appsByKey[k]?.let { AppRepository.launch(context, it) } }
                delay(240)
                clearReach()
            }
        }
    }

    fun assign(target: TapTarget) {
        // 部位そのものの絵を渡す。「どこに登録しているか」は名前より絵が早い。
        val sprite = flora.organs.firstOrNull { it.id == target.id }?.bitmap
            ?: flora.gemma?.frames?.lastOrNull()?.bitmap
        drawerMode = DrawerMode.Assign(
            target.id, target.label, bindings[target.id].orEmpty(), sprite,
        )
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
            fanOffset = { fanOffset },
            bindings = bindings,
            appsByKey = appsByKey,
            showLabels = showLabels,
            showHitAreas = showHitAreas,
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .onSizeChanged { canvasSize = Size(it.width.toFloat(), it.height.toFloat()) }
                // 咲いた花を回して、隠れているアプリを出す。
                //
                // 登録数が一度に咲かせられる数（5）を超えると、残りは
                // どこにも出てこない。横に滑らせて並びをずらす。
                .pointerInput(reach) {
                    val open = reach
                    if (open == null || !open.rotatable) return@pointerInput
                    var acc = 0f
                    detectHorizontalDragGestures(
                        onDragStart = { acc = 0f },
                        onHorizontalDrag = { change, dx ->
                            acc += dx
                            while (acc >= ROTATE_STEP_PX) {
                                fanOffset -= 1; acc -= ROTATE_STEP_PX
                            }
                            while (acc <= -ROTATE_STEP_PX) {
                                fanOffset += 1; acc += ROTATE_STEP_PX
                            }
                            change.consume()
                        },
                    )
                }
                .pointerInput(transform, flora, reach) {
                    detectTapGestures(
                        onTap = { p ->
                            val open = reach
                            // 咲いている花のどれかを押したら、そのアプリを起動
                            if (open != null && open.appKeys.size > 1) {
                                val picked = pickFlower(open, transform, p, bloomSizeOf(flora))
                                if (picked != null) {
                                    open.appAt(picked, fanOffset)
                                        ?.let { appsByKey[it] }
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
                            store.put(displayScope, m.organId, keys)
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
                        store.remove(displayScope, id)
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

/** 咲いた花の大きさ。描画と判定で同じ値を使うための 1 か所。 */
private fun bloomSizeOf(flora: Flora): Float = (flora.vine?.size ?: 120f) * 1.22f

/**
 * 咲いている花のどれを押したか。
 *
 * [size] は描画側と同じ値を渡すこと。別の値を置くと、判定の円が絵からずれて
 * 「花を押しているのに反応しない」ことになる（以前は判定だけ 108 固定で、
 * 絵は vine.size * 1.22 ＝ 約 153 だった）。
 */
private fun pickFlower(reach: Reach, t: SceneTransform, p: Offset, size: Float): Int? {
    var best: Int? = null
    var bestD = Float.MAX_VALUE
    for (i in reach.tips.indices) {
        val c = t.toScreen(reach.bloomCentre(i, size))
        val d = hypot(p.x - c.x, p.y - c.y)
        if (d <= size * 0.72f * t.scale && d < bestD) {
            bestD = d
            best = i
        }
    }
    return best
}
