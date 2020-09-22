package dev.olog.basil.list

import androidx.compose.foundation.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.olog.basil.composable.ViewPager
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.BasilTheme

val ListHorizontalPadding = 32.dp
const val ListHeightFraction = 0.6f

@Preview
@Composable
private fun ListContentPreview() {
    BasilTheme {
        ListContent(Recipe.sample, mutableStateOf(0), 0f)
    }
}

@Composable
fun ListContent(
    items: List<Recipe>,
    currentPage: MutableState<Int> ,
    fraction: Float
) {
    WithConstraints(Modifier
        .fillMaxWidth()
        .fillMaxHeight(ListHeightFraction)
    ) {
        ViewPager(
            items = items,
            currentPage
        ) { index, item ->
            Stack(Modifier.fillMaxSize()) {
                Recipe(
                    item = item,
                    selected = items[currentPage.value],
                    fraction = fraction,
                    maxWidth = maxWidth
                )
            }
        }
    }
}

@Composable
private fun StackScope.Recipe(
    item: Recipe,
    selected: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float,
    maxWidth: Dp
) {
    Stack(
        Modifier
            .align(Alignment.Center)
            .width(maxWidth)
            .padding(horizontal = ListHorizontalPadding)
            .aspectRatio(1f)
    ) {
        CoilImage(
            data = item.url,
            modifier = Modifier.fillMaxSize(),
            loading = {
                Stack(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
        )
        if (item == selected) {
            Scrim(fraction)
        }
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