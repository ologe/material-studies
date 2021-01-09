package dev.olog.shared.extension

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@get:Composable
val MaterialColors: Colors
    get() = MaterialTheme.colors

@get:Composable
val MaterialTypography: Typography
    get() = MaterialTheme.typography

@get:Composable
val MaterialShape: Shapes
    get() = MaterialTheme.shapes