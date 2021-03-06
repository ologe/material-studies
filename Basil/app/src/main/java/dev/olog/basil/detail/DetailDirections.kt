package dev.olog.basil.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.basil.composable.stepper.VerticalStepper
import dev.olog.basil.R
import dev.olog.shared.extension.MaterialColors
import dev.olog.shared.extension.MaterialTypography

@Composable
fun RecipeDirections() {
    Row(Modifier.fillMaxSize()) {

        val text by remember {
            mutableStateOf(LoremIpsum(30).values.joinToString())
        }
        val currentPage = remember {
            mutableStateOf(0)
        }

        ScrollableColumn(
            Modifier.weight(1f)
                .padding(vertical = 24.dp)
                .padding(start = 24.dp, end = 12.dp)
        ) {
            // TODO slide animation on page change
            Text(
                text = text,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                bitmap = imageResource(id = R.drawable.cat1),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1.4f)
                    .fillMaxWidth()
            )
        }

        VerticalStepper(
            items = (1..8).toList(),
            currentPage = currentPage,
            modifier = Modifier.padding(16.dp).padding(top = 4.dp),
            indicatorModifier = Modifier.border(3.dp, MaterialColors.onPrimary, CircleShape),
        ) { item, _, underIndicator ->
            Text(
                text = item.toString().padStart(2, '0'),
                fontSize = 16.sp,
                style = MaterialTypography.body1,
                fontWeight = if (underIndicator) FontWeight.Bold else FontWeight.SemiBold,
                color = MaterialColors.onBackground,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}