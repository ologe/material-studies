package dev.olog.shared

import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable

@Composable
fun HighEmphasis(content: @Composable () -> Unit) {
    ProvideEmphasis(
        emphasis = AmbientEmphasisLevels.current.high,
        content = content
    )
}

@Composable
fun MediumEmphasis(content: @Composable () -> Unit) {
    ProvideEmphasis(
        emphasis = AmbientEmphasisLevels.current.medium,
        content = content
    )
}

@Composable
fun DisabledEmphasis(content: @Composable () -> Unit) {
    ProvideEmphasis(
        emphasis = AmbientEmphasisLevels.current.disabled,
        content = content
    )
}