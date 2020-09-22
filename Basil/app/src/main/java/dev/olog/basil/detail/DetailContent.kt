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
import dev.olog.basil.utils.AnimationUtils.translateToEnd
import dev.olog.basil.utils.AnimationUtils.translateToStart
import dev.olog.basil.utils.fakeClickable
import dev.olog.basil.utils.screenHeightDp
import java.util.*

private const val EAGER_END_THRESHOLD = 0.1f
private const val LATE_START_THRESHOLD = 0.6f

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
        val eagerEndAlpha = translateToStart(fraction, EAGER_END_THRESHOLD)
        val lateStartAlpha = translateToEnd(fraction, LATE_START_THRESHOLD)

        val lateStartAlphaModifier = Modifier.drawLayer(alpha = lateStartAlpha)

        // title + description, same height as content list
        UntilListContentImage(topPeek) {
            Stack(Modifier.fillMaxWidth().preferredHeight(bottomPeek)) {
                DownArrow(eagerEndAlpha)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                RecipeTitle(item.title)
                HorizontalSpacer(lateStartAlphaModifier)
                RecipeDescription(Modifier.weight(1f).then(lateStartAlphaModifier))
            }
        }
        RecipeMacros(
            modifier = Modifier
                .padding(horizontal = ListHorizontalPadding)
                .fillMaxWidth()
                .then(lateStartAlphaModifier)
        )
        HorizontalSpacer(lateStartAlphaModifier)
        RecipeAllergens(
            modifier = Modifier
                .padding(horizontal = ListHorizontalPadding)
                .fillMaxWidth()
                .then(lateStartAlphaModifier),
            allergens = item.allergens
        )
        Spacer(Modifier.weight(1f))
        Buttons()
    }
}

@Composable
private fun UntilListContentImage(
    peek: Dp,
    content: @Composable StackScope.() -> Unit
) {
    Stack(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = peek)
            .height(screenHeightDp * ListHeightFraction - peek),
        children = content
    )
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
private fun HorizontalSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = ListHorizontalPadding)
            .background(green500)
    )
}

@Composable
private fun RecipeDescription(
    modifier: Modifier = Modifier
) {
    Text(
        text = LoremIpsum(100).values.joinToString(),
        modifier = modifier.padding(horizontal = ListHorizontalPadding + 12.dp).padding(bottom = 32.dp),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RecipeMacros(modifier: Modifier = Modifier) {
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
private fun RecipeAllergens(
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
        Spacer(modifier = Modifier
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