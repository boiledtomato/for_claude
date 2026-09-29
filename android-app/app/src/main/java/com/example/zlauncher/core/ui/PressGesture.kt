package com.example.zlauncher.core.ui

/**
 * 指を置いたあとの分かれ道。**Compose に依存しない**ので、そのままテストにかけられる。
 *
 * 同じアイコンに 3 つの意味を載せている:
 *
 * | 操作 | 結果 |
 * |---|---|
 * | 押してすぐ離す | 起動 |
 * | 押したまま動かさない | 取り外しモード |
 * | 押してそのまま横に動かす | 別カテゴリーへの移動 |
 * | 押してそのまま縦に動かす | 一覧の巻き取り（ここでは何も起きない） |
 *
 * 判定は**競争**として書く ― 長押しの時間が来るのが先か、指がスロップを越えるのが先か。
 * 「長押ししてから動かす」にすると移動のたびに 0.5 秒待たされ、「動いたら常にドラッグ」に
 * すると取り外しモードに入れなくなる。
 */
object PressGesture {

    enum class Outcome {
        /** まだ決まらない。指も離れていないし、どちらの条件にも届いていない */
        PENDING,
        TAP,
        LONG_PRESS,
        DRAG,
    }

    /**
     * @param movedPx 押した位置からの**横**の距離。縦は一覧の巻き取りに残すので見ない
     * @param elapsedMs 押してからの経過
     * @param lifted 指が離れたか
     * @param touchSlopPx これを越えたら「動かした」とみなす幅（端末ごとに違う）
     * @param longPressTimeoutMs これを過ぎたら「長押し」とみなす時間（端末ごとに違う）
     */
    fun decide(
        movedPx: Float,
        elapsedMs: Long,
        lifted: Boolean,
        touchSlopPx: Float,
        longPressTimeoutMs: Long,
    ): Outcome = when {
        // 時間内に動いた ＝ 運ぶつもり。長押しの成立を待たない
        movedPx > touchSlopPx && elapsedMs < longPressTimeoutMs -> Outcome.DRAG
        // 置いたまま時間切れ ＝ その場の操作。指が離れていないことが条件
        !lifted && elapsedMs >= longPressTimeoutMs -> Outcome.LONG_PRESS
        // どちらにも届かないまま離した ＝ ただのタップ
        lifted -> Outcome.TAP
        else -> Outcome.PENDING
    }
}
