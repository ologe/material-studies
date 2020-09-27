package dev.olog.fortnightly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
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

}