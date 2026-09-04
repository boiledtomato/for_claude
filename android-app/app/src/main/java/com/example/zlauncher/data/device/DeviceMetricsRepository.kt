package com.example.zlauncher.data.device

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Environment
import android.os.StatFs
import android.media.AudioManager
import android.net.TrafficStats
import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 端末の実測値。コンソールを開いている間だけ [REFRESH_INTERVAL_MS] ごとに更新する
 * （Flow の購読が切れればポーリングも止まる）。
 *
 * 取得はすべて runCatching で包む。1 つの値が取れないだけでコンソール全体を
 * 落とさないため。
 */
@Singleton
class DeviceMetricsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // 履歴とレート計算のために採取は 1 本だけ動かす。購読ごとに走らせると差分が二重に出る
    val metrics: StateFlow<DeviceMetrics> = flow {
        while (currentCoroutineContext().isActive) {
            emit(read())
            delay(REFRESH_INTERVAL_MS)
        }
    }.flowOn(Dispatchers.IO)
        .stateIn(scope, SharingStarted.WhileSubscribed(3_000), DeviceMetrics.EMPTY)

    private val rxHistory = ArrayDeque<Float>()
    private val txHistory = ArrayDeque<Float>()
    private val batteryHistory = ArrayDeque<Float>()
    private var lastRxBytes = -1L
    private var lastTxBytes = -1L
    private var lastSampleAt = 0L
    private val deviceInfo: DeviceInfo by lazy { readDeviceInfo() }

    fun read(): DeviceMetrics {
        val battery = readBattery()
        val storage = readStorage()
        val memory = readMemory()
        val network = readNetwork()
        val traffic = readTraffic()
        val audio = readAudio()
        return DeviceMetrics(
            batteryPercent = battery.percent,
            batteryCharging = battery.charging,
            batteryTemperatureC = battery.temperatureC,
            storageUsedBytes = storage.first,
            storageTotalBytes = storage.second,
            memoryUsedBytes = memory.first,
            memoryTotalBytes = memory.second,
            network = network.kind,
            networkValidated = network.validated,
            vpnActive = network.vpn,
            downstreamKbps = network.downstreamKbps,
            uptimeMillis = SystemClock.elapsedRealtime(),
            sampledAtMillis = System.currentTimeMillis(),
            rxBytesTotal = traffic.rxTotal,
            txBytesTotal = traffic.txTotal,
            mobileRxBytes = traffic.mobileRx,
            mobileTxBytes = traffic.mobileTx,
            rxBytesPerSec = traffic.rxPerSec,
            txBytesPerSec = traffic.txPerSec,
            rxHistory = rxHistory.toList(),
            txHistory = txHistory.toList(),
            batteryHistory = batteryHistory.toList(),
            mediaVolumePercent = audio.first,
            ringVolumePercent = audio.second,
            brightnessPercent = readBrightness(),
            info = deviceInfo,
        )
    }

    private data class Traffic(
        val rxTotal: Long,
        val txTotal: Long,
        val mobileRx: Long,
        val mobileTx: Long,
        val rxPerSec: Long,
        val txPerSec: Long,
    )

    /**
     * TrafficStats は端末全体の累計を権限なしで返す（アプリ別は特別な許可が要る）。
     * 前回との差分をとって実測レートにする。UNSUPPORTED(-1) が返る機種もあるので必ず判定する。
     */
    private fun readTraffic(): Traffic = runCatching {
        val rx = TrafficStats.getTotalRxBytes()
        val tx = TrafficStats.getTotalTxBytes()
        if (rx == TrafficStats.UNSUPPORTED.toLong() || tx == TrafficStats.UNSUPPORTED.toLong()) {
            return@runCatching Traffic(0, 0, 0, 0, 0, 0)
        }
        val now = SystemClock.elapsedRealtime()
        val elapsedSec = if (lastSampleAt > 0) ((now - lastSampleAt) / 1000f).coerceAtLeast(0.2f) else 0f
        val rxPerSec = if (lastRxBytes >= 0 && elapsedSec > 0) ((rx - lastRxBytes) / elapsedSec).toLong().coerceAtLeast(0) else 0
        val txPerSec = if (lastTxBytes >= 0 && elapsedSec > 0) ((tx - lastTxBytes) / elapsedSec).toLong().coerceAtLeast(0) else 0
        lastRxBytes = rx
        lastTxBytes = tx
        lastSampleAt = now

        pushHistory(rxHistory, rxPerSec / 1024f)
        pushHistory(txHistory, txPerSec / 1024f)

        val mobileRx = TrafficStats.getMobileRxBytes().coerceAtLeast(0)
        val mobileTx = TrafficStats.getMobileTxBytes().coerceAtLeast(0)
        Traffic(rx, tx, mobileRx, mobileTx, rxPerSec, txPerSec)
    }.getOrDefault(Traffic(0, 0, 0, 0, 0, 0))

    private fun pushHistory(buffer: ArrayDeque<Float>, value: Float) {
        buffer.addLast(value)
        while (buffer.size > HISTORY_SIZE) buffer.removeFirst()
    }

    /** メディア音量 to 着信音量（%） */
    private fun readAudio(): Pair<Int, Int> = runCatching {
        val audio = context.getSystemService<AudioManager>() ?: return@runCatching 0 to 0
        fun percent(stream: Int): Int {
            val max = audio.getStreamMaxVolume(stream)
            return if (max > 0) audio.getStreamVolume(stream) * 100 / max else 0
        }
        percent(AudioManager.STREAM_MUSIC) to percent(AudioManager.STREAM_RING)
    }.getOrDefault(0 to 0)

    private fun readBrightness(): Int = runCatching {
        val raw = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        (raw * 100 / 255).coerceIn(0, 100)
    }.getOrDefault(0)

    private fun readDeviceInfo(): DeviceInfo = runCatching {
        val metrics = context.resources.displayMetrics
        DeviceInfo(
            model = "${Build.MANUFACTURER} ${Build.MODEL}".trim(),
            androidVersion = Build.VERSION.RELEASE.orEmpty(),
            sdkInt = Build.VERSION.SDK_INT,
            screenWidthPx = metrics.widthPixels,
            screenHeightPx = metrics.heightPixels,
            densityDpi = metrics.densityDpi,
            cpuCores = Runtime.getRuntime().availableProcessors(),
        )
    }.getOrDefault(DeviceInfo())

    private data class Battery(val percent: Int, val charging: Boolean, val temperatureC: Float)

    private fun readBattery(): Battery = runCatching {
        // sticky broadcast なので receiver を登録せずその場で読める
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            ?: return@runCatching Battery(0, false, 0f)
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val tenthsC = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
        val percent = if (level >= 0 && scale > 0) (level * 100 / scale) else 0
        pushHistory(batteryHistory, percent.toFloat())
        Battery(
            percent = percent,
            charging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL,
            temperatureC = tenthsC / 10f,
        )
    }.getOrDefault(Battery(0, false, 0f))

    /** used to total */
    private fun readStorage(): Pair<Long, Long> = runCatching {
        val stat = StatFs(Environment.getDataDirectory().path)
        val total = stat.blockCountLong * stat.blockSizeLong
        val available = stat.availableBlocksLong * stat.blockSizeLong
        (total - available) to total
    }.getOrDefault(0L to 0L)

    /** used to total */
    private fun readMemory(): Pair<Long, Long> = runCatching {
        val activityManager = context.getSystemService<ActivityManager>() ?: return@runCatching 0L to 0L
        val info = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(info)
        (info.totalMem - info.availMem) to info.totalMem
    }.getOrDefault(0L to 0L)

    private data class Net(
        val kind: NetworkKind,
        val validated: Boolean,
        val vpn: Boolean,
        val downstreamKbps: Int,
    )

    private fun readNetwork(): Net = runCatching {
        val manager = context.getSystemService<ConnectivityManager>()
            ?: return@runCatching Net(NetworkKind.NONE, false, false, 0)
        val active = manager.activeNetwork ?: return@runCatching Net(NetworkKind.NONE, false, false, 0)
        val caps = manager.getNetworkCapabilities(active)
            ?: return@runCatching Net(NetworkKind.NONE, false, false, 0)
        val kind = when {
            caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkKind.WIFI
            caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkKind.CELLULAR
            caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkKind.ETHERNET
            else -> NetworkKind.OTHER
        }
        Net(
            kind = kind,
            validated = caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED),
            vpn = caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN),
            downstreamKbps = caps.linkDownstreamBandwidthKbps,
        )
    }.getOrDefault(Net(NetworkKind.NONE, false, false, 0))

    private companion object {
        /** トラフィックのレートを見せたいので 1 秒。購読が切れれば止まる */
        const val REFRESH_INTERVAL_MS = 1_000L
        const val HISTORY_SIZE = 40
    }
}
