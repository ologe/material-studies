package dev.olog.basil.composable.viewpager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.WithConstraints
import kotlin.math.floor

/**
 * @param content T item, float (0..1) fraction offset, bool true when is left item
 */
@Composable
fun <T> ViewPager(
    items: List<T>,
    modifier: Modifier = Modifier,
    state: ViewPagerState = rememberViewPagerState(initialPage = 0),
    orientation: Orientation = Orientation.Horizontal,
    isUserInputEnabled: Boolean = true,
    alignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.(T, Float, Boolean) -> Unit
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
            contentAlignment = alignment
        ) {
            val offset = floor(state.offset)
            val leftPage = (offset / pageSize).toInt() // left or center
            val leftPageStartOffset = leftPage * pageSize - offset

            val itemFraction = (state.offset % pageSize) / pageSize

            // left page
            Page(offset = leftPageStartOffset, orientation = orientation) {
                content(items[leftPage], itemFraction, true)
            }
            // right page
            if (leftPage + 1 < itemCount) {
                Page(offset = leftPageStartOffset + pageSize, orientation = orientation) {
                    content(items[leftPage + 1], itemFraction, false)
                }
            }
        }
    }
}

@Composable
private fun Page(
    offset: Float,
    orientation: Orientation,
    content: @Composable BoxScope.() -> Unit
) {
    val modifier = when (orientation) {
        Orientation.Horizontal -> Modifier.offset(x = { offset })
        Orientation.Vertical -> Modifier.offset(y = { offset })
    }
    Box(
        modifier = modifier,
        content = content
    )
}