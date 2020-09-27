package dev.olog.crane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.crane.composable.Background
import dev.olog.crane.composable.stepper.Stepper
import dev.olog.crane.theme.CraneTheme
import dev.olog.crane.utils.toFloatPx
import java.util.*

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

    Background {
        Column {
            val toolbarHeight = 56.dp
            CraneMainToolbar(toolbarHeight, tabs, currentPage)
            Stack(Modifier.fillMaxSize()) {
                val itemHeight = 48.dp
                val itemSpacing = 8.dp

                CraneTabsActions(tabs[currentPage.value], itemHeight, itemSpacing)
                val items = if (tabs[currentPage.value] == Tab.Sleep) 3 else 4
                val listPaddingTop = itemHeight * items + itemSpacing * (items - 1) // TODO dynamic
                CraneViewPager(listPaddingTop + 16.dp)
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
    currentPage: MutableState<Int>
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

@Composable
private fun CraneViewPager(
    paddingTop: Dp
) {
    LazyColumnForIndexed(items = (0..10).toList().map { it.toString() }) { index, item ->
        if (index == 0) {
            Spacer(modifier = Modifier.height(paddingTop))
        } else {
            val shape = if (index == 1) MaterialTheme.shapes.large else RectangleShape
            Surface(
                Modifier.fillMaxWidth().height(80.dp),
                shape = shape
            ) {

            }
        }
    }
}