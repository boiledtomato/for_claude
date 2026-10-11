package com.example.zlauncher.data.apps

import com.example.zlauncher.domain.model.AppEntry
import com.example.zlauncher.domain.model.AppSortOrder
import java.text.Collator
import java.util.Locale

/**
 * 並び順。
 *
 * 注意: Android にはアプリ名の読み仮名を取得する API が無いため、「名前順」は
 * 英字 → かな → 漢字（コードポイント順）になる。完全な五十音順は実現できない。
 * AOSP の Launcher3 も同じ挙動で、代替として「インストール日時順」を用意している。
 *
 * Collator はロケール変更に追従させるため毎回作る（static に持つと端末の言語設定を
 * 変えたあとも古いロケールで並び続ける）。
 */
object AppSorter {

    fun sort(apps: List<AppEntry>, order: AppSortOrder): List<AppEntry> {
        val collator = Collator.getInstance(Locale.getDefault()).apply { strength = Collator.PRIMARY }
        val byLabel = Comparator<AppEntry> { a, b ->
            val result = collator.compare(a.label, b.label)
            if (result != 0) result else a.packageName.compareTo(b.packageName)
        }
        return when (order) {
            AppSortOrder.LABEL -> apps.sortedWith(byLabel)
            AppSortOrder.INSTALL_TIME ->
                apps.sortedWith(compareByDescending<AppEntry> { it.firstInstallTime }.then(byLabel))
        }
    }
}
