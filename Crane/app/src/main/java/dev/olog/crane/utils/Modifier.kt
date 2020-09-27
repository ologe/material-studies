package dev.olog.crane.utils

import androidx.compose.runtime.Stable
import androidx.compose.ui.LayoutModifier
import androidx.compose.ui.Measurable
import androidx.compose.ui.MeasureScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints

@Stable
fun Modifier.offsetGetter(
    x: () -> Int = { 0 },
    y: () -> Int = { 0 },
) = this.then(OffsetModifier(x, y, true))

private data class OffsetModifier(
    val getX: () -> Int,
    val getY: () -> Int,
    val rtlAware: Boolean
) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureScope.MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            if (rtlAware) {
                placeable.placeRelative(getX(), getY())
            } else {
                placeable.place(getX(), getY())
            }
        }
    }
}