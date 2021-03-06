package dev.olog.basil.composable.stepper

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.basil.theme.BasilTheme
import dev.olog.shared.extension.MaterialColors
import dev.olog.shared.extension.MaterialTypography
import dev.olog.shared.extension.toDp

@Preview
@Composable
private fun VerticalStepperPreview() {
    BasilTheme {
        VerticalStepper(
            items = (1..8).toList(),
            modifier = Modifier.padding(16.dp).padding(top = 4.dp),
            indicatorModifier = Modifier.border(3.dp, MaterialColors.onPrimary, CircleShape),
        ) { item, _, underIndicator ->
            Text(
                text = item.toString().padStart(2, '0'),
                fontSize = 16.sp,
                style = MaterialTypography.body1,
                fontWeight = if (underIndicator) FontWeight.Bold else FontWeight.SemiBold,
                color = MaterialColors.onBackground,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun<T> VerticalStepper(
    items: List<T>,
    modifier: Modifier = Modifier,
    currentPage: MutableState<Int> = mutableStateOf(0),
    indicatorModifier: Modifier = Modifier.border(3.dp, MaterialColors.secondary, CircleShape),
    content: @Composable (T, Int, Boolean) -> Unit,
) {
    val itemCount = items.size

    val state = rememberStepperState(currentPage)
    var slotWidth by remember { mutableStateOf(0.dp) }
    val scrollState = rememberScrollState()

    Box(modifier) {

        val density = AmbientDensity.current.density
        ScrollableColumn(
            modifier = Modifier.onGloballyPositioned {
                if (state.slotHeight == 0f) {
                    state.slotHeight = (it.size.height / itemCount).toFloat()
                    state.maxBound = it.size.height.toFloat()
                    slotWidth = (it.size.width / density).dp
                    state.snapTo(currentPage.value)
                }
            },
            scrollState = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StepperSlots(
                items = items,
                state = state,
                onClick = { _, index -> state.animate(index) },
                children = content
            )
        }

        Box(
            Modifier
                .height(state.height().toDp())
                .width(slotWidth)
                .graphicsLayer(translationY = state.topOffset - scrollState.value)
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
            ).graphicsLayer(
                scaleX = scale,
                scaleY = scale,
            )

        Box(modifier = modifier) {
            children(item, index, isUnderIndicator)
        }
    }
}