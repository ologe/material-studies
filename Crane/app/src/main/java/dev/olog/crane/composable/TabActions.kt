package dev.olog.crane.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeableConstants
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.crane.Tab
import dev.olog.crane.composable.TabActionsConstants.itemHeight
import dev.olog.crane.composable.TabActionsConstants.itemSpacing
import dev.olog.crane.utils.animateSpec
import dev.olog.crane.utils.exhaustive

object TabActionsConstants {
    val itemHeight = 48.dp
    val itemSpacing = 8.dp
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CraneTabsActions(tab: Tab) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        CraneTabsActionOne(tab)
        CraneTabsActionTwo(tab)
        CraneTabsActionThree(tab)
        CraneTabsActionFour(tab)
    }
}

@Composable
private fun CraneTabsActionOne(tab: Tab) {
    val text = when (tab) {
        Tab.Fly -> "1 Adult, Economy"
        Tab.Sleep -> "1 Adult"
        Tab.Eat -> "1 Adult"
    }
    PeopleAction(text)
}

@Composable
private fun CraneTabsActionTwo(tab: Tab) {
    when (tab) {
        Tab.Fly -> LocationAction(Icons.Default.LocationOn, "Seoul, Korea")
        Tab.Sleep -> CalendarAction("")
        Tab.Eat -> CalendarAction("Sept 4")
    }.exhaustive
}

@Composable
private fun CraneTabsActionThree(tab: Tab) {
    when (tab) {
        Tab.Fly -> LocationAction(Icons.Default.Flight, "")
        Tab.Sleep -> LocationAction(Icons.Default.Hotel, "")
        Tab.Eat -> TimeAction("")
    }.exhaustive
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CraneTabsActionFour(tab: Tab) {
    AnimatedVisibility(
        visible = tab != Tab.Sleep,
        enter = fadeIn(animSpec = SwipeableConstants.animateSpec),
        exit = fadeOut(animSpec = SwipeableConstants.animateSpec)
    ) {
        when (tab) {
            Tab.Fly -> LocationAction(Icons.Default.LocalDining,"")
            Tab.Sleep -> {
                // TODO fade out don't work because it don't fade out last tab, but this one
            }
            Tab.Eat -> CalendarAction("")
        }.exhaustive
    }
}

@Composable
private fun PeopleAction(text: String) {
    CraneTabsAction(
        icon = Icons.Default.Person,
        text = text,
        placeholder = "no placeholder"
    )
}

@Composable
private fun LocationAction(
    icon: VectorAsset,
    text: String,
) {
    CraneTabsAction(
        icon = icon,
        text = text,
        placeholder = "Select Location"
    )
}

@Composable
private fun CalendarAction(text: String) {
    CraneTabsAction(
        icon = Icons.Default.CalendarToday,
        text = text,
        placeholder = "Select Dates"
    )
}

@Composable
private fun TimeAction(text: String) {
    CraneTabsAction(
        icon = Icons.Default.History,
        text = text,
        placeholder = "Select Time"
    )
}

@Composable
private fun CraneTabsAction(
    icon: VectorAsset,
    text: String,
    placeholder: String
) {
    Box(
        // TODO touches don't passes to this because list is on top
        modifier = Modifier.fillMaxWidth()
            .height(itemHeight)
            .clickable(onClick = {})
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.primary),
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