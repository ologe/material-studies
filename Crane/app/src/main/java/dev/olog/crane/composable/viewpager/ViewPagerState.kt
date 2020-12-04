package dev.olog.crane.composable.viewpager

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimatedFloat
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DefaultAnimationClock
import androidx.compose.material.SwipeableConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AmbientAnimationClock
import dev.olog.crane.utils.animateSpec
import dev.olog.crane.utils.scrollSpec

@Composable
fun rememberViewPagerState(
    initialPage: Int,
    scrollSpec: AnimationSpec<Float> = SwipeableConstants.scrollSpec,
    animateSpec: AnimationSpec<Float> = SwipeableConstants.animateSpec,
): ViewPagerState {
    val clock = AmbientAnimationClock.current.asDisposableClock()
    return rememberSavedInstanceState(
        clock,
        saver = ViewPagerState.Saver(
            scrollSpec = scrollSpec,
            animateSpec = animateSpec
        )
    ) {
        ViewPagerState(
            initialPage = initialPage,
            scrollSpec = scrollSpec,
            animateSpec = animateSpec
        )
    }
}

class ViewPagerState(
    private val initialPage: Int,
    private val scrollSpec: AnimationSpec<Float>,
    private val animateSpec: AnimationSpec<Float>,
) {

    var pageSize: Int = 0

    private val initialOffset: Float
        get() = (initialPage * pageSize).toFloat()

    // scroll offset
    private var internalOffset: Float by mutableStateOf(0f)

    // scroll offset by initial page size
    val offset: Float
        get() = initialOffset + internalOffset


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
        page: Int,
        anim: AnimationSpec<Float> = animateSpec
    ) {
        val targetValue = page * pageSize
        animateToInternal(targetValue - initialOffset, anim)
    }

    fun animateTo(
        targetValue: Float,
        anim: AnimationSpec<Float> = scrollSpec
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

        // TODO save last page instead of initial one?
        @Suppress("FunctionName")
        fun Saver(
            scrollSpec: AnimationSpec<Float>,
            animateSpec: AnimationSpec<Float>,
        ) = Saver<ViewPagerState, Int>(
            save = { it.initialPage },
            restore = { ViewPagerState(
                initialPage = it,
                scrollSpec = scrollSpec,
                animateSpec = animateSpec
            ) }
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