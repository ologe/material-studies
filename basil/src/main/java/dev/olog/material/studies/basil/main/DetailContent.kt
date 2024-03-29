package dev.olog.material.studies.basil.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AirplaneTicket
import androidx.compose.material.icons.rounded.Egg
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import dev.olog.material.studies.basil.data.model.Recipe
import dev.olog.material.studies.basil.data.model.RecipeMacro
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.ResponsiveText

@Composable
fun DetailHeaderContent(
    pagerState: PagerState,
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        val recipe = recipes[it]
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                ResponsiveText(
                    text = recipe.name,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 100.sp,
                        hyphens = Hyphens.Auto,
                    ),
                    textAlign = TextAlign.Center,
                    color = BasilColors.secondary800,
                    maxLines = 2,
                    modifier = Modifier.scale(1.01f) // slightly increase font size
                )
            }

            Spacer(Modifier.height(BasilLayoutConstants.DownArrowSize))
        }
    }
}

@Composable
fun DetailDescriptionContent(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Divider(color = BasilColors.primary500)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            ResponsiveText(
                text = recipe.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 40.sp
                ),
                color = BasilColors.primary800,
            )
        }
    }
}

@Composable
fun DetailExtraContent(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Macros(
            macro = recipe.macro,
            modifier = Modifier.padding(horizontal = BasilLayoutConstants.ListPaddingPadding)
        )
        Divider(
            modifier = Modifier.padding(horizontal = BasilLayoutConstants.ListPaddingPadding),
            color = BasilColors.primary500,
        )
        Allergens(
            recipe = recipe,
            modifier = Modifier.padding(horizontal = BasilLayoutConstants.ListPaddingPadding)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun Macros(
    macro: RecipeMacro,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Macro(
            name = "Calories",
            value = "${macro.calories}g",
            modifier = Modifier.weight(1f),
        )
        Macro(
            name = "Protein",
            value = "${macro.protein}g",
            modifier = Modifier.weight(1f),
        )
        Macro(
            name = "Fat",
            value = "${macro.fat}g",
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun Macro(
    name: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle1,
            color = BasilColors.primary500,
            fontWeight = FontWeight.Light,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1,
            color = BasilColors.primary800,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun Allergens(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Allergen(
            icon = Icons.Rounded.AirplaneTicket,
            text = "Gluten-free",
            modifier = Modifier.weight(1f)
        )
        Allergen(
            icon = Icons.Rounded.Egg,
            text = "Egg Free",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun Allergen(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                imageVector = icon,
                contentDescription = null,
                colorFilter = ColorFilter.tint(BasilColors.primary800),
            )
            Text(
                text = text,
                color = BasilColors.primary800,
            )
        }
    }
}