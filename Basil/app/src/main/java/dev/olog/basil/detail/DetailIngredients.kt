package dev.olog.basil.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.basil.theme.green800
import dev.olog.basil.theme.lekton
import dev.olog.shared.extension.toFloatPx
import dev.olog.shared.extension.toIntPx
import kotlin.random.Random

@Composable
fun RecipeIngredients() {
    Column(modifier = Modifier.fillMaxWidth(),) {
        for (i in (0..5)) {
            Row(
                Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(asset = Icons.Default.AddCircleOutline)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Test $i",
                    fontWeight = FontWeight.Bold,
                    fontFamily = lekton,
                    fontSize = 18.sp,
                )
                DottedDivider(Modifier.weight(1f))
                Text(
                    text = "${Random.nextInt(1, 10)} tbsp",
                    fontWeight = FontWeight.Normal,
                    fontFamily = lekton,
                    fontSize = 18.sp,
                )
            }
        }
    }

}

@Composable
private fun DottedDivider(
    modifier: Modifier = Modifier,
    strokeSize: Int = 3.dp.toIntPx(),
    strokeOffset: Int = 5.dp.toIntPx(),
) {
    Canvas(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .drawLayer(translationY = 4.dp.toFloatPx())
    ) {
        for (index in 0 until (size.width.toInt() / (strokeSize + strokeOffset))) {
            val start = index * strokeSize + index * strokeOffset
            val end = start + strokeSize
            drawLine(
                color = green800.copy(alpha = .9f),
                start = Offset(start.toFloat(), 0f),
                end = Offset(end.toFloat(), 0f),
                strokeWidth = strokeSize.toFloat()
            )
        }
    }
}