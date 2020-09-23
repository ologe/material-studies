package dev.olog.basil.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.onPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.basil.R
import dev.olog.basil.composable.Background
import dev.olog.basil.model.Category
import dev.olog.basil.model.Category.Entrees
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.toDp
import dev.olog.basil.utils.toIntPx
import java.util.*

@Preview
@Composable
private fun DrawerContentPreview() {
    BasilTheme {
        Background {
            val state = mutableStateOf(Entrees)
            DrawerContent(state)
        }
    }
}

@Composable
fun DrawerContent(
    selected: MutableState<Category>
) {

    Stack(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DrawerHeader()
            DrawerCategories(selected)
        }

        Text(
            text = "BASiL",
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.h2,
        )
    }
}

@Composable
private fun DrawerHeader() {
    Spacer(Modifier.height(96.dp))
    Image(
        asset = vectorResource(R.drawable.vd_shopping_list),
        modifier = Modifier.size(48.dp),
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
    )
    Spacer(Modifier.height(16.dp))
    Text(
        text = "Shopping List".toUpperCase(Locale.ROOT),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Normal,
    )
    Spacer(Modifier.height(64.dp))
    // divider
    Spacer(Modifier.width(96.dp)
        .height(1.dp)
        .background(MaterialTheme.colors.onPrimary)
    )
    Spacer(Modifier.height(24.dp))
}

@Composable
private fun DrawerCategories(
    selected: MutableState<Category>
) {
    val categories = Category.values().toList()

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (category in categories) {
            DrawerCategory(category.toString(), category == selected.value) {
                selected.value = category
            }
        }
    }
}

@Composable
private fun DrawerCategory(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                onClick = onClick,
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var width by remember { mutableStateOf(0) }

        Text(
            text = text.toUpperCase(Locale.ROOT),
            style = MaterialTheme.typography.h5,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier
                .padding(24.dp)
                .onPositioned {
                    width = it.size.width
                }
        )

        if (isSelected) {
            // TODO now showing in the preview
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .width(width.toDp())
                    .drawLayer(translationY = -20.dp.toIntPx().toFloat())
                    .background(MaterialTheme.colors.onPrimary)
            )
        }
    }

}