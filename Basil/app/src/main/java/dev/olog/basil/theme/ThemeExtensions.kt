package dev.olog.basil.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
val MaterialColors: Colors
    get() = MaterialTheme.colors

@Composable
val MaterialTypography: Typography
    get() = MaterialTheme.typography

@Composable
val MaterialShape: Shapes
    get() = MaterialTheme.shapes