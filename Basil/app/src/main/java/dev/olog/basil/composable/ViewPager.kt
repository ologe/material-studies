package dev.olog.basil.composable

import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.StackScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import dev.olog.basil.utils.offsetGetter
import kotlin.math.floor

@Composable
fun <T> ViewPager(
    items: List<T>,
    state: ViewPagerState = rememberViewPagerState(initialPage = 0),
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
    isUserInputEnabled: Boolean = true,
    children: @Composable StackScope.(T) -> Unit
) {
    val itemCount = items.size

    WithConstraints(modifier) {
        val pageSize = when (orientation) {
            Orientation.Vertical -> constraints.maxHeight
            Orientation.Horizontal -> constraints.maxWidth
        }

        val maxScroll = (pageSize * (itemCount - 1)).toFloat()
        state.pageSize = pageSize
        state.bounds = 0f.rangeTo(maxScroll)

        Stack(
            modifier = Modifier.viewPager(
                state = state,
                maxWidthPx = pageSize,
                orientation = orientation,
                isUserInputEnabled = isUserInputEnabled
            )
        ) {
            val offset = floor(state.offset).toInt()
            val leftPage = offset / pageSize // left or center
            val leftPageStartOffset = leftPage * pageSize - offset

            // left page
            Page(offset = leftPageStartOffset, orientation = orientation) {
                children(items[leftPage])
            }
            // right page
            if (leftPage + 1 < itemCount) {
                Page(offset = leftPageStartOffset + pageSize, orientation = orientation) {
                    children(items[leftPage + 1])
                }
            }
        }
    }
}

@Composable
private fun Page(
    offset: Int,
    orientation: Orientation,
    children: @Composable StackScope.() -> Unit
) {
    val modifier = when (orientation) {
        Orientation.Horizontal -> Modifier.offsetGetter(x = { offset })
        Orientation.Vertical -> Modifier.offsetGetter(y = { offset })
    }
    Stack(
        modifier = modifier,
        children = children
    )
}