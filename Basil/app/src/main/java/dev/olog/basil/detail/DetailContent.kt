package dev.olog.basil.detail

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.annotation.FloatRange
import androidx.ui.tooling.preview.datasource.LoremIpsum
import dev.olog.basil.composable.AllergenRow
import dev.olog.basil.composable.viewpager.ViewPager
import dev.olog.basil.composable.viewpager.ViewPagerState
import dev.olog.basil.list.ListHeightFraction
import dev.olog.basil.list.ListHorizontalPadding
import dev.olog.basil.model.Allergen
import dev.olog.basil.model.Recipe
import dev.olog.basil.theme.green500
import dev.olog.basil.utils.AnimationUtils.translateToEnd
import dev.olog.basil.utils.AnimationUtils.translateToStart
import dev.olog.basil.utils.ParallaxUtils.DetailParallaxDp
import dev.olog.basil.utils.ParallaxUtils.computeParallax
import dev.olog.basil.utils.fakeClickable
import dev.olog.basil.utils.screenHeightDp
import dev.olog.basil.utils.toFloatPx
import dev.olog.basil.R
import dev.olog.basil.theme.MaterialColors
import dev.olog.basil.theme.MaterialTypography
import dev.olog.basil.utils.scaleDown

private const val EAGER_END_THRESHOLD = 0.1f
private const val LATE_START_THRESHOLD = 0.6f


@Composable
fun DetailContent(
    items: List<Recipe>,
    viewPagerState: ViewPagerState,
    tabDrawerState: SwipeableState<DetailTabDrawerState>,
    item: Recipe,
    peekTop: Dp,
    peekBottom: Dp,
    @FloatRange(0.0, 1.0) detailFraction: Float
) {
    val eagerEndFraction = translateToStart(detailFraction, EAGER_END_THRESHOLD)
    val lateStartFraction = translateToEnd(detailFraction, LATE_START_THRESHOLD)

    val lateStartModifier = Modifier.drawLayer(
        translationY = -(50.dp * (1f - lateStartFraction)).toFloatPx()
    )

    Box {
        Column(
            // made clickable so below content cannot be clicked
            modifier = Modifier
                .fillMaxSize()
                .fakeClickable()
                .scaleDown(tabDrawerState.progress.offset),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            val lateStartAlphaModifier = Modifier.drawLayer(alpha = lateStartFraction)

            // title + description, same height as content list
            UntilListContentImage(peekTop) {
                Box(Modifier.fillMaxWidth().preferredHeight(peekBottom)) {
                    DownArrow(eagerEndFraction)
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    RecipeTitle(items, viewPagerState, detailFraction)

                    Column(
                        modifier = lateStartModifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        HorizontalSpacer(lateStartAlphaModifier)
                        RecipeDescription(Modifier.weight(1f).then(lateStartAlphaModifier))
                    }
                }
            }
            Column(
                modifier = lateStartModifier,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                RecipeMacros(
                    modifier = Modifier
                        .padding(horizontal = ListHorizontalPadding)
                        .fillMaxWidth()
                        .then(lateStartAlphaModifier)
                )
                HorizontalSpacer(lateStartAlphaModifier)
                RecipeAllergens(
                    modifier = Modifier
                        .padding(horizontal = ListHorizontalPadding)
                        .fillMaxWidth()
                        .then(lateStartAlphaModifier),
                    allergens = item.allergens
                )
                Spacer(Modifier.weight(1f))
            }
        }
        DetailTabDrawer(
            state = tabDrawerState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun UntilListContentImage(
    peek: Dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeightDp * ListHeightFraction - peek),
        children = content
    )
}

@Composable
private fun RecipeTitle(
    items: List<Recipe>,
    state: ViewPagerState,
    @FloatRange(0.0, 1.0) detailFraction: Float,
) {
    Box(Modifier.fillMaxWidth()) {
        ViewPager(
            items = items,
            state = state,
            isUserInputEnabled = detailFraction < 0.1f // enable touch only when detail is down
        ) { item, itemFraction, isLeft ->

            val parallax = computeParallax(
                fraction = itemFraction,
                isLeft = isLeft,
                parallax = DetailParallaxDp
            )

            // TODO size granularity
            Text(
                text = item.title,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 32.dp)
                    .drawLayer(translationX = parallax),
                style = MaterialTypography.h2,
                textAlign = TextAlign.Center,
                color = MaterialColors.secondary,
                maxLines = 2
            )
        }
    }
}

@Composable
private fun BoxScope.DownArrow(fraction: Float) {
    Icon(
        asset = vectorResource(R.drawable.vd_fat_arrow),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .drawLayer(
                alpha = (1 - fraction * 3).coerceIn(0f, 1f),
                scaleX = 1.2f
            )
            .padding(bottom = 8.dp)
    )
}

@Composable
private fun HorizontalSpacer(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = ListHorizontalPadding)
            .background(green500)
    )
}

@Composable
private fun RecipeDescription(
    modifier: Modifier = Modifier
) {
    Text(
        text = LoremIpsum(100).values.joinToString(),
        modifier = modifier.padding(horizontal = ListHorizontalPadding + 12.dp).padding(bottom = 32.dp),
        style = MaterialTypography.h6,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun RecipeMacros(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val innerModifier = Modifier.weight(1f)
        Macro("Calories", "465g", innerModifier)
        Spacer(Modifier.width(1.dp).height(64.dp).background(green500.copy(alpha = .2f)))
        Macro("Protein", "27g", innerModifier)
        Spacer(Modifier.width(1.dp).height(64.dp).background(green500.copy(alpha = .2f)))
        Macro("Fat", "12g", innerModifier)
    }
}

@Composable
private fun Macro(
    title: String,
    amount: String,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = title, style = MaterialTypography.subtitle1)
        Text(text = amount, style = MaterialTypography.subtitle2)
    }
}

@Composable
private fun RecipeAllergens(
    allergens: Set<Allergen>,
    modifier: Modifier = Modifier
) {
    // TODO probably a better layout should be a flex box
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (allergen in allergens) {
            AllergenRow(allergen)
        }
    }
}