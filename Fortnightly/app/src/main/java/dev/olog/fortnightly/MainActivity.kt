package dev.olog.fortnightly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import dev.olog.fortnightly.feed.FeedContent
import dev.olog.fortnightly.ui.FortnightlyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FortnightlyTheme {
                FeedContent()
            }
        }
    }
}
