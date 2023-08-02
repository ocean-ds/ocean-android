package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(20.dp)
    ) {
        OceanStatusListItem(
            title = "Title",
            description = "Description",
            caption = "Caption",
            tagLabel = "Tag",
            tagType = OceanTagType.Warning
        )

        OceanStatusListItem(
            title = "Title",
            description = "Description",
            caption = "Caption",
            tagLabel = "Tag",
            tagType = OceanTagType.Warning,
            tagPosition = OceanStatusListItemTagPosition.RIGHT,
            rightIconType = OceanStatusListItemRightIconType.CONTEXT_MENU
        )

        OceanStatusListItem(
            title = "Title",
            description = "Description",
            caption = "Caption",
            tagLabel = "Tag",
            tagType = OceanTagType.Warning,
            tagPosition = OceanStatusListItemTagPosition.RIGHT,
            rightIconType = OceanStatusListItemRightIconType.NONE
        )
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
    onClickRightIcon: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
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
                color = OceanColors.interfaceDarkPure
            )
            Text(
                text = description,
                style = OceanTextStyle.description
            )

            OceanSpacing.StackXXXS()

            Text(
                text = caption,
                style = OceanTextStyle.caption
            )

            if (tagPosition == OceanStatusListItemTagPosition.BOTTOM) {
                OceanSpacing.StackXXXS()
                OceanTag(label = tagLabel, type = tagType)
            }
        }

        if (tagPosition == OceanStatusListItemTagPosition.RIGHT) {
            OceanTag(label = tagLabel, type = tagType)
        }

        val rightIcon = when (rightIconType) {
            OceanStatusListItemRightIconType.CHEVRON -> {
                R.drawable.icon_chevron_right
            }
            OceanStatusListItemRightIconType.CONTEXT_MENU -> {
                R.drawable.icon_vertical_solid_dots
            }
            OceanStatusListItemRightIconType.NONE ->  null
        }

        rightIcon?.let {
            OceanSpacing.StackXS()
            IconButton(
                onClick = { onClickRightIcon?.invoke() },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = it),
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
    NONE,
    CHEVRON,
    CONTEXT_MENU
}