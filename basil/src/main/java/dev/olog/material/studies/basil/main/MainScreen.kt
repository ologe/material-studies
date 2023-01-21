package dev.olog.material.studies.basil.main

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dev.olog.material.studies.basil.data.model.Recipe
import dev.olog.material.studies.basil.data.model.RecipeCategory
import dev.olog.material.studies.basil.main.layout.BasilLayout
import dev.olog.material.studies.basil.main.layout.BasilLayoutState
import dev.olog.material.studies.basil.main.layout.BasilLayoutStateValue
import dev.olog.material.studies.basil.main.layout.rememberBasilLayoutState
import dev.olog.material.studies.basil.theme.BasilTheme
import dev.olog.material.studies.shared.DevicePreviews
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    recipes: List<Recipe>,
    selectedCategory: RecipeCategory,
    onSelectedCategoryUpdate: (RecipeCategory) -> Unit,
    layoutState: BasilLayoutState = rememberBasilLayoutState(
        initialState = BasilLayoutStateValue.Detail
    ),
    sheetState: BottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
    ),
    pagerState: PagerState = rememberPagerState(
        pageCount = recipes.size
    )
) {
    val scope = rememberCoroutineScope()
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
                onCategoryChange = onSelectedCategoryUpdate,
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
                offset = it,
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

@DevicePreviews
@Composable
private fun Preview() {
    BasilTheme {
        MainScreen(
            recipes = listOf(
                Recipe(
                    id = "",
                    name = "Pot Pie",
                    description = "",
                    category = RecipeCategory.Desserts,
                    imageUrl = "",
                    instructions = emptyList(),
                    ingredients = emptyList(),
                )
            ),
            selectedCategory = RecipeCategory.Desserts,
            onSelectedCategoryUpdate = {},
        )
    }
}