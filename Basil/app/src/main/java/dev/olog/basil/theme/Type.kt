package dev.olog.basil.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import dev.olog.basil.R
import dev.olog.shared.utils.TypographyUtils

val montserrat = fontFamily(
    font(R.font.montserrat_bold, FontWeight.Bold),
    font(R.font.montserrat_medium, FontWeight.Medium),
    font(R.font.montserrat_regular, FontWeight.Normal),
    font(R.font.montserrat_semibold, FontWeight.SemiBold),
)

val lekton = fontFamily(
    font(R.font.lekton_regular, FontWeight.Normal),
    font(R.font.lekton_bold, FontWeight.Bold),
)

val typography = TypographyUtils.fromDefaults(
    h1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = lekton,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = lekton,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = lekton,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)