package dev.olog.basil.utils

import androidx.ui.tooling.preview.PreviewParameterProvider
import dev.olog.basil.composable.DrawerPage
import dev.olog.basil.model.Allergen

class AllergenPreviewProvider : PreviewParameterProvider<Allergen> {

    override val values: Sequence<Allergen>
        get() = sequenceOf(*Allergen.values())
}

class DrawerPagePreviewProvider : PreviewParameterProvider<DrawerPage> {

    override val values: Sequence<DrawerPage>
        get() = sequenceOf(*DrawerPage.values())
}