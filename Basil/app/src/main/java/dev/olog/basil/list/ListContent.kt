package dev.olog.basil.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.CircularProgressIndicator
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
        ListContent(Recipe.sample)
    }
}

@Composable
fun ListContent(items: List<Recipe>) {
    LazyRowFor(items) {
        Stack(
            Modifier.fillMaxSize()
        ) {
            CoilImage(
                data = it.url,
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(.5f)
                    .aspectRatio(1f)
                    .padding(32.dp),
                loading = {
                    CircularProgressIndicator()
                },
            )
        }
    }
}