package dev.olog.basil.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.olog.basil.R
import dev.olog.basil.composable.Background
import dev.olog.basil.model.Category
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.ParallaxUtils.DrawerParallaxDp
import dev.olog.shared.extension.MaterialColors
import dev.olog.shared.extension.MaterialTypography
import dev.olog.shared.extension.toDp
import dev.olog.shared.extension.toFloatPx
import dev.olog.shared.extension.toIntPx
import java.util.*

@Preview
@Composable
private fun DrawerContentPreview() {
    BasilTheme {
        Background {
            val state = mutableStateOf(Category.Entrees)
            DrawerContent(state, 1f)
        }
    }
}

@Composable
fun DrawerContent(
    selected: MutableState<Category>,
    drawerFraction: Float,
) {

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawerHeader(drawerFraction)
            DrawerCategories(selected, drawerFraction)
        }

        Text(
            text = "BASiL",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTypography.h2,
        )
    }
}

@Composable
private fun DrawerHeader(
    drawerFraction: Float,
) {
    Column(
        modifier = Modifier.parallax(Category.values().size, drawerFraction),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(96.dp))
        Image(
            imageVector = vectorResource(R.drawable.vd_shopping_list),
            modifier = Modifier.size(48.dp),
            colorFilter = ColorFilter.tint(MaterialColors.onPrimary)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.shopping_list).toUpperCase(Locale.getDefault()),
            style = MaterialTypography.h5,
            fontWeight = FontWeight.Normal,
        )
        Spacer(Modifier.height(64.dp))
        // divider
        Spacer(Modifier.width(96.dp)
            .height(1.dp)
            .background(MaterialColors.onPrimary)
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun DrawerCategories(
    selected: MutableState<Category>,
    drawerFraction: Float,
) {
    val categories = Category.values()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for ((index, category) in categories.withIndex()) {
            val inverseIndex = categories.size - index
            DrawerCategory(
                text = category.textify(),
                isSelected = category == selected.value,
                modifier = Modifier.parallax(inverseIndex, drawerFraction),
                onClick = { selected.value = category }
            )
        }
    }
}

@Composable
private fun DrawerCategory(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = onClick,
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var width by remember { mutableStateOf(0) }

        Text(
            text = text.toUpperCase(Locale.getDefault()),
            style = MaterialTypography.h5,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(24.dp)
                .onGloballyPositioned {
                    width = it.size.width
                }
        )

        if (isSelected) {
            // TODO now showing in the preview
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(width.toDp())
                    .graphicsLayer(translationY = -20.dp.toIntPx().toFloat())
                    .background(MaterialColors.onPrimary)
            )
        }
    }

}

/**
 * @param position position in layout, 0 bottom, n top
 * n
 * n - 1
 * ..
 * 1
 * 0
 */
private fun Modifier.parallax(position: Int, fraction: Float): Modifier = composed {
    this.then(
        Modifier.graphicsLayer(
            translationY = -DrawerParallaxDp.toFloatPx() * position * (1f - fraction)
        )
    )
}

@Composable
private fun Category.textify(): String = when (this) {
    Category.Appetizers -> stringResource(R.string.category_appetizers)
    Category.Entrees -> stringResource(R.string.category_entrees)
    Category.Desserts -> stringResource(R.string.category_desserts)
    Category.Cocktails -> stringResource(R.string.category_cocktails)
}