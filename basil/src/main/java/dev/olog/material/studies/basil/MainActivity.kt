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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import dev.olog.material.studies.basil.theme.BasilColors
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
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    BasilLayout(
                        state = rememberBasilLayoutState(
                            initialValue = BasilLayoutStateValue.List,
                            listPositionPercentage = .2f,
                            listPadding = 40.dp,
                        ),
                        modifier = Modifier
                            .fillMaxSize(),
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
                                fontSize = 72.sp,
                                letterSpacing = .11.em
                            )
                            Column(
                                modifier = Modifier.boundingBox(),
                            ) {
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .boundingBox(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Creamy Pesto Pasta",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = 62.sp, // todo response text size
                                        color = BasilColors.secondary800
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