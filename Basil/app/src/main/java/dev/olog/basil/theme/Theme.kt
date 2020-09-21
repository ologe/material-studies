package dev.olog.basil.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Palette = lightColors(
    background = Color(0xFF_fffbe6),
    primary = Color(0xFF_356859),
    primaryVariant = Color(0xFF_37966f),
    secondary = Color(0xFF_fd5523),
    secondaryVariant = Color(0xFF_fd5523),
)

@Composable
fun BasilTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = Palette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}