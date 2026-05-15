package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.extensions.compose.disabledOverlay
import br.com.useblu.oceands.model.compose.OceanCardCornerTag
import br.com.useblu.oceands.ui.compose.OceanBorderRadius

@Composable
internal fun BaseCardListItem(
    modifier: Modifier = Modifier,
    type: OceanCardListItemType,
    disabled: Boolean,
    isSelected: Boolean,
    cornerTag: OceanCardCornerTag? = null,
    onClick: (() -> Unit)? = null,
    onDisabledClick: (() -> Unit)? = null,
    targetBorderColor: Color? = null,
    content: @Composable () -> Unit
) {
    val hasValidClick = onClick != null || (disabled && onDisabledClick != null)

    val backgroundColor = OceanCardListItemStyle.Default.getBackgroundColor(
        isSelected = isSelected,
        type = type
    )
    val borderColor = targetBorderColor ?: OceanCardListItemStyle.Default.getBorderColor(
        isSelected = isSelected,
        type = type
    )

    val borderRadius = OceanBorderRadius.SM.allCorners

    val cardModifier = modifier.disabledOverlay(
        isDisabled = disabled,
        disabledAlpha = 0.00f,
        borderRadius = borderRadius
    )

    val cardColors = CardDefaults.cardColors(
        containerColor = backgroundColor,
        disabledContainerColor = backgroundColor
    )

    if (hasValidClick) {
        Card(
            modifier = cardModifier,
            colors = cardColors,
            shape = borderRadius.shape(),
            border = BorderStroke(1.dp, borderColor),
            enabled = (disabled && onDisabledClick == null).not(),
            onClick = {
                if (disabled) {
                    onDisabledClick?.invoke()
                } else {
                    onClick?.invoke()
                }
            }
        ) {
            CardContentWithCornerTag(cornerTag = cornerTag, content = content)
        }
    } else {
        Card(
            modifier = cardModifier,
            colors = cardColors,
            shape = borderRadius.shape(),
            border = BorderStroke(1.dp, borderColor)
        ) {
            CardContentWithCornerTag(cornerTag = cornerTag, content = content)
        }
    }
}

@Composable
private fun CardContentWithCornerTag(
    cornerTag: OceanCardCornerTag?,
    content: @Composable () -> Unit
) {
    Box {
        content()

        if (cornerTag != null) {
            OceanTag(
                style = cornerTag.tag,
                modifier = Modifier.align(cornerTag.alignment)
            )
        }
    }
}
