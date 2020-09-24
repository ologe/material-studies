package dev.olog.basil.composable.stepper

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
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
    private val top: AnimatedFloat,
    private val bottom: AnimatedFloat,
    private val currentPageState: MutableState<Int>
) {

    companion object {
        private val DURATION = DefaultDurationMillis
        private const val DELAY = 50

        fun Saver(
            top: AnimatedFloat,
            bottom: AnimatedFloat
        ) = Saver<StepperState, Int>(
            save = { it.currentPageState.value },
            restore = { StepperState(top, bottom, mutableStateOf(it)) }
        )
    }

    var maxBound = 0f
    var slotHeight = 0f

    fun snapTo(page: Int) {
        val newTop = heightOf(page)
        val newBottom = heightOf(page + 1)
        top.animateTo(newTop, TweenSpec(1, easing = LinearEasing))
        bottom.animateTo(newBottom, TweenSpec(1, easing = LinearEasing))
    }

    fun animate(toPage: Int) {
        val currentPage = currentPageState.value
        when {
            toPage > currentPage -> {
                top.animateTo(heightOf(toPage), TweenSpec(DURATION, delay = DELAY))
                bottom.animateTo(heightOf(toPage + 1), TweenSpec(DURATION))
            }
            currentPage > toPage -> {
                top.animateTo(heightOf(toPage), TweenSpec(DURATION, ))
                bottom.animateTo(heightOf(toPage + 1), TweenSpec(DURATION, delay = DELAY))
            }
        }

        currentPageState.value = toPage
    }

    private fun heightOf(page: Int): Float {
        return slotHeight * page
    }


    fun height(): Int {
        val targetValue = abs(topOffset - bottomOffset)
        if (targetValue + topOffset > maxBound) {
            return (maxBound - topOffset).toInt()
        }
        return targetValue.toInt()
    }

    val topOffset: Float
        get() = top.value

    val bottomOffset: Float
        get() = bottom.value

}