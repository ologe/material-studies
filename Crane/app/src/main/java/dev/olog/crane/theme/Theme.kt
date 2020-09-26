package dev.olog.crane.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val purple800 = Color(0xFF_5d1049)
val purple900 = Color(0xFF_4e0d3a)
val purple700 = Color(0xFF_720d5d)
val red700 = Color(0xFF_e30424)

private val Palette = lightColors(
    background = purple800,
    onBackground = Color.White,

    primary = purple700,
    primaryVariant = purple900,
    onPrimary = Color.White,

    secondary = red700,
    secondaryVariant = red700,
    onSecondary = Color.White,

    surface = Color.White,
    onSurface = Color.Black,

    error = Color(0xFF_fd9725),
    onError = Color.White
)

@Composable
fun CraneTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = Palette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}