package dev.olog.basil.composable.drawer

import androidx.compose.material.SwipeableState

val SwipeableState<DrawerPage>.drawerOffset: Int
    get() = offset.value.toInt().coerceAtLeast(0)

val SwipeableState<DrawerPage>.detailOffset: Int
    get() = offset.value.toInt().coerceAtMost(0)