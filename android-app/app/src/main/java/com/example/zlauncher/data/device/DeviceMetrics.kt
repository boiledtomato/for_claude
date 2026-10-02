package com.example.zlauncher.data.device

enum class NetworkKind { WIFI, CELLULAR, ETHERNET, OTHER, NONE }

/** 端末の静的な情報。毎回読み直す必要がないものはこちら */
data class DeviceInfo(
    val model: String = "",
    val androidVersion: String = "",
    val sdkInt: Int = 0,
    val screenWidthPx: Int = 0,
    val screenHeightPx: Int = 0,
    val densityDpi: Int = 0,
    val cpuCores: Int = 0,
)

/**
 * 端末から実際に取得できる値だけで構成する。
 * 追加の権限が要るもの（Wi-Fi の SSID、アプリ別通信量、利用時間）はここには入れない。
 */
data class DeviceMetrics(
    val batteryPercent: Int,
    val batteryCharging: Boolean,
    val batteryTemperatureC: Float,
    val storageUsedBytes: Long,
    val storageTotalBytes: Long,
    val memoryUsedBytes: Long,
    val memoryTotalBytes: Long,
    val network: NetworkKind,
    val networkValidated: Boolean,
    val vpnActive: Boolean,
    val downstreamKbps: Int,
    val uptimeMillis: Long,
    val sampledAtMillis: Long,
    /** 端末全体の累計（起動から）。TrafficStats は権限不要で読める */
    val rxBytesTotal: Long = 0,
    val txBytesTotal: Long = 0,
    val mobileRxBytes: Long = 0,
    val mobileTxBytes: Long = 0,
    /** 直近 1 秒の実測レート */
    val rxBytesPerSec: Long = 0,
    val txBytesPerSec: Long = 0,
    /** 直近の推移（古い→新しい）。スパークライン用 */
    val rxHistory: List<Float> = emptyList(),
    val txHistory: List<Float> = emptyList(),
    val batteryHistory: List<Float> = emptyList(),
    val mediaVolumePercent: Int = 0,
    val ringVolumePercent: Int = 0,
    val brightnessPercent: Int = 0,
    val info: DeviceInfo = DeviceInfo(),
) {
    val storageUsedRatio: Float
        get() = if (storageTotalBytes > 0) (storageUsedBytes.toFloat() / storageTotalBytes) else 0f

    val storageFreeBytes: Long get() = (storageTotalBytes - storageUsedBytes).coerceAtLeast(0)

    val memoryUsedRatio: Float
        get() = if (memoryTotalBytes > 0) (memoryUsedBytes.toFloat() / memoryTotalBytes) else 0f

    val memoryFreeBytes: Long get() = (memoryTotalBytes - memoryUsedBytes).coerceAtLeast(0)

    val online: Boolean get() = network != NetworkKind.NONE && networkValidated

    /** Wi-Fi 側は「全体 − モバイル」で求める */
    val wifiRxBytes: Long get() = (rxBytesTotal - mobileRxBytes).coerceAtLeast(0)

    val wifiTxBytes: Long get() = (txBytesTotal - mobileTxBytes).coerceAtLeast(0)

    companion object {
        val EMPTY = DeviceMetrics(
            batteryPercent = 0,
            batteryCharging = false,
            batteryTemperatureC = 0f,
            storageUsedBytes = 0,
            storageTotalBytes = 0,
            memoryUsedBytes = 0,
            memoryTotalBytes = 0,
            network = NetworkKind.NONE,
            networkValidated = false,
            vpnActive = false,
            downstreamKbps = 0,
            uptimeMillis = 0,
            sampledAtMillis = 0,
        )
    }
}
