package dev.olog.owl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object Navigator {

    sealed class Screen {

        object Personalize : Screen()
        object Browse : Screen()
        object Learn : Screen()

    }

    private val flow: MutableStateFlow<Screen> = MutableStateFlow(Screen.Personalize)
    val current: Flow<Screen>
        get() = flow

    fun navigateTo(screen: Screen) {
        flow.value = screen
    }

}