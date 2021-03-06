package dev.olog.owl.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dev.olog.owl.ui.PinkTheme
import dev.olog.shared.composable.Background

class LearnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LearnFragmentContent()
            }
        }
    }

}

@Composable
private fun LearnFragmentContent() {
    PinkTheme {
        Background {

        }
    }
}

@Preview
@Composable
private fun LearnFragmentContentPreview() {
    LearnFragmentContent()
}