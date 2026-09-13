package com.botanical.launcher.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botanical.launcher.garden.Palette

/** 版面そのものの設定。紙の余白を長押しすると出る。 */
@Composable
fun PlateSettingsDialog(
    captionsVisible: Boolean,
    onToggleCaptions: (Boolean) -> Unit,
    hitAreasVisible: Boolean,
    onToggleHitAreas: (Boolean) -> Unit,
    onClearAll: () -> Unit,
    onOpenHomeSettings: () -> Unit,
    onDismiss: () -> Unit,
) {
    val serif = TextStyle(fontFamily = FontFamily.Serif, fontSize = 15.sp, color = Palette.Ink)

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Palette.Paper,
        titleContentColor = Palette.Ink,
        textContentColor = Palette.Ink,
        title = {
            Text(
                "Herbarium",
                style = serif.copy(fontSize = 20.sp, fontStyle = FontStyle.Italic),
            )
        },
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("学名のキャプションを表示", style = serif, modifier = Modifier.weight(1f))
                    Switch(
                        checked = captionsVisible,
                        onCheckedChange = onToggleCaptions,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Palette.Cream,
                            checkedTrackColor = Palette.Green1,
                            uncheckedThumbColor = Palette.Cream,
                            uncheckedTrackColor = Palette.InkSoft,
                        ),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("タップできる範囲を表示", style = serif, modifier = Modifier.weight(1f))
                    Switch(
                        checked = hitAreasVisible,
                        onCheckedChange = onToggleHitAreas,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Palette.Cream,
                            checkedTrackColor = Palette.Green1,
                            uncheckedThumbColor = Palette.Cream,
                            uncheckedTrackColor = Palette.InkSoft,
                        ),
                    )
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "花や葉を長押しすると、その部位にアプリを割り当てられます。",
                    style = serif.copy(fontSize = 13.sp, color = Palette.InkSoft),
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "右上の蕾をタップするとアプリ一覧が開きます。",
                    style = serif.copy(fontSize = 13.sp, color = Palette.InkSoft),
                )
                Spacer(Modifier.height(14.dp))
                TextButton(onClick = onOpenHomeSettings) {
                    Text("端末のホームアプリ設定を開く", style = serif.copy(color = Palette.Green1))
                }
                TextButton(onClick = onClearAll) {
                    Text("すべての割り当てを解除", style = serif.copy(color = Palette.Crimson))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("閉じる", style = serif.copy(color = Palette.Ink))
            }
        },
    )
}
