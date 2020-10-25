package dev.olog.basil.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dev.olog.basil.R
import dev.olog.basil.model.Allergen
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.theme.MaterialColors
import dev.olog.basil.theme.MaterialTypography
import dev.olog.basil.utils.AllergenPreviewProvider
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
        Allergen.GlutenFree -> AllergenContent(
            text = stringResource(R.string.allergen_gluten_free),
            asset = vectorResource(R.drawable.vd_leaf),
            modifier = modifier,
            iconModifier = iconModifier
        )
        Allergen.EggFree -> AllergenContent(
            text = stringResource(R.string.allergen_egg_free),
            asset = vectorResource(R.drawable.vd_egg),
            modifier = modifier,
            iconModifier = iconModifier
        )
    }.exhaustive
}

@Composable
private fun AllergenContent(
    text: String,
    asset: VectorAsset,
    modifier: Modifier = Modifier,
    iconModifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(iconModifier) {
            Image(
                asset = asset,
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialColors.onPrimary)
            )
            Image(
                asset = vectorResource(R.drawable.vd_crossed_circle),
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialColors.secondary)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(text = text, style = MaterialTypography.subtitle1)
    }
}