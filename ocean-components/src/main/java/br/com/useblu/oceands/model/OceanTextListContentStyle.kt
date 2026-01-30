package br.com.useblu.oceands.model

import androidx.compose.runtime.Composable
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanTextStyle

sealed interface OceanTextListContentStyle {
    data object Default : OceanTextListContentStyle

    data object Inverted : OceanTextListContentStyle

    @Composable
    fun titleColor(enabled: Boolean) =
        when (this) {
            Default ->
                if (enabled) OceanColors.interfaceDarkPure
                else OceanColors.interfaceDarkUp
            Inverted ->
                if (enabled) OceanColors.interfaceDarkDown
                else OceanColors.interfaceLightDeep
        }

    @Composable
    fun descriptionColor(enabled: Boolean) =
        when (this) {
            Default ->
                if (enabled) OceanColors.interfaceDarkDeep
                else OceanColors.interfaceLightDeep
            Inverted ->
                if (enabled) OceanColors.interfaceDarkPure
                else OceanColors.interfaceDarkUp
        }

    @Composable
    fun titleTextStyle() =
        when (this) {
            Default -> OceanTextStyle.paragraph
            Inverted -> OceanTextStyle.description
        }

    @Composable
    fun descriptionTextStyle() =
        when (this) {
            Default -> OceanTextStyle.description
            Inverted -> OceanTextStyle.paragraph
        }
}
