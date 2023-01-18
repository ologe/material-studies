package dev.olog.material.studies.basil.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    expand: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Divider()
        Tabs(expand)
    }
}

@Composable
private fun Tabs(
    expand: () -> Unit,
) {
    Row(Modifier.height(48.dp)) {
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = expand,
        ) {
            Text(text = "Ingredients")
        }
        TextButton(
            modifier = Modifier.weight(1f),
            onClick = expand,
        ) {
            Text(text = "Directions")
        }
    }
}