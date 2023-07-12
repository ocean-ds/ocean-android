package br.com.useblu.oceands.ui.compose

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class OceanButtonStyle: ButtonSizes, IButtonColors {
    object PrimarySmall: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by small
    object PrimaryMedium: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by medium
    object PrimaryLarge: OceanButtonStyle(),
        IButtonColors by primaryDefaultColors,
        ButtonSizes by large

    object PrimaryInverseSmall: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by small
    object PrimaryInverseMedium: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by medium
    object PrimaryInverseLarge: OceanButtonStyle(),
        IButtonColors by primaryInverseColors,
        ButtonSizes by large

    object PrimaryCriticalSmall: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by small

    object PrimaryCriticalMedium: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by medium

    object PrimaryCriticalLarge: OceanButtonStyle(),
        IButtonColors by primaryCriticalColors,
        ButtonSizes by large

    object SecondarySmall: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by small
    object SecondaryMedium: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by medium
    object SecondaryLarge: OceanButtonStyle(),
        IButtonColors by secondaryDefaultColors,
        ButtonSizes by large

    object SecondaryCriticalSmall: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by small
    object SecondaryCriticalMedium: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by medium
    object SecondaryCriticalLarge: OceanButtonStyle(),
        IButtonColors by secondaryCriticalColors,
        ButtonSizes by large
}

private interface ButtonSizes {
    fun getHeight(): Dp
    fun getHorizontalPadding(): Dp
    fun getIconPadding(): Dp = 8.dp
    @Composable
    fun getTextSize() = OceanFontSize.xxs()
}

private interface IButtonColors {
    @Composable
    fun getColors(): ButtonColors
    @Composable
    fun getLoadingColor(): Color = OceanColors.interfaceLightUp()
}

private val small = object: ButtonSizes {
    override fun getHeight() = 32.dp
    override fun getHorizontalPadding() = 16.dp
    override fun getIconPadding() = 4.dp
    @Composable
    override fun getTextSize() = OceanFontSize.xxxs()
}
private val medium = object: ButtonSizes {
    override fun getHeight() = 48.dp
    override fun getHorizontalPadding() = 24.dp
}
private val large = object: ButtonSizes {
    override fun getHeight() = 56.dp
    override fun getHorizontalPadding() = 32.dp
}

private val primaryDefaultColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryDefaultButtonColors()
}
private val primaryInverseColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryInverseButtonColors()
}
private val primaryCriticalColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryCriticalButtonColors()
}
private val secondaryDefaultColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryDefaultButtonColors()
    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure()

}
private val secondaryCriticalColors = object: IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryCriticalButtonColors()
    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure()
}