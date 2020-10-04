package dev.olog.fortnightly.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.res.imageResource
import dev.olog.fortnightly.R

sealed class FeedState {

    companion object {
        @Composable
        val sample: List<FeedState>
            get() = listOf(
                ToolbarSpacer,
                Hashtags(
                    listOf("TechDesign", "Reform", "HealthcareRevolution", "GreenArmy", "Stocks"),
                ),
                BigItem(
                    image = imageResource(R.drawable.img1),
                    title = "The Quiet, Yet Powerful Healthcare Revolution",
                    tags = listOf("World")
                ),
                Item(
                    image = imageResource(R.drawable.img2),
                    title = "Divided American Lives During War",
                    tags = listOf("Politics")
                ),
                Item(
                    image = imageResource(R.drawable.img3),
                    title = "Llmamas Patrol The Central Coast of California",
                    tags = listOf("US", "Environment")
                ),
                Item(
                    image = imageResource(R.drawable.img3),
                    title = "As Stocks Stagnate, Many Looks To Currency",
                    tags = listOf("World")
                ),
                Item(
                    image = imageResource(R.drawable.img1),
                    title = "Designers Use Tech To Make Futuristic Fabrics",
                    tags = listOf("Tech")
                ),
            )
    }

    object ToolbarSpacer : FeedState()

    data class Hashtags(
        val value: List<String>
    ) : FeedState()

    data class BigItem(
        val image: ImageAsset,
        val title: String,
        val tags: List<String>
    ) : FeedState()

    data class Item(
        val image: ImageAsset,
        val title: String,
        val tags: List<String>
    ) : FeedState()

}