package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemDescription
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemDescriptionGenericType
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemSize
import br.com.useblu.oceands.model.compose.inlinetextlistitem.OceanInlineTextListItemTitle
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanInlineTextListItemPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure),
    ) {
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title ="Title"),
            description = OceanInlineTextListItemDescription.Default(text = "Description")
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title ="Title"),
            description = OceanInlineTextListItemDescription.Default(text = "Description"),
            size = OceanInlineTextListItemSize.SMALL
        )
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.WithTag(title = "Title", tagIcon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, tagText = "Tag Title"),
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
            title = OceanInlineTextListItemTitle.Default(title = "Title"),
            description = OceanInlineTextListItemDescription.Action(title = "Action", onClick = {}, buttonStyle = OceanButtonStyle.PrimarySmall),
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
                Row(
                    modifier = Modifier
                        .background(
                            color = OceanColors.statusWarningUp,
                            shape = RoundedCornerShape(percent = 50)
                        )
                        .padding(horizontal = OceanSpacing.xxs),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    title.tagIcon?.let {
                        OceanIcon(
                            iconType = it,
                            tint = OceanColors.statusWarningDeep
                        )
                    }
                    OceanSpacing.StackXXXS()
                    OceanText(
                        text = title.tagText,
                        style = size.getTitleStyle(),
                        color = OceanColors.statusWarningDeep
                    )
                }
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
                        .background(brush, RoundedCornerShape(4.dp))
                        .fillMaxWidth(0.5f)
                        .height(size.getDescriptionStyle().fontSize.value.dp + OceanSpacing.xxs)
                        .weight(1f)
                )
                OceanSpacing.StackXXS()
                Spacer(
                    modifier = Modifier
                        .background(brush, RoundedCornerShape(4.dp))
                        .fillMaxWidth(0.5f)
                        .height(size.getDescriptionStyle().fontSize.value.dp + OceanSpacing.xxs)
                        .weight(1f)
                )
            }
        }

    }
}