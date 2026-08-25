package com.example.zlauncher.data.device

enum class NetworkKind { WIFI, CELLULAR, ETHERNET, OTHER, NONE }

/**
 * 端末から実際に取得できる値だけで構成する。
 * 追加の権限が要るもの（Wi-Fi の SSID、通信量、利用時間）はここには入れない。
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
) {
    val storageUsedRatio: Float
        get() = if (storageTotalBytes > 0) (storageUsedBytes.toFloat() / storageTotalBytes) else 0f

    val storageFreeBytes: Long get() = (storageTotalBytes - storageUsedBytes).coerceAtLeast(0)

    val memoryUsedRatio: Float
        get() = if (memoryTotalBytes > 0) (memoryUsedBytes.toFloat() / memoryTotalBytes) else 0f

    val memoryFreeBytes: Long get() = (memoryTotalBytes - memoryUsedBytes).coerceAtLeast(0)

    val online: Boolean get() = network != NetworkKind.NONE && networkValidated

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
