package dev.olog.fortnightly.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowForIndexed
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import dev.olog.fortnightly.feed.FeedState
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.ui.librefranklyn
import dev.olog.shared.extension.MaterialTypography
import dev.olog.shared.extension.toFloatPx

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
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 12.dp,
) {
    // TODO content fade start and end
    LazyRowForIndexed(
        items = items,
        modifier = modifier.fillMaxWidth().height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) { index, item ->
        if (index == 0) {
            // padding start
            Spacer(modifier = Modifier.width(horizontalPadding))
        }
        Text(
            text = "#$item",
            style = MaterialTypography.body1,
            fontFamily = librefranklyn,
            letterSpacing = 0.em,
        )
        if (index != items.lastIndex) {
            DottedDividerVertical(
                modifier = Modifier
                    .fillMaxHeight(.4f)
                    .padding(horizontal = 16.dp)
                    .graphicsLayer(translationY = 2.dp.toFloatPx())
            )
        } else {
            // padding end
            Spacer(modifier = Modifier.width(horizontalPadding))
        }
    }
}