package br.com.useblu.oceands.components.compose.cardlistitem.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.utils.OceanIcons

sealed interface OceanCardListItemStyle {
    data object Default : OceanCardListItemStyle

    data class Highlighted(
        val caption: String,
        val backgroundColor: Color,
        val icon: OceanIcons,
        val iconColor: Color,
        val animation: Animation? = null
    ) : OceanCardListItemStyle {
        data class Animation(
            val targetBorderColor: Color,
            val shadowColor: Color
        )
    }

    @Composable
    fun getBackgroundColor(isSelected: Boolean, type: OceanCardListItemType): Color {
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
    fun getBorderColor(isSelected: Boolean, type: OceanCardListItemType): Color {
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
}
