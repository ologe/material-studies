package dev.olog.material.studies.basil.main.layout

import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Drawer
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.List
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Detail
import kotlin.math.roundToInt

enum class BasilLayoutStateValue {
    Drawer,
    List,
    Detail,
}

class BasilLayoutState(
    initialState: BasilLayoutStateValue,
) : SwipeableState<BasilLayoutStateValue>(initialState) {

    val drawerProgress: Float
        get() = when {
            progress.from == List && progress.to == Drawer -> progress.fraction
            progress.from == Drawer && progress.to == List -> 1 - progress.fraction
            progress.from == Drawer && progress.to == Drawer -> 1f
            else -> 0f
        }

    val detailProgress: Float
        get() = when {
            progress.from == List && progress.to == Detail -> progress.fraction
            progress.from == Detail && progress.to == List -> 1 - progress.fraction
            progress.from == Detail && progress.to == Detail -> 1f
            else -> 0f
        }

    private val intOffset: Int
        get() = offset.value.takeUnless { it.isNaN() }?.roundToInt() ?: 0

    val drawerOffset: IntOffset
        get() = IntOffset(0, intOffset)

    val listOffset: IntOffset
        get() = IntOffset(0, intOffset.coerceAtLeast(0))

    val detailOffset: IntOffset
        get() = IntOffset(0, intOffset)

}

@Composable
fun rememberBasilLayoutState(
    initialState: BasilLayoutStateValue = List,
): BasilLayoutState {
    return remember(initialState) {
        BasilLayoutState(initialState)
    }
}