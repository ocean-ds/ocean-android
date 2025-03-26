package br.com.useblu.oceands.components.compose.cardlistitem.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanColors

sealed interface OceanCardListItemStyle {
    data object Default : OceanCardListItemStyle {
        @Composable
        override fun getBackgroundColor(isSelected: Boolean, type: OceanCardListItemType): Color {
            return when (type) {
                is OceanCardListItemType.Default -> {
                    if (isSelected) {
                        OceanColors.interfaceLightUp
                    } else OceanColors.interfaceLightPure
                }

                is OceanCardListItemType.Selectable -> {
                    OceanColors.interfaceLightPure
                }
            }
        }

        @Composable
        override fun getBorderColor(isSelected: Boolean, type: OceanCardListItemType): Color {
            return when (type) {
                is OceanCardListItemType.Default -> {
                    if (isSelected) {
                        OceanColors.brandPrimaryUp
                    } else OceanColors.interfaceLightDown
                }

                is OceanCardListItemType.Selectable -> {
                    OceanColors.interfaceLightDown
                }
            }
        }

        @Composable
        override fun getIconBackgroundColor(isSelected: Boolean): Color {
            return if (isSelected) {
                OceanColors.brandPrimaryDown
            } else OceanColors.interfaceLightUp
        }
    }

    @Composable
    fun getBackgroundColor(isSelected: Boolean, type: OceanCardListItemType): Color

    @Composable
    fun getBorderColor(isSelected: Boolean, type: OceanCardListItemType): Color

    @Composable
    fun getIconBackgroundColor(isSelected: Boolean): Color
}
