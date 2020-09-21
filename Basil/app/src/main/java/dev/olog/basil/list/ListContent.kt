package dev.olog.basil.list

import androidx.compose.foundation.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.BasilTheme

@Preview
@Composable
private fun ListContentPreview() {
    BasilTheme {
        ListContent(Recipe.sample, Recipe.sample.first(), 0f)
    }
}

@Composable
fun ListContent(
    items: List<Recipe>,
    selected: Recipe,
    fraction: Float
) {
    WithConstraints {
        LazyRowFor(
            items = items,
            modifier = Modifier.padding(bottom = maxHeight * 0.35f)
        ) {
            Stack(Modifier.fillMaxSize()) {
                Recipe(
                    item = it,
                    selected = selected,
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
            .padding(horizontal = 32.dp)
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