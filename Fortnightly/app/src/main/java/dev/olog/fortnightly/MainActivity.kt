package dev.olog.fortnightly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.DrawerConstants.ScrimDefaultOpacity
import androidx.compose.ui.platform.setContent
import dev.olog.fortnightly.drawer.DrawerContent
import dev.olog.fortnightly.feed.FeedContent
import dev.olog.fortnightly.ui.FortnightlyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FortnightlyTheme {
                val state = rememberDrawerState(DrawerValue.Closed)
                ModalDrawerLayout(
                    drawerState = state,
                    scrimColor = MaterialTheme.colors.background.copy(alpha = ScrimDefaultOpacity),
                    drawerContent = {
                        DrawerContent(onCloseClick = { state.close() })
                    },
                    bodyContent = {
                        FeedContent(onDrawerClick = { state.open() })
                    }
                )
            }
        }
    }
}
