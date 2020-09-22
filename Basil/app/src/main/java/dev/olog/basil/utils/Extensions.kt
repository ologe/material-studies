package dev.olog.basil.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.toIntPx(): Int {
    val density = DensityAmbient.current.density
    return (this.value * density).toInt()
}

@Composable
fun Int.toDp(): Dp {
    val density = DensityAmbient.current.density
    return (this / density).dp
}

inline val <T> T.exhaustive: T
    get() = this

@Composable
inline val screenHeightDp: Dp
    get() = ConfigurationAmbient.current.screenHeightDp.dp

@Composable
inline val screenHeightPx: Int
    get() {
        val density = DensityAmbient.current.density
        return (screenHeightDp.value * density).toInt()
    }