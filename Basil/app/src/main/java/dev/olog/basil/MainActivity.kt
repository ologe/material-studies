package dev.olog.basil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.basil.composable.*
import dev.olog.basil.detail.DetailContent
import dev.olog.basil.drawer.DrawerContent
import dev.olog.basil.list.ListContent
import dev.olog.basil.list.Recipe
import dev.olog.basil.theme.BasilTheme
import dev.olog.basil.utils.toIntPx
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasilTheme {
                MainActivityContent()
            }
        }
    }
}

@Preview
@Composable
private fun MainActivityContentPreview() {
    BasilTheme {
        MainActivityContent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainActivityContent(
    peek: Dp = 200.dp
) {

    val items by remember {
        mutableStateOf(Recipe.sample)
    }
    val selected by remember {
        mutableStateOf(items.first())
    }

    val state = rememberSwipeableState(DrawerPage.LIST)

    // TODO extract from BasilDrawer??
    val detailPageHeight = (ConfigurationAmbient.current.screenHeightDp.dp - peek).toIntPx()

    Background {
        BasilDrawer(
            peek = peek,
            state = state,
            drawerContent = { DrawerContent() },
            listContent = {
                val fraction = abs(state.detailOffset.toFloat()) / detailPageHeight
                ListContent(items, selected, fraction)
            },
            detailContent = {
                DetailContent(selected)
            }
        )
    }
}






