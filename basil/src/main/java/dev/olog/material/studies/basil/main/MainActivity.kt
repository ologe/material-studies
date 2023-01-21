package dev.olog.material.studies.basil.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import dev.olog.material.studies.basil.data.model.RecipeCategory
import dev.olog.material.studies.basil.main.layout.BasilLayout
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue
import dev.olog.material.studies.basil.main.layout.rememberBasilLayoutState
import dev.olog.material.studies.basil.theme.BasilTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            BasilTheme {

                val recipes = viewModel.recipes
                    .collectAsState(null).value ?: return@BasilTheme

                val layoutState = rememberBasilLayoutState(BasilLayoutStateValue.Drawer)
                val pagerState = rememberPagerState(pageCount = recipes.size)
                val sheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed,
                )
                val scope = rememberCoroutineScope()

                val selectedCategory = viewModel.selectedCategory
                    .collectAsState(null).value ?: return@BasilTheme
                val selectedRecipe by remember {
                    derivedStateOf { recipes[pagerState.currentPage] }
                }

                BasilLayout(
                    layoutState = layoutState,
                    sheetState = sheetState,
                    drawerContent = {
                        DrawerContent(
                            categories = RecipeCategory.values().toList(),
                            selectedCategory = selectedCategory,
                            onCategoryChange = {
                                viewModel.updateCategory(it)
                            }
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
                            recipe = selectedRecipe
                        )
                    },
                    detailExtraContent = {
                        DetailExtraContent(
                            recipe = selectedRecipe,
                        )
                    },
                    sheetContent = {
                        SheetContent(
                            recipe = selectedRecipe,
                            expand = {
                                scope.launch { sheetState.expand() }
                            }
                        )
                    }
                )
            }
        }
    }

}