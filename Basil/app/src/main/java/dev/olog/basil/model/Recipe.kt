package dev.olog.basil.model

data class Recipe(
    val url: String,
    val title: String
) {

    companion object {

        val sample = listOf(
            Recipe("https://loremflickr.com/500/500", "Creamy Pesto Pasta"),
            Recipe("https://loremflickr.com/500/500", "Beef Pot Pie"),
            Recipe("https://loremflickr.com/500/500", "Herb Roasted Chicken"),
            Recipe("https://loremflickr.com/500/500", "Spinach File Puffs"),
        )

    }

}