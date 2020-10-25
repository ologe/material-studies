package dev.olog.basil.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
inline val screenHeightDp: Dp
    get() = ConfigurationAmbient.current.screenHeightDp.dp

@Composable
inline val screenWidthDp: Dp
    get() = ConfigurationAmbient.current.screenWidthDp.dp

@Composable
inline val screenHeightPx: Int
    get() {
        val density = DensityAmbient.current.density
        return (screenHeightDp.value * density).toInt()
    }

@Composable
inline val screenWidthPx: Int
    get() {
        val density = DensityAmbient.current.density
        return (screenWidthDp.value * density).toInt()
    }

operator fun String.times(times: Int): String {
    return buildString {
        repeat(times) {
            append(this@times)
        }
    }
}