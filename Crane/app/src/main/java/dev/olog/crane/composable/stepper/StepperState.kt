package dev.olog.crane.composable.stepper

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.ui.platform.AnimationClockAmbient
import kotlin.math.abs

@Composable
fun rememberStepperState(
    currentPageState: MutableState<Int>
): StepperState {
    val clock = AnimationClockAmbient.current.asDisposableClock()
    val top = animatedFloat(0f)
    val bottom = animatedFloat(0f)

    return rememberSavedInstanceState(
        clock,
        saver = StepperState.Saver(top, bottom)
    ) {
        StepperState(top, bottom, currentPageState)
    }
}

class StepperState(
    private val left: AnimatedFloat,
    private val right: AnimatedFloat,
    private val currentPageState: MutableState<Int>
) {

    companion object {
        private const val DURATION = 200

        fun Saver(
            top: AnimatedFloat,
            bottom: AnimatedFloat
        ) = Saver<StepperState, Int>(
            save = { it.currentPageState.value },
            restore = { StepperState(top, bottom, mutableStateOf(it)) }
        )
    }

    var maxBound = 0f
    var slotWidth = 0f

    fun snapTo(page: Int) {
        val newLeft = widthOf(page)
        val newRight = widthOf(page + 1)
        left.animateTo(newLeft, TweenSpec(1, easing = LinearEasing))
        right.animateTo(newRight, TweenSpec(1, easing = LinearEasing))
    }

    fun animate(toPage: Int) {
        val currentPage = currentPageState.value
        when {
            toPage > currentPage -> {
                left.animateTo(widthOf(toPage), TweenSpec(DURATION))
                right.animateTo(widthOf(toPage + 1), TweenSpec(DURATION))
            }
            currentPage > toPage -> {
                left.animateTo(widthOf(toPage), TweenSpec(DURATION, ))
                right.animateTo(widthOf(toPage + 1), TweenSpec(DURATION))
            }
        }

        currentPageState.value = toPage
    }

    private fun widthOf(page: Int): Float {
        return slotWidth * page
    }


    fun width(): Int {
        val targetValue = abs(leftOffset - rightOffset)
        if (targetValue + leftOffset > maxBound) {
            return (maxBound - leftOffset).toInt()
        }
        return targetValue.toInt()
    }

    val leftOffset: Float
        get() = left.value

    val rightOffset: Float
        get() = right.value

}