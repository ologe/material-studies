package dev.olog.material.studies.basil.drawer

import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.*
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.List
import dev.olog.material.studies.basil.theme.BasilColors
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
    listContentPadding: Dp = 24.dp,
    scrimColor: Color = BasilColors.primary50,
): BasilDrawerState {
    return remember(initialValue, confirmStateChange, listContentPadding, scrimColor) {
        BasilDrawerState(
            initialValue = initialValue,
            confirmStateChange = confirmStateChange,
            listContentPadding = listContentPadding,
            scrimColor = scrimColor,
        )
    }
}

class BasilDrawerState(
    initialValue: BasilDrawerPage,
    confirmStateChange: (newValue: BasilDrawerPage) -> Boolean,
    val listContentPadding: Dp,
    val scrimColor: Color,
) : SwipeableState<BasilDrawerPage>(
    initialValue = initialValue,
    animationSpec = SwipeableDefaults.AnimationSpec, // TODO try to recreate animation
    confirmStateChange = confirmStateChange
) {

    private fun listTopOffset(constraints: Constraints): Float {
        return constraints.maxHeight * .15f
    }

    @Composable
    fun anchors(
        constraints: Constraints,
    ): Map<Float, BasilDrawerPage> {
        return remember(constraints) {
            mapOf(
                constraints.maxHeight.toFloat() to Category,
                0f to List,
                -(computePositionY(constraints, Detail).toFloat()) to Detail,
            )
        }
    }

    fun computeHeight(
        constraints: Constraints,
        page: BasilDrawerPage
    ): Int = when (page) {
        Category -> constraints.maxHeight
        List -> constraints.maxWidth
        Detail -> constraints.maxHeight
    }

    fun computePositionY(
        constraints: Constraints,
        page: BasilDrawerPage
    ): Int = when (page) {
        Category -> -constraints.maxHeight
        List -> listTopOffset(constraints).roundToInt()
        Detail -> computePositionY(constraints, List) + computeHeight(constraints, List)
    }

    val detailFraction: Float
        get() = with(progress) {
            when {
                from == Detail && from == to -> 1f
                from == List && to == Detail -> fraction
                from == Detail && to == List -> 1f - fraction
                else -> 0f
            }
        }

}