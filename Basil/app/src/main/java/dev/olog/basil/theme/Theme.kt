package dev.olog.basil.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val green800 = Color(0xFF_356859)
val green500 = Color(0xFF_37966f)
val green100 = Color(0xFF_b9e4c9)
val orange800 = Color(0xFF_fd5523)
val yellow50 = Color(0xFF_fffbe6)

private val Palette = lightColors(
    background = yellow50,
    onBackground = green800,

    primary = green800,
    primaryVariant = green500,
    onPrimary = green800,

    secondary = orange800,
    secondaryVariant = orange800,
    onSecondary = Color.Black,

    surface = yellow50,
    onSurface = green800
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