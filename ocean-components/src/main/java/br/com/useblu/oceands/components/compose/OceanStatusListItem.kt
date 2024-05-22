package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanStatusListItemPreview() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagIcon = OceanIcons.ACADEMIC_CAP_SOLID,
                badge = "66+",
                onClick = {
                    println("click container")
                }
            )

            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                tagIcon = OceanIcons.ACADEMIC_CAP_SOLID,
                rightIconType = OceanStatusListItemRightIconType.CONTEXT_MENU,
                onClick = {
                    println("click container")
                },
                onClickRightIcon = {
                    println("click right icon")
                }
            )

            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                isReadOnly = true
            )

            OceanStatusListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                isInactive = true,
                onClick = {
                    println("click container")
                },
                onClickRightIcon = {
                    println("click right icon")
                }
            )

            OceanStatusListItem(
                title = "Title",
                description = "Description",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                isInactive = true,
                onClick = {
                    println("click container")
                },
                onClickRightIcon = {
                    println("click right icon")
                }
            )

            OceanStatusListItem(
                title = "Title",
                caption = "Caption",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                isInactive = true,
                onClick = {
                    println("click container")
                },
                onClickRightIcon = {
                    println("click right icon")
                }
            )

            OceanStatusListItem(
                title = "Title",
                tagLabel = "Tag",
                tagType = OceanTagType.Warning,
                tagPosition = OceanStatusListItemTagPosition.RIGHT,
                isInactive = true,
                onClick = {
                    println("click container")
                },
                onClickRightIcon = {
                    println("click right icon")
                }
            )

            OceanStatusListItem(
                title = "Title",
                description = "Description",
                badge = "6",
                rightIconType = OceanStatusListItemRightIconType.CHEVRON,
                onClick = {
                    println("click container")
                }
            )
        }
    }
}

@Composable
fun OceanStatusListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    tagLabel: String? = null,
    tagIcon: OceanIcons? = null,
    tagPosition: OceanStatusListItemTagPosition? = OceanStatusListItemTagPosition.BOTTOM,
    tagType: OceanTagType? = OceanTagType.Warning,
    badge: String? = null,
    badgeType: OceanBadgeType? = null,
    isReadOnly: Boolean = false,
    isInactive: Boolean = false,
    rightIconType: OceanStatusListItemRightIconType = OceanStatusListItemRightIconType.CHEVRON,
    onClick: (() -> Unit)? = null,
    onClickRightIcon: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable(
                enabled = !isReadOnly && !isInactive && onClick != null,
                onClick = { onClick?.invoke() }
            )
            .background(color = OceanColors.interfaceLightPure)
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = OceanTextStyle.paragraph,
                color = if (isInactive) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkPure
            )

            description?.let {
                Text(
                    text = it,
                    style = OceanTextStyle.description,
                    color = if (isInactive) OceanColors.interfaceDarkUp else Color.Unspecified
                )
            }

            caption?.let {
                OceanSpacing.StackXXXS()
                Text(
                    text = it,
                    style = OceanTextStyle.caption,
                    color = if (isInactive) OceanColors.interfaceDarkUp else Color.Unspecified
                )
            }

            tagLabel?.let {
                if (tagType != null && tagPosition == OceanStatusListItemTagPosition.BOTTOM) {
                    OceanSpacing.StackXXXS()
                    OceanTag(label = tagLabel, type = tagType, icon = tagIcon)
                }
            }
        }

        badge?.let {
            OceanBadge(
                text = badge,
                type = badgeType ?: OceanBadgeType.WARNING,
                size = OceanBadgeSize.Medium
            )
            OceanSpacing.StackXXXS()
        }

        tagLabel?.let {
            if (tagType != null && tagPosition == OceanStatusListItemTagPosition.RIGHT) {
                OceanTag(label = tagLabel, type = tagType, icon = tagIcon)
            }
        }

        if (!isReadOnly) {
            val rightIcon = when (rightIconType) {
                OceanStatusListItemRightIconType.CHEVRON -> {
                    R.drawable.icon_chevron_right
                }

                OceanStatusListItemRightIconType.CONTEXT_MENU -> {
                    R.drawable.icon_vertical_solid_dots
                }
            }

            val isIconClickable = onClickRightIcon != null && !isInactive

            if (isIconClickable) {
                OceanSpacing.StackXS()
                IconButton(
                    onClick = { onClickRightIcon?.invoke() },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = rightIcon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = OceanColors.interfaceDarkUp
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = OceanColors.interfaceDarkUp
                )
            }
        }
    }
}

enum class OceanStatusListItemTagPosition {
    BOTTOM,
    RIGHT
}

enum class OceanStatusListItemRightIconType {
    CHEVRON,
    CONTEXT_MENU
}