package br.com.useblu.oceands.components.compose.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors

sealed interface OceanBannerStyle {
    data object Neutral : OceanBannerStyle
    data object Brand : OceanBannerStyle
    data object Warning : OceanBannerStyle
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
            Brand -> OceanColors.brandPrimaryPure
            Warning -> OceanColors.statusWarningUp
            is Custom -> backgroundColor
        }

    @Composable
    fun getTitleColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDeep
            Brand -> OceanColors.interfaceLightPure
            is Custom -> titleColor
        }

    @Composable
    fun getDescriptionColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDown
            Brand -> OceanColors.interfaceLightUp
            is Custom -> descriptionColor
        }

    fun getButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.PrimarySmall
            Brand -> OceanButtonStyle.SecondarySmall
            Warning -> OceanButtonStyle.PrimaryWarningSmall
            is Custom -> customButtonStyle
        }
}
