package dev.olog.crane.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(4.dp), // TODO buttons has to be RoundedCornerShape(50)
    medium = RoundedCornerShape(50),
    large = RoundedCornerShape(topLeft = 16.dp, topRight = 16.dp, bottomLeft = 0.dp, bottomRight = 0.dp)
)