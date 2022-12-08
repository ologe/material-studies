package dev.olog.material.studies.basil.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.olog.material.studies.basil.R
import dev.olog.material.studies.shared.TypographyUtils

@Composable
fun basilTypography(): Typography {
    return TypographyUtils.fromDefaults(
        h1 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 96.sp,
        ),
        h2 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 60.sp
        ),
        h3 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 48.sp
        ),
        h4 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 34.sp
        ),
        h5 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        ),
        h6 = TextStyle(
            fontFamily = lektonFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = lektonFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = lektonFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        ),
        body1 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        body2 = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        button = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        caption = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
    )
}

private val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_black, FontWeight.Black),
    Font(R.font.montserrat_extra_bold, FontWeight.ExtraBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_semi_bold, FontWeight.SemiBold),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_extra_light, FontWeight.ExtraLight),
    Font(R.font.montserrat_thin, FontWeight.Thin),
)

private val lektonFontFamily = FontFamily(
    Font(R.font.lekton_regular, FontWeight.Normal),
    Font(R.font.lekton_bold, FontWeight.Bold),
)