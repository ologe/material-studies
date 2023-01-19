package dev.olog.material.studies.basil.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.material.studies.basil.theme.BasilTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private const val Duration = 400
private val Delay = (Duration * .16f).roundToInt()
private val HeadAnimationSpec = tween<Float>(Duration)
private val TailAnimationSpec = tween<Float>(Duration, Delay)

@Composable
fun Stepper(
    selected: Int,
    count: Int,
    stepSize: Dp,
    onSelectionChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    render: @Composable BoxScope.(Int) -> Unit,
) {
    val latestSelected = remember { mutableStateOf<Int?>(null) }
    val density = LocalDensity.current
    val topOffset = remember {
        Animatable(with(density) { stepSize.toPx() } * selected)
    }
    val bottomOffset = remember {
        Animatable(with(density) { stepSize.toPx() } * (selected + 1))
    }

    LaunchedEffect(selected) {
        val latest = latestSelected.value
        if (latest != null) {
            if (selected > latest) {
                launch {
                    bottomOffset.animateTo(
                        targetValue = with(density) { stepSize.toPx() } * (selected + 1),
                        animationSpec = HeadAnimationSpec,
                    )
                }
                launch {
                    topOffset.animateTo(
                        targetValue = with(density) { stepSize.toPx() } * (selected),
                        animationSpec = TailAnimationSpec,
                    )
                }
            } else if (selected < latest) {
                launch {
                    topOffset.animateTo(
                        targetValue = with(density) { stepSize.toPx() } * (selected),
                        animationSpec = HeadAnimationSpec
                    )
                }
                launch {
                    bottomOffset.animateTo(
                        targetValue = with(density) { stepSize.toPx() } * (selected + 1),
                        animationSpec = TailAnimationSpec,
                    )
                }
            }
        }

        latestSelected.value = selected
    }

    Box(
        modifier = modifier
            .clipToBounds()
            .padding(1.dp) // avoid border clipping
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            repeat(count) {
                val top = with(density) { stepSize.toPx() * it }
                val bottom = with(density) { stepSize.toPx() * (it + 1) }
                // use some delta value to check a little inside the bounds
                val delta = with(density) { stepSize.toPx() / 3 }
                val isUnderIndicator =
                    (top + delta) >= topOffset.value && (bottom - delta) <= bottomOffset.value
                val scale = if (isUnderIndicator) 1.3f else 1f

                Box(
                    modifier = Modifier
                        .size(stepSize)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { onSelectionChanged(it) }
                        )
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    render(it)
                }
            }
        }


        val color = LocalContentColor.current.copy(alpha = .7f)
        val cornerSize = with(density) { stepSize.toPx() }
        Canvas(Modifier.matchParentSize()) {
            drawRoundRect(
                color = color,
                topLeft = Offset(0f, topOffset.value - scrollState.value),
                size = Size(size.width, bottomOffset.value - topOffset.value),
                cornerRadius = CornerRadius(cornerSize, cornerSize),
                style = Stroke(2.dp.toPx())
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    BasilTheme {
        var selected by remember { mutableStateOf(0) }

        Stepper(
            selected = selected,
            count = 10,
            stepSize = 48.dp,
            onSelectionChanged = { selected = it },
        ) {
            Text(
                text = (it + 1).toString().padStart(2, '0'),
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}