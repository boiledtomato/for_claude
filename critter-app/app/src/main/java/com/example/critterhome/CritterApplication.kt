package com.example.critterhome

import android.app.Application
import com.example.critterhome.data.AppsRepository
import com.example.critterhome.data.GameStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * 依存はここで 1 回だけ組み立てる。画面は 1 枚、部品も数個なので DI ライブラリは使わない。
 */
class CritterApplication : Application() {
    private val scope = CoroutineScope(SupervisorJob())

    val apps: AppsRepository by lazy { AppsRepository(this, scope) }
    val game: GameStore by lazy { GameStore(this) }
}
