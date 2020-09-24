package dev.olog.basil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.SpringSpec
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import dev.olog.basil.composable.*
import dev.olog.basil.composable.viewpager.rememberViewPagerState
import dev.olog.basil.detail.DetailContent
import dev.olog.basil.detail.DetailTabDrawerState
import dev.olog.basil.drawer.DrawerContent
import dev.olog.basil.list.ListContent
import dev.olog.basil.model.Category
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.DrawerPagePreviewProvider
import dev.olog.basil.utils.screenHeightDp
import dev.olog.basil.utils.toIntPx
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasilTheme {
                MainActivityContent(Recipe.sample)
            }
        }
    }
}

@Preview
@Composable
private fun MainActivityContentPreview(
    @PreviewParameter(DrawerPagePreviewProvider::class) page: DrawerPage
) {
    BasilTheme {
        MainActivityContent(
            items = Recipe.sample,
            initialPage = page
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainActivityContent(
    items: List<Recipe>,
    initialPage: DrawerPage = DrawerPage.LIST,
    topPeek: Dp = 40.dp,
    bottomPeek: Dp = 300.dp
) {

    val currentCategory = savedInstanceState { Category.Entrees }

    // TODO change items based on category
    val viewPagerState = rememberViewPagerState(initialPage = 0)
    val swipeableState = rememberSwipeableState(
        initialValue = initialPage,
        animationSpec = SpringSpec(stiffness = 100f)
    )

    // TODO extract from BasilDrawer??
    val detailPageHeight = (screenHeightDp - bottomPeek).toIntPx()
    val drawerPageHeight = (screenHeightDp + topPeek).toIntPx()
    val detailFraction = (abs(swipeableState.detailOffset.toFloat()) / detailPageHeight).coerceIn(0f, 1f)
    val drawerFraction = (abs(swipeableState.drawerOffset.toFloat()) / drawerPageHeight).coerceIn(0f, 1f)
    val tabDrawerState = rememberSwipeableState(
        initialValue = DetailTabDrawerState.Collapsed,
        animationSpec = SpringSpec(stiffness = 150f)
    )

    Background {
        BasilDrawer(
            topPeek = topPeek,
            bottomPeek = bottomPeek,
            state = swipeableState,
            drawerContent = {
                DrawerContent(
                    selected = currentCategory,
                    drawerFraction = drawerFraction,
                )
            },
            listContent = {
                ListContent(
                    items = items,
                    viewPagerState = viewPagerState,
                    tabDrawerState = tabDrawerState,
                    drawerFraction = drawerFraction,
                    detailFraction = detailFraction
                )
            },
            detailContent = {
                // TODO use some sync viewpager for detail title?
                DetailContent(
                    topPeek = topPeek,
                    bottomPeek = bottomPeek,
                    items = items,
                    viewPagerState = viewPagerState,
                    tabDrawerState = tabDrawerState,
                    item = items[0],
                    detailFraction = detailFraction
                )
            }
        )
    }
}




