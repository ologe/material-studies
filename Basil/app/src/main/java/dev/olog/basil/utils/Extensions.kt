package dev.olog.basil.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toIntPx(): Int {
    val density = DensityAmbient.current.density
    return (this.value * density).toInt()
}