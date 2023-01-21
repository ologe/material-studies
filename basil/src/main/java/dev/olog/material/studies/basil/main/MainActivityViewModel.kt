package dev.olog.material.studies.basil.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.olog.material.studies.basil.data.RecipeRepository
import dev.olog.material.studies.basil.data.model.RecipeCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {

    val categories: List<RecipeCategory> = repository.getAllCategories()
    private val _selectedCategory = MutableStateFlow(categories.first())

    val selectedCategory: Flow<RecipeCategory> = _selectedCategory
    val recipes = selectedCategory.map {
        repository.getRecipes(it)
    }

    fun updateCategory(category: RecipeCategory) {
        _selectedCategory.value = category
    }

}