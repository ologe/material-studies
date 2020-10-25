package dev.olog.owl.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import dev.olog.owl.R
import dev.olog.shared.utils.TypographyUtils


val rubik = fontFamily(
    font(R.font.rubik_regular, FontWeight.Normal),
    font(R.font.rubik_medium, FontWeight.Medium),
    font(R.font.rubik_bold, FontWeight.Bold)
)

val typography = TypographyUtils.fromDefaults(
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