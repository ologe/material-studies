package dev.olog.material.studies.basil

object DummyData {

    val description = "Guilt-free gluten-free spaghetti pasta is sautéed in a garlic, kale pesto. It's an easy and healthy dinner."

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
)

data class Macro(
    val calories: Int = 465,
    val protein: Int = 27,
    val fat: Int = 12,
)