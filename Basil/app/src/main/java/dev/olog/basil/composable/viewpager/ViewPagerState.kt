package dev.olog.basil.composable.viewpager

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DefaultAnimationClock
import androidx.compose.material.SwipeableDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AmbientAnimationClock

@Composable
fun rememberViewPagerState(
    initialPage: Int,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec
): ViewPagerState {
    val clock = AmbientAnimationClock.current.asDisposableClock()
    return rememberSavedInstanceState(
        clock,
        saver = ViewPagerState.Saver(
            animationSpec = animationSpec
        )
    ) {
        ViewPagerState(
            initialPage = initialPage,
            animationSpec = animationSpec
        )
    }
}

class ViewPagerState(
    private val initialPage: Int,
    private val animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
) {

    var pageSize: Int = 0

    private val initialOffset: Float
        get() = (initialPage * pageSize).toFloat()

    // scroll offset
    private var internalOffset: Float by mutableStateOf(0f)

    // scroll offset by initial page size
    val offset: Float
        get() {
            if (pageSize == 0) {
                return 0f
            }
            return initialOffset + internalOffset
        }

    val currentPage: Int
        get() {
            if (pageSize == 0) {
                return 0
            }
            return offset.toInt() / pageSize
        }

    private val holder: AnimatedFloat = NotificationBasedAnimatedFloat(0f, DefaultAnimationClock()) {
        if(it in bounds) {
            internalOffset = it
        }
    }

    private val boundsState = mutableStateOf(0f.rangeTo(0f))
    var bounds: ClosedFloatingPointRange<Float>
        get() = boundsState.value
        set(value) {
            boundsState.value = value
        }

    val lowerBound: Float
        get() = bounds.start

    val upperBound: Float
        get() = bounds.endInclusive

    fun snapTo(targetValue: Float) {
        snapToInternal(targetValue - initialOffset)
    }

    private fun snapToInternal(targetValue: Float) {
        internalOffset = targetValue
        holder.snapTo(internalOffset)
    }

    fun animateTo(
        targetValue: Float,
        anim: AnimationSpec<Float> = animationSpec
    ) {
        animateToInternal(targetValue - initialOffset, anim)
    }

    private fun animateToInternal(
        targetValue: Float,
        anim: AnimationSpec<Float>
    ) {
        holder.animateTo(targetValue, anim) { _, endOffset ->
            internalOffset = endOffset
        }
    }

    companion object {

        @Suppress("FunctionName")
        fun Saver(
            animationSpec: AnimationSpec<Float>
        ) = Saver<ViewPagerState, Int>(
            save = { it.currentPage },
            restore = { ViewPagerState(initialPage = it, animationSpec = animationSpec) }
        )
    }

}

private class NotificationBasedAnimatedFloat(
    initialValue: Float,
    clock: AnimationClockObservable,
    val onNewValue: (Float) -> Unit
) : AnimatedFloat(clock) {
    override var value = initialValue
        set(value) {
            field = value
            onNewValue(value)
        }
}