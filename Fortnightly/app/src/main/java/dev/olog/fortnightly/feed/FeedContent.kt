package dev.olog.fortnightly.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.composable.*
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.utils.exhaustive

@Preview
@Composable
private fun FeedContentPreview() {
    FortnightlyTheme {
        Column {
            StatusBar()
            FeedContent()
        }
    }
}

@Composable
fun FeedContent(onDrawerClick: () -> Unit = {}) {
    Surface(Modifier.fillMaxSize()) {
        val scrollState = rememberLazyListState()
        Box {
            FortnightlyList(FeedState.sample, scrollState)
            CollapsibleToolbar(
                scrollState = scrollState,
                onDrawerClick = onDrawerClick
            )
        }
    }
}

@Composable
private fun FortnightlyList(
    items: List<FeedState>,
    scrollState: LazyListState
) {
    val itemPadding = Modifier.padding(horizontal = 16.dp)
    val itemVerticalPadding = 28.dp
    LazyColumnFor(
        items = items,
        state = scrollState,
    ) { item ->
        when (item) {
            FeedState.ToolbarSpacer -> Spacer(modifier = Modifier.height(56.dp))
            is FeedState.Hashtags -> HashtagsContent(
                items = item.value,
                modifier = itemPadding.padding(vertical = 8.dp) // todo move padding inside
            )
            is FeedState.BigItem -> BigFeedItemContent(
                image = item.image,
                title = item.title,
                tags = item.tags,
                modifier = itemPadding.padding(bottom = itemVerticalPadding)
            )
            is FeedState.Item -> FeedItemContent(
                image = item.image,
                title = item.title,
                tags = item.tags,
                modifier = itemPadding.padding(vertical = itemVerticalPadding)
            )
        }.exhaustive
        if (item is FeedState.BigItem || item is FeedState.Item) {
            DottedDividerHorizontal(Modifier.fillMaxWidth().then(itemPadding))
        }
    }
}

