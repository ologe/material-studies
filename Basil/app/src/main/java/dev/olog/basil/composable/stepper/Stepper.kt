package dev.olog.basil.composable.stepper

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.onPositioned
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import dev.olog.basil.utils.toDp

@Composable
fun<T> Stepper(
    items: List<T>,
    currentPage: MutableState<Int> = mutableStateOf(1),
    modifier: Modifier = Modifier,
    indicatorModifier: Modifier = Modifier.border(3.dp, MaterialTheme.colors.secondary, CircleShape),
    children: @Composable (T, Int) -> Unit,
) {
    val itemCount = items.size

    val state = rememberStepperState(currentPage)
    var slotWidth by remember { mutableStateOf(0.dp) }
    val scrollState = rememberScrollState()

    Stack(modifier) {

        val density = DensityAmbient.current.density
        ScrollableColumn(
            modifier = Modifier.onPositioned {
                // TODO find another way
                if (state.slotHeight == 0f) {
                    state.slotHeight = (it.size.height / itemCount).toFloat()
                    state.maxBound = it.size.height.toFloat()
                    slotWidth = (it.size.width / density).dp
                    state.snapTo(currentPage.value)
                }
            },
            scrollState = scrollState
        ) {
            StepperSlots(
                items = items,
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
    onClick: (T, Int) -> Unit,
    children: @Composable (T, Int) -> Unit
) {
    items.forEachIndexed { index, item ->

        val modifier = Modifier
            .clickable(
                onClick = { onClick(item, index) },
                indication = null
            )

        Stack(modifier = modifier) {
            children(item, index)
        }
    }
}