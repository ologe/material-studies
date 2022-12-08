package dev.olog.material.studies.basil.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.BasilDetail
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.*
import dev.olog.material.studies.basil.drawer.BasilDrawerPage.List
import dev.olog.material.studies.basil.theme.BasilTheme
import dev.olog.material.studies.shared.remap
import kotlin.math.roundToInt

@Composable
fun BasilDrawer(
    state: BasilDrawerState,
    modifier: Modifier = Modifier,
    categoryContent: @Composable () -> Unit,
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
            // category
            Box(Modifier.offset { IntOffset(x = 0, y = offsetPx) }) {
                categoryContent()
            }

            // title
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = 0, y = offsetPx) }
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "BASiL",
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            // offset the y by top padding
                            placeable.place(0, -(placeable.height - placeable[FirstBaseline]))
                        }
                    }
                )
            }

            // list
            Box(
                modifier = Modifier
                    .offset { IntOffset(x = 0, y = offsetPx.coerceAtLeast(0)) }
                    .padding(state.listContentPadding)
                    .drawWithContent {
                        drawContent()
                        // draw scrim
                        rotate(180f) {
                            val height = remap(0f, 1f, 0f, size.height, state.detailFraction * 1.1f)
                            drawRect(
                                color = state.scrimColor,
                                topLeft = Offset.Zero,
                                size = size.copy(height = height)
                            )
                        }
                    }
            ) {
                listContent()
            }

            // detail
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
        val titlePlaceable = measurables[1].measure(constraints)

        val listHeight = state.computeHeight(constraints, List)
        val listPlaceable = measurables[2].measure(
            constraints.copy(maxHeight = listHeight)
        )
        val detailPlaceable = measurables[3].measure(
            constraints.copy(maxHeight = state.computeHeight(constraints, Detail))
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            categoryPlaceable.place(x = 0, y = state.computePositionY(constraints, Category), zIndex = 1f)
            titlePlaceable.place(x = 0, y = -(titlePlaceable.height * .2f).roundToInt())
            listPlaceable.place(x = 0, y = state.computePositionY(constraints, List), zIndex = 0f)
            detailPlaceable.place(x = 0, y = state.computePositionY(constraints, Detail), zIndex = 1f)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PreviewContent()
}

@Composable
fun PreviewContent() {
    BasilTheme {
        val state = rememberBasilDrawerState(
            initialValue = List
        )

        BasilDrawer(
            state = state,
            categoryContent = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("category")

                }
            },
            listContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                )
            },
            detailContent = {
                BasilDetail(
                    state = state,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        )
    }
}