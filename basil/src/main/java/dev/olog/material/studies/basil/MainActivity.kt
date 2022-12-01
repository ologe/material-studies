package dev.olog.material.studies.basil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BasilTheme {
                Content()
            }
        }
    }

}

@Composable
private fun Content() {

}

@Preview
@Composable
private fun Preview() {
    BasilTheme {
        Content()
    }
}