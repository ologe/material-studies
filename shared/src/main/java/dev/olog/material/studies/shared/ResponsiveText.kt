package dev.olog.material.studies.shared

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.TextDelegate
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Stable
fun ResponsiveSize(
    minTextSize: TextUnit = 1.sp,
    stepGranularity: Float = 0.95f,
): ResponsiveSize {
    require(minTextSize.isSp) { "only sp is supported, but is $minTextSize" }
    require(stepGranularity in 0.01..0.99) { "step=$stepGranularity out of range [0.01..0.99]" }
    return ResponsiveSize(packFloats(minTextSize.value, stepGranularity))
}

@Immutable
@JvmInline
value class ResponsiveSize internal constructor(private val packed: Long) {

    @Stable
    val minTextSize: TextUnit
        get() = unpackFloat1(packed).sp

    @Stable
    val stepGranularity: Float
        get() = unpackFloat2(packed)
}

@Composable
@NonRestartableComposable
fun ResponsiveText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    responsiveSize: ResponsiveSize = ResponsiveSize(),
) {
    ResponsiveText(
        text = AnnotatedString(text),
        modifier = modifier,
        color = color,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style,
        responsiveSize = responsiveSize,
    )
}

@Composable
fun ResponsiveText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    responsiveSize: ResponsiveSize = ResponsiveSize(),
) {
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        }
    }
    // mergedStyle implementation copied from androidx.compose.material.Text
    // needed to avoid difference between computed style and used style
    val mergedStyle = style.merge(
        TextStyle(
            color = textColor,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        )
    )

    BoxWithConstraints(
        modifier = modifier,
        propagateMinConstraints = true,
    ) {
        val adjustedStyle = rememberFittingTextStyle(
            text = text,
            responsiveSize = responsiveSize,
            style = mergedStyle,
            maxLines = maxLines,
            softWrap = softWrap,
        )

        Text(
            text = text,
            modifier = modifier,
            overflow = TextOverflow.Visible,
            softWrap = softWrap,
            maxLines = maxLines,
            inlineContent = inlineContent,
            onTextLayout = onTextLayout,
            style = adjustedStyle,
        )
    }
}

@Composable
private fun BoxWithConstraintsScope.rememberFittingTextStyle(
    text: AnnotatedString,
    responsiveSize: ResponsiveSize,
    style: TextStyle,
    maxLines: Int,
    softWrap: Boolean,
): TextStyle {
    val density = LocalDensity.current
    val fontFamilyResolver = LocalFontFamilyResolver.current
    val layoutDirection = LocalLayoutDirection.current

    // recompute on the following changes
    return remember(
        text,
        responsiveSize,
        style,
        density,
        fontFamilyResolver,
        layoutDirection,
        maxLines,
        constraints,
    ) {
        // decrease font size until matches
        style.decreasingSizeSequence(responsiveSize)
            .map { fontSize ->
                TextDelegate(
                    text = text,
                    style = style.copy(
                        fontSize = fontSize,
                        // unset line height when text size is lower
                        lineHeight = if (fontSize == style.fontSize) style.lineHeight else TextUnit.Unspecified,
                    ),
                    density = density,
                    fontFamilyResolver = fontFamilyResolver,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = maxLines,
                    softWrap = softWrap,
                )
            }
            .filter {
                val result = it.layout(constraints, layoutDirection)
                !result.hasVisualOverflow || !result.isTextEllipsized()
            }
            .map { it.style }
            .firstOrNull()
            ?: style // default to initial style is nothing is fitting
    }
}

private fun TextStyle.decreasingSizeSequence(
    responsiveSize: ResponsiveSize
) = sequence {
    var size = fontSize
    yield(size)
    while (size > responsiveSize.minTextSize) {
        size *= responsiveSize.stepGranularity
        yield(size)
    }
}

private fun TextLayoutResult.isTextEllipsized(): Boolean {
    return isLineEllipsized(lineCount - 1)
}