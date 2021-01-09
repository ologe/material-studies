package dev.olog.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.toIntPx

@get:Composable
inline val screenHeightDp: Dp
    get() = AmbientConfiguration.current.screenHeightDp.dp

@get:Composable
inline val screenWidthDp: Dp
    get() = AmbientConfiguration.current.screenWidthDp.dp

@get:Composable
inline val screenHeightPx: Int
    get() = screenHeightDp.toIntPx()

@get:Composable
inline val screenWidthPx: Int
    get() = screenWidthDp.toIntPx()