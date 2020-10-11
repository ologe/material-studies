package dev.olog.owl.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(topLeftPercent = 50),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(topLeft = 16.dp, bottomLeft = 16.dp)
)