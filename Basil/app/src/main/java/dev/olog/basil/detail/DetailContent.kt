package dev.olog.basil.detail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import androidx.ui.tooling.preview.datasource.LoremIpsum
import dev.olog.basil.list.ListHeightFraction
import dev.olog.basil.list.ListHorizontalPadding
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.green500
import dev.olog.basil.utils.fakeClickable

@Composable
fun DetailContent(
    topPeek: Dp,
    bottomPeek: Dp,
    item: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float
) {
    Column(
        // made clickable so below content cannot be clicked
        modifier = Modifier.fillMaxSize().fakeClickable()
    ) {
        // title + description, same height as content list
        Stack(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = topPeek * 2) // no idea why * 2
                .fillMaxHeight(ListHeightFraction)
        ) {
            Stack(Modifier.fillMaxWidth().preferredHeight(bottomPeek)) {
                DownArrow(fraction)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RecipeTitle(item.title)
                // val threshold = 0.4f TODO slow start
                // val alpha = (fraction - threshold).coerceAtLeast(0f) * fraction
                DescriptionSpacer(fraction)
                Description(fraction)
            }
        }
    }
}

@Composable
private fun RecipeTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 32.dp),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.secondary,
    )
}

@Composable
private fun StackScope.DownArrow(fraction: Float) {
    Icon(
        asset = Icons.Default.KeyboardArrowDown,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .drawLayer(
                alpha = (1 - fraction * 3).coerceIn(0f, 1f),
                scaleX = 1.2f,
                scaleY = 1f
            ).padding(bottom = 8.dp)
    )
}

@Composable
private fun DescriptionSpacer(fraction: Float) {
    Spacer(
        Modifier
            .drawLayer(alpha = fraction)
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = ListHorizontalPadding)
            .background(green500)
    )
}

@Composable
private fun Description(fraction: Float) {
    Text(
        text = LoremIpsum(100).values.joinToString(),
        modifier = Modifier
            .fillMaxSize()
            .drawLayer(alpha = fraction)
            .padding(horizontal = ListHorizontalPadding + 12.dp),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}