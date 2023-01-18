package dev.olog.material.studies.shared

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize

fun Modifier.size(size: IntSize): Modifier = composed {
    val width = with(LocalDensity.current) { size.width.toDp() }
    val height = with(LocalDensity.current) { size.height.toDp() }
    Modifier.size(width, height)
}