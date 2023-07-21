package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

object OceanTextStyle {

    private val heading @Composable get() = TextStyle(
        color = OceanColors.interfaceDarkPure,
        fontFamily = OceanFontFamily.HighlightExtraBold
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
        fontFamily = OceanFontFamily.HighlightBold
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