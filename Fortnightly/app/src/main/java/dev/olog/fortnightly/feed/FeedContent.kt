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
import dev.olog.shared.composable.StatusBar
import dev.olog.shared.extension.exhaustive

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
                onDrawerClick = onDrawerClick,
                modifier = Modifier.padding(horizontal = 6.dp)
            )
        }
    }
}

@Composable
private fun FortnightlyList(
    items: List<FeedState>,
    scrollState: LazyListState
) {
    val itemHorizontalPadding = 16.dp
    val itemVerticalPadding = 20.dp
    LazyColumnFor(
        items = items,
        state = scrollState,
    ) { item ->
        when (item) {
            FeedState.ToolbarSpacer -> Spacer(modifier = Modifier.height(toolbarHeight))
            is FeedState.Hashtags -> HashtagsContent(
                items = item.value,
                modifier = Modifier.padding(vertical = 2.dp),
                horizontalPadding = itemHorizontalPadding
            )
            is FeedState.BigItem -> BigFeedItemContent(
                image = item.image,
                title = item.title,
                tags = item.tags,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = itemVerticalPadding)
                    .padding(horizontal = itemHorizontalPadding)
            )
            is FeedState.Item -> FeedItemContent(
                image = item.image,
                title = item.title,
                tags = item.tags,
                modifier = Modifier.padding(
                    vertical = itemVerticalPadding,
                    horizontal = itemHorizontalPadding
                )
            )
        }.exhaustive
        if (item is FeedState.BigItem || item is FeedState.Item) {
            DottedDividerHorizontal(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = itemHorizontalPadding)
            )
        }
    }
}

