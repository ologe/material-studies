package dev.olog.material.studies.basil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import dev.olog.material.studies.basil.theme.BasilTheme
import dev.olog.material.studies.basil.v2.BasilLayout
import dev.olog.material.studies.basil.v2.BasilLayoutStateValue
import dev.olog.material.studies.basil.v2.rememberBasilLayoutState
import dev.olog.material.studies.shared.boundingBox

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BasilTheme {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    val height = 250.dp
                    val width = height / 16 * 9
                    BasilLayout(
                        state = rememberBasilLayoutState(
                            initialValue = BasilLayoutStateValue.List,
                            listPositionPercentage = .15f,
                            listPadding = 16.dp,
                        ),
                        modifier = Modifier
                            .size(width = width, height = height),
                        listContent = {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Magenta)
                            )
                        },
                        detailContent = {
                            Text(
                                text = "BASiL",
                                modifier = Modifier.boundingBox(),
                                textAlign = TextAlign.Center,
                            )
                            Column(
                                modifier = Modifier.boundingBox(),
                            ) {
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .boundingBox(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Creamy Pesto Pasta",
                                        textAlign = TextAlign.Center,
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(12.dp) // TODO increase
                                )
                            }

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color.LightGray)
                            ) {

                            }
                        }
                    )
                }
            }
        }
    }

}