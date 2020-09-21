package dev.olog.basil.detail

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.olog.basil.list.Recipe

@Composable
fun DetailContent(item: Recipe) {
    Column(
        Modifier.fillMaxSize()
            .clickable(
                onClick = {},
                indication = null
            )
    ) {
        Text(
            text = item.title,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary,
        )
    }
}

