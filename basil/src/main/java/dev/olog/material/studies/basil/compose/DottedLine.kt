package dev.olog.material.studies.basil.compose

import androidx.compose.foundation.Canvas
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun DottedLine(
    modifier: Modifier = Modifier,
    size: Dp = 2.dp,
    space: Dp = 5.dp,
    color: Color = LocalContentColor.current,
) {
    Canvas(
        modifier = modifier,
        onDraw = {

            val sizePx = size.toPx()
            val spacePx = space.toPx()
            val steps = (this.size.width / (sizePx + spacePx)).roundToInt()

            for (index in 0 until steps) {
                drawRect(
                    color = color,
                    topLeft = Offset(
                        x = (sizePx + spacePx) * index,
                        y = 0f,
                    ),
                    size = Size(sizePx, sizePx)
                )
            }
        }
    )
}