package dev.olog.shared.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.MaterialColors

@Preview
@Composable
private fun StatusBarPreview() {
    val colors = lightColors(primaryVariant = Color(0xff_e8e8e8))
    MaterialTheme(colors = colors) {
        StatusBar()
    }
}

// just for previews
@Composable
fun StatusBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(MaterialColors.primaryVariant)
    ) {
        val size = 10.dp
        val color = Color.DarkGray.copy(alpha = 0.7f)
        Row(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.size(size).background(color))
            Spacer(modifier = Modifier.size(size).clip(CircleShape).background(color))
            Triangle(color, modifier = Modifier.size(size))
        }
    }
}

@Composable
private fun Triangle(
    color: Color,
    modifier: Modifier = Modifier
) {
    val path by remember {
        val path = Path().apply { fillType = PathFillType.EvenOdd }
        mutableStateOf(path)
    }
    Canvas(modifier = modifier) {
        path.reset()
        path.lineTo(this.size.width, 0f)
        path.lineTo(this.size.width / 2f, this.size.height)
        path.close()
        drawPath(path, color)
    }
}