package dev.olog.crane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.olog.crane.composable.Background
import dev.olog.crane.composable.stepper.Stepper
import dev.olog.crane.theme.CraneTheme
import dev.olog.crane.utils.toFloatPx
import dev.olog.crane.utils.toIntPx
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
    Background {
        Column {
            CraneMainToolbar()
            CraneViewPager()
        }
    }
}

enum class Tabs {
    Fly,
    Sleep,
    Eat
}

@Composable
private fun CraneMainToolbar() {
    Row(Modifier.fillMaxWidth().height(56.dp)) {
        IconButton(onClick = {} ) {
            Icon(asset = Icons.Rounded.Menu)
        }
        Stack(Modifier.weight(1f).fillMaxHeight()) {
            Stepper(
                items = Tabs.values().toList(),
                modifier = Modifier.fillMaxSize().padding(8.dp),
            ) { item, index, selected ->
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
private fun CraneViewPager() {

}