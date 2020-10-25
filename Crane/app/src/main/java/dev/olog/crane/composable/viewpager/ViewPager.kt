package dev.olog.crane.composable.viewpager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import dev.olog.crane.utils.offsetGetter
import kotlin.math.floor

/**
 * @param children T item, float (0..1) fraction offset, bool true when is left item
 */
@Composable
fun <T> ViewPager(
    items: List<T>,
    state: ViewPagerState = rememberViewPagerState(initialPage = 0),
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
    isUserInputEnabled: Boolean = true,
    alignment: Alignment = Alignment.Center,
    children: @Composable BoxScope.(T) -> Unit
) {
    val itemCount = items.size

    WithConstraints(modifier) {
        val pageSize = when (orientation) {
            Orientation.Vertical -> constraints.maxHeight
            Orientation.Horizontal -> constraints.maxWidth
        }

        onCommit {
            val maxScroll = (pageSize * (itemCount - 1)).toFloat()
            state.pageSize = pageSize
            state.bounds = 0f.rangeTo(maxScroll)
        }

        Box(
            modifier = Modifier.viewPager(
                state = state,
                maxWidthPx = pageSize,
                orientation = orientation,
                isUserInputEnabled = isUserInputEnabled
            ),
            alignment = alignment
        ) {
            val offset = floor(state.offset).toInt()

            for ((index, item) in items.withIndex()) {
                val leftPageStartOffset = index * pageSize - offset
                Page(offset = leftPageStartOffset, orientation = orientation) {
                    children(item)
                }
            }
        }
    }
}

@Composable
private fun Page(
    offset: Int,
    orientation: Orientation,
    children: @Composable BoxScope.() -> Unit
) {
    val modifier = when (orientation) {
        Orientation.Horizontal -> Modifier.offsetGetter(x = { offset })
        Orientation.Vertical -> Modifier.offsetGetter(y = { offset })
    }
    Box(
        modifier = modifier,
        children = children
    )
}