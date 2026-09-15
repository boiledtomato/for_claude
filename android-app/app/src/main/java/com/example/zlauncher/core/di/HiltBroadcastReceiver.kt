package com.example.zlauncher.core.di

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * `@AndroidEntryPoint` を付けた BroadcastReceiver の土台。
 *
 * Hilt は注入を「生成した親クラスの `onReceive`」で行うので、子は必ず
 * `super.onReceive()` を通さないと注入されない。ところが [BroadcastReceiver.onReceive] は
 * 抽象メソッドで、Kotlin からは super 呼び出しができない（Hilt が親クラスを差し替えるのは
 * Kotlin のコンパイルより後）。中身の無い実装を 1 枚挟むと呼べるようになる。
 * Hilt の文書が Kotlin 向けに案内しているやり方そのまま。
 */
abstract class HiltBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) = Unit
}
