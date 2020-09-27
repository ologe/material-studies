package dev.olog.fortnightly.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val purple700 = Color(0xff_6b38fb)
val grey100 = Color(0xff_e8e8e8)

private val Palette = lightColors(
    background = Color.White,
    onBackground = Color.Black,

    primary = Color.White,
    primaryVariant = grey100,
    onPrimary = Color.Black,

    secondary = purple700,
    secondaryVariant = purple700,
    onSecondary = Color.White,

    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun FortnightlyTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = Palette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}