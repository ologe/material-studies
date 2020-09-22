package dev.olog.basil.detail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.weight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import androidx.ui.tooling.preview.datasource.LoremIpsum
import dev.olog.basil.composable.AllergenRow
import dev.olog.basil.list.ListHeightFraction
import dev.olog.basil.list.ListHorizontalPadding
import dev.olog.basil.model.Allergen
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.green500
import dev.olog.basil.utils.fakeClickable
import java.util.*

@Composable
fun DetailContent(
    topPeek: Dp,
    bottomPeek: Dp,
    item: Recipe,
    @FloatRange(0.0, 1.0) fraction: Float
) {
    Column(
        // made clickable so below content cannot be clicked
        modifier = Modifier.fillMaxSize().fakeClickable(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // title + description, same height as content list
        Stack(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = topPeek)
                .fillMaxHeight(ListHeightFraction * 0.9f) // TODO mmm check on different screen sizes
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
                Spacer(fraction)
                Description(fraction)
            }
        }
        Macros(
            modifier = Modifier
                .padding(horizontal = ListHorizontalPadding)
                .fillMaxWidth()
        )
        Spacer(1f)
        Allergens(
            modifier = Modifier
                .padding(horizontal = ListHorizontalPadding)
                .fillMaxWidth(),
            allergens = item.allergens
        )
        Spacer(Modifier.weight(1f))
        Buttons()
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
private fun Spacer(fraction: Float) {
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

@Composable
private fun Macros(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val innerModifier = Modifier.weight(1f)
        Macro("Calories", "465g", innerModifier)
        Spacer(Modifier.width(1.dp).height(64.dp).background(green500.copy(alpha = .2f)))
        Macro("Protein", "27g", innerModifier)
        Spacer(Modifier.width(1.dp).height(64.dp).background(green500.copy(alpha = .2f)))
        Macro("Fat", "12g", innerModifier)
    }
}

@Composable
private fun Macro(
    title: String,
    amount: String,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.subtitle1)
        Text(text = amount, style = MaterialTheme.typography.subtitle2)
    }
}

@Composable
private fun Allergens(
    allergens: Set<Allergen>,
    modifier: Modifier = Modifier
) {
    // TODO probably a better layout should be a flex box
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (allergen in allergens) {
            AllergenRow(allergen)
        }
    }
}

@Composable
private fun Buttons() {
    Stack {
        Spacer(modifier = Modifier // TODO spacer is too high
            .background(Color.Black.copy(.2f))
            .fillMaxWidth()
            .height(1.dp)
        )
        Row(
            Modifier.fillMaxWidth().height(56.dp),
        ) {
            Button("Ingredients".toUpperCase(Locale.ROOT))
            Button("Directions".toUpperCase(Locale.ROOT))
        }
    }
}

@Composable
private fun Button(
    text: String
) {
    TextButton(onClick = {},
        Modifier.weight(1f)
            .fillMaxHeight()
    ) {
        Text(text)
    }
}