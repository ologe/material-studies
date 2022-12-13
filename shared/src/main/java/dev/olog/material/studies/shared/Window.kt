package dev.olog.material.studies.shared

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity

fun Modifier.statusBarHeight(): Modifier = composed {
    windowInsetsTopHeight(WindowInsets.statusBars)
}

@Composable
fun statusBarHeight(): Int {
    return WindowInsets.statusBars.only(WindowInsetsSides.Top).getTop(LocalDensity.current)
}