package dev.olog.basil.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange

object ParallaxUtils {

    val ListParallaxDp = 30.dp
    val DetailParallaxDp = 30.dp * 3
    val DrawerParallaxDp = ListParallaxDp * 3f

    @Composable
    fun computeParallax(
        @FloatRange(0.0, 1.0) fraction: Float,
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