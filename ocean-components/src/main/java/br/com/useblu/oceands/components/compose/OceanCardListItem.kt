package br.com.useblu.oceands.components.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.useblu.oceands.components.compose.cardlistitem.DeprecatedTransitionCardListItem
import br.com.useblu.oceands.components.compose.cardlistitem.model.OceanCardListItemType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.utils.OceanIcons

@Deprecated("Uses OceanCardListItem from components.compose.cardlistitem")
@Composable
fun OceanCardListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String = "",
    caption: String = "",
    leadingIconToken: OceanIcons? = null,
    trailingIconToken: OceanIcons? = null,
    showIconBackground: Boolean = true,
    disabled: Boolean = false,
    tagLabel: String = "",
    tagType: OceanTagType? = null,
    onClick: (() -> Unit)? = null,
    isSelected: Boolean = false
) {
    val tagStyle = if (tagLabel.isNotBlank() && tagType != null) {
        OceanTagStyle.Default(
            label = tagLabel,
            type = tagType,
            layout = OceanTagLayout.Medium(
                icon = getIconDefault(tagType)
            )
        )
    } else null

    DeprecatedTransitionCardListItem(
        modifier = modifier,
        title = title,
        description = description,
        caption = caption,
        tagStyle = tagStyle,
        type = OceanCardListItemType.Default(
            leadingIconToken = leadingIconToken,
            trailingIconToken = trailingIconToken,
            showIconBackground = showIconBackground
        ),
        disabled = disabled,
        isSelected = isSelected,
        onClick = onClick
    )
}
