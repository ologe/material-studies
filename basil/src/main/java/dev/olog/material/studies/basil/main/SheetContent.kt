package dev.olog.material.studies.basil.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.olog.material.studies.basil.Recipe
import dev.olog.material.studies.basil.compose.Stepper
import dev.olog.material.studies.shared.animation.SlideVerticallyTransitionSpec

enum class SheetTab {
    Ingredients,
    Directions,
}

@Composable
fun SheetContent(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    expand: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var currentTab by remember { mutableStateOf(SheetTab.Ingredients) }
        Divider() // TODO hide divider
        Tabs {
            expand()
            currentTab = it
        }
        // TODO add divider below whne expanded
        when (currentTab) {
            SheetTab.Ingredients -> Ingredients(recipe)
            SheetTab.Directions -> Directions(recipe)
        }
    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    expand: (SheetTab) -> Unit,
) {
    Row(modifier.height(48.dp)) {
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = { expand(SheetTab.Ingredients) },
        ) {
            Text(text = "Ingredients")
        }
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = { expand(SheetTab.Directions) },
        ) {
            Text(text = "Directions")
        }
    }
}

@Composable
private fun Ingredients(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        for (ingredient in recipe.ingredients) {
            Ingredient(text = ingredient.name, quantity = ingredient.quantity)
        }
    }
}

@Composable
private fun Ingredient(
    text: String,
    quantity: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Default.PlusOne,
            contentDescription = null,
        )
        Text(text = text)
        Canvas(
            modifier = Modifier.weight(1f),
            onDraw = {

            }
        )
        Text(text = quantity)
    }
}

@Composable
private fun Directions(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    var selectedDirection by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
    ) {
        AnimatedContent(
            modifier = Modifier.weight(1f),
            targetState = selectedDirection,
            transitionSpec = SlideVerticallyTransitionSpec()
        ) {
            Column {
                val direction = recipe.directions[it]
                Text(
                    text = direction.header,
                    style = MaterialTheme.typography.h4,
                )
                Text(text = direction.text)
            }
        }
        Stepper(
            selected = selectedDirection,
            count = recipe.directions.size,
            onSelectionChanged = {
                selectedDirection = it
            },
            render = {
                Text(
                    text = (it + 1).toString().padStart(2, '0'),
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        )
    }
}