package dev.olog.material.studies.basil.v2

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.SwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.measure
import dev.olog.material.studies.shared.statusBarHeight
import kotlin.math.roundToInt

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

@Composable
fun BasilLayout(
    state: BasilLayoutState,
    modifier: Modifier = Modifier,
    listContent: @Composable () -> Unit,
    detailContent: @Composable () -> Unit,
) {

    BoxWithConstraints(modifier.fillMaxSize()) {
        val density = LocalDensity.current
        val measurer = remember(constraints, state) {
            BasilLayoutMeasurer(constraints, state, density)
        }

        val statusBarHeight = statusBarHeight()
        val listRect = measurer.measureListRect()
        val offsetPx = state.offset.value.roundToInt()

        BasilLayout(
            measurer = measurer,
            modifier = Modifier
                .matchParentSize()
                .swipeable(
                    state = state,
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        constraints.maxHeight.toFloat() - statusBarHeight to BasilLayoutStateValue.Drawer,
                        0f to BasilLayoutStateValue.List,
                        -listRect.bottom.toFloat() to BasilLayoutStateValue.Detail
                    ),
                )
        ) {
            // drawer
            Box(
                Modifier
                    .offset { IntOffset(0, offsetPx) }
                    .background(Color.Black.copy(alpha = .5f))
            )

            // list
            BasilListLayout(
                measurer = measurer,
                modifier = Modifier.background(Color.Red.copy(alpha = .5f))
            ) {
                listContent()
            }

            // detail
            BasilDetailLayout(
                measurer = measurer,
                modifier = Modifier
                    .offset { IntOffset(0, offsetPx) }
                    .background(Color.Green.copy(alpha = .5f))
            ) {
                detailContent()
            }
        }

        // status bar
        Box(
            Modifier
                .fillMaxWidth()
                .statusBarHeight()
                .background(BasilColors.primary50)
        )
    }
}

@Composable
private fun BasilLayout(
    measurer: BasilLayoutMeasurer,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val drawer = measurables[0].measure(constraints)
        val list = measurables[1].measure(constraints)
        val listRect = measurer.measureListRect()
        val detail = measurables[2].measure(
            constraints.copy(
                minHeight = constraints.maxHeight + listRect.bottom,
                maxHeight = constraints.maxHeight + listRect.bottom,
            )
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            drawer.place(0, -constraints.maxHeight)
            list.place(0, 0)
            detail.place(0, 0)
        }
    }
}

@Composable
private fun BasilListLayout(
    measurer: BasilLayoutMeasurer,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val listRect = measurer.measureListRect()
        val placeable = measurables[0].measure(listRect)

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.place(listRect.left, listRect.top)
        }
    }
}

@Composable
private fun BasilDetailLayout(
    measurer: BasilLayoutMeasurer,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        val mainHeaderRect = measurer.measureMainHeaderRect()
        val recipeNameRect = measurer.measureRecipeTextRect()
        val otherRecipeInfoRect = measurer.measureOtherRecipeInfoRect(constraints.maxHeight)

        val mainHeaderPlaceable = measurables[0].measure(mainHeaderRect)
        val namePlaceable = measurables[1].measure(recipeNameRect)
        val otherRecipeInfoPlaceable = measurables[2].measure(otherRecipeInfoRect)

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight // TODO + list rect
        ) {
            mainHeaderPlaceable.place(mainHeaderRect.left, mainHeaderRect.top)
            namePlaceable.place(recipeNameRect.left, recipeNameRect.top)
            otherRecipeInfoPlaceable.place(otherRecipeInfoRect.left, otherRecipeInfoRect.top)
        }
    }
}

class BasilLayoutMeasurer(
    private val constraints: Constraints,
    private val state: BasilLayoutState,
    private val density: Density,
) {

    fun measureMainHeaderRect(): IntRect = with(density) {
        val listRect = measureListRect()
        IntRect(
            left = 0,
            top = 0,
            right = constraints.maxWidth,
            bottom = listRect.top,
        )
    }

    fun measureListRect(): IntRect = with(density) {
        val top = (constraints.maxHeight * state.listPositionPercentage).roundToInt()
        val height = constraints.maxWidth - (state.listPadding * 2).roundToPx()
        val left = state.listPadding.roundToPx()
        IntRect(
            left = left,
            top = top,
            right = constraints.maxWidth - left,
            bottom = top + height,
        )
    }

    fun measureRecipeTextRect(): IntRect = with(density) {
        val top = measureListRect().bottom
        IntRect(
            left = 0,
            top = top,
            right = constraints.maxWidth,
            bottom = constraints.maxHeight,
        )
    }

    fun measureOtherRecipeInfoRect(maxHeight: Int): IntRect = with(density) {
        IntRect(
            left = 0,
            top = constraints.maxHeight,
            right = constraints.maxWidth,
            bottom = maxHeight,
        )
    }

}

