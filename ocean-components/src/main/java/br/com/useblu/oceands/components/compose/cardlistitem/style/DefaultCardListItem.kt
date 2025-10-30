package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.cardlistitem.ContentCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemContentStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType

@Composable
internal fun DefaultCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    contentStyle: OceanCardListItemContentStyle = OceanCardListItemContentStyle.Default,
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)?,
    onDisabledClick: (() -> Unit)?
) {
    BaseCardListItem(
        modifier = modifier,
        type = type,
        disabled = disabled,
        isSelected = isSelected,
        onClick = onClick,
        onDisabledClick = onDisabledClick
    ) {
        ContentCardListItem(
            title = title,
            description = description,
            caption = caption,
            contentStyle = contentStyle,
            tagStyle = tagStyle,
            type = type,
            style = OceanCardListItemStyle.Default,
            isSelected = isSelected,
            disabled = disabled
        )
    }
}
