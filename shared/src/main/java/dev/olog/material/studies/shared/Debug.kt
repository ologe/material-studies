package dev.olog.material.studies.shared

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.boundingBox() = this then Modifier
    .border(1.dp, Color.Gray)