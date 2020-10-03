package dev.olog.fortnightly.composable

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRowForIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.feed.FeedState
import dev.olog.fortnightly.ui.FortnightlyTheme

@Preview
@Composable
private fun HashtagsContentPreview() {
    FortnightlyTheme {
        Surface {
            val item = FeedState.sample.filterIsInstance<FeedState.Hashtags>().first()
            HashtagsContent(items = item.value)
        }
    }
}

@Composable
fun HashtagsContent(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    // TODO content fade start and end
    LazyRowForIndexed(
        items = items,
        modifier = modifier.fillMaxWidth().height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) { index, item ->
        Text(
            text = "#$item",
            style = MaterialTheme.typography.body2,
        )
        if (index != item.lastIndex) {
            DottedDividerVertical(
                modifier = Modifier
                    .fillMaxHeight(.4f)
                    .padding(horizontal = 16.dp)
                    .drawLayer(translationY = 2.dp.toFloatPx())
            )
        }
    }
}