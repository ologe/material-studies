package dev.olog.basil.list

import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import dev.olog.basil.composable.ViewPager
import dev.olog.basil.composable.ViewPagerState
import dev.olog.basil.model.Recipe
import dev.olog.basil.utils.toIntPx

val ListHorizontalPadding = 32.dp
const val ListHeightFraction = 0.6f
val ListParallaxDp = 30.dp

@Composable
fun ListContent(
    items: List<Recipe>,
    state: ViewPagerState,
    fraction: Float
) {

    WithConstraints(Modifier
        .fillMaxWidth()
        .fillMaxHeight(ListHeightFraction)
    ) {
        ViewPager(
            items = items,
            state = state
        ) { item, itemFraction, _ ->
            Stack(Modifier.fillMaxSize()) {
                Recipe(
                    item = item,
                    maxWidth = maxWidth,
                    fraction = fraction,
                    itemFraction = itemFraction
                )
            }
        }
    }
}

@Composable
private fun StackScope.Recipe(
    item: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float,
    @FloatRange(0.0, 1.0) itemFraction: Float,
    maxWidth: Dp
) {
    Stack(
        Modifier
            .align(Alignment.Center)
            .width(maxWidth)
            .padding(horizontal = ListHorizontalPadding)
            .aspectRatio(1f)
            .clipToBounds()
    ) {
        Image(
            asset = item.image,
            modifier = Modifier.fillMaxSize().drawLayer(
                scaleY = 1.1f,
                scaleX = 1.1f,
                translationX = itemFraction * ListParallaxDp.toIntPx()
            )
        )
        Scrim(fraction)
    }
}

@Composable
private fun StackScope.Scrim(fraction: Float) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction * 2f)
            .background(MaterialTheme.colors.surface)
            .align(Alignment.BottomCenter)
    )
}