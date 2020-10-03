package dev.olog.fortnightly.composable

import androidx.compose.animation.animate
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.pressIndicatorGestureFilter
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.layout.ContentScale

@Composable
fun FilterImage(
    asset: ImageAsset,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    filterColor: Color = Color(0xFF_311b92),
    filterMaxAlpha: Float = 0.6f
) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    val filterAlpha = if (isClicked) filterMaxAlpha else 0.01f
    val colorFilter = ColorFilter(
        color = animate(filterColor.copy(alpha = filterAlpha)),
        blendMode = BlendMode.Hardlight
    )

    Image(
        asset = asset,
        modifier = modifier
            .pressIndicatorGestureFilter(
                onStart = { isClicked = true },
                onStop = { isClicked = false },
                onCancel = { isClicked = false }
            ),
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}