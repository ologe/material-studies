package dev.olog.basil.composable

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.olog.basil.theme.MaterialColors

@Composable
fun Background(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialColors.background,
        contentColor = MaterialColors.onBackground,
        content = content
    )
}