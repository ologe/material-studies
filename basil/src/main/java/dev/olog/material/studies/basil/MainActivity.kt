package dev.olog.material.studies.basil

import android.annotation.SuppressLint
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.bumptech.glide.integration.compose.GlideImage
import dev.olog.material.studies.basil.domain.Data
import dev.olog.material.studies.basil.drawer.BasilLayout
import dev.olog.material.studies.basil.drawer.BasilLayoutState
import dev.olog.material.studies.basil.drawer.BasilLayoutStateValue
import dev.olog.material.studies.basil.drawer.rememberBasilLayoutState
import dev.olog.material.studies.basil.theme.BasilColors
import dev.olog.material.studies.basil.theme.BasilTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BasilTheme {
                Screen(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize()
                )
            }
        }
    }

}

@Composable
@SuppressLint("ModifierParameter")
private fun Screen(
    state: BasilLayoutState = rememberBasilLayoutState(
        initialValue = BasilLayoutStateValue.List,
        listPositionPercentage = .2f,
        listPadding = 40.dp,
    ),
    modifier: Modifier = Modifier,
) {
    BasilLayout(
        state = state,
        modifier = modifier.fillMaxSize(),
        listContent = {
            ListContent()
        },
        detailContent = {
            DetailContent()
        }
    )
}

@Composable
private fun ListContent() {
    GlideImage(
        model = Data.recipes[0].url,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun DetailContent() {
    Text(
        text = "BASiL",
        textAlign = TextAlign.Center,
        fontSize = 72.sp,
        letterSpacing = 0.1.em
    )
    Column {
        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
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
            .background(Color.LightGray.copy(alpha = .5f))
    ) {

    }
}