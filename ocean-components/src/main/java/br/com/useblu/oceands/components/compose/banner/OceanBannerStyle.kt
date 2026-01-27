package br.com.useblu.oceands.components.compose.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors

sealed interface OceanBannerStyle {
    data object Neutral : OceanBannerStyle
    data object Brand : OceanBannerStyle
    data object Warning : OceanBannerStyle

    @Composable
    fun getBackgroundColor(): Color =
        when (this) {
            Neutral -> OceanColors.interfaceLightUp
            Brand -> OceanColors.brandPrimaryPure
            Warning -> OceanColors.statusWarningUp
        }

    @Composable
    fun getTitleColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDeep
            Brand -> OceanColors.interfaceLightPure
        }

    @Composable
    fun getDescriptionColor(): Color =
        when (this) {
            Neutral, Warning -> OceanColors.interfaceDarkDown
            Brand -> OceanColors.interfaceLightUp
        }

    fun getButtonStyle(): OceanButtonStyle =
        when (this) {
            Neutral -> OceanButtonStyle.PrimarySmall
            Brand -> OceanButtonStyle.SecondarySmall
            Warning -> OceanButtonStyle.PrimaryWarningSmall
        }
}
