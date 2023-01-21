package dev.olog.material.studies.basil.data

import android.content.Context
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.olog.material.studies.basil.R
import dev.olog.material.studies.basil.data.model.Recipe
import dev.olog.material.studies.basil.data.model.RecipeCategory
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

// https://www.themealdb.com/api.php
@Singleton
class RecipeRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun getRecipes(category: RecipeCategory): List<Recipe> {
        return Json.decodeFromString(load(R.raw.pasta))
    }


    fun getAllCategories(): List<RecipeCategory> {
        return Json.decodeFromString(load(R.raw.categories))
    }

    private fun load(@RawRes id: Int): String {
        return context.resources.openRawResource(id)
            .bufferedReader()
            .use { it.readText() }
    }

}