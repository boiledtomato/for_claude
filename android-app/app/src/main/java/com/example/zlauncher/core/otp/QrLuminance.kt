package com.example.zlauncher.core.otp

/**
 * カメラの輝度面（YUV の Y だけ）を、読み取りにかけられる形に整える。
 *
 * ここを Android から切り離してあるのは、**端末がないと確かめられない部分を減らす**ため。
 * 実機で撮った絵で初めて分かる不具合の大半は、この 2 つの取り違えから出る:
 *
 * - **行の余白（rowStride）。** カメラは幅より広い間隔で 1 行を並べることがある。
 *   詰め直さずに読むと、絵が斜めにずれて QR の升目が崩れる
 * - **向き。** 画面が縦でもカメラの絵は横で出てくる。zxing の QR 読み取りは多少の
 *   傾きなら通すが、90 度回ったままだと落ちる率が上がる
 */
object QrLuminance {

    /** 行の余白を落として、幅 × 高さちょうどの並びにする */
    fun pack(source: ByteArray, width: Int, height: Int, rowStride: Int): ByteArray {
        require(width > 0 && height > 0) { "empty image" }
        require(rowStride >= width) { "row stride cannot be narrower than the image" }
        if (rowStride == width && source.size == width * height) return source

        val out = ByteArray(width * height)
        for (row in 0 until height) {
            val from = row * rowStride
            if (from + width > source.size) break
            System.arraycopy(source, from, out, row * width, width)
        }
        return out
    }

    /**
     * 時計回りに [degrees] だけ回す。返すのは回したあとの並びで、90/270 では幅と高さが入れ替わる。
     * 0・90・180・270 以外は受け取らない ― カメラが返すのはこの 4 つだけで、
     * 中途半端な角度を黙って丸めると、読めない理由が分からなくなる。
     */
    fun rotate(source: ByteArray, width: Int, height: Int, degrees: Int): Rotated {
        require(source.size >= width * height) { "buffer is smaller than the image" }
        return when (((degrees % 360) + 360) % 360) {
            0 -> Rotated(source, width, height)

            90 -> {
                val out = ByteArray(width * height)
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        out[x * height + (height - 1 - y)] = source[y * width + x]
                    }
                }
                Rotated(out, height, width)
            }

            180 -> {
                val out = ByteArray(width * height)
                val last = width * height - 1
                for (i in 0 until width * height) out[last - i] = source[i]
                Rotated(out, width, height)
            }

            270 -> {
                val out = ByteArray(width * height)
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        out[(width - 1 - x) * height + y] = source[y * width + x]
                    }
                }
                Rotated(out, height, width)
            }

            else -> throw IllegalArgumentException("unsupported rotation: $degrees")
        }
    }

    data class Rotated(val bytes: ByteArray, val width: Int, val height: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Rotated) return false
            return width == other.width && height == other.height && bytes.contentEquals(other.bytes)
        }

        override fun hashCode(): Int = (width * 31 + height) * 31 + bytes.contentHashCode()
    }
}
