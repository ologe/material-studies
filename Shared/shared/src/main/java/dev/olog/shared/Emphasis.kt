package dev.olog.shared

import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers

@Composable
fun HighEmphasis(content: @Composable () -> Unit) {
    Providers(
        AmbientContentAlpha provides ContentAlpha.high,
        content = content
    )
}

@Composable
fun MediumEmphasis(content: @Composable () -> Unit) {
    Providers(
        AmbientContentAlpha provides ContentAlpha.high,
        content = content
    )
}

@Composable
fun DisabledEmphasis(content: @Composable () -> Unit) {
    Providers(
        AmbientContentAlpha provides ContentAlpha.high,
        content = content
    )
}