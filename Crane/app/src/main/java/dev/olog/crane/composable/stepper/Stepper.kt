package dev.olog.crane.composable.stepper

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.toDp

@Composable
fun<T> Stepper(
    items: List<T>,
    modifier: Modifier = Modifier,
    currentPage: MutableState<Int> = mutableStateOf(0),
    onClick: (T, Int) -> Unit = { _, _ -> },
    children: @Composable (T, Int, Boolean) -> Unit,
) {
    val itemCount = items.size

    val state = rememberStepperState(currentPage)

    Box(
        modifier,
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = Modifier.onGloballyPositioned {
                // TODO find another way
                if (state.slotWidth == 0f) {
                    state.slotWidth = (it.size.width / itemCount).toFloat()
                    state.maxBound = it.size.width.toFloat()
                    state.snapTo(currentPage.value)
                }
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            StepperSlots(
                items = items,
                state = state,
                modifier = Modifier.weight(1f),
                onClick = { item, index ->
                    onClick(item, index)
                    state.animate(index)
                },
                children = children
            )
        }

        Box(
            Modifier
                .fillMaxHeight(0.9f)
                .width(state.width().toDp())
                .graphicsLayer(translationX = state.leftOffset)
                .border(3.dp, Color.White, CircleShape),
        )
    }
}

@Composable
private fun<T> StepperSlots(
    items: List<T>,
    state: StepperState,
    modifier: Modifier = Modifier,
    onClick: (T, Int) -> Unit,
    children: @Composable (T, Int, Boolean) -> Unit
) {
    val slotWidth = state.slotWidth
    val top = state.leftOffset
    val bottom = state.rightOffset
    val delta = slotWidth / 3f

    items.forEachIndexed { index, item ->

        val thisLeft = index * slotWidth + delta
        val thisRight = thisLeft + delta

        val isUnderIndicator = top <= thisLeft && thisRight <= bottom


        Box(
            modifier = modifier
                .fillMaxHeight(0.9f)
                .clip(CircleShape)
                .clickable(
                    onClick = { onClick(item, index) },
//                    indication = Indica
                ),
            contentAlignment = Alignment.Center,
        ) {
            children(item, index, isUnderIndicator)
        }
    }
}