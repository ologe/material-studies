package dev.olog.material.studies.basil.main.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.BottomWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.TopWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.TotalWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Detail
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Drawer
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.List
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.animation.AnimationUtils
import dev.olog.material.studies.shared.fraction
import dev.olog.material.studies.shared.rememberStatusBarHeight
import dev.olog.material.studies.shared.size
import dev.olog.material.studies.shared.toDp
import kotlin.math.roundToInt

@Composable
fun BasilLayout(
    layoutState: BasilLayoutState,
    sheetState: BottomSheetState,
    modifier: Modifier = Modifier,
    drawerContent: @Composable (Float) -> Unit,
    listContent: @Composable () -> Unit,
    detailHeaderContent: @Composable (Float) -> Unit,
    detailDescriptionContent: @Composable (Float) -> Unit,
    detailExtraContent: @Composable (Float) -> Unit,
    sheetContent: @Composable ColumnScope.() -> Unit,
) {
    val statusBarHeight = rememberStatusBarHeight()

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val drawerSize = IntSize(width, height - statusBarHeight * 2)
        val listSize = IntSize(width, width)
        val headerSize = IntSize(
            width,
            ((height - listSize.height) / TotalWeight * TopWeight).roundToInt()
        )
        val detailSize = IntSize(width, height - statusBarHeight)
        val detailUpperSize = IntSize(
            width,
            ((height - listSize.height) / TotalWeight * BottomWeight).roundToInt()
        )

        Box(
            Modifier
                .fillMaxSize()
                .swipeable(
                    state = layoutState,
                    anchors = remember(constraints, statusBarHeight) {
                        mapOf(
                            (constraints.maxHeight - statusBarHeight).toFloat() to Drawer,
                            0f to List,
                            -(headerSize.height + listSize.height - statusBarHeight).toFloat() to Detail,
                        )
                    },
                    orientation = Orientation.Vertical,
                    enabled = sheetState.isCollapsed,
                    resistance = null,
                ),
        ) {

            Box(
                modifier = Modifier
                    .size(drawerSize)
                    .offset(y = -drawerSize.height.toDp())
                    .offset { layoutState.drawerOffset }
            ) {
                drawerContent(layoutState.drawerProgress)
            }

            // list
            Box(
                modifier = Modifier
                    .size(listSize)
                    .offset(y = headerSize.height.toDp())
                    .offset { layoutState.listOffset }
            ) {
                listContent()
            }

            // BASiL header
            Box(
                modifier = Modifier
                    .size(headerSize)
                    .offset { layoutState.detailOffset },
                contentAlignment = Alignment.TopCenter,
            ) {
                Text(
                    text = "BASiL",
                    fontSize = 68.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 5.sp,
                    color = BasilColors.primary800,
                    modifier = Modifier.fixTextPadding(),
                )
            }

            Image(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(BasilLayoutConstants.DownArrowSize)
                    .offset { layoutState.drawerOffset }
                    .graphicsLayer {
                        scaleX = 1.4f // mimic wide icon
                        alpha = AnimationUtils.translateToEnd(1f - layoutState.detailProgress, .5f)
                    },
                colorFilter = ColorFilter.tint(LocalContentColor.current)
            )

            BottomSheetScaffold(
                scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState,
                ),
                sheetBackgroundColor = BasilColors.secondary50.copy(alpha = .97f),
                sheetElevation = 0.dp,
                sheetPeekHeight = BasilLayoutConstants.SheetHeight,
                backgroundColor = Color.Transparent,
                modifier = Modifier
                    .size(detailSize)
                    .offset(y = (headerSize.height + listSize.height).toDp())
                    .offset { layoutState.detailOffset },
                sheetContent = sheetContent,
                sheetGesturesEnabled = sheetState.isExpanded,
            ) {
                Box(
                    Modifier
                        // scale down when bottom sheet is expanded
                        .graphicsLayer {
                            val scale = AnimationUtils.remap(
                                inMin = 0f, inMax = 1f,
                                outMin = .85f, outMax = 1f,
                                value = 1 - sheetState.fraction,
                            )

                            scaleX = scale
                            scaleY = scale
                        },
                ) {
                    Box(
                        modifier = Modifier
                            .size(detailUpperSize)
                    ) {
                        detailHeaderContent(layoutState.detailProgress)
                    }

                    Box(
                        Modifier
                            .padding(horizontal = BasilLayoutConstants.ListPaddingPadding)
                            .height((listSize.height + headerSize.height - statusBarHeight).toDp() - BasilLayoutConstants.ListPaddingPadding)
                            .padding(top = detailUpperSize.height.toDp() - BasilLayoutConstants.DownArrowSize)
                            .fillMaxWidth()
                    ) {
                        detailDescriptionContent(layoutState.detailProgress)
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = (listSize.height + headerSize.height - statusBarHeight).toDp() - BasilLayoutConstants.ListPaddingPadding)
                    ) {
                        detailExtraContent(layoutState.detailProgress)
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(statusBarHeight.toDp())
                    .background(BasilColors.secondary50)
            )
        }
    }
}

object BasilLayoutConstants {

    const val TopWeight = 1f
    const val BottomWeight = 2f
    const val TotalWeight = TopWeight + BottomWeight
    val ListPaddingPadding = 32.dp
    val DownArrowSize = 48.dp
    val SheetHeight = 56.dp

}

private fun Modifier.fixTextPadding(): Modifier = composed {
    val additionalHeight = rememberStatusBarHeight() - with(LocalDensity.current) { 18.dp.toPx() }

    Modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val firstBaseline = placeable[FirstBaseline]
        layout(placeable.width, placeable.height) {
            placeable.place(0, firstBaseline - placeable.height + additionalHeight.roundToInt())
        }
    }
}