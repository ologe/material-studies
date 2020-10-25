package dev.olog.basil.composable.stepper

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import dev.olog.basil.theme.MaterialColors
import dev.olog.basil.utils.toDp

@Composable
fun<T> Stepper(
    items: List<T>,
    currentPage: MutableState<Int> = mutableStateOf(0),
    modifier: Modifier = Modifier,
    indicatorModifier: Modifier = Modifier.border(3.dp, MaterialColors.secondary, CircleShape),
    children: @Composable (T, Int, Boolean) -> Unit,
) {
    val itemCount = items.size

    val state = rememberStepperState(currentPage)
    var slotWidth by remember { mutableStateOf(0.dp) }
    val scrollState = rememberScrollState()

    Box(modifier) {

        val density = DensityAmbient.current.density
        ScrollableColumn(
            modifier = Modifier.onGloballyPositioned {
                // TODO find another way
                if (state.slotHeight == 0f) {
                    state.slotHeight = (it.size.height / itemCount).toFloat()
                    state.maxBound = it.size.height.toFloat()
                    slotWidth = (it.size.width / density).dp
                    state.snapTo(currentPage.value)
                }
            },
            scrollState = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
            // TODO allow spaced by
        ) {
            StepperSlots(
                items = items,
                state = state,
                onClick = { _, index -> state.animate(index) },
                children = children
            )
        }

        Box(
            Modifier
                .height(state.height().toDp())
                .width(slotWidth)
                .drawLayer(translationY = state.topOffset - scrollState.value)
                .then(indicatorModifier)
        )
    }
}

@Composable
private fun<T> StepperSlots(
    items: List<T>,
    state: StepperState,
    onClick: (T, Int) -> Unit,
    children: @Composable (T, Int, Boolean) -> Unit
) {
    val slotHeight = state.slotHeight
    val top = state.topOffset
    val bottom = state.bottomOffset
    val delta = slotHeight / 3f

    items.forEachIndexed { index, item ->

        val thisTop = index * slotHeight + delta
        val thisBottom = thisTop + delta

        val isUnderIndicator = top <= thisTop && thisBottom <= bottom
        val scale = if (isUnderIndicator) 1.1f else 1f

        val modifier = Modifier
            .clickable(
                onClick = { onClick(item, index) },
                indication = null
            ).drawLayer(
                scaleX = scale,
                scaleY = scale,
            )

        Box(modifier = modifier) {
            children(item, index, isUnderIndicator)
        }
    }
}