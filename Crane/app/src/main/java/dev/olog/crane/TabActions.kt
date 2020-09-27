package dev.olog.crane

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.crane.utils.exhaustive

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CraneTabsActions(
    tab: Tab,
    itemHeight: Dp,
    itemSpacing: Dp,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        CraneTabsActionOne(itemHeight, tab)
        CraneTabsActionTwo(itemHeight, tab)
        CraneTabsActionThree(itemHeight, tab)
        CraneTabsActionFour(itemHeight, tab)
    }
}

@Composable
private fun CraneTabsActionOne(
    height: Dp,
    tab: Tab,
) {
    val text = when (tab) {
        Tab.Fly -> "1 Adult, Economy"
        Tab.Sleep -> "1 Adult"
        Tab.Eat -> "1 Adult"
    }
    PeopleAction(height, text)
}

@Composable
private fun CraneTabsActionTwo(
    height: Dp,
    tab: Tab,
) {
    when (tab) {
        Tab.Fly -> LocationAction(height, Icons.Default.LocationOn, "Seoul, Korea")
        Tab.Sleep -> CalendarAction(height, "")
        Tab.Eat -> CalendarAction(height, "Sept 4")
    }.exhaustive
}

@Composable
private fun CraneTabsActionThree(
    height: Dp,
    tab: Tab,
) {
    when (tab) {
        Tab.Fly -> LocationAction(height, Icons.Default.Flight, "")
        Tab.Sleep -> LocationAction(height, Icons.Default.Hotel, "")
        Tab.Eat -> TimeAction(height, "")
    }.exhaustive
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CraneTabsActionFour(
    height: Dp,
    tab: Tab,
) {
    AnimatedVisibility(
        visible = tab != Tab.Sleep,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        when (tab) {
            Tab.Fly -> LocationAction(height = height, icon = Icons.Default.LocalDining, text = "")
            Tab.Sleep -> {}
            Tab.Eat -> CalendarAction(height = height, text = "")
        }.exhaustive
    }
}

@Composable
private fun PeopleAction(
    height: Dp,
    text: String
) {
    CraneTabsAction(
        height = height,
        icon = Icons.Default.Person,
        text = text,
        placeholder = "no placeholder"
    )
}

@Composable
private fun LocationAction(
    height: Dp,
    icon: VectorAsset,
    text: String,
) {
    CraneTabsAction(
        height = height,
        icon = icon,
        text = text,
        placeholder = "Select Location"
    )
}

@Composable
private fun CalendarAction(
    height: Dp,
    text: String,
) {
    CraneTabsAction(
        height = height,
        icon = Icons.Default.CalendarToday,
        text = text,
        placeholder = "Select Dates"
    )
}

@Composable
private fun TimeAction(
    height: Dp,
    text: String,
) {
    CraneTabsAction(
        height = height,
        icon = Icons.Default.History,
        text = text,
        placeholder = "Select Time"
    )
}

@Composable
private fun PlaceAction(
    height: Dp,
    text: String
) {
    CraneTabsAction(
        height = height,
        icon = Icons.Default.Person,
        text = text,
        placeholder = "Select a place"
    )
}

@Composable
private fun CraneTabsAction(
    height: Dp,
    icon: VectorAsset,
    text: String,
    placeholder: String
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(height),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            Modifier.fillMaxSize().padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                icon,
                colorFilter = ColorFilter.tint(color = if (text.isNotBlank()) Color.White else Color.White.copy(alpha = 0.4f))
            )
            Text(
                text = if (text.isNotBlank()) text else placeholder,
                color = if (text.isNotBlank()) Color.White else Color.White.copy(alpha = 0.4f),
                textAlign = TextAlign.Center
            )
        }
    }
}