package com.example.zlauncher.ui.console

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import com.example.zlauncher.domain.model.AppEntry

/**
 * どの枠に落とすかの当たり判定。**Android にも Compose の実行時にも依存しない**ので、
 * そのままテストにかけられる（[Rect] と [Offset] はただの値）。
 */
object DropTargets {

    /**
     * [point] を含む枠の id。[exclude] は持ち上げた元のカテゴリーで、自分自身の上に
     * 落としても何も起きないように外す。
     *
     * 重なった枠は先に登録されたほうを採る。レールの行は重ならないので実際には起きないが、
     * 「たまたま両方に入った」ときに結果が揺れないようにしておく。
     */
    fun hit(targets: Map<String, Rect>, point: Offset, exclude: String?): String? =
        targets.entries.firstOrNull { (id, rect) -> id != exclude && rect.contains(point) }?.key
}

/**
 * アイコンをカテゴリーからカテゴリーへ運んでいる最中の状態。
 *
 * **画面をまたぐので [ConsoleScreen] が持つ。** 掴むのは右のカテゴリー面、落とすのは左の
 * レールで、どちらか一方に閉じ込めると相手の位置が分からない。座標はウィンドウ基準で
 * そろえる ― ペインとレールでは親の座標系が違う。
 */
@Stable
class AppDragState {

    data class Payload(val entry: AppEntry, val fromCategoryId: String)

    /** 落とし先が決まった状態。確認を挟んでから実際に動かす */
    data class Drop(val entry: AppEntry, val fromCategoryId: String, val toCategoryId: String)

    var payload by mutableStateOf<Payload?>(null)
        private set

    /** 指の位置（ウィンドウ基準） */
    var position by mutableStateOf(Offset.Zero)
        private set

    private val targets = mutableStateMapOf<String, Rect>()

    /** いま指が乗っている落とし先。レール側の強調に使う */
    val hovered: String?
        get() = payload?.let { DropTargets.hit(targets, position, exclude = it.fromCategoryId) }

    val isDragging: Boolean get() = payload != null

    fun start(entry: AppEntry, fromCategoryId: String, at: Offset) {
        payload = Payload(entry, fromCategoryId)
        position = at
    }

    fun moveTo(at: Offset) {
        if (payload != null) position = at
    }

    /** 指を離した。落とし先の上なら [Drop] を返す（呼び出し側が確認を出す） */
    fun finish(): Drop? {
        val held = payload ?: return null
        val target = hovered
        payload = null
        return target?.let { Drop(held.entry, held.fromCategoryId, it) }
    }

    fun cancel() {
        payload = null
    }

    /**
     * 落とし先の登録。レールの行が自分の位置を申告する。
     *
     * 未分類を集める枠は**登録しない**（所属を保存していないので、落としても計算で戻る）。
     * 呼び出し側で外す。
     */
    fun setTarget(id: String, bounds: Rect) {
        targets[id] = bounds
    }

    fun removeTarget(id: String) {
        targets.remove(id)
    }
}
