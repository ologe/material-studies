package dev.olog.material.studies.basil.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.data.model.RecipeCategory
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.basil.theme.BasilTheme

@Composable
fun DrawerContent(
    categories: List<RecipeCategory>,
    selectedCategory: RecipeCategory,
    modifier: Modifier = Modifier,
    onCategoryChange: (RecipeCategory) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = null,
                colorFilter = ColorFilter.tint(BasilColors.primary800),
                modifier = Modifier
                    .border(3.dp, BasilColors.primary100, CircleShape)
                    .size(48.dp)
                    .padding(12.dp)
            )
            Text(
                text = "SHOPPING LIST",
                color = BasilColors.primary500,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier.width(100.dp),
                color = BasilColors.primary800,
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                for (category in categories) {
                    val isSelected = category == selectedCategory
                    Text(
                        text = category.toString().uppercase(),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 16.sp,
                        color = if (isSelected) BasilColors.primary800 else BasilColors.primary500,
                        modifier = Modifier.clickable(
                            indication = null,
                            onClick = {
                                onCategoryChange(category)
                            },
                            interactionSource = remember { MutableInteractionSource() }
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    BasilTheme {
        var category by remember {
            mutableStateOf(RecipeCategory.Desserts)
        }

        DrawerContent(
            categories = RecipeCategory.values().toList(),
            selectedCategory = category,
            onCategoryChange = { category = it }
        )
    }
}