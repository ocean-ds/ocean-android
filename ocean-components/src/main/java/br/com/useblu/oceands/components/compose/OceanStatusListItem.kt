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
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle


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
        }
    }
}

@Composable
fun OceanStatusListItem(
    title: String,
    description: String,
    caption: String,
    tagLabel: String,
    tagPosition: OceanStatusListItemTagPosition = OceanStatusListItemTagPosition.BOTTOM,
    tagType: OceanTagType = OceanTagType.Warning,
    isReadOnly: Boolean = false,
    isInactive: Boolean = false,
    rightIconType: OceanStatusListItemRightIconType = OceanStatusListItemRightIconType.CHEVRON,
    onClick: (() -> Unit)? = null,
    onClickRightIcon: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
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
            Text(
                text = description,
                style = OceanTextStyle.description,
                color = if (isInactive) OceanColors.interfaceDarkUp else Color.Unspecified
            )

            OceanSpacing.StackXXXS()

            Text(
                text = caption,
                style = OceanTextStyle.caption,
                color = if (isInactive) OceanColors.interfaceDarkUp else Color.Unspecified
            )

            if (tagPosition == OceanStatusListItemTagPosition.BOTTOM) {
                OceanSpacing.StackXXXS()
                OceanTag(label = tagLabel, type = tagType)
            }
        }

        if (tagPosition == OceanStatusListItemTagPosition.RIGHT) {
            OceanTag(label = tagLabel, type = tagType)
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