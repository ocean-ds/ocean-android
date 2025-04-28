package br.com.useblu.oceands.components.compose.cardlistitem.style

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.cardlistitem.ContentCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemStyle
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType

@Composable
internal fun DefaultCardListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    caption: String = "",
    tagStyle: OceanTagStyle? = null,
    type: OceanCardListItemType = OceanCardListItemType.Default(),
    disabled: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    BaseCardListItem(
        modifier = modifier,
        type = type,
        disabled = disabled,
        isSelected = isSelected,
        onClick = onClick
    ) {
        ContentCardListItem(
            title = title,
            description = description,
            caption = caption,
            tagStyle = tagStyle,
            type = type,
            style = OceanCardListItemStyle.Default,
            isSelected = isSelected,
            disabled = disabled
        )
    }
}
