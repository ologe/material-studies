package dev.olog.crane.model

sealed class TabsAction {

    data class Fly(
        val people: Int,
        val fromLocation: String,
        val toLocation: String,
        val dates: String,
    ) : TabsAction()

    data class Sleep(
        val people: Int,
        val location: String,
        val dates: String
    ) : TabsAction()

    data class Eat(
        val people: Int,
        val date: String,
        val time: String,
        val location: String,
    ) : TabsAction()

}