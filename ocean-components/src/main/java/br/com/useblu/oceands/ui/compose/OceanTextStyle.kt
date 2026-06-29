package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object OceanTextStyle {

    // Necessário para que o `lineHeight` declarado bata com o "line-height" do Figma:
    // remove o font padding legado e centraliza a folga da linha (OQ-03 / §3.1 do spec MR-492).
    private val noFontPadding = PlatformTextStyle(includeFontPadding = false)
    private val centeredLineHeight = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )

    private val heading @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseExtraBold,
        platformStyle = noFontPadding,
        lineHeightStyle = centeredLineHeight
    )

    private val subtitle @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        platformStyle = noFontPadding,
        lineHeightStyle = centeredLineHeight
    )

    private val body @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        platformStyle = noFontPadding,
        lineHeightStyle = centeredLineHeight
    )

    val eyebrow @Composable get() = body.copy(
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.xxxs,
        letterSpacing = 2.16.sp,
        lineHeight = OceanFontSize.xxxs * 1.5f
    )

    val paragraph @Composable get() = body.copy(
        fontSize = OceanFontSize.xs,
        lineHeight = OceanFontSize.xs * 1.5f
    )

    val description @Composable get() = body.copy(
        fontSize = OceanFontSize.xxs,
        lineHeight = OceanFontSize.xxs * 1.5f
    )

    val descriptionStrike @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxs,
        textDecoration = TextDecoration.LineThrough
    )

    val error @Composable get() = TextStyle(
        color = OceanColors.statusNegativePure,
        fontFamily = OceanFontFamily.BaseMedium,
        fontSize = OceanFontSize.xxxs
    )

    val hint @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkUp,
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxxs
    )

    val caption @Composable get() = body.copy(
        fontSize = OceanFontSize.xxxs,
        lineHeight = OceanFontSize.xxxs * 1.5f
    )

    val captionBold @Composable get() = body.copy(
        fontFamily = OceanFontFamily.BaseMedium,
        fontSize = OceanFontSize.xxxs,
        lineHeight = OceanFontSize.xxxs * 1.5f
    )

    val lead @Composable get() = body.copy(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.sm,
        lineHeight = OceanFontSize.sm * 1.5f
    )

    val heading1 @Composable get() = heading.copy(
        fontSize = OceanFontSize.lg,
        lineHeight = OceanFontSize.lg * 1.24f
    )

    val heading2 @Composable get() = heading.copy(
        fontSize = OceanFontSize.md,
        lineHeight = OceanFontSize.md * 1.24f
    )

    val heading3 @Composable get() = heading.copy(
        fontSize = OceanFontSize.sm,
        lineHeight = OceanFontSize.sm * 1.24f
    )

    val heading4 @Composable get() = heading.copy(
        fontSize = OceanFontSize.xs,
        lineHeight = OceanFontSize.xs * 1.32f
    )

    val heading4Strike @Composable get() = heading.copy(
        fontSize = OceanFontSize.xs,
        fontFamily = OceanFontFamily.BaseBold,
        textDecoration = TextDecoration.LineThrough
    )

    val heading5 @Composable get() = heading4.copy(
        fontSize = OceanFontSize.xxs,
        lineHeight = OceanFontSize.xxs * 1.32f
    )

    val subtitle1 @Composable get() = subtitle.copy(
        fontSize = OceanFontSize.sm,
        lineHeight = OceanFontSize.sm * 1.32f
    )

    val subtitle2 @Composable get() = subtitle.copy(
        fontSize = OceanFontSize.xs,
        lineHeight = OceanFontSize.xs * 1.32f
    )

    val display @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.display
    )

    val display2 @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.giant
    )
}

@Preview
@Composable
private fun TextStylePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp)
    ) {
        Text(text = "Display 2", style = OceanTextStyle.display2)
        Text(text = "Display 1", style = OceanTextStyle.display)
        Text(text = "Heading 1", style = OceanTextStyle.heading1)
        Text(text = "Heading 2", style = OceanTextStyle.heading2)
        Text(text = "Heading 3", style = OceanTextStyle.heading3)
        Text(text = "Heading 4", style = OceanTextStyle.heading4)
        Text(text = "Heading 5", style = OceanTextStyle.heading5)
        Text(text = "Subtitle 1", style = OceanTextStyle.subtitle1)
        Text(text = "Subtitle 2", style = OceanTextStyle.subtitle2)
        Text(text = "Paragraph", style = OceanTextStyle.paragraph)
        Text(text = "Description", style = OceanTextStyle.description)
        Text(text = "Description Strike", style = OceanTextStyle.descriptionStrike)
        Text(text = "Caption", style = OceanTextStyle.caption)
        Text(text = "Lead", style = OceanTextStyle.lead)
        Text(text = "Error", style = OceanTextStyle.error)
        Text(text = "Hint", style = OceanTextStyle.hint)
        Text(text = "EYEBROW", style = OceanTextStyle.eyebrow)
    }
}
