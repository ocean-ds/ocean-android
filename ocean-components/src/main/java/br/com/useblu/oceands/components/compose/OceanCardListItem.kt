package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons


@Preview(device = "spec:width=800dp,height=550.9dp,dpi=440")
@Composable
private fun OceanCardListItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description"
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title",
                leadingIconToken = OceanIcons.SWITCH_HORIZONTAL_OUTLINE
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                disabled = true
            )
        }

        Column(
            modifier = Modifier
                .width(250.dp)
        ) {
            OceanCardListItem(
                title = "Title",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                showIconBackground = false
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                disabled = true
            )

            OceanSpacing.StackXXS()

            OceanCardListItem(
                title = "Title",
                description = "Description",
                caption = "Caption",
                leadingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                trailingIconToken = OceanIcons.PLACEHOLDER_OUTLINE,
                tagLabel = "Ativo",
                tagType = OceanTagType.Positive
            )
        }
    }
}

@Composable
fun OceanCardListItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    caption: String? = null,
    leadingIconToken: OceanIcons? = null,
    trailingIconToken: OceanIcons? = null,
    showIconBackground: Boolean = true,
    disabled: Boolean = false,
    tagLabel: String? = null,
    tagType: OceanTagType? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .clickable(
                enabled = onClick != null && !disabled,
                onClick = { onClick?.invoke() }
            )
            .background(
                color = OceanColors.interfaceLightPure,
            )
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIconToken?.let {
            val iconSize = if (showIconBackground) 24.dp else 20.dp

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .iconContainerBackground(showIconBackground)
            ) {
                OceanIcon(
                    iconType = it,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(iconSize),
                    tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.brandPrimaryDown
                )
            }

            OceanSpacing.StackXS()
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Text(
                    text = title,
                    style = OceanTextStyle.paragraph,
                    color = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkPure
                )

                if (!tagLabel.isNullOrBlank() && tagType != null) {
                    OceanTag(label = tagLabel, type = tagType)
                }
            }

            description?.let {
                Text(
                    text = description,
                    style = OceanTextStyle.description,
                    color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
                )
            }

            caption?.let {
                OceanSpacing.StackXXXS()

                Text(
                    text = caption,
                    style = OceanTextStyle.caption,
                    color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
                )
            }
        }

        trailingIconToken?.let {
            OceanSpacing.StackXS()

            OceanIcon(
                iconType = it,
                modifier = Modifier.size(20.dp),
                tint = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkUp
            )
        }
    }
}


