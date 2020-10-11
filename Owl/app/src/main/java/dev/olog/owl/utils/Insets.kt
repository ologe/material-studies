package dev.olog.owl.utils

import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO jetpack compose insets snippet don't work with compose alpha04

fun Modifier.statusBarHeight(): Modifier {
    return this then Modifier.height(24.dp)
}

fun Modifier.navigationBarHeight(): Modifier {
    return this then Modifier.height(24.dp) // TODO
}