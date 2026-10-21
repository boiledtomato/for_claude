package com.example.critterhome.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.critterhome.data.AppEntry
import com.example.critterhome.game.MonsterSprite

/** アイコンは読み込めるまで枠だけ出す。読み込み中に何も無いと、並びが後から詰まって見える */
@Composable
fun AppIcon(entry: AppEntry, iconProvider: suspend (AppEntry) -> Bitmap?, size: Dp) {
    val bitmap by produceState<Bitmap?>(initialValue = null, entry.key) { value = iconProvider(entry) }
    val image = remember(bitmap) { bitmap?.asImageBitmap() }
    Box(
        Modifier
            .size(size)
            .clip(RoundedCornerShape(size / 4))
            .background(if (image == null) Game.colors.sunken else Game.colors.window),
        contentAlignment = Alignment.Center,
    ) {
        image?.let { Image(it, contentDescription = entry.label, modifier = Modifier.size(size)) }
    }
}

/** 相棒の姿。段階が変わったときだけ描き直す */
@Composable
fun CompanionSprite(companion: CompanionUi, size: Dp) {
    val grid = remember(companion.seed, companion.stage) {
        MonsterSprite.pixels(companion.seed, if (companion.met) companion.stage else 0)
    }
    PixelSprite(
        grid = grid,
        color = Game.colors.habitat(companion.habitat),
        size = size,
        silhouette = !companion.met,
        fainted = companion.fainted,
    )
}

/** 戻るのあるサブ画面の枠。題名の左に ▶BACK */
@Composable
fun SubScreen(
    title: String,
    onBack: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CursorItem("BACK", selected = true, onClick = onBack)
            Spacer(Modifier.weight(1f))
            Text(title, style = RetroType.Title, color = Game.colors.ink)
        }
        content()
    }
}

/** 名前と値を 1 行に。ステータス画面とトレーナーカードで使う */
@Composable
fun InfoLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 1.dp)) {
        Text(label, style = RetroType.Small, color = Game.colors.inkDim, modifier = Modifier.width(72.dp))
        Text(value, style = RetroType.Small, color = Game.colors.ink, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

/** 住みかの札。色の帯と名前（色だけに頼らない） */
@Composable
fun HabitatTag(habitat: com.example.critterhome.game.Habitat) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Game.colors.habitat(habitat))
        )
        Spacer(Modifier.width(4.dp))
        Text(habitat.title.uppercase(), style = RetroType.Small.copy(fontSize = 9.sp), color = Game.colors.inkSoft)
    }
}
