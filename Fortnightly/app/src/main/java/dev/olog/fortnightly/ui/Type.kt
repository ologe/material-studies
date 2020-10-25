package dev.olog.fortnightly.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.olog.fortnightly.R
import dev.olog.shared.utils.TypographyUtils

val oldlondon = fontFamily(
    font(R.font.old_london, FontWeight.Normal)
)

val merryweather = fontFamily(
    font(R.font.merriweather_black, FontWeight.Black),
    font(R.font.merriweather_blackitalic, FontWeight.Black, style = FontStyle.Italic),
    font(R.font.merriweather_bold, FontWeight.Bold),
    font(R.font.merriweather_bolditalic, FontWeight.Bold, style = FontStyle.Italic),
    font(R.font.merriweather_light, FontWeight.Light),
    font(R.font.merriweather_regular, FontWeight.Normal),
    font(R.font.merriweather_regularitalic, FontWeight.Normal, style = FontStyle.Italic),
)

val librefranklyn = fontFamily(
    font(R.font.librefranklin_black, FontWeight.Black),
    font(R.font.librefranklin_bold, FontWeight.Bold),
    font(R.font.librefranklin_extrabold, FontWeight.ExtraBold),
    font(R.font.librefranklin_light, FontWeight.Light),
    font(R.font.librefranklin_medium, FontWeight.Medium),
    font(R.font.librefranklin_regular, FontWeight.Normal),
    font(R.font.librefranklin_semibold, FontWeight.SemiBold),
    font(R.font.librefranklin_thin, FontWeight.Thin),
)

val typography = TypographyUtils.fromDefaults(
    h1 = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = merryweather,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = librefranklyn,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.05.em
    )
)