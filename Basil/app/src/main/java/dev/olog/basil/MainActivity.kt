package dev.olog.basil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.basil.composable.*
import dev.olog.basil.detail.DetailContent
import dev.olog.basil.drawer.DrawerContent
import dev.olog.basil.list.ListContent
import dev.olog.basil.model.Category
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.screenHeightDp
import dev.olog.basil.utils.toIntPx
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasilTheme {
                val items by remember { mutableStateOf(Recipe.sample) }
                MainActivityContent(items)
            }
        }
    }
}

@Preview
@Composable
private fun MainActivityContentPreview() {
    BasilTheme {
        MainActivityContent(Recipe.sample)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainActivityContent(
    items: List<Recipe>,
    topPeek: Dp = 40.dp,
    bottomPeek: Dp = 300.dp
) {

    val currentCategory = savedInstanceState { Category.Entrees }

    // TODO change items based on category
    val viewPagerState = rememberViewPagerState(initialPage = 0)
    val swipeableState = rememberSwipeableState(DrawerPage.LIST)

    // TODO extract from BasilDrawer??
    val detailPageHeight = (screenHeightDp - bottomPeek).toIntPx()
    val fraction = (abs(swipeableState.detailOffset.toFloat()) / detailPageHeight).coerceIn(0f, 1f)

    Background {
        BasilDrawer(
            topPeek = topPeek,
            bottomPeek = bottomPeek,
            state = swipeableState,
            drawerContent = { DrawerContent(currentCategory) },
            listContent = {
                ListContent(
                    items = items,
                    state = viewPagerState,
                    fraction = fraction
                )
            },
            detailContent = {
                // TODO use some sync viewpager for detail title?
                DetailContent(
                    topPeek = topPeek,
                    bottomPeek = bottomPeek,
                    items = items,
                    state = viewPagerState,
                    item = items[0],
                    fraction = fraction
                )
            }
        )
    }
}




