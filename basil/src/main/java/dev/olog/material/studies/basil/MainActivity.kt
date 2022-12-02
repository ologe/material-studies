package dev.olog.material.studies.basil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.drawer.BasilDrawer

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BasilTheme {
                Content()
            }
        }
    }

}

@Composable
private fun Content() {
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

@Preview
@Composable
private fun Preview() {
    BasilTheme {
        Content()
    }
}