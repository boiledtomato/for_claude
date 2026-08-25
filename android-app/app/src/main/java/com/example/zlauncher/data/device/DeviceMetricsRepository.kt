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
import android.os.SystemClock
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    val metrics: Flow<DeviceMetrics> = flow {
        while (currentCoroutineContext().isActive) {
            emit(read())
            delay(REFRESH_INTERVAL_MS)
        }
    }.flowOn(Dispatchers.IO).conflate()

    fun read(): DeviceMetrics {
        val battery = readBattery()
        val storage = readStorage()
        val memory = readMemory()
        val network = readNetwork()
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
        )
    }

    private data class Battery(val percent: Int, val charging: Boolean, val temperatureC: Float)

    private fun readBattery(): Battery = runCatching {
        // sticky broadcast なので receiver を登録せずその場で読める
        val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            ?: return@runCatching Battery(0, false, 0f)
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val tenthsC = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
        Battery(
            percent = if (level >= 0 && scale > 0) (level * 100 / scale) else 0,
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
        const val REFRESH_INTERVAL_MS = 2_000L
    }
}
