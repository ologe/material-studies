package dev.olog.fortnightly.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toIntPx(): Int {
    val density = DensityAmbient.current.density
    return (this.value * density).toInt()
}

@Composable
fun Dp.toFloatPx(): Float {
    val density = DensityAmbient.current.density
    return this.value * density
}

@Composable
fun Int.toDp(): Dp {
    val density = DensityAmbient.current.density
    return (this / density).dp
}

@Composable
fun Float.toDp(): Dp {
    val density = DensityAmbient.current.density
    return (this / density).dp
}

inline val <T> T.exhaustive: T
    get() = this