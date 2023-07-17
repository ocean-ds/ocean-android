package br.com.useblu.oceands.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

object OceanTextStyle {

    @Composable
    private fun heading() = TextStyle(
        color = OceanColors.interfaceDarkPure(),
        fontFamily = OceanFontFamily.HighlightExtraBold
    )

    @Composable
    private fun subtitle() = TextStyle(
        color = OceanColors.interfaceDarkDown(),
        fontFamily = OceanFontFamily.BaseRegular
    )

    @Composable
    fun paragraph() = TextStyle(
        color = OceanColors.interfaceDarkDown(),
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xs()
    )

    @Composable
    fun description() = TextStyle(
        color = OceanColors.interfaceDarkDown(),
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxs()
    )

    @Composable
    fun caption() = TextStyle(
        color = OceanColors.interfaceDarkDown(),
        fontFamily = OceanFontFamily.BaseRegular,
        fontSize = OceanFontSize.xxxs()
    )

    @Composable
    fun lead() = TextStyle(
        color = OceanColors.interfaceDarkDeep(),
        fontFamily = OceanFontFamily.BaseBold,
        fontSize = OceanFontSize.sm()
    )

    @Composable
    fun heading1() = heading().copy(
        fontSize = OceanFontSize.lg()
    )

    @Composable
    fun heading2() = heading().copy(
        fontSize = OceanFontSize.md()
    )

    @Composable
    fun heading3() = heading().copy(
        fontSize = OceanFontSize.sm()
    )

    @Composable
    fun heading4() = heading().copy(
        fontSize = OceanFontSize.xs(),
        fontFamily = OceanFontFamily.HighlightBold
    )

    @Composable
    fun heading5() = heading4().copy(
        fontSize = OceanFontSize.xxs(),
    )

    @Composable
    fun subtitle1() = subtitle().copy(
        fontSize = OceanFontSize.md()
    )

    @Composable
    fun subtitle2() = subtitle().copy(
        fontSize = OceanFontSize.sm()
    )
}