package dev.olog.material.studies.basil.drawer

import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp

enum class BasilLayoutStateValue {
    Drawer,
    List,
    Detail,
}

@Composable
fun rememberBasilLayoutState(
    initialValue: BasilLayoutStateValue,
    listPositionPercentage: Float,
    listPadding: Dp,
): BasilLayoutState {
    return remember(initialValue, listPositionPercentage, listPadding) {
        BasilLayoutState(
            initialValue = initialValue,
            listPositionPercentage = listPositionPercentage,
            listPadding = listPadding
        )
    }
}

class BasilLayoutState(
    val initialValue: BasilLayoutStateValue,
    val listPositionPercentage: Float,
    val listPadding: Dp,
) : SwipeableState<BasilLayoutStateValue>(
    initialValue = initialValue
)