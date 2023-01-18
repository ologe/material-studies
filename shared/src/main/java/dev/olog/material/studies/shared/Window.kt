package dev.olog.material.studies.shared

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun rememberStatusBarHeight(): Int {
    return WindowInsets.statusBars
        .only(WindowInsetsSides.Top)
        .getTop(LocalDensity.current)
}

@Composable
fun rememberNavigationBarHeight(): Int {
    return WindowInsets.navigationBars
        .only(WindowInsetsSides.Bottom)
        .getBottom(LocalDensity.current)
}