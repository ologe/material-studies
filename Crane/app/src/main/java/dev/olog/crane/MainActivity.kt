package dev.olog.crane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import dev.olog.crane.composable.Background
import dev.olog.crane.composable.CraneTabsActions
import dev.olog.crane.composable.TabActionsConstants.itemHeight
import dev.olog.crane.composable.TabActionsConstants.itemSpacing
import dev.olog.crane.composable.stepper.Stepper
import dev.olog.crane.composable.viewpager.ViewPager
import dev.olog.crane.composable.viewpager.ViewPagerState
import dev.olog.crane.composable.viewpager.rememberViewPagerState
import dev.olog.crane.model.ExploreModel
import dev.olog.crane.model.craneDestinations
import dev.olog.crane.model.craneHotels
import dev.olog.crane.model.craneRestaurants
import dev.olog.crane.theme.CraneTheme
import dev.olog.crane.utils.exhaustive
import dev.olog.crane.utils.toFloatPx
import dev.olog.shared.MediumEmphasis
import java.util.*

// TODO draw over navigation bar + made it transaprent
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CraneTheme {
                CraneContent()
            }
        }
    }
}

@Composable
private fun CraneContent() {
    val tabs = Tab.values().toList()
    val currentPage = remember {
        mutableStateOf(0)
    }

    val viewPagerState = rememberViewPagerState(initialPage = 0)

    Background {
        Column {
            val toolbarHeight = 56.dp
            CraneMainToolbar(toolbarHeight, tabs, currentPage, viewPagerState)
            Stack(Modifier.fillMaxSize()) {
                CraneTabsActions(tabs[currentPage.value])
                CraneViewPager(viewPagerState)
            }
        }
    }
}

enum class Tab {
    Fly,
    Sleep,
    Eat
}

@Composable
private fun CraneMainToolbar(
    height: Dp,
    tabs: List<Tab>,
    currentPage: MutableState<Int>,
    state: ViewPagerState
) {
    Row(Modifier.fillMaxWidth().height(height).padding(end = 16.dp)) {
        Image(vectorResource(R.drawable.vd_menu), Modifier.fillMaxHeight())
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            asset = vectorResource(R.drawable.vd_crane_logo),
            modifier = Modifier.fillMaxHeight().drawLayer(translationY = 4.dp.toFloatPx())
        )
        Spacer(modifier = Modifier.width(24.dp))
        Stack(Modifier.weight(1f).fillMaxHeight()) {
            Stepper(
                items = tabs,
                currentPage = currentPage,
                modifier = Modifier.fillMaxSize().padding(8.dp),
                onClick = { _, index -> state.animateTo(index) }
            ) { item, _, selected ->
                val textColor = MaterialTheme.colors.onBackground
                Text(
                    text = item.toString().toUpperCase(Locale.ROOT),
                    color = if (selected) textColor else textColor.copy(alpha = 0.6f),
                    modifier = Modifier.drawLayer(translationY = -2.dp.toFloatPx()),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

sealed class MainActivityState {

    object Padding: MainActivityState()
    data class Header(val text: String): MainActivityState()
    data class Item(val item: ExploreModel): MainActivityState()

}

@OptIn(ExperimentalStdlibApi::class)
@Composable
private fun CraneViewPager(state: ViewPagerState) {
    ViewPager(
        items = Tab.values().toList(),
        state = state,
        isUserInputEnabled = false
    ) { tab ->
        val actionsCount = if (tab == Tab.Sleep) 3 else 4
        val tabActionsSize = itemHeight * actionsCount + itemSpacing * (actionsCount - 1)
        val paddingTop = tabActionsSize + 24.dp

        val exploreModel = when (tab) {
            Tab.Fly -> craneDestinations
            Tab.Sleep -> craneHotels
            Tab.Eat -> craneRestaurants
        }
        val items = buildList {
            add(MainActivityState.Padding)
            add(MainActivityState.Header(buildHeaderTitle(tab)))
            addAll(exploreModel.map { MainActivityState.Item(it) })
        }

        LazyColumnFor(items) { item ->
            when (item) {
                is MainActivityState.Padding -> Spacer(modifier = Modifier.height(paddingTop))
                is MainActivityState.Header -> ListHeader(item.text)
                is MainActivityState.Item -> ListItem(item.item)
            }.exhaustive
        }
        // TODO item divider
    }
}

private fun buildHeaderTitle(tab: Tab) = when (tab) {
    Tab.Fly -> "Explore Flights by Destination"
    Tab.Sleep -> "Explore Properties by Destination"
    Tab.Eat -> "Explore Restaurants by Destination"
}

@Composable
private fun ListHeader(text: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.large
    ) {
        MediumEmphasis {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
private fun ListItem(item: ExploreModel) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable(onClick = {})
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CoilImageWithCrossfade(
                data = item.imageUrl,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop,
                loading = {
                    Spacer(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primary))
                }
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = item.city.nameToDisplay,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                MediumEmphasis {
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}