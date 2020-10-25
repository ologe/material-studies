package dev.olog.owl.personalize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.ui.tooling.preview.Preview
import dev.olog.owl.ui.YellowTheme
import dev.olog.shared.composable.Background

class PersonalizeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                PersonalizeFragmentContent()
            }
        }
    }

}

@Composable
private fun PersonalizeFragmentContent() {
    YellowTheme {
        Background {

        }
    }
}

@Preview
@Composable
private fun PersonalizeFragmentContentPreview() {
    PersonalizeFragmentContent()
}