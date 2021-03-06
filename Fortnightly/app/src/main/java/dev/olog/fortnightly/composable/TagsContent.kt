package dev.olog.fortnightly.composable

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.shared.MediumEmphasis
import dev.olog.shared.extension.MaterialTypography
import dev.olog.shared.extension.toFloatPx
import java.util.*

@Preview
@Composable
private fun TagsContentPreview() {
    FortnightlyTheme {
        Surface {
            TagsContent(
                tags = listOf("US", "Environment"),
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            )
        }
    }
}

@Composable
fun TagsContent(
    tags: List<String>,
    modifier: Modifier = Modifier.graphicsLayer(translationY = 2.dp.toFloatPx())
) {
    ScrollableRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        for ((index, item) in tags.withIndex()) {
            TagText(item.toUpperCase(Locale.ROOT))

            // divider
            if (index != tags.lastIndex) {
                MediumEmphasis {
                    TagText("¬")
                }
            }
        }
    }
}

@Composable
private fun TagText(title: String) {
    Text(
        text = title,
        style = MaterialTypography.overline,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center
    )
}