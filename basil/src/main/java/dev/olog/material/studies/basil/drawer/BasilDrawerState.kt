package dev.olog.material.studies.basil.drawer

import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.*
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.List
import kotlin.math.roundToInt

enum class BasilDrawerPage {
    Category,
    List,
    Detail
}

@Composable
fun rememberBasilDrawerState(
    initialValue: BasilDrawerPage = List,
    confirmStateChange: (newValue: BasilDrawerPage) -> Boolean = { true },
    categoryOverflow: Dp = 64.dp,
): BasilDrawerState {
    val categoryOverflowPx = with(LocalDensity.current) { categoryOverflow.toPx() }
    return remember(initialValue, confirmStateChange, categoryOverflowPx) {
        BasilDrawerState(
            initialValue = initialValue,
            confirmStateChange = confirmStateChange,
            categoryOverflowPx = categoryOverflowPx
        )
    }
}

class BasilDrawerState(
    initialValue: BasilDrawerPage,
    confirmStateChange: (newValue: BasilDrawerPage) -> Boolean = { true },
    val categoryOverflowPx: Float,
) : SwipeableState<BasilDrawerPage>(
    initialValue = initialValue,
    animationSpec = SwipeableDefaults.AnimationSpec, // TODO try to recreate animation
    confirmStateChange = confirmStateChange
) {


    @Composable
    fun anchors(
        constraints: Constraints,
    ): Map<Float, BasilDrawerPage> {
        return remember(constraints) {
            mapOf(
                constraints.maxHeight.toFloat() to Category,
                0f to List,
                -(computeHeight(constraints, List).toFloat() + categoryOverflowPx) to Detail,
            )
        }
    }

    fun computeHeight(
        constraints: Constraints,
        page: BasilDrawerPage
    ): Int = when (page) {
        Category -> constraints.maxHeight + categoryOverflowPx.toInt()
        List -> constraints.maxWidth
        Detail -> constraints.maxHeight
    }

    fun computePositionY(
        constraints: Constraints,
        page: BasilDrawerPage
    ): Int = when (page) {
        Category -> -constraints.maxHeight
        List -> categoryOverflowPx.roundToInt()
        Detail -> categoryOverflowPx.roundToInt() + computeHeight(constraints, List)
    }

}