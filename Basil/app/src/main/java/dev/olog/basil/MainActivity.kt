package dev.olog.basil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.olog.basil.detail.DetailContent
import dev.olog.basil.drawer.DrawerContent
import dev.olog.basil.list.ListContent
import dev.olog.basil.ui.BasilTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasilTheme {
                MainActivityContent()
            }
        }
    }
}

@Preview
@Composable
private fun MainActivityContentPreview() {
    BasilTheme {
        MainActivityContent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainActivityContent(
    peek: Dp = 200.dp
) {
    BasilDrawer(
        peek = peek,
        drawerContent = { DrawerContent() },
        listContent = { ListContent() },
        detailContent = { DetailContent() }
    )
}






