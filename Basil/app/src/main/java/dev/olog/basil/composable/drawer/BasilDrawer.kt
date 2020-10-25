package dev.olog.basil.composable.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.unit.Dp
import dev.olog.basil.composable.drawer.DrawerPage.*
import dev.olog.basil.utils.*

@Composable
fun BasilDrawer(
    peekTop: Dp,
    peekBottom: Dp,
    initialValue: DrawerPage = LIST,
    state: SwipeableState<DrawerPage> = rememberSwipeableState(initialValue),
    drawerContent: @Composable () -> Unit,
    listContent: @Composable () -> Unit,
    detailContent: @Composable () -> Unit
) {
    // TODO remember?
    val anchors = mapOf(
        screenHeightPx.toFloat() to DRAWER,
        0f to LIST,
        -(screenHeightPx.toFloat() - peekBottom.toFloatPx()) to DETAIL,
    )

    Box(
        modifier = Modifier.swipeable(
            state = state,
            anchors = anchors,
            orientation = Orientation.Vertical,
            thresholds = { _, _ ->
                // TODO didn't understand too much this api, with this value, viewpager works half the time
                FractionalThreshold(0.8f)
            },
        ).offsetGetter(y = { state.drawerOffset })
    ) {
        val offsetGetter = Modifier.offsetGetter(y = { state.detailOffset })
        DrawerSlot(
            peek = peekTop,
            // move content when detail is opening
            modifier = offsetGetter,
            content = drawerContent
        )
        ListSlot(
            peek = peekTop,
            modifier = Modifier.align(Alignment.BottomCenter),
            content = listContent
        )
        DetailSlot(
            peek = peekBottom,
            // scroll detail
            modifier = offsetGetter,
            content = detailContent
        )
    }
}

@Composable
private fun DrawerSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .offset(y = -(screenHeightDp - peek * 0.5f))
            .fillMaxWidth()
            .height(screenHeightDp + peek)
    ) {
        content()
    }
}

@Composable
private fun ListSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(screenHeightDp - peek)
    ) {
        content()
    }
}

@Composable
private fun DetailSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .offset(y = screenHeightDp - peek)
            .fillMaxWidth()
            .height(screenHeightDp)
    ) {
        content()
    }
}