package br.com.useblu.oceands.components.compose.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors

sealed interface OceanBannerStyle {
    data object Neutral : OceanBannerStyle
    data object Brand : OceanBannerStyle
    data object Warning : OceanBannerStyle
    data object Negative : OceanBannerStyle
    data object Emphasys : OceanBannerStyle
    data class Custom(
        val backgroundColor: Color,
        val titleColor: Color,
        val descriptionColor: Color,
        val customButtonStyle: OceanButtonStyle
    ) : OceanBannerStyle

    @Composable
    fun getBackgroundColor(): Color =
        when (this) {
            Neutral -> OceanColors.interfaceLightUp
            Brand, Emphasys -> OceanColors.brandPrimaryPure
            Warning -> OceanColors.statusWarningUp
            Negative -> OceanColors.statusNegativeUp
            is Custom -> backgroundColor
        }

    @Composable
    fun getTitleColor(): Color =
        when (this) {
            Neutral, Warning, Negative -> OceanColors.interfaceDarkDeep
            Brand, Emphasys -> OceanColors.interfaceLightPure
            is Custom -> titleColor
        }

    @Composable
    fun getDescriptionColor(): Color =
        when (this) {
            Neutral, Warning, Negative -> OceanColors.interfaceDarkDown
            Brand, Emphasys -> OceanColors.interfaceLightUp
            is Custom -> descriptionColor
        }

    fun getButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.PrimarySmall
            Brand, Emphasys -> OceanButtonStyle.SecondarySmall
            Warning -> OceanButtonStyle.PrimaryWarningSmall
            Negative -> OceanButtonStyle.PrimaryCriticalSmall
            is Custom -> customButtonStyle
        }

    fun getSecondaryButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.TertiarySmall
            Brand, Emphasys -> OceanButtonStyle.PrimaryInverseSmall
            Warning -> OceanButtonStyle.TertiaryWarningSmall
            Negative -> OceanButtonStyle.TertiaryCriticalSmall
            is Custom -> customButtonStyle
        }
}
