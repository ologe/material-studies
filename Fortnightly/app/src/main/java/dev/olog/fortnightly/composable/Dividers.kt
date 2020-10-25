package dev.olog.fortnightly.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.shared.extension.toIntPx

@Preview
@Composable
private fun DottedDividerHorizontalPreview() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp)
            .background(Color.White),
    ) {
        DottedDividerHorizontal(Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
private fun DottedDividerVerticalPreview() {
    Box(
        Modifier
            .height(200.dp)
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .background(Color.White),
    ) {
        DottedDividerVertical(Modifier.align(Alignment.Center))
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

@Composable
fun DottedDividerVertical(
    modifier: Modifier = Modifier,
    strokeSize: Int = 1.5.dp.toIntPx(),
    strokeOffset: Int = 3.dp.toIntPx(),
) {
    Canvas(modifier = modifier.fillMaxHeight()) {
        for (index in 0 until (size.height.toInt() / (strokeSize + strokeOffset))) {
            val top = index * strokeSize + index * strokeOffset
            val bottom = top + strokeSize
            drawLine(
                color = Color.Black.copy(alpha = .2f),
                start = Offset(0f, top.toFloat()),
                end = Offset(0f, bottom.toFloat()),
                strokeWidth = strokeSize.toFloat()
            )
        }
    }
}