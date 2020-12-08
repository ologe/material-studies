package dev.olog.basil.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.olog.basil.R
import dev.olog.basil.model.Allergen
import dev.olog.basil.theme.BasilTheme
import dev.olog.shared.extension.MaterialColors
import dev.olog.shared.extension.MaterialTypography
import dev.olog.basil.utils.AllergenPreviewProvider
import dev.olog.shared.extension.exhaustive

@Preview
@Composable
private fun AllergenRowDefaultPreview() {
    BasilTheme {
        AllergenRow(allergen = Allergen.EggFree)
    }
}

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
) {
    val iconModifier = Modifier.size(32.dp)
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
    asset: ImageVector,
    modifier: Modifier = Modifier,
    iconModifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(iconModifier) {
            Image(
                imageVector = asset,
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialColors.onPrimary)
            )
            Image(
                imageVector = vectorResource(R.drawable.vd_crossed_circle),
                modifier = Modifier.matchParentSize(),
                colorFilter = ColorFilter.tint(MaterialColors.secondary)
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(text = text, style = MaterialTypography.subtitle1)
    }
}