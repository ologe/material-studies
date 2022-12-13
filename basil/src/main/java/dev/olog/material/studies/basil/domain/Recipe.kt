package dev.olog.material.studies.basil.domain

import androidx.compose.runtime.Stable
import java.util.*

@Stable
data class Recipe(
    val id: String,
    val name: String,
    val url: String,
)

object Data {

    val recipes = listOf(
        Recipe(
            id = UUID.randomUUID().toString(),
            name = "Creamy Pesto Pasta",
            url = "https://images.unsplash.com/photo-1518779578993-ec3579fee39f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1935&q=80",
        ),
        Recipe(
            id = UUID.randomUUID().toString(),
            name = "Herb Roasted Chicken",
            url = "https://images.unsplash.com/photo-1490645935967-10de6ba17061?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1153&q=80",
        ),
        Recipe(
            id = UUID.randomUUID().toString(),
            name = "Beef PotPie",
            url = "https://images.unsplash.com/photo-1525059696034-4967a8e1dca2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=688&q=80"
        )
    )

}