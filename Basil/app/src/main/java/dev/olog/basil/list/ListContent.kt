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
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.chrisbanes.accompanist.coil.CoilImage
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
    LazyRowFor(items) {
        Stack(
            Modifier.fillMaxSize()
        ) {
            Stack(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(.5f)
                    .aspectRatio(1f)
                    .padding(32.dp)
            ) {
                CoilImage(
                    data = it.url,
                    modifier = Modifier.fillMaxSize(),
                    loading = {
                        CircularProgressIndicator()
                    }
                )
                if (it == selected) {
                    Scrim(fraction)
                }
            }
        }
    }
}

@Composable
private fun StackScope.Scrim(fraction: Float) {
    Box(Modifier
        .fillMaxWidth()
        .fillMaxHeight(fraction * 2f)
        .background(MaterialTheme.colors.surface)
        .align(Alignment.BottomCenter)
    )
}