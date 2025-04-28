package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius

@Composable
internal fun BaseCardListItem(
    modifier: Modifier = Modifier,
    type: OceanCardListItemType,
    disabled: Boolean,
    isSelected: Boolean,
    onClick: (() -> Unit)?,
    targetBorderColor: Color? = null,
    content: @Composable () -> Unit
) {
    val backgroundColor = OceanCardListItemStyle.Default.getBackgroundColor(
        isSelected = isSelected,
        type = type
    )
    val borderColor = targetBorderColor ?: OceanCardListItemStyle.Default.getBorderColor(
        isSelected = isSelected,
        type = type
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        ),
        shape = OceanBorderRadius.SM.allCorners.shape(),
        border = BorderStroke(1.dp, borderColor),
        enabled = onClick != null && !disabled,
        onClick = { onClick?.invoke() }
    ) {
        content()
    }
}
