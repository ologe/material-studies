package dev.olog.basil.utils

import androidx.annotation.FloatRange
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.scaleDown(
    @FloatRange(from = 0.0, to = 1.0) offset: Float,
    amount: Float = 0.05f
) = composed {
    this.then(
        Modifier.graphicsLayer(
            scaleX = 1 - offset * amount,
            scaleY = 1 - offset * amount,
        )
    )
}