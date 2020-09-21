package dev.olog.basil.composable

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
fun Background(content: @Composable () -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        content = content
    )
}