package dev.olog.fortnightly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import dev.olog.fortnightly.composable.FortnightlyToolbar
import dev.olog.fortnightly.ui.FortnightlyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FortnightlyTheme {
                FortnightlyContent()
            }
        }
    }
}

@Composable
private fun FortnightlyContent() {
    Surface {
        val scrollState = rememberScrollState()
        Stack {
            FortnightlyList(scrollState)
            FortnightlyToolbar(scrollState)
        }
    }
}

@Composable
private fun FortnightlyList(scrollState: ScrollState) {
    ScrollableColumn(
        scrollState = scrollState
    ) {
        for (index in (0..10)) {
            if (index == 0) {
                Spacer(modifier = Modifier.height(56.dp))
            }
            Stack(Modifier.fillMaxWidth().height(80.dp)) {
                Text(text = "item $index")
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
        }
    }
}