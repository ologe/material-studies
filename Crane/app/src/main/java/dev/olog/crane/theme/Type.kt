package dev.olog.crane.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import dev.olog.crane.R
import dev.olog.shared.utils.TypographyUtils

val raleway = fontFamily(
    font(R.font.raleway_light, FontWeight.Light),
    font(R.font.raleway_regular, FontWeight.Normal),
    font(R.font.raleway_semibold, FontWeight.SemiBold),
    font(R.font.raleway_medium, FontWeight.Medium),
)

val typography = TypographyUtils.fromDefaults(
    h1 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )
)