package dev.olog.fortnightly.composable

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.datasource.LoremIpsum
import dev.olog.fortnightly.feed.FeedState
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.shared.extension.MaterialTypography
import dev.olog.shared.extension.toFloatPx

@Preview
@Composable
private fun BigFeedItemPreview() {
    FortnightlyTheme {
        val item = FeedState.sample.filterIsInstance<FeedState.BigItem>().first()
        BigFeedItemContent(
            image = item.image,
            title = item.title,
            tags = item.tags,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun FeedItemSingleLinePreview() {
    FortnightlyTheme {
        val item = FeedState.sample.filterIsInstance<FeedState.Item>().first()
        FeedItemContent(
            image = item.image,
            title = "The Future of Gasoline",
            tags = item.tags,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun FeedItemMultiLinePreview() {
    FortnightlyTheme {
        val item = FeedState.sample.filterIsInstance<FeedState.Item>().first()
        FeedItemContent(
            image = item.image,
            title = item.title,
            tags = item.tags,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun FeedItemTextTooLongPreview() {
    FortnightlyTheme {
        val item = FeedState.sample.filterIsInstance<FeedState.Item>().first()
        FeedItemContent(
            image = item.image,
            title = LoremIpsum(50).values.joinToString(),
            tags = item.tags,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BigFeedItemContent(
    image: ImageAsset,
    title: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        FilterImage(
            asset = image,
            modifier = Modifier.fillMaxWidth().aspectRatio(1.35f)
        )
        TagsContent(
            tags = tags,
            modifier = Modifier.drawLayer(translationY = 5.dp.toFloatPx()),
        )
        Text(
            text = title,
            maxLines = 2,
            style = MaterialTypography.subtitle1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp
        )
    }
}

@Composable
fun FeedItemContent(
    image: ImageAsset,
    title: String,
    tags: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().height(76.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TagsContent(tags = tags)
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTypography.subtitle1,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
        FilterImage(
            asset = image,
            modifier = Modifier.aspectRatio(1f).fillMaxHeight()
        )
    }
}