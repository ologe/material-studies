package dev.olog.material.studies.basil.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RecipeCategory {
    @SerialName("Pasta")
    Pasta,
    @SerialName("Beef")
    Beef,
    @SerialName("Seafood")
    Seafood,
    @SerialName("Desserts")
    Desserts,
}