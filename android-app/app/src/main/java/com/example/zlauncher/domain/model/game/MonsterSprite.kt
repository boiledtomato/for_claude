package com.example.zlauncher.domain.model.game

import kotlin.random.Random

/**
 * 相棒のドット絵を種から作る。画像ファイルは持たない。
 *
 * 左半分を乱数で塗って左右に写すと、それだけで「生き物っぽい」形になる。中心に近いほど
 * 塗る確率を上げて塊にし、中心から繋がっていない欠片は捨てる ― 宙に浮いた 1 マスがあると
 * 生き物ではなくノイズに見える。最後に塗ったマスの周りへ輪郭を回す。
 *
 * 進化の段階で格子が大きくなる（[sizeFor]）。輪郭を回す 1 マスの余白を含んだ大きさ。
 */
object MonsterSprite {
    const val EMPTY = 0
    const val BODY = 1
    const val OUTLINE = 2
    const val EYE = 3
    const val ACCENT = 4

    fun sizeFor(stage: Int): Int = when (stage.coerceIn(0, 2)) {
        0 -> 10
        1 -> 12
        else -> 14
    }

    /** 行の配列。`grid[row][col]` */
    fun pixels(seed: String, stage: Int): List<IntArray> {
        val size = sizeFor(stage)
        val inner = size - 2
        val half = inner / 2
        val rng = Random(MonsterSpecies.seedOf(seed) * 31 + stage)
        val grid = List(size) { IntArray(size) }

        // 1. 左半分を塗る。中心ほど濃く
        for (r in 0 until inner) {
            val dy = (r - (inner - 1) / 2f) / (inner / 2f)
            for (c in 0 until half) {
                val dx = (half - 1 - c) / half.toFloat()
                val p = 0.92f - 0.75f * (dx * dx + dy * dy)
                val spine = c == half - 1 && r in 1 until inner - 1
                if (spine || rng.nextFloat() < p) grid[r + 1][c + 1] = BODY
            }
        }
        // 2. 左右に写す
        for (r in 0 until size) for (c in 0 until size / 2) grid[r][size - 1 - c] = grid[r][c]

        // 3. 中心から繋がっていないマスを捨てる
        keepCentralBlob(grid)

        // 4. 目。体の上から 3 割あたりの行に、左右対称で 1 組
        val rows = grid.indices.filter { r -> grid[r].any { it == BODY } }
        if (rows.isNotEmpty()) {
            val eyeRow = rows.first() + ((rows.last() - rows.first()) * 0.3f).toInt()
            val gap = 1 + rng.nextInt(2)
            val col = size / 2 - 1 - gap
            if (col >= 1 && grid[eyeRow][col] == BODY) {
                grid[eyeRow][col] = EYE
                grid[eyeRow][size - 1 - col] = EYE
            }
        }

        // 5. 進化した姿には模様を入れる
        if (stage > 0) {
            val chance = 0.10f + 0.06f * stage
            for (r in 0 until size) for (c in 0 until size / 2) {
                if (grid[r][c] == BODY && rng.nextFloat() < chance) {
                    grid[r][c] = ACCENT
                    grid[r][size - 1 - c] = ACCENT
                }
            }
        }

        // 6. 輪郭
        val filled = { r: Int, c: Int ->
            r in 0 until size && c in 0 until size && grid[r][c] != EMPTY && grid[r][c] != OUTLINE
        }
        val outline = mutableListOf<Pair<Int, Int>>()
        for (r in 0 until size) for (c in 0 until size) {
            if (grid[r][c] == EMPTY &&
                (filled(r - 1, c) || filled(r + 1, c) || filled(r, c - 1) || filled(r, c + 1))
            ) outline += r to c
        }
        outline.forEach { (r, c) -> grid[r][c] = OUTLINE }
        return grid
    }

    private fun keepCentralBlob(grid: List<IntArray>) {
        val size = grid.size
        val start = (size / 2 until size).asSequence()
            .flatMap { r -> sequenceOf(r, size - 1 - r) }
            .firstOrNull { r -> grid[r][size / 2 - 1] == BODY }
            ?: return
        val seen = Array(size) { BooleanArray(size) }
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue += start to size / 2 - 1
        seen[start][size / 2 - 1] = true
        while (queue.isNotEmpty()) {
            val (r, c) = queue.removeFirst()
            for ((nr, nc) in listOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1)) {
                if (nr in 0 until size && nc in 0 until size && !seen[nr][nc] && grid[nr][nc] == BODY) {
                    seen[nr][nc] = true
                    queue += nr to nc
                }
            }
        }
        for (r in 0 until size) for (c in 0 until size) if (!seen[r][c]) grid[r][c] = EMPTY
    }
}
