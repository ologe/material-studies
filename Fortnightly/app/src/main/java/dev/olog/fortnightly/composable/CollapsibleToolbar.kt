package dev.olog.fortnightly.composable

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.transition
import androidx.compose.foundation.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.onPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.utils.AnimationUtils.translateToStart
import dev.olog.fortnightly.utils.toIntPx

private val toolbarHeight = 56.dp

private enum class ToolbarState {
    Expanded,
    Collapsed
}

private val cornerRadius = DpPropKey("cornerRadius")
private val elevation = DpPropKey("elevation")
private val widthPercent = FloatPropKey("widthPercent")
private val alphaPercent = FloatPropKey("alphaPercent")
private val offsetPercent = FloatPropKey("offsetPercent")

private val definition = transitionDefinition<ToolbarState> {
    state(ToolbarState.Expanded) {
        this[cornerRadius] = 0.dp
        this[elevation] = 0.dp
        this[widthPercent] = 1f
        this[alphaPercent] = 1f
        this[offsetPercent] = 0f
    }
    state(ToolbarState.Collapsed) {
        this[cornerRadius] = 28.dp
        this[elevation] = 8.dp
        this[widthPercent] = 0f
        this[alphaPercent] = 0f
        this[offsetPercent] = 1f
    }
}

@Preview
@Composable
fun CollapsibleToolbarPreview() {
    FortnightlyTheme {
        CollapsibleToolbar(rememberLazyListState())
    }
}

@Composable
fun CollapsibleToolbar(
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    height: Dp = toolbarHeight,
    onDrawerClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
) {
    val heightPx = height.toIntPx()
    val canSeeFirstItem = scrollState.firstVisibleItemIndex == 0
    val moreThanToolbar = !canSeeFirstItem || (canSeeFirstItem && scrollState.firstVisibleItemScrollOffset > (heightPx / 3))
    val scrollValue by remember(moreThanToolbar) {
        mutableStateOf(if (moreThanToolbar) ToolbarState.Collapsed else ToolbarState.Expanded)
    }

    val state = transition(definition, scrollValue)

    val widthPercent = state[widthPercent]
    val cornerRadius = state[cornerRadius]
    val elevation = state[elevation]
    val offset = state[offsetPercent]
    val alpha = state[alphaPercent]

    Surface(
        modifier = Modifier
            .defaultMinSizeConstraints(minWidth = 92.dp)
            .fillMaxWidth(widthPercent)
            .height(height),
        shape = CutCornerShape(bottomRight = cornerRadius),
        elevation = elevation,
    ) {

        ConstraintLayout(
            modifier = modifier.fillMaxSize()
        ) {

            val (drawer, header, search) = createRefs()

            IconButton(
                onClick = onDrawerClick,
                modifier = Modifier.constrainAs(drawer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            ) {
                Icon(Icons.Default.Menu)
            }

            ToolbarFullText(
                offset = offset,
                alpha = alpha,
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(drawer.end)
                }
            )

            IconButton(
                modifier = Modifier
                    .constrainAs(search) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .drawLayer(alpha = state[alphaPercent]),
                onClick = onSearchClick
            ) {
                Icon(Icons.Default.Search, tint = MaterialTheme.colors.secondary)
            }
        }
    }
}

@Composable
private fun ToolbarFullText(
    offset: Float,
    alpha: Float,
    modifier: Modifier = Modifier,
) {
    var leftTextWidth by remember { mutableStateOf(0f) }

    Row(
        modifier = modifier
            .padding(bottom = 4.dp) // center text
            .drawLayer(translationX = offset * -leftTextWidth),
    ) {
        val leftTextAlpha = 1f - translateToStart(1f - alpha, .8f)
        FortnightlyText(
            text = "The ",
            modifier = Modifier
                .drawLayer(alpha = leftTextAlpha)
                .onPositioned {
                    leftTextWidth = it.size.width.toFloat()
                }
        )
        FortnightlyText(
            text = "F"
        )
        val rightTextAlpha = 1f - translateToStart(1f - alpha, .4f)
        if (rightTextAlpha >= 0.1f) {
            FortnightlyText(
                text = "ortnightly",
                modifier = Modifier.drawLayer(alpha = rightTextAlpha)
            )
        }
    }
}