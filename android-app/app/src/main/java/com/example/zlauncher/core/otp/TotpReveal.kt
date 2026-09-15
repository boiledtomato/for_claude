package com.example.zlauncher.core.otp

/**
 * ホーム画面に置いたコードを「いつまで出すか」「次にいつ描き直すか」。
 *
 * ウィジェットは常に見えている場所なので、コードを出しっぱなしにしない。タップしてから
 * [WINDOW_MILLIS] のあいだだけ出し、そのあいだにコードが切り替わったら描き直す。
 *
 * 描き直す時刻は「窓の終わり」と「今のコードが切れる時刻」の早いほうになる。前者だけを
 * 見ていると、窓の途中で切り替わった古いコードをそのまま出し続けてしまう ― 認証側は
 * 普通 1 つ前まで通すので、気付かないまま外れる場面が出る。
 */
object TotpReveal {

    /** タップしてからコードを出しておく長さ。ちょうど 1 周期ぶん */
    const val WINDOW_MILLIS = 30_000L

    fun windowEnd(nowMillis: Long): Long = nowMillis + WINDOW_MILLIS

    fun isRevealed(nowMillis: Long, windowEndMillis: Long): Boolean = nowMillis < windowEndMillis

    /** 今のコードが切れる時刻。ちょうど境目にいるときは、その場ではなく次の境目を返す */
    fun codeExpiresAt(nowMillis: Long, periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS): Long {
        require(periodSeconds > 0) { "period must be positive" }
        val period = periodSeconds * 1000L
        return nowMillis + (period - Math.floorMod(nowMillis, period))
    }

    /**
     * 次に描き直す時刻。窓が終わっていれば null（もう出さない）。
     *
     * 呼び出し側はここへアラームを置き、起きたらまた同じ判断をする。1 本のアラームを
     * 使い回すので、30 秒ごとに予定を積み上げない。
     */
    fun nextTickAt(
        nowMillis: Long,
        windowEndMillis: Long,
        periodSeconds: Int = Totp.DEFAULT_PERIOD_SECONDS,
    ): Long? {
        if (!isRevealed(nowMillis, windowEndMillis)) return null
        return minOf(windowEndMillis, codeExpiresAt(nowMillis, periodSeconds))
    }
}
