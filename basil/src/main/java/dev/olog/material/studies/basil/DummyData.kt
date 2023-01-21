package dev.olog.material.studies.basil


object DummyData {

    const val description = "Guilt-free gluten-free spaghetti pasta is sautéed in a garlic, kale pesto. It's an easy and healthy dinner."

    val ingredients = listOf(
        Ingredient(name = "Basil", quantity = "6 tbsp"),
        Ingredient(name = "Gluten-free Spaghetti", quantity = "2 cups"),
        Ingredient(name = "Garlic", quantity = "1 tbsp"),
        Ingredient(name = "Ricotta", quantity = "4 cups"),
        Ingredient(name = "Kale", quantity = "3 cups"),
        Ingredient(name = "Red Pepper Flakes", quantity = "1 tbsp"),
        Ingredient(name = "Extra Virgin Olive Oil", quantity = "1 tbsp"),
        Ingredient(name = "Salt", quantity = "1 tbsp"),
        Ingredient(name = "Pine nuts", quantity = "1 tbsp"),
    )

    val directions = listOf(
        Direction(
            header = "Chop the Pesto Ingredients",
            text = "Place the basil leaves, garlic and pine nuts on a sturdy cutting board.\n\nRoughly chop the ingredients by hand before using the food processor. This will insure your pesto is smooth and creamy."
        ),
        Direction(
            header = "Combine and Toss",
            text = "Combine the kale, garlic and red pepper flakes in a large bowl.\n\nToss the ingredients by hand"
        )
    )

    // https://picsum.photos/1000
    val recipes = listOf(
        Recipe(
            "Creamy Pesto Pasta",
            "https://i.picsum.photos/id/952/1000/1000.jpg?hmac=yHuthtZ_SMhTOVt-SXL98MCWjugUnkBo7gMqEtKkFik",
        ),
        Recipe(
            "Spinach Filo Puffs",
            "https://i.picsum.photos/id/1077/1000/1000.jpg?hmac=B2AkBaJVaHaqUImSDLiprJXdGKwPaWJ1B64ibAMDIEE",
        ),
        Recipe(
            "Beef Pot Pie",
            "https://i.picsum.photos/id/299/1000/1000.jpg?hmac=DRpkgVaALpt0f0Y4kSTUOtLJ66_ULgUDZn2n6pbuafA"
        ),
        Recipe(
            "Herb Roasted Chicken",
            "https://i.picsum.photos/id/541/1000/1000.jpg?hmac=P9DEGoMReIcrb-kSE6_96C7f5UxbVDC-OHCFIAhced8"
        ),
    )

}

data class Recipe(
    val name: String,
    val imageUrl: String,
    val description: String = DummyData.description,
    val macro: Macro = Macro(),
    val ingredients: List<Ingredient> = DummyData.ingredients,
    val directions: List<Direction> = DummyData.directions + DummyData.directions + DummyData.directions
)

data class Macro(
    val calories: Int = 465,
    val protein: Int = 27,
    val fat: Int = 12,
)

data class Ingredient(
    val name: String,
    val quantity: String,
)

data class Direction(
    val header: String,
    val text: String,
)