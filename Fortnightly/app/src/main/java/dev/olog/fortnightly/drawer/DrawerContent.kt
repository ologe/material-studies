package dev.olog.fortnightly.drawer

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.composable.FortnightlyText
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.ui.librefranklyn

@Preview
@Composable
private fun DrawerContentPreview() {
    FortnightlyTheme {
        Surface(Modifier.fillMaxSize()) {
            Column {
                DrawerContent()
            }
        }
    }
}

@Suppress("unused")
@Composable
fun ColumnScope.DrawerContent(
    onCloseClick: () -> Unit = {}
) {
    DrawerHeader(onCloseClick)

    var selectedCategory by remember {
        mutableStateOf(DrawerCategory.World)
    }

    ScrollableColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        for (value in DrawerCategory.values()) {
            DrawerItemCategory(
                category = value,
                isSelected = value == selectedCategory,
                onClick = { selectedCategory = it }
            )
        }
    }
}

@Composable
private fun DrawerHeader(
    onCloseClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconButton(onClick = onCloseClick) {
            Icon(asset = Icons.Default.Close)
        }
        FortnightlyText()
    }
}

@Composable
private fun DrawerItemCategory(
    category: DrawerCategory,
    isSelected: Boolean,
    onClick: (DrawerCategory) -> Unit = {}
) {
    val iconSize = 48.dp
    Column(Modifier.fillMaxWidth()) {
        // category
        CategoryContent(
            category = category,
            isSelected = isSelected,
            iconSize = iconSize,
            onClick = onClick
        )
        val isFrontPage = category == DrawerCategory.FrontPage
        // subcategory
        if (!isFrontPage) {
            SubCategoryContent(
                isSelected = isSelected,
                modifier = Modifier.padding(start = iconSize)
            )
        }
    }
}

@Composable
private fun CategoryContent(
    category: DrawerCategory,
    isSelected: Boolean,
    iconSize: Dp,
    onClick: (DrawerCategory) -> Unit = {}
) {
    val isFrontPage = category == DrawerCategory.FrontPage
    val iconRotation by remember(isSelected) {
        mutableStateOf(if (isSelected) 180f else 0f)
    }

    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick(category) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // icon
        Box(modifier = Modifier.size(iconSize)) {
            if (!isFrontPage) { // draw icon only when is not front page
                val tint = MaterialTheme.colors.onSurface
                Icon(
                    asset = Icons.Default.ArrowDropDown,
                    tint = if (isSelected) tint else tint.copy(alpha = 0.4f),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .drawLayer(rotationZ = animate(iconRotation))
                )
            }
        }

        // text
        Text(
            text = category.toString(),
            style = MaterialTheme.typography.h6,
            fontStyle = FontStyle.Normal,
            fontFamily = librefranklyn,
            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SubCategoryContent(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val animSpec by remember {
        mutableStateOf(tween<IntSize>())
    }
    AnimatedVisibility(
        visible = isSelected,
        enter = expandVertically(animSpec = animSpec),
        exit = shrinkVertically(animSpec = animSpec),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            for (value in DrawerSubCategory.values()) {
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    fontStyle = FontStyle.Normal,
                    fontFamily = librefranklyn,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}