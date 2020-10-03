package dev.olog.fortnightly.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.utils.toIntPx

@Preview
@Composable
private fun DottedDividerVerticalPreview() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
            .background(Color.White),
    ) {
        DottedDividerHorizontal(Modifier.align(Alignment.Center))
    }
}

@Composable
fun DottedDividerHorizontal(
    modifier: Modifier = Modifier,
    strokeSize: Int = 1.5.dp.toIntPx(),
    strokeOffset: Int = 3.dp.toIntPx(),
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        for (index in 0 until (size.width.toInt() / (strokeSize + strokeOffset))) {
            val start = index * strokeSize + index * strokeOffset
            val end = start + strokeSize
            drawLine(
                color = Color.Black.copy(alpha = .2f),
                start = Offset(start.toFloat(), 0f),
                end = Offset(end.toFloat(), 0f),
                strokeWidth = strokeSize.toFloat()
            )
        }
    }
}