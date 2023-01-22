package dev.olog.material.studies.basil.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import dev.olog.material.studies.basil.data.model.Recipe
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants
import dev.olog.material.studies.basil.main.layout.BasilLayoutState
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.animation.rememberDecelerateEasing
import kotlin.math.roundToInt

private val ParallaxAmount = 50.dp

@Composable
fun ListContent(
    layoutState: BasilLayoutState,
    pagerState: PagerState,
    recipes: List<Recipe>
) {
    HorizontalPager(pagerState) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BasilLayoutConstants.ListPaddingPadding)
                .drawParallaxScrim(BasilColors.primary50, layoutState.detailProgress)
                .clipToBounds()
                .graphicsLayer {
                    scaleX = 1.1f
                    scaleY = 1.1f
                }.offset {
                    val offset = calculateCurrentOffsetForPage(it)
                    IntOffset((offset * ParallaxAmount.toPx()).roundToInt(), 0)
                },
            contentAlignment = Alignment.Center,
        ) {
            val recipe = recipes[it]
            if (LocalInspectionMode.current) {
                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Magenta)
                )
            } else {
                GlideImage(
                    model = recipe.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }
}

// TODO triggers lots of recompositions
private fun Modifier.drawParallaxScrim(
    color: Color,
    progress: Float,
): Modifier = composed {
    val easing = rememberDecelerateEasing(.6f)

    Modifier.drawWithContent {
        drawContent()
        rotate(180f) {
            drawRect(
                color = color,
                size = Size(size.width, size.height * easing.transform(progress))
            )
        }
    }
}