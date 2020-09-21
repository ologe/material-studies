package dev.olog.basil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FixedThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.basil.utils.toIntPx

enum class DrawerPage {
    DRAWER,
    LIST,
    DETAIL
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasilDrawer(
    peek: Dp,
    initialValue: DrawerPage = DrawerPage.LIST,
    drawerContent: @Composable StackScope.() -> Unit,
    listContent: @Composable StackScope.() -> Unit,
    detailContent: @Composable StackScope.() -> Unit
) {
    val screenHeightPx = ConfigurationAmbient.current.screenHeightDp.dp.toIntPx()
    val peekPx = peek.toIntPx()
    val state = rememberSwipeableState(
        initialValue = initialValue
    )
    val anchors = mapOf(
        screenHeightPx.toFloat() to DrawerPage.DRAWER,
        0f to DrawerPage.LIST,
        -peekPx.toFloat() to DrawerPage.DETAIL,
    )

    Stack(
        modifier = Modifier.swipeable(
            state = state,
            anchors = anchors,
            orientation = Orientation.Vertical,
            thresholds = { _, _ -> FixedThreshold(56.dp) }
        ).offsetPx(y = state.offset)
    ) {
        DrawerSlot(drawerContent)
        ListSlot(listContent)
        DetailSlot(peek, detailContent)
    }
}

@Composable
private fun DrawerSlot(
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = Modifier
            .offset(y = -(ConfigurationAmbient.current.screenHeightDp.dp))
            .fillMaxWidth()
            .height(ConfigurationAmbient.current.screenHeightDp.dp),
        children = content
    )
}

@Composable
private fun ListSlot(
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = Modifier
            .fillMaxWidth()
            .height(ConfigurationAmbient.current.screenHeightDp.dp),
        children = content
    )
}

@Composable
private fun DetailSlot(
    peek: Dp,
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = Modifier
            .offset(y = ConfigurationAmbient.current.screenHeightDp.dp - peek)
            .fillMaxWidth()
            .height(ConfigurationAmbient.current.screenHeightDp.dp),
        children = content
    )
}