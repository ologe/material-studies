package dev.olog.owl.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import dev.olog.owl.composable.Background
import dev.olog.owl.ui.PinkTheme

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