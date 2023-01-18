package dev.olog.material.studies.shared

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Stable
@Composable
fun Int.toDp(): Dp {
    return with(LocalDensity.current) { this@toDp.toDp() }
}

val BottomSheetState.fraction: Float
    get() = when {
        progress.from == Collapsed && progress.to == Expanded -> progress.fraction
        progress.from == Expanded && progress.to == Collapsed -> 1f - progress.fraction
        progress.from == Expanded && progress.to == Expanded -> 1f
        else -> 0f
    }