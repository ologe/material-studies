package dev.olog.basil.utils

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.olog.shared.extension.toFloatPx
import dev.olog.shared.extension.toIntPx

object ParallaxUtils {

    val ListParallaxDp = 30.dp
    val DetailParallaxDp = 30.dp * 3
    val DrawerParallaxDp = ListParallaxDp * 3f

    @Composable
    fun computeParallax(
        @FloatRange(from = 0.0, to = 1.0) fraction: Float,
        isLeft: Boolean,
        parallax: Dp
    ): Float {
        if (isLeft) {
            return -fraction * parallax.toIntPx()
        } else {
            return (parallax - (parallax * fraction)).toFloatPx()
        }
    }

}