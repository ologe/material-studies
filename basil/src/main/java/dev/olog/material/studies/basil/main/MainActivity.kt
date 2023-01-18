package dev.olog.material.studies.basil.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.rememberPagerState
import dev.olog.material.studies.basil.DummyData
import dev.olog.material.studies.basil.main.layout.BasilLayout
import dev.olog.material.studies.basil.main.layout.rememberBasilLayoutState
import dev.olog.material.studies.basil.theme.BasilTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BasilTheme {
                val recipes = DummyData.recipes
                val layoutState = rememberBasilLayoutState()
                val pagerState = rememberPagerState(pageCount = recipes.size)

                BasilLayout(
                    layoutState = layoutState,
                    drawerContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Green)
                        )
                    },
                    listContent = {
                        ListContent(
                            layoutState = layoutState,
                            pagerState = pagerState,
                            recipes = recipes
                        )
                    },
                    detailHeaderContent = {
                        DetailHeaderContent(
                            pagerState = pagerState,
                            recipes = recipes,
                            offset = it
                        )
                    },
                    detailDescriptionContent = {
                        DetailDescriptionContent(
                            recipe = recipes[pagerState.currentPage]
                        )
                    },
                    detailExtraContent = {
                        DetailExtraContent(
                            recipe = recipes[pagerState.currentPage]
                        )
                    }
                )
            }
        }
    }

}