package com.example.zlauncher.ui.apps.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.core.designsystem.ZType
import com.example.zlauncher.core.ui.springyClick

/**
 * ホームのアプリ検索。アプリ数が増えると、配置よりも検索の方が早く目的に着く。
 * Web Insights のログ検索も見た目を揃えたいので、プレースホルダだけ差し替えて使い回す。
 */
@Composable
fun AppSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search apps",
) {
    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ZColors.Surface.copy(alpha = 0.75f))
            .border(1.dp, ZColors.Outline, shape)
            .padding(horizontal = 11.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Box(Modifier.size(6.dp).clip(RoundedCornerShape(999.dp)).background(ZColors.AccentAlt))
        Box(Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(placeholder, style = ZType.Body, color = ZColors.TextSecondary)
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = ZType.Body.copy(color = ZColors.TextPrimary),
                cursorBrush = SolidColor(ZColors.AccentAlt),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        if (query.isNotEmpty()) {
            Text(
                text = "✕",
                style = ZType.Body.copy(fontSize = 13.sp),
                color = ZColors.TextSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .springyClick(onClick = onClear)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
            )
        }
    }
}
