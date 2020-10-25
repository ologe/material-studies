package dev.olog.basil.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.util.annotation.FloatRange

fun Modifier.scaleDown(
    @FloatRange(0.0, 1.0) offset: Float,
    amount: Float = 0.05f
) = this then Modifier.drawLayer(
    scaleX = 1 - offset * amount,
    scaleY = 1 - offset * amount,
)