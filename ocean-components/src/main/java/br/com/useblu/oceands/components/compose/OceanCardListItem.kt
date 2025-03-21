package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview(device = "spec:width=800dp,height=550.9dp,dpi=440")
@Composable
private fun OceanCardListItemPreview() {
    OceanTheme {
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
                    title = "Title",
                    onClick = {}
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
                    leadingIconToken = OceanIcons.PAGBLU_OUTLINE,
                    isSelected = true
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
}

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
    val backgroundColor = if (isSelected) {
        OceanColors.interfaceLightUp
    } else OceanColors.interfaceLightPure

    val borderColor = if (isSelected) {
        OceanColors.brandPrimaryUp
    } else OceanColors.interfaceLightDown

    val iconBackgroundColor = if (isSelected) {
        OceanColors.brandPrimaryDown
    } else OceanColors.interfaceLightUp

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(OceanSpacing.xs)
        ) {
            if (leadingIconToken != null) {
                val iconSize = if (showIconBackground) 24.dp else 20.dp

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .iconContainerBackground(showIconBackground, iconBackgroundColor)
                ) {
                    OceanIcon(
                        iconType = leadingIconToken,
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

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row {
                    Text(
                        text = title,
                        style = OceanTextStyle.paragraph,
                        color = if (disabled) OceanColors.interfaceLightDeep else OceanColors.interfaceDarkPure
                    )

                    if (tagLabel.isNotBlank() && tagType != null) {
                        OceanTag(
                            style = OceanTagStyle.Default(
                                label = tagLabel,
                                type = tagType,
                                layout = OceanTagLayout.Medium(
                                    icon = getIconDefault(tagType)
                                )
                            )
                        )
                    }
                }

                if (description.isNotBlank()) {
                    OceanText(
                        text = description,
                        style = OceanTextStyle.description,
                        color = if (disabled) OceanColors.interfaceLightDeep else Color.Unspecified
                    )
                }

                if (caption.isNotBlank()) {
                    OceanSpacing.StackXXXS()

                    OceanText(
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
}
