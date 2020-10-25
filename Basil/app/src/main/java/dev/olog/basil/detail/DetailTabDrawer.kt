package dev.olog.basil.detail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import dev.olog.basil.detail.DetailTabDrawerState.Collapsed
import dev.olog.basil.detail.DetailTabDrawerState.Expanded
import dev.olog.shared.extension.MaterialColors
import dev.olog.shared.utils.AnimationUtils
import dev.olog.basil.utils.exhaustive
import dev.olog.basil.utils.screenHeightPx
import dev.olog.basil.utils.toIntPx
import java.util.*

enum class DetailTabDrawerState {
    Expanded,
    Collapsed
}

enum class DetailTabDrawerPage {
    Ingredients,
    Directions
}

@Composable
fun DetailTabDrawer(
    state: SwipeableState<DetailTabDrawerState> = rememberSwipeableState(Collapsed),
    modifier: Modifier = Modifier,
    buttonsHeight: Dp = 64.dp,
) {
    val anchors = mapOf(
        (screenHeightPx.toFloat() - buttonsHeight.toIntPx()) to Collapsed,
        0f to Expanded
    )

    var page by remember { mutableStateOf(DetailTabDrawerPage.Directions) }

    Box(
        modifier
            .offsetPx(y = state.offset)
            .swipeable(
                state = state,
                anchors = anchors,
                thresholds = { _, _ -> FixedThreshold(56.dp) },
                orientation = Orientation.Vertical,
                enabled = state.value == Expanded
            )
            .fillMaxSize()
            .background(MaterialColors.background.copy(alpha = .95f))
            .clickable(indication = null) {

            }
    ) {
        Buttons(state.progress.offset) {
            page = it
            state.animateTo(Expanded)
        }

        if (state.progress.isCollapsed) {
            return@Box
        }

        Column(Modifier.drawLayer(
            alpha = AnimationUtils.translateToEnd(state.progress.offset, 0.4f))) {
            Spacer(modifier = Modifier.height(buttonsHeight))
            Crossfade(page) { // TODO animate slide
                when (page) {
                    DetailTabDrawerPage.Ingredients -> RecipeIngredients()
                    DetailTabDrawerPage.Directions -> RecipeDirections()
                }.exhaustive
            }

        }
    }
}

@Composable
private fun Buttons(
    @FloatRange(0.0, 1.0) offset: Float,
    onClick: (DetailTabDrawerPage) -> Unit
) {

    Column {
        Spacer(
            modifier = Modifier
                .drawLayer(alpha = 1 - AnimationUtils.translateToStart(offset, .2f))
                .background(Color.Black.copy(.2f))
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(
            Modifier.fillMaxWidth().height(56.dp),
        ) {
            Button("Ingredients".toUpperCase(Locale.ROOT)) {
                onClick(DetailTabDrawerPage.Ingredients)
            }
            Button("Directions".toUpperCase(Locale.ROOT)) {
                onClick(DetailTabDrawerPage.Directions)
            }
        }
        Spacer(
            modifier = Modifier
                .drawLayer(alpha = AnimationUtils.translateToEnd(offset, 0.6f))
                .background(MaterialColors.onBackground.copy(alpha = .2f))
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Composable
private fun RowScope.Button(
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.weight(1f).fillMaxHeight()
    ) {
        Text(text)
    }
}


val SwipeProgress<DetailTabDrawerState>.isExpanded: Boolean
    get() = from == Expanded && to == Expanded

val SwipeProgress<DetailTabDrawerState>.isCollapsed: Boolean
    get() = from == Collapsed && to == Collapsed

val SwipeProgress<DetailTabDrawerState>.isExpanding: Boolean
    get() = from == Collapsed && to == Expanded

val SwipeProgress<DetailTabDrawerState>.isCollapsing: Boolean
    get() = from == Expanded && to == Collapsed

val SwipeProgress<DetailTabDrawerState>.offset: Float
    get() {
        return when {
            isExpanded -> 1f
            isExpanding -> fraction
            isCollapsed -> 0f
            isCollapsing -> 1 - fraction
            else -> throw IllegalArgumentException("invalid $this")
        }
    }