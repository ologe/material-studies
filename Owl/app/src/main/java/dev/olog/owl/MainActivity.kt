package dev.olog.owl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import dev.olog.owl.browse.BrowseFragment
import dev.olog.owl.learn.LearnFragment
import dev.olog.owl.personalize.PersonalizeFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        Navigator.current
            .onEach { navigate(it) }
            .launchIn(lifecycleScope)
    }

    private fun navigate(screen: Navigator.Screen) {
        val fragment = screen.toFragment()
        supportFragmentManager.commit {
            replace(android.R.id.content, fragment)
            if (screen == Navigator.Screen.Learn) {
                addToBackStack(null)
            }
        }
    }

    private fun Navigator.Screen.toFragment(): Fragment = when (this) {
        Navigator.Screen.Personalize -> PersonalizeFragment()
        Navigator.Screen.Browse -> BrowseFragment()
        Navigator.Screen.Learn -> LearnFragment()
    }

}

