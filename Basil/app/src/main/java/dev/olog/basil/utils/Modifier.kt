package dev.olog.basil.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.util.annotation.FloatRange

@Composable
fun Modifier.fakeClickable(): Modifier {
    return this then clickable(onClick = {}, indication = null)
}

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

fun Modifier.scaleDown(
    @FloatRange(0.0, 1.0) offset: Float,
    amount: Float = 0.05f
) = this then Modifier.drawLayer(
    scaleX = 1 - offset * amount,
    scaleY = 1 - offset * amount,
)