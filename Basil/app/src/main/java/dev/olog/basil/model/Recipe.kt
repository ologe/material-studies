package dev.olog.basil.model

data class Recipe(
    val url: String,
    val title: String,
    val allergens: Set<Allergen> = emptySet()
) {

    companion object {

        val sample = listOf(
            Recipe("https://loremflickr.com/500/500", "Creamy Pesto Pasta", setOf(Allergen.GlutenFree, Allergen.EggFree)),
            Recipe("https://loremflickr.com/500/500", "Beef Pot Pie", setOf(Allergen.EggFree)),
            Recipe("https://loremflickr.com/500/500", "Herb Roasted Chicken"),
            Recipe("https://loremflickr.com/500/500", "Spinach File Puffs", setOf(Allergen.GlutenFree)),
        )

    }

}