package dev.olog.crane.composable.viewpager

import androidx.compose.foundation.gestures.draggable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.gesture.MinFlingVelocity
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.toIntPx
import kotlin.math.abs

fun Modifier.viewPager(
    state: ViewPagerState,
    maxWidthPx: Int,
    orientation: Orientation = Orientation.Horizontal,
    isUserInputEnabled: Boolean = true,
    threshold: Dp = 56.dp,
): Modifier = composed {

    val thresholdPx = threshold.toIntPx()
    val minFlingVelocity = MinFlingVelocity.toIntPx().toFloat()

    draggable(
        orientation = orientation,
        onDragStopped = { velocity ->
            if (!isUserInputEnabled) {
                return@draggable
            }
            val mod = state.offset % maxWidthPx
            val left = state.offset - mod
            val right = left + maxWidthPx
            val animateTo = when {
                mod < thresholdPx -> left
                (maxWidthPx - mod) < thresholdPx -> right
                abs(velocity) < minFlingVelocity -> if (mod < maxWidthPx / 2) left else right
                velocity < 0 -> right
                velocity > 0 -> left
                else -> throw IllegalStateException()
            }
            state.animateTo(animateTo)
        },
        onDrag = { delta ->
            if (!isUserInputEnabled) {
                return@draggable
            }
            val newAmount = (state.offset - delta)
                .coerceIn(state.lowerBound, state.upperBound)
            state.snapTo(newAmount)
        }
    )
}