package dev.olog.basil.drawer

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.olog.basil.R
import java.util.*

@Composable
fun DrawerContent() {
    Stack(Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.app_name).toUpperCase(Locale.ROOT), // TODO use widget uppercase when avaialble
            modifier = Modifier.align(Alignment.BottomCenter),
            style = MaterialTheme.typography.h2,
            // TODO green
        )
    }
}