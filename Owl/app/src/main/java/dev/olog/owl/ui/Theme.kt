package dev.olog.owl.ui

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val owlYellow = Color(0xFF_ffde03)
val owlBlue = Color(0xFF_0336ff)
val owlPink = Color(0xFF_ff0266)

private val YellowColors = lightColors(
    background = owlYellow,
    onBackground = Color.Black,

    primary = owlYellow,
    primaryVariant = Color(0xFF_ffc000),
    onPrimary = Color.Black,

    secondary = owlBlue,
    secondaryVariant = owlBlue,
    onSecondary = Color.White,

    surface = Color.White,
    onSurface = Color.Black
)

private val BlueColors = lightColors(
    background = owlBlue,
    onBackground = Color.White,

    primary = owlBlue,
    primaryVariant = Color(0xFF_0035C9),
    onPrimary = Color.White,

    secondary = owlYellow,
    secondaryVariant = owlYellow,
    onSecondary = Color.Black,

    surface = Color.White,
    onSurface = Color.Black
)

private val PinkColors = lightColors(
    background = owlPink,
    onBackground = Color.Black,

    primary = owlPink,
    primaryVariant = owlPink,
    onPrimary = Color.Black,

    // no secondary color
    secondary = owlPink,
    secondaryVariant = owlPink,
    onSecondary = Color.Black,

    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun YellowTheme(content: @Composable () -> Unit) {
    OwlTheme(YellowColors, content)
}

@Composable
fun BlueTheme(content: @Composable () -> Unit) {
    OwlTheme(BlueColors, content)
}

@Composable
fun PinkTheme(content: @Composable () -> Unit) {
    OwlTheme(PinkColors, content)
}

@Composable
private fun OwlTheme(
    colors: Colors,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}