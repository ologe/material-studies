package dev.olog.basil.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.unit.Dp
import dev.olog.basil.composable.DrawerPage.*
import dev.olog.basil.utils.offsetGetter
import dev.olog.basil.utils.screenHeightDp
import dev.olog.basil.utils.screenHeightPx
import dev.olog.basil.utils.toIntPx

enum class DrawerPage {
    DRAWER,
    LIST,
    DETAIL
}

@OptIn(ExperimentalMaterialApi::class)
val SwipeableState<DrawerPage>.drawerOffset: Int
    get() = offset.value.toInt().coerceAtLeast(0)

@OptIn(ExperimentalMaterialApi::class)
val SwipeableState<DrawerPage>.detailOffset: Int
    get() = offset.value.toInt().coerceAtMost(0)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasilDrawer(
    topPeek: Dp,
    bottomPeek: Dp,
    initialValue: DrawerPage = LIST,
    state: SwipeableState<DrawerPage> = rememberSwipeableState(initialValue),
    drawerContent: @Composable StackScope.() -> Unit,
    listContent: @Composable StackScope.() -> Unit,
    detailContent: @Composable StackScope.() -> Unit
) {
    val bottomPeekPx = bottomPeek.toIntPx()
    // TODO remember?
    val anchors = mapOf(
        screenHeightPx.toFloat() to DRAWER,
        0f to LIST,
        -(screenHeightPx - bottomPeekPx).toFloat() to DETAIL,
    )

    Stack(
        modifier = Modifier.swipeable(
            state = state,
            anchors = anchors,
            orientation = Orientation.Vertical,
            thresholds = { _, _ ->
                FractionalThreshold(0.3f)
            }
        ).offsetGetter(getY = { state.drawerOffset })
    ) {
        val offsetGetter = Modifier.offsetGetter(getY = { state.detailOffset })
        DrawerSlot(
            peek = topPeek,
            // move content when detail is opening
            modifier = offsetGetter,
            content = drawerContent
        )
        ListSlot(
            peek = topPeek,
            modifier = Modifier.align(Alignment.BottomCenter),
            content = listContent
        )
        DetailSlot(
            peek = bottomPeek,
            // scroll detail
            modifier = offsetGetter,
            content = detailContent
        )
    }
}

// TODO content don't start from top, that gap is = peek.dp
@Composable
private fun DrawerSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = modifier then Modifier
            .offset(y = -(ConfigurationAmbient.current.screenHeightDp.dp - peek))
            .fillMaxWidth()
            .height(ConfigurationAmbient.current.screenHeightDp.dp),
        children = content
    )
}

@Composable
private fun ListSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = modifier then Modifier
            .fillMaxWidth()
            .height(screenHeightDp - peek),
        children = content
    )
}

@Composable
private fun DetailSlot(
    peek: Dp,
    modifier: Modifier = Modifier,
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = modifier then Modifier
            .offset(y = screenHeightDp - peek)
            .fillMaxWidth()
            .height(screenHeightDp),
        children = content
    )
}