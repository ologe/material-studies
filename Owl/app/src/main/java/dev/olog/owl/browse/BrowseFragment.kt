package dev.olog.owl.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import dev.olog.owl.ui.BlueTheme
import dev.olog.shared.composable.Background

class BrowseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                BrowseFragmentContent()
            }
        }
    }

}

@Composable
private fun BrowseFragmentContent() {
    BlueTheme {
        Background {

        }
    }
}

@Preview
@Composable
private fun BrowseFragmentContentPreview() {
    BrowseFragmentContent()
}