package br.com.useblu.oceands.components.compose.cardlistitem.type

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Composable
internal fun LeadingDefaultTypeCardListItem(
    type: OceanCardListItemType.Default,
    style: OceanCardListItemStyle,
    isSelected: Boolean,
    disabled: Boolean
) {
    if (type.leadingIconToken != null) {
        val iconSize = if (type.showIconBackground) 24.dp else 20.dp
        val iconBackgroundColor = style.getIconBackgroundColor(isSelected = isSelected)
        Box(
            modifier = Modifier
                .size(40.dp)
                .iconContainerBackground(type.showIconBackground, iconBackgroundColor)
        ) {
            OceanIcon(
                iconType = type.leadingIconToken,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize),
                tint = when {
                    disabled -> OceanColors.interfaceLightDeep
                    isSelected -> OceanColors.interfaceLightPure
                    else -> OceanColors.brandPrimaryDown
                }
            )
        }

        OceanSpacing.StackXS()
    }
}
