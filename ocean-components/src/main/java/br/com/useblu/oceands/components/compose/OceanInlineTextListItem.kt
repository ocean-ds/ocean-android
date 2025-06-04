package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.model.OceanInlineTextList
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemDescription
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemDescriptionGenericType
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemSize
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemTitle
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanInlineTextListItemPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
    ) {
        Column(
            modifier = Modifier
                .padding(OceanSpacing.xs)
        ) {
            OceanInlineTextListItem(
                item = OceanInlineTextList(
                    label = "Label",
                    value = "Value",
                    newValue = "New Value",
                    color = "colorStatusPositiveDeep",
                    icon = "tagsolid"
                )
            )
            OceanInlineTextListItem(
                item = OceanInlineTextList(
                    label = "Label with tooltip",
                    tooltip = "Tooltip text",
                    value = "Value",
                    color = "colorStatusPositiveDeep"
                )
            )
            OceanInlineTextListItem(
                item = OceanInlineTextList(
                    label = "Label",
                    value = "Value",
                    newValue = "New Value",
                    color = "colorInterfaceDarkDown"
                )
            )
            OceanInlineTextListItem(
                item = OceanInlineTextList(
                    label = "Label bold",
                    value = "Value bold",
                    color = "colorInterfaceDarkPure",
                    isBold = true,
                    icon = OceanIcons.BRIEFCASE_OUTLINE.token
                )
            )
            OceanInlineTextListItem(
                item = OceanInlineTextList(
                    label = "Label",
                    value = "Value",
                    color = "colorStatusNeutralDeep",
                    icon = "information-circle-outline"
                )
            )
        }
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Default(text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.WithTag(
                title = "Title",
                tagStyle = OceanTagStyle.Default(
                    label = "Tag Title",
                    layout = OceanTagLayout.Medium(icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE)
                )
            ),
            description = OceanInlineTextListItemDescription.Default(text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Custom(title = "Title") {
                Box(
                    modifier = Modifier
                        .size(OceanSpacing.xs, OceanSpacing.xs)
                        .background(color = OceanColors.interfaceDarkDeep)
                )
            },
            description = OceanInlineTextListItemDescription.Default(text = "Description"),
            size = OceanInlineTextListItemSize.SMALL
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Warning(icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Positive(icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Inactive(icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Highlight(icon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Strikethrough(oldText = "Old", currentText = "Current")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Action(title = "Action", onClick = {}, buttonStyle = OceanButtonStyle.PrimarySmall)
        )
        OceanInlineTextListItem(
            item = OceanInlineTextList(
                label = "Loading",
                value = "..."
            ),
            isLoading = true
        )
    }
}

@Composable
fun OceanInlineTextListItem(
    modifier: Modifier = Modifier,
    title: OceanInlineTextListItemTitle,
    description: OceanInlineTextListItemDescription,
    size: OceanInlineTextListItemSize = OceanInlineTextListItemSize.DEFAULT,
    isLoading: Boolean = false
) {
    if (isLoading) {
        OceanInlineTextListItemLoading(
            modifier = modifier,
            size = size
        )
        return
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanInlineTextListItemTitle(
                title = title,
                size = size
            )
            Spacer(modifier = Modifier.weight(1f))
            OceanInlineTextListItemDescription(
                description = description,
                size = size
            )
        }
    }
}

@Composable
fun OceanInlineTextListItem(
    modifier: Modifier = Modifier,
    item: OceanInlineTextList,
    size: OceanInlineTextListItemSize = OceanInlineTextListItemSize.DEFAULT,
    isLoading: Boolean = false
) {
    if (isLoading) {
        OceanInlineTextListItemLoading(
            modifier = modifier,
            size = size
        )
        return
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanText(
                    text = item.label ?: "",
                    style = OceanTextStyle.paragraph
                )

                if (!item.tooltip.isNullOrBlank()) {
                    OceanTooltip(item.tooltip) {
                        Icon(
                            painter = painterResource(id = R.drawable.ocean_icon_info_solid),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = OceanSpacing.xxxs)
                                .size(OceanSpacing.xs),
                            tint = OceanColors.interfaceLightDeep
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!item.icon.isNullOrBlank()) {
                    OceanIcon(
                        iconType = OceanIcons.fromToken(item.icon),
                        modifier = Modifier.size(OceanSpacing.xs),
                        tint = item.color?.let { OceanColors.fromString(color = item.color) }
                            ?: Color.Unspecified
                    )
                }

                val textColor = if (item.newValue.isNullOrBlank() || item.value.isNullOrBlank()) {
                    OceanColors.fromString(color = item.color ?: "")
                } else OceanColors.interfaceDarkDown

                val valueStyle = if (item.isBold == true) {
                    OceanTextStyle.paragraph.copy(
                        fontFamily = OceanFontFamily.BaseBold
                    )
                } else OceanTextStyle.paragraph

                val valueDecoration = if (item.isStrike == true) {
                    TextDecoration.LineThrough
                } else null

                OceanText(
                    text = item.value ?: "",
                    style = valueStyle,
                    color = textColor,
                    modifier = Modifier.padding(start = OceanSpacing.xxxs),
                    textDecoration = valueDecoration
                )

                if (!item.newValue.isNullOrBlank()) {
                    val color = item.color?.let {
                        OceanColors.fromString(color = it)
                    }
                    OceanText(
                        text = item.newValue,
                        color = color ?: Color.Unspecified,
                        style = OceanTextStyle.paragraph,
                        modifier = Modifier.padding(start = OceanSpacing.xxxs)
                    )
                }
            }
        }
    }
}

@Composable
private fun OceanInlineTextListItemTitle(
    title: OceanInlineTextListItemTitle,
    size: OceanInlineTextListItemSize
) {
    when (title) {
        is OceanInlineTextListItemTitle.Default -> {
            OceanText(
                text = title.title,
                style = OceanTextStyle.description,
                color = OceanColors.interfaceDarkDown
            )
        }
        is OceanInlineTextListItemTitle.WithTag -> {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OceanText(
                    text = title.title,
                    style = size.getTitleStyle()
                )
                OceanSpacing.StackXXS()
                OceanTag(
                    style = title.tagStyle
                )
            }
        }
        is OceanInlineTextListItemTitle.Custom -> {
            OceanText(
                text = title.title,
                style = size.getTitleStyle()
            )

            OceanSpacing.StackXXS()

            title.content()
        }
    }
}

@Composable
private fun OceanInlineTextListItemDescription(
    description: OceanInlineTextListItemDescription,
    size: OceanInlineTextListItemSize
) {
    when (description) {
        is OceanInlineTextListItemDescriptionGenericType -> {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                description.icon?.let {
                    OceanIcon(
                        iconType = it,
                        tint = description.getTintColor()
                    )
                    OceanSpacing.StackXXXS()
                }

                OceanText(
                    text = description.text,
                    style = size.getDescriptionStyle(),
                    color = description.getTintColor(),
                    fontWeight = description.fontWeight
                )
            }
        }

        is OceanInlineTextListItemDescription.Strikethrough -> {
            OceanText(
                text = description.oldText,
                style = size.getDescriptionStyle(),
                textDecoration = TextDecoration.LineThrough,
                color = OceanColors.interfaceDarkDeep,
                fontWeight = FontWeight.Medium
            )
            OceanSpacing.StackXXXS()
            OceanText(
                text = description.currentText,
                style = size.getDescriptionStyle(),
                color = description.getTintColor(),
                fontWeight = FontWeight.Medium
            )
        }

        is OceanInlineTextListItemDescription.Action -> {
            OceanButton(
                button = OceanButtonModel(
                    text = description.title,
                    onClick = description.onClick,
                    buttonStyle = description.buttonStyle
                )
            )
        }
    }
}

@Composable
private fun OceanInlineTextListItemLoading(
    modifier: Modifier,
    size: OceanInlineTextListItemSize
) {
    OceanShimmering { brush ->
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .padding(OceanSpacing.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .borderBackground(
                            brush = brush,
                            borderRadius = OceanBorderRadius.Tiny.allCorners
                        )
                        .fillMaxWidth(0.5f)
                        .height(size.getDescriptionStyle().fontSize.value.dp + OceanSpacing.xxs)
                        .weight(1f)
                )
                OceanSpacing.StackXXS()
                Spacer(
                    modifier = Modifier
                        .borderBackground(
                            brush = brush,
                            borderRadius = OceanBorderRadius.Tiny.allCorners
                        )
                        .fillMaxWidth(0.5f)
                        .height(size.getDescriptionStyle().fontSize.value.dp + OceanSpacing.xxs)
                        .weight(1f)
                )
            }
        }
    }
}
