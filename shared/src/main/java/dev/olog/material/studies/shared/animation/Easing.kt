package dev.olog.material.studies.shared.animation

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlin.math.pow

@Composable
fun rememberDecelerateEasing(factor: Float): Easing {
    return remember(factor) {
        Easing {
            if (factor == 1f) {
                return@Easing 1f - (1f - it) * (1f - it)
            }
            1f - (1.0 - it).pow(2.0 * factor).toFloat()
        }
    }
}