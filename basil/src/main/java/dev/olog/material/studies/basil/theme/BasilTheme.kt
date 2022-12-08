package dev.olog.material.studies.basil.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import dev.olog.material.studies.shared.Background

@Composable
fun BasilTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            background = BasilColors.secondary50,
            onBackground = BasilColors.primary800,
            primary = BasilColors.primary800,
            secondary = BasilColors.secondary800,
        ),
        typography = basilTypography(),
        content = {
            Background {
                content()
            }
        },
    )
}



