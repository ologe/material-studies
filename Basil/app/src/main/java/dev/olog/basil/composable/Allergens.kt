package dev.olog.basil.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dev.olog.basil.R
import dev.olog.basil.model.Allergen
import dev.olog.basil.model.AllergenPreviewProvider
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.exhaustive

@Preview
@Composable
private fun AllergenRowPreview(
    @PreviewParameter(AllergenPreviewProvider::class) allergen: Allergen
) {
    BasilTheme {
        AllergenRow(
            allergen = allergen,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
fun AllergenRow(
    allergen: Allergen,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier.size(32.dp)
) {
    when (allergen) {
        Allergen.GlutenFree -> AllergenUi(
            text = "Gluten-free",
            asset = vectorResource(R.drawable.vd_leaf),
            modifier = modifier,
            iconModifier = iconModifier
        )
        Allergen.EggFree -> AllergenUi(
            text = "Egg free",
            asset = vectorResource(R.drawable.vd_egg),
            modifier = modifier,
            iconModifier = iconModifier
        )
    }.exhaustive
}

@Composable
private fun AllergenUi(
    text: String,
    asset: VectorAsset,
    modifier: Modifier = Modifier,
    iconModifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Stack(iconModifier) {
            Image(
                asset = asset,
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
            )
            Image(
                asset = vectorResource(R.drawable.vd_crossed_circle),
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.subtitle1)
    }
}