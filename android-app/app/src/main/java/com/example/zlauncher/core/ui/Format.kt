package com.example.zlauncher.core.ui

import java.util.Locale
import kotlin.math.abs

/** 桁を揃えたいので Formatter ではなく自前で整形する（"12.3 GB" 形式） */
fun formatBytes(bytes: Long): Pair<String, String> {
    val units = listOf("B", "KB", "MB", "GB", "TB")
    var value = abs(bytes).toDouble()
    var index = 0
    while (value >= 1024 && index < units.lastIndex) {
        value /= 1024
        index++
    }
    val text = when {
        index == 0 -> value.toInt().toString()
        value >= 100 -> String.format(Locale.US, "%.0f", value)
        value >= 10 -> String.format(Locale.US, "%.1f", value)
        else -> String.format(Locale.US, "%.1f", value)
    }
    return text to units[index]
}

/** 稼働時間などの表示。"3d 5h" / "5h 12m" / "12m" */
fun formatDuration(millis: Long): String {
    val totalMinutes = millis / 60_000
    val days = totalMinutes / (60 * 24)
    val hours = (totalMinutes / 60) % 24
    val minutes = totalMinutes % 60
    return when {
        days > 0 -> "${days}d ${hours}h"
        hours > 0 -> "${hours}h ${minutes}m"
        else -> "${minutes}m"
    }
}
