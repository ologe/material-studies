package dev.olog.fortnightly.composable

import androidx.compose.foundation.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.ui.oldlondon

@Preview
@Composable
private fun FortnightlyTextPreview() {
    FortnightlyTheme {
        FortnightlyText()
    }
}

@Composable
fun FortnightlyText(
    text: String = "The Fortnightly",
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 22.sp
) {
    Text(
        text = text,
        modifier = modifier,
        letterSpacing = (-0.01).em,
        fontSize = fontSize,
        fontFamily = oldlondon
    )
}