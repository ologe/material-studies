package dev.olog.basil.model

import androidx.ui.tooling.preview.PreviewParameterProvider

enum class Allergen {
    GlutenFree,
    EggFree
}

class AllergenPreviewProvider : PreviewParameterProvider<Allergen> {

    override val values: Sequence<Allergen>
        get() = sequenceOf(*Allergen.values())
}