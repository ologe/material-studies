package dev.olog.material.studies.basil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.olog.material.studies.basil.drawer.PreviewContent
import dev.olog.material.studies.basil.theme.BasilTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BasilTheme {
                PreviewContent()
            }
        }
    }

}