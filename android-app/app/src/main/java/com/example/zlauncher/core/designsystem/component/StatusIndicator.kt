package com.example.zlauncher.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zlauncher.core.designsystem.LocalStatusColors
import com.example.zlauncher.core.designsystem.ZColors
import com.example.zlauncher.domain.model.CardStatus

/** 6dp のドット。ステータス色は面積を絞るのが原則 */
@Composable
fun StatusDot(status: CardStatus, modifier: Modifier = Modifier) {
    val color = LocalStatusColors.current.colorFor(status)
    Box(modifier.size(6.dp).background(color, CircleShape))
}

/** 低彩度のピル。色だけに意味を持たせないため必ずラベルを取る */
@Composable
fun StatusPill(status: CardStatus, label: String, modifier: Modifier = Modifier) {
    val color = if (status == CardStatus.NEUTRAL || status == CardStatus.UNKNOWN) {
        ZColors.TextSecondary
    } else {
        LocalStatusColors.current.colorFor(status)
    }
    Box(
        modifier
            .background(color.copy(alpha = 0.08f), RoundedCornerShape(999.dp))
            .border(1.dp, color.copy(alpha = 0.35f), RoundedCornerShape(999.dp))
            .padding(horizontal = 7.dp, vertical = 3.dp)
    ) {
        Text(text = label, color = color, fontSize = 9.5.sp)
    }
}

/** カード左端の 2dp バー。amber / red のときだけ使う */
internal fun statusEdge(status: CardStatus, colors: com.example.zlauncher.core.designsystem.StatusColors): Color? =
    when (status) {
        CardStatus.AMBER -> colors.amber
        CardStatus.RED -> colors.red
        else -> null
    }
