package dev.olog.material.studies.basil.main.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.BottomWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.TopWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutConstants.TotalWeight
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Detail
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.Drawer
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue.List
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.rememberStatusBarHeight
import dev.olog.material.studies.shared.size
import dev.olog.material.studies.shared.toDp
import kotlin.math.roundToInt

@Composable
fun BasilLayout(
    layoutState: BasilLayoutState,
    modifier: Modifier = Modifier,
    drawerContent: @Composable (Float) -> Unit,
    listContent: @Composable () -> Unit,
    detailHeaderContent: @Composable (Float) -> Unit,
    detailDescriptionContent: @Composable (Float) -> Unit,
    detailExtraContent: @Composable (Float) -> Unit,
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
                    style = MaterialTheme.typography.h1.copy(
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                modifier = Modifier
                    .size(detailSize)
                    .offset(y = (headerSize.height + listSize.height).toDp())
                    .offset { layoutState.detailOffset }
            ) {
                Box(Modifier.size(detailUpperSize)) {
                    detailHeaderContent(layoutState.detailProgress)
                }

                val descriptionPadding = detailUpperSize.height.toDp() - BasilLayoutConstants.DownArrowSize
                val descriptionHeight = (headerSize.height + listSize.height).toDp() -
                        (detailUpperSize.height).toDp() -
                        BasilLayoutConstants.ListHorizontalPadding

                Box(
                    Modifier
                        .padding(top = descriptionPadding)
                        .padding(horizontal = BasilLayoutConstants.ListHorizontalPadding)
                        .fillMaxWidth()
                        .height(descriptionHeight)
                ) {
                    detailDescriptionContent(layoutState.detailProgress)
                }

                Box(
                    modifier = Modifier
                        // slightly correct, padding is not pixel perfect
                        .padding(top = descriptionPadding + descriptionHeight + 4.dp)
                ) {
                    detailExtraContent(layoutState.detailProgress)
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

    const val TopWeight = 1.2f
    const val BottomWeight = 2f
    const val TotalWeight = TopWeight + BottomWeight
    val ListHorizontalPadding = 32.dp
    val DownArrowSize = 48.dp

}
