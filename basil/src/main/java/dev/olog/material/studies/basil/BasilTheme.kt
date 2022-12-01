package dev.olog.material.studies.basil

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.olog.material.studies.shared.Background

@Composable
fun BasilTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            background = Color(0xFF_FFFBE6),
            primary = Color(0xFF_356859),
            primaryVariant = Color(0xFF_e9e6d0),
            secondary = Color(0xFF_FD5523),
        ),
        content = {
            Background {
                content()
            }
        },
    )
}

