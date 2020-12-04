package dev.olog.shared

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.fakeClickable(): Modifier = composed {
    this.then(
        clickable(onClick = {}, indication = null)
    )
}