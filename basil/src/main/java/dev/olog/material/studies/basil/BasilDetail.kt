package dev.olog.material.studies.basil

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.drawer.BasilDrawerPage
import dev.olog.material.studies.basil.drawer.BasilDrawerState
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.shared.inverseLerp

@Composable
fun BasilDetail(
    state: BasilDrawerState,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier) {
        val constraints = this.constraints

        val listTopPosition = state.computePositionY(constraints, BasilDrawerPage.List)
        val listHeight = state.computeHeight(constraints, BasilDrawerPage.List)
        val listContentPadding = state.listContentPadding
        val titlePercentage = .3f
        val titleHeight = with(LocalDensity.current) { (listTopPosition + listHeight * titlePercentage).toDp() }
        val descriptionHeight = with(LocalDensity.current) { (listHeight * (1f - titlePercentage)).toDp() }

        Column {
            // title
            Box(
                modifier = Modifier
                    .height(titleHeight),
//                    .background(Color.Magenta.copy(alpha = .5f)),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Text(
                    text = "Creamy Pesto Pasta",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )

                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = null,
                )
            }

            Column(
                modifier = modifier.graphicsLayer {
                    alpha = inverseLerp(.2f, 1f, state.detailFraction)
                }
            ){
                // divider
                Divider(
                    color = BasilColors.primary800,
                    modifier = Modifier
                        .padding(horizontal = listContentPadding)
                )
                // description
                Box(
                    modifier = Modifier
                        .height(descriptionHeight)
                        .padding(listContentPadding)
                        .padding(4.dp)
                ) {
                    Text(
                        text = LoremIpsum(100).values.joinToString(),
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                }
                // other
            }
        }
    }
}