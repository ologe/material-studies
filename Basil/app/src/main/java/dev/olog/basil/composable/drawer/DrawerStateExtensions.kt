package dev.olog.basil.composable.drawer

import androidx.compose.material.SwipeableState

val SwipeableState<DrawerPage>.drawerOffset: Float
    get() {
        if (offset.value.isNaN()) {
            // first emitted value is NaN
            return 0f
        }
        return offset.value.coerceAtLeast(0f)
    }

val SwipeableState<DrawerPage>.detailOffset: Float
    get() {
        if (offset.value.isNaN()) {
            // first emitted value is NaN
            return 0f
        }
        return offset.value.coerceAtMost(0f)
    }