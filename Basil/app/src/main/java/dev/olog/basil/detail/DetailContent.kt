package dev.olog.basil.detail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import dev.olog.basil.list.Recipe

@Composable
fun DetailContent(
    peek: Dp,
    item: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float
) {
    Column(
        Modifier.fillMaxSize()
            .clickable(
                onClick = {},
                indication = null
            )
    ) {
        MiniDetail(peek, item, fraction)
    }
}

@Composable
private fun MiniDetail(
    peek: Dp,
    item: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float
) {
    Stack(
        Modifier.fillMaxWidth()
            .height(peek)
    ) {
        Text(
            text = item.title,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 32.dp, bottom = 24.dp),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary,
        )
        // TODO increase icon size
        Icon(
            asset = Icons.Default.KeyboardArrowDown,
            modifier = Modifier.align(Alignment.BottomCenter)
                .drawLayer(
                    alpha = (1 - fraction * 3).coerceIn(0f, 1f),
                    scaleX = 1.2f,
                    scaleY = 1f
                )
        )
    }
}