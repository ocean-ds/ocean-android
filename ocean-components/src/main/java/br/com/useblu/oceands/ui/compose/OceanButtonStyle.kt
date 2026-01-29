package br.com.useblu.oceands.ui.compose

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class OceanButtonStyle(
    private val buttonSize: ButtonSizes,
    private val buttonColors: IButtonColors
) : ButtonSizes by buttonSize,
    IButtonColors by buttonColors {
    data object PrimarySmall : OceanButtonStyle(small, primaryDefaultColors)
    data object PrimaryMedium : OceanButtonStyle(medium, primaryDefaultColors)
    data object PrimaryLarge : OceanButtonStyle(large, primaryDefaultColors)

    data object PrimaryInverseSmall : OceanButtonStyle(small, primaryInverseColors)
    data object PrimaryInverseMedium : OceanButtonStyle(medium, primaryInverseColors)
    data object PrimaryInverseLarge : OceanButtonStyle(large, primaryInverseColors)

    data object PrimaryCriticalSmall : OceanButtonStyle(small, primaryCriticalColors)
    data object PrimaryCriticalMedium : OceanButtonStyle(medium, primaryCriticalColors)
    data object PrimaryCriticalLarge : OceanButtonStyle(large, primaryCriticalColors)

    data object PrimaryWarningSmall : OceanButtonStyle(small, primaryWarningColors)
    data object PrimaryWarningMedium : OceanButtonStyle(medium, primaryWarningColors)
    data object PrimaryWarningLarge : OceanButtonStyle(large, primaryWarningColors)

    data object SecondarySmall : OceanButtonStyle(small, secondaryDefaultColors)
    data object SecondaryMedium : OceanButtonStyle(medium, secondaryDefaultColors)
    data object SecondaryLarge : OceanButtonStyle(large, secondaryDefaultColors)

    data object SecondaryCriticalSmall : OceanButtonStyle(small, secondaryCriticalColors)
    data object SecondaryCriticalMedium : OceanButtonStyle(medium, secondaryCriticalColors)
    data object SecondaryCriticalLarge : OceanButtonStyle(large, secondaryCriticalColors)

    data object SecondaryWarningSmall : OceanButtonStyle(small, secondaryWarningColors)
    data object SecondaryWarningMedium : OceanButtonStyle(medium, secondaryWarningColors)
    data object SecondaryWarningLarge : OceanButtonStyle(large, secondaryWarningColors)

    data object TertiarySmall : OceanButtonStyle(small, tertiaryDefaultColors)
    data object TertiaryMedium : OceanButtonStyle(medium, tertiaryDefaultColors)
    data object TertiaryLarge : OceanButtonStyle(large, tertiaryDefaultColors)

    data object TertiaryCriticalSmall : OceanButtonStyle(small, tertiaryCriticalColors)
    data object TertiaryCriticalMedium : OceanButtonStyle(medium, tertiaryCriticalColors)
    data object TertiaryCriticalLarge : OceanButtonStyle(large, tertiaryCriticalColors)

    data object TertiaryWarningSmall : OceanButtonStyle(small, tertiaryWarningColors)
    data object TertiaryWarningMedium : OceanButtonStyle(medium, tertiaryWarningColors)
    data object TertiaryWarningLarge : OceanButtonStyle(large, tertiaryWarningColors)
}

private interface ButtonSizes {
    fun getHeight(): Dp
    fun getHorizontalPadding(): Dp
    fun getIconPadding(): Dp = 8.dp

    @Composable
    fun getTextSize() = OceanFontSize.xs
}

private interface IButtonColors {
    @Composable
    fun getColors(): ButtonColors

    @Composable
    fun getLoadingColor(): Color = OceanColors.interfaceLightUp
}

private val small = object : ButtonSizes {
    override fun getHeight() = 32.dp
    override fun getHorizontalPadding() = 16.dp
    override fun getIconPadding() = 4.dp

    @Composable
    override fun getTextSize() = OceanFontSize.xxs
}
private val medium = object : ButtonSizes {
    override fun getHeight() = 48.dp
    override fun getHorizontalPadding() = 24.dp
    override fun getIconPadding() = 4.dp
}
private val large = object : ButtonSizes {
    override fun getHeight() = 56.dp
    override fun getHorizontalPadding() = 32.dp
}

private val primaryDefaultColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryDefault
}
private val primaryInverseColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryInverse
}
private val primaryCriticalColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryCritical
}
private val primaryWarningColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.primaryWarning

    @Composable
    override fun getLoadingColor() = OceanColors.interfaceLightPure
}
private val secondaryDefaultColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryDefault

    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure
}
private val secondaryCriticalColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryCritical

    @Composable
    override fun getLoadingColor() = OceanColors.brandPrimaryPure
}
private val secondaryWarningColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.secondaryWarning

    @Composable
    override fun getLoadingColor() = OceanColors.statusWarningDeep
}
private val tertiaryDefaultColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.tertiaryDefault
}
private val tertiaryCriticalColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.tertiaryCritical
}
private val tertiaryWarningColors = object : IButtonColors {
    @Composable
    override fun getColors() = OceanButtonColors.tertiaryWarning

    @Composable
    override fun getLoadingColor() = OceanColors.statusWarningDeep
}
