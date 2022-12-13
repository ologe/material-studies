package dev.olog.material.studies.shared

import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntRect

fun Measurable.measure(rect: IntRect): Placeable {
    return measure(Constraints.fixed(rect.width, rect.height))
}