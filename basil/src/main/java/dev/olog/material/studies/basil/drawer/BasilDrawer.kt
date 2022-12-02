package dev.olog.material.studies.basil.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.BasilTheme
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.*
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.List
import kotlin.math.roundToInt

@Composable
fun BasilDrawer(
    state: BasilDrawerState = rememberBasilDrawerState(),
    modifier: Modifier = Modifier,
    categoryContent: @Composable (PaddingValues) -> Unit,
    listContent: @Composable () -> Unit,
    detailContent: @Composable () -> Unit,
) {

    BoxWithConstraints(modifier = modifier) {
        val offsetPx = state.offset.value.roundToInt()
        val anchors = state.anchors(constraints)

        BasilDrawerLayout(
            state = state,
            modifier = modifier
                .clipToBounds() // do not draw outside of screen
                .swipeable(
                    state = state,
                    orientation = Orientation.Vertical,
                    anchors = anchors,
                    resistance = null,
                    thresholds = { _, _ -> FractionalThreshold(.5f) },
                ),
        ) {
            Box(
                modifier = Modifier.offset { IntOffset(x = 0, y = offsetPx) }
            ) {
                val categoryOverflow = with(LocalDensity.current) { state.categoryOverflowPx.toDp() }
                categoryContent(PaddingValues(bottom = categoryOverflow))
            }

            Box(
                modifier = Modifier.offset { IntOffset(x = 0, y = offsetPx.coerceAtLeast(0)) }
            ) {
                listContent()
            }

            Box(
                modifier = Modifier.offset { IntOffset(x = 0, y = offsetPx) }
            ) {
                detailContent()
            }
        }
    }
}

@Composable
private fun BasilDrawerLayout(
    state: BasilDrawerState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val categoryPlaceable = measurables[0].measure(
            constraints.copy(maxHeight = state.computeHeight(constraints, Category))
        )

        val listHeight = state.computeHeight(constraints, List)
        val listPlaceable = measurables[1].measure(
            constraints.copy(maxHeight = listHeight)
        )
        val detailPlaceable = measurables[2].measure(
            constraints.copy(maxHeight = state.computeHeight(constraints, Detail))
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            categoryPlaceable.place(x = 0, y = state.computePositionY(constraints, Category), zIndex = 1f)
            listPlaceable.place(x = 0, y = state.computePositionY(constraints, List), zIndex = 0f)
            detailPlaceable.place(x = 0, y = state.computePositionY(constraints, Detail), zIndex = 1f)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    BasilTheme {
        BasilDrawer(
            categoryContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Cyan.copy(alpha = .4f)),
                ) {
                    Text(
                        text = "category",
                        modifier = Modifier
                            .padding(it)
                            .align(Alignment.Center)
                    )
                    Text(
                        text = "BASil",
                        modifier = Modifier.align(Alignment.BottomCenter),
                        fontSize = 74.sp,
                        fontWeight = FontWeight.Black,
                    )
                }
            },
            listContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red.copy(alpha = .4f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Spacer(
                        modifier = Modifier
                            .padding(24.dp)
                            .background(Color.Black)
                            .aspectRatio(1f)
                            .fillMaxSize()
                    )
                }
            },
            detailContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green.copy(alpha = .4f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "detail")
                }
            }
        )
    }
}