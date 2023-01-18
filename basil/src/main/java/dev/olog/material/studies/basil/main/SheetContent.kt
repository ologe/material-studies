package dev.olog.material.studies.basil.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.dp

enum class SheetTab {
    Ingredients,
    Directions,
}

@Composable
fun SheetContent(
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
            SheetTab.Ingredients -> Ingredients()
            SheetTab.Directions -> Directions()
        }
    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    expand: (SheetTab) -> Unit,
) {
    Row(Modifier.height(48.dp)) {
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
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Ingredient(text = "Basil", quantity = "6 tbsp")
        Ingredient(text = "Gluten-free Spaghetti", quantity = "2 cups")
        Ingredient(text = "Garlic", quantity = "1 tbsp")
        Ingredient(text = "Ricotta", quantity = "4 cups")
        Ingredient(text = "Kale", quantity = "3 cups")
        Ingredient(text = "Red Pepper Flakes", quantity = "1 tbsp")
        Ingredient(text = "Extra Virgin Olive Oil", quantity = "1 tbsp")
        Ingredient(text = "Salt", quantity = "1 tbsp")
        Ingredient(text = "Pine nuts", quantity = "1 tbsp")
    }
}

@Composable
private fun Directions(
    modifier: Modifier = Modifier,
) {

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