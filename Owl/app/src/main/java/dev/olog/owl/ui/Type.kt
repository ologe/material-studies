package dev.olog.owl.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import dev.olog.owl.R


val rubik = fontFamily(
    font(R.font.rubik_regular, FontWeight.Normal),
    font(R.font.rubik_medium, FontWeight.Medium),
    font(R.font.rubik_bold, FontWeight.Bold)
)

val typography = typographyFromDefaults(
    h1 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

private fun typographyFromDefaults(
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