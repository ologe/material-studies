package dev.olog.material.studies.basil.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
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
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.compose.DottedLine
import dev.olog.material.studies.basil.compose.Stepper
import dev.olog.material.studies.basil.data.model.Recipe
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.animation.AnimationUtils
import dev.olog.material.studies.shared.animation.SlideVerticallyTransitionSpec
import dev.olog.material.studies.shared.fraction

enum class SheetTab {
    Ingredients,
    Directions,
}

@Composable
fun SheetContent(
    recipe: Recipe,
    sheetState: BottomSheetState,
    modifier: Modifier = Modifier,
    expand: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        var currentTab by remember { mutableStateOf(SheetTab.Ingredients) }
        Divider(
            color = BasilColors.primary50,
            modifier = Modifier.graphicsLayer {
                val fraction = sheetState.fraction
                alpha = 1 - AnimationUtils.translateToStart(fraction, .3f)
            }
        )
        Tabs {
            expand()
            currentTab = it
        }
        Column(
            Modifier.graphicsLayer {
                val fraction = sheetState.fraction
                alpha = AnimationUtils.translateToEnd(fraction, .4f)
            }
        ) {
            Divider(color = BasilColors.primary50)
            when (currentTab) {
                SheetTab.Ingredients -> Ingredients(recipe)
                SheetTab.Directions -> Directions(recipe)
            }
        }
    }
}

@Composable
private fun Tabs(
    modifier: Modifier = Modifier,
    expand: (SheetTab) -> Unit,
) {
    Row(modifier.height(BasilLayoutConstants.SheetHeight)) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(
                    onClick = { expand(SheetTab.Ingredients) },
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "INGREDIENTS",
                fontWeight = FontWeight.SemiBold,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(
                    onClick = { expand(SheetTab.Directions) },
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "DIRECTIONS",
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun Ingredients(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
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
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            imageVector = Icons.Default.AddCircleOutline,
            contentDescription = null,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
        )

        DottedLine(
            Modifier.weight(1f)
        )

        Text(
            text = quantity,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
        )
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
            .padding(16.dp)
    ) {
        AnimatedContent(
            modifier = Modifier.weight(1f).padding(end = 16.dp),
            targetState = selectedDirection,
            transitionSpec = SlideVerticallyTransitionSpec()
        ) {
            Column {
                val direction = recipe.instructions[it]
                Text(
                    text = direction,
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 35.sp,
                )
            }
        }
        Stepper(
            selected = selectedDirection,
            count = recipe.instructions.size,
            onSelectionChanged = {
                selectedDirection = it
            },
            render = {
                Text(
                    text = (it + 1).toString().padStart(2, '0'),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
            }
        )
    }
}