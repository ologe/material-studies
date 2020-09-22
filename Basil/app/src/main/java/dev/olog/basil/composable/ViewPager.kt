package dev.olog.basil.composable

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.TargetAnimation
import androidx.compose.foundation.animation.defaultFlingConfig
import androidx.compose.foundation.gestures.ScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.ExperimentalSubcomposeLayoutApi
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.LayoutDirectionAmbient
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.olog.basil.utils.screenWidthPx
import dev.olog.basil.utils.toIntPx

// TODO this is a dump custom implementation
//  sometimes scroll breaks
//  change with a real implementation when possible
@Composable
fun<T> ViewPager(
    items: List<T>,
    currentPage: MutableState<Int> = savedInstanceState { 0 } ,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    itemContent: @Composable LazyItemScope.(Int, T) -> Unit
) {
    CustomLazyFor(
        itemsCount = items.size,
        currentPage = currentPage,
        modifier = modifier,
        contentPadding = contentPadding,
        verticalAlignment = verticalAlignment,
        isVertical = false
    ) { index ->
        val item = items[index]
        {
            key(index) {
                itemContent(index, item)
            }
        }
    }
}

@Suppress("NOTHING_TO_INLINE")
@Composable
@OptIn(ExperimentalSubcomposeLayoutApi::class)
internal inline fun CustomLazyFor(
    itemsCount: Int,
    currentPage: MutableState<Int>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    isVertical: Boolean,
    noinline itemContentFactory: LazyItemScope.(Int) -> @Composable () -> Unit
) {
    val state = remember { CustomLazyForState(isVertical = isVertical) }
    val scrollController = rememberViewPagerController(
        itemsCount = itemsCount,
        currentPage = currentPage,
        consumeScrollDelta = state.onScrollDelta
    )
    state.scrollableController = scrollController
    val reverseDirection = LayoutDirectionAmbient.current == LayoutDirection.Rtl && !isVertical
    SubcomposeLayout<DataIndex>(
        modifier
            .scrollable(
                orientation = if (isVertical) Orientation.Vertical else Orientation.Horizontal,
                reverseDirection = reverseDirection,
                controller = scrollController
            )
            .clipToBounds()
            .padding(contentPadding)
            .then(state.remeasurementModifier)
    ) { constraints ->
        state.measure(
            this,
            constraints,
            horizontalAlignment,
            verticalAlignment,
            itemsCount,
            itemContentFactory
        )
    }
}

@Composable
private fun rememberViewPagerController(
    itemsCount: Int,
    currentPage: MutableState<Int>,
    consumeScrollDelta: (Float) -> Float
): ScrollableController {
    val screenWidth = screenWidthPx

    val minFlingVelocity = 100.dp.toIntPx().toFloat()

    val clocks = AnimationClockAmbient.current.asDisposableClock()
    val flingConfig = defaultFlingConfig {
        // negative velocity -> page = page + 1
        // positive velocity -> page = page - 1

        val newPage = when {
            it in -minFlingVelocity..minFlingVelocity -> currentPage.value
            it < 0 -> currentPage.value + 1
            it > 0 -> currentPage.value - 1
            else -> throw IllegalArgumentException(it.toString())
        }.coerceIn(0, itemsCount - 1)
        val target = newPage * screenWidth
        currentPage.value = newPage
        TargetAnimation(-target.toFloat())
    }

    return remember(clocks, flingConfig) {
        ScrollableController(consumeScrollDelta, flingConfig, clocks)
    }
}
