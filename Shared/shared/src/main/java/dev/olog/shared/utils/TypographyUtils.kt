package dev.olog.shared.utils

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle

object TypographyUtils {

    fun fromDefaults(
        h1: TextStyle,
        h2: TextStyle,
        h3: TextStyle,
        h4: TextStyle,
        h5: TextStyle,
        h6: TextStyle,
        subtitle1: TextStyle,
        subtitle2: TextStyle,
        body1: TextStyle,
        body2: TextStyle,
        button: TextStyle,
        caption: TextStyle,
        overline: TextStyle
    ): Typography {
        val defaults = Typography()
        return Typography(
            h1 = defaults.h1.merge(h1),
            h2 = defaults.h2.merge(h2),
            h3 = defaults.h3.merge(h3),
            h4 = defaults.h4.merge(h4),
            h5 = defaults.h5.merge(h5),
            h6 = defaults.h6.merge(h6),
            subtitle1 = defaults.subtitle1.merge(subtitle1),
            subtitle2 = defaults.subtitle2.merge(subtitle2),
            body1 = defaults.body1.merge(body1),
            body2 = defaults.body2.merge(body2),
            button = defaults.button.merge(button),
            caption = defaults.caption.merge(caption),
            overline = defaults.overline.merge(overline)
        )
    }

}