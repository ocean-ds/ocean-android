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
            Negative -> OceanColors.statusNegativePure
            is Custom -> backgroundColor
        }

    @Composable
    fun getTitleColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDeep
            Brand, Emphasys, Negative -> OceanColors.interfaceLightPure
            is Custom -> titleColor
        }

    @Composable
    fun getDescriptionColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDown
            Brand, Emphasys, Negative -> OceanColors.interfaceLightUp
            is Custom -> descriptionColor
        }

    fun getButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.PrimarySmall
            Brand, Emphasys -> OceanButtonStyle.SecondarySmall
            Warning -> OceanButtonStyle.PrimaryWarningSmall
            Negative -> OceanButtonStyle.PrimaryInverseSmall
            is Custom -> customButtonStyle
        }

    fun getSecondaryButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.SecondarySmall
            Brand, Emphasys -> OceanButtonStyle.PrimaryInverseSmall
            Warning -> OceanButtonStyle.SecondaryWarningSmall
            Negative -> OceanButtonStyle.SecondaryCriticalSmall
            is Custom -> customButtonStyle
        }
}
