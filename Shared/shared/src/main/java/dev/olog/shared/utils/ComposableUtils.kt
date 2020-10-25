package dev.olog.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.toIntPx

@Composable
inline val screenHeightDp: Dp
    get() = ConfigurationAmbient.current.screenHeightDp.dp

@Composable
inline val screenWidthDp: Dp
    get() = ConfigurationAmbient.current.screenWidthDp.dp

@Composable
inline val screenHeightPx: Int
    get() = screenHeightDp.toIntPx()

@Composable
inline val screenWidthPx: Int
    get() = screenWidthDp.toIntPx()