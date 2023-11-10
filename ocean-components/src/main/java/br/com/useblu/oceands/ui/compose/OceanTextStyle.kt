package br.com.useblu.oceands.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
private fun TextStylePreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp)
    ) {
        Text(text = "Heading 1", style = OceanTextStyle.heading1)
        Text(text = "Heading 2", style = OceanTextStyle.heading2)
        Text(text = "Heading 3", style = OceanTextStyle.heading3)
        Text(text = "Heading 4", style = OceanTextStyle.heading4)
        Text(text = "Heading 5", style = OceanTextStyle.heading5)
        Text(text = "Subtitle 1", style = OceanTextStyle.subtitle1)
        Text(text = "Subtitle 2", style = OceanTextStyle.subtitle2)
        Text(text = "Paragraph", style = OceanTextStyle.paragraph)
        Text(text = "Description", style = OceanTextStyle.description)
        Text(text = "Caption", style = OceanTextStyle.caption)
        Text(text = "Lead", style = OceanTextStyle.lead)
    }
}

object OceanTextStyle {

    private val heading @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseExtraBold
    )

    private val subtitle @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular
    )

    val paragraph @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xs
    )

    val description @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxs
    )

    val caption @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxxs
    )

    val captionBold @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDown,
        fontFamily = OceanFontFamily.BaseMedium,
        fontSize = OceanFontSize.xxxs
    )

    val lead @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkDeep,
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.sm
    )

    val heading1 @Composable get() = heading.copy(
        fontSize = OceanFontSize.lg
    )

    val heading2 @Composable get() = heading.copy(
        fontSize = OceanFontSize.md
    )

    val heading3 @Composable get() = heading.copy(
        fontSize = OceanFontSize.sm
    )

    val heading4 @Composable get() = heading.copy(
        fontSize = OceanFontSize.xs,
        fontFamily = OceanFontFamily.BaseBold
    )

    val heading5 @Composable get() = heading4.copy(
        fontSize = OceanFontSize.xxs,
    )

    val subtitle1 @Composable get() = subtitle.copy(
        fontSize = OceanFontSize.md
    )

    val subtitle2 @Composable get() = subtitle.copy(
        fontSize = OceanFontSize.sm
    )
}
