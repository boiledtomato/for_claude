package com.example.critterhome.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/** トレーナーの HP。電池の残量（%）と充電中かどうか */
data class BatteryLevel(val percent: Int = 0, val charging: Boolean = false)

/**
 * 電池の変化。ACTION_BATTERY_CHANGED は sticky なので、登録した瞬間に現在値が 1 度届く。
 * 権限は要らない。
 */
fun batteryLevels(context: Context): Flow<BatteryLevel> = callbackFlow {
    fun read(intent: Intent?): BatteryLevel {
        if (intent == null) return BatteryLevel()
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100)
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val percent = if (level >= 0 && scale > 0) level * 100 / scale else 0
        return BatteryLevel(
            percent = percent,
            charging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL,
        )
    }
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            trySend(read(intent))
        }
    }
    val sticky = ContextCompat.registerReceiver(
        context,
        receiver,
        IntentFilter(Intent.ACTION_BATTERY_CHANGED),
        ContextCompat.RECEIVER_NOT_EXPORTED,
    )
    trySend(read(sticky))
    awaitClose { runCatching { context.unregisterReceiver(receiver) } }
}.distinctUntilChanged()
