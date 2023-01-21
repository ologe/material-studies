package dev.olog.material.studies.basil.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    val category: RecipeCategory,
    @SerialName("image_url")
    val imageUrl: String,
    val instructions: List<String>,
    val ingredients: List<RecipeIngredient>,
) {

    val macro: RecipeMacro
        get() = RecipeMacro()

}

@Serializable
data class RecipeIngredient(
    val name: String,
    val quantity: String,
)

data class RecipeMacro(
    val calories: Int = 465,
    val protein: Int = 27,
    val fat: Int = 12,
)