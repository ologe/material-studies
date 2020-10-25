package dev.olog.shared.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toFloatPx(): Float {
    val density = DensityAmbient.current.density
    return this.value * density
}

@Composable
fun Dp.toIntPx(): Int {
    return this.toFloatPx().toInt()
}

@Composable
fun Float.toDp(): Dp {
    val density = DensityAmbient.current.density
    return (this / density).dp
}

@Composable
fun Int.toDp(): Dp {
    return this.toFloat().toDp()
}