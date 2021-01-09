package dev.olog.basil.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import dev.olog.basil.R

data class Recipe(
    val image: ImageBitmap,
    val title: String,
    val allergens: Set<Allergen> = emptySet()
) {

    companion object {

        @get:Composable
        val sample : List<Recipe>
            get() {
                return listOf(
                    Recipe(imageResource(R.drawable.cat3), "Creamy Pesto Pasta", setOf(Allergen.GlutenFree, Allergen.EggFree)),
                    Recipe(imageResource(R.drawable.cat2), "Beef Pot Pie", setOf(Allergen.EggFree)),
                    Recipe(imageResource(R.drawable.cat1), "Herb Roasted Chicken"),
                    Recipe(imageResource(R.drawable.cat4), "Spinach File Puffs", setOf(Allergen.GlutenFree)),
                )
            }

    }

}