package dev.olog.fortnightly.composable

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.utils.toFloatPx
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
    modifier: Modifier = Modifier
) {
    ScrollableRow(
        modifier = modifier.drawLayer(translationY = 2.dp.toFloatPx()),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        for ((index, item) in tags.withIndex()) {
            TagText(item.toUpperCase(Locale.ROOT))

            // divider
            if (index != tags.lastIndex) {
                ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
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
        style = MaterialTheme.typography.overline,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center
    )
}