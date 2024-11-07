package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanInvertedTextListItemPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightDown)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Default("Description")
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Inactive("Description")
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Positive("Description", icon = OceanIcons.EYE_OFF_OUTLINE)
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Warning("Description")
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Default(
                description = "Description",
                colorDescription = OceanColors.brandPrimaryDown,
                icon = OceanIcons.DOCUMENT_TEXT_SOLID,
                colorIcon = OceanColors.brandPrimaryDown
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Highlight(
                description = "Description",
                fontSize = OceanFontSize.xs
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.Highlight("Description", tagLabel = "Label")
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            type = InvertedTextListType.StrikeThrough("Text", "Current Text")
        )
        OceanInvertedTextListItem(
            modifier = Modifier,
            title = "Title",
            backgroundColor = OceanColors.interfaceDarkUp,
            type = InvertedTextListType.Highlight("Description", tagLabel = "Label")
        )

        OceanInvertedTextListItemSkeleton(1)
    }
}

@Deprecated(
    "Deprecated! Use OceanInvertedTextListItem with InvertedListItemStyle instead",
)
@Composable
fun OceanInvertedTextListItem(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color = OceanColors.interfaceLightPure,
    type: InvertedTextListType
) {
    Column(
        modifier = modifier
            .background(backgroundColor)
            .padding(
                vertical = OceanSpacing.xxs,
                horizontal = OceanSpacing.xs
            )
            .fillMaxWidth()
    ) {
        OceanText(
            text = title,
            style = OceanTextStyle.description
        )

        type.TypeContent()
    }
}

sealed interface InvertedTextListType {
    @Composable
    fun TypeContent()
    data class Default(
        val description: String,
        val colorDescription: Color? = null,
        val icon: OceanIcons? = null,
        val colorIcon: Color? = null
    ) : InvertedTextListType {
        @Composable
        override fun TypeContent() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    OceanIcon(
                        iconType = icon,
                        tint = colorIcon ?: OceanColors.statusWarningDeep,
                        modifier = Modifier.size(20.dp)
                    )
                    OceanSpacing.StackXXXS()
                }

                OceanText(
                    text = description,
                    style = OceanTextStyle.paragraph,
                    color = colorDescription ?: OceanColors.interfaceDarkPure
                )
            }
        }
    }

    data class Inactive(val description: String) : InvertedTextListType {
        @Composable
        override fun TypeContent() {
            OceanText(
                text = description,
                style = OceanTextStyle.paragraph,
                color = OceanColors.interfaceDarkUp
            )
        }
    }

    data class Positive(val description: String, val icon: OceanIcons? = null) :
        InvertedTextListType {
        @Composable
        override fun TypeContent() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    OceanIcon(
                        iconType = icon,
                        tint = OceanColors.statusPositiveDeep,
                        modifier = Modifier.size(20.dp)
                    )
                    OceanSpacing.StackXXXS()
                }

                OceanText(
                    text = description,
                    style = OceanTextStyle.paragraph,
                    color = OceanColors.statusPositiveDeep
                )
            }
        }
    }

    data class Warning(val description: String, val icon: OceanIcons? = null) :
        InvertedTextListType {
        @Composable
        override fun TypeContent() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != null) {
                    OceanIcon(
                        iconType = icon,
                        tint = OceanColors.statusWarningDeep,
                        modifier = Modifier.size(20.dp)
                    )
                    OceanSpacing.StackXXXS()
                }

                OceanText(
                    text = description,
                    style = OceanTextStyle.paragraph,
                    color = OceanColors.statusWarningDeep
                )
            }
        }
    }

    data class Highlight(
        val description: String,
        val tagLabel: String? = null,
        val tagIcon: OceanIcons? = null,
        val tagType: OceanTagType = OceanTagType.Positive,
        val fontSize: TextUnit = TextUnit.Unspecified
    ) : InvertedTextListType {
        @Composable
        override fun TypeContent() {
            Column {
                OceanText(
                    text = description,
                    style = OceanTextStyle.lead,
                    color = OceanColors.interfaceDarkPure,
                    fontSize = fontSize
                )

                if (tagLabel != null) {
                    OceanSpacing.StackXXS()
                    OceanTag(
                        style = OceanTagStyle.Default(
                            label = tagLabel,
                            type = tagType,
                            layout = OceanTagLayout.Small()
                        )
                    )
                }
            }
        }
    }

    data class StrikeThrough(
        val strikedDescription: String,
        val description: String
    ) : InvertedTextListType {
        @Composable
        override fun TypeContent() {
            Row {
                OceanText(
                    text = strikedDescription,
                    style = OceanTextStyle.description,
                    color = OceanColors.interfaceDarkPure,
                    textDecoration = TextDecoration.LineThrough
                )

                OceanSpacing.StackXXXS()

                OceanText(
                    text = description,
                    style = OceanTextStyle.description,
                    color = OceanColors.statusPositiveDeep
                )
            }
        }
    }
}

@Composable
fun OceanInvertedTextListItemSkeleton(items: Int) {
    OceanShimmering { brush ->
        repeat(items) {
            Column(
                modifier = Modifier
                    .background(OceanColors.interfaceLightPure)
                    .padding(
                        vertical = OceanSpacing.xxs,
                        horizontal = OceanSpacing.xs
                    )
                    .fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .width(96.dp)
                        .height(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
                OceanSpacing.StackXXS()
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
            }
        }
    }
}

@Preview
@Composable
private fun InvertedTextListPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightDown)
            .padding(OceanSpacing.xxs),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.Default(
                content = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = "Caption"
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning
                )
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.Default(
                content = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    unchanged = true
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning
                )
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.ContentInfo(
                title = "Title",
                description = "Description",
                descriptionStyle = DescriptionStyle.Default(
                    icon = OceanIcons.PLACEHOLDER_SOLID,
                    color = OceanColors.statusWarningDeep
                )
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.ContentInfo(
                title = "Title",
                description = "Description",
                descriptionStyle = DescriptionStyle.Default(
                    icon = OceanIcons.PLACEHOLDER_SOLID,
                    color = OceanColors.statusPositiveDeep
                )
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.Highlight(
                title = "Title",
                description = "Description",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning
                )
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.HighlightLead(
                title = "Title",
                description = "Description",
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.Strikethrough(
                title = "Title",
                strokeText = "Stroke",
                actualText = "Actual"
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            isLoading = true,
            style = InvertedListItemStyle.Default(
                content = ContentListStyle.Inverted(
                    title = "Title",
                    description = "Description",
                    caption = ""
                ),
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Warning
                )
            )
        )
    }
}


@Composable
fun OceanInvertedTextListItem(
    modifier: Modifier = Modifier,
    style: InvertedListItemStyle,
    isLoading: Boolean = false
) {
    if (isLoading) {
        OceanInvertedTextListItemSkeleton(1)
        return
    }

    when (style) {
        is InvertedListItemStyle.Default -> {
            DefaultInvertedTextList(
                modifier = modifier,
                content = style.content,
                tagStyle = style.tagStyle
            )
        }

        is InvertedListItemStyle.ContentInfo -> {
            ContentInfoInvertedTextList(
                modifier = modifier,
                style = style
            )
        }

        is InvertedListItemStyle.Strikethrough -> {
            StrikethroughInvertedTextList(
                modifier = modifier,
                content = ContentListStyle.Strikethrough(
                    title = style.title,
                    description = style.strokeText,
                    newValue = style.actualText
                )
            )
        }

        is InvertedListItemStyle.Highlight -> {
            HighlightInvertedTextList(
                modifier = modifier,
                style = style
            )
        }

        is InvertedListItemStyle.HighlightLead -> {
            HighlightLeadInvertedTextList(
                modifier = modifier,
                style = style
            )
        }
    }
}

@Composable
private fun DefaultInvertedTextList(
    modifier: Modifier = Modifier,
    content: ContentListStyle.Inverted,
    tagStyle: OceanTagStyle
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
            .fillMaxWidth()
    ) {
        OceanContentList(
            style = content,
            modifier = Modifier.weight(1f)
        )

        OceanTag(
            style = tagStyle
        )
    }
}

@Composable
private fun StrikethroughInvertedTextList(
    modifier: Modifier = Modifier,
    content: ContentListStyle.Strikethrough,
) {
    Column(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
            .fillMaxWidth()
    ) {
        OceanContentList(
            style = content,
        )
    }
}

@Composable
private fun ContentInfoInvertedTextList(
    modifier: Modifier = Modifier,
    style: InvertedListItemStyle.ContentInfo
) {
    Column(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
            .fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (style.descriptionStyle.icon != OceanIcons.UNDEFINED) {
                Icon(
                    painter = painterResource(id = style.descriptionStyle.icon.icon),
                    tint = style.descriptionStyle.color,
                    contentDescription = null
                )
            }
            OceanText(
                text = style.description,
                style = OceanTextStyle.paragraph.copy(
                    color = style.descriptionStyle.color
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun HighlightInvertedTextList(
    modifier: Modifier = Modifier,
    style: InvertedListItemStyle.Highlight
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            OceanText(
                text = style.title,
                style = OceanTextStyle.description,
                color = OceanColors.interfaceDarkDown
            )
            OceanText(
                text = style.description,
                style = OceanTextStyle.heading4,
            )
        }
        if (style.tagStyle != OceanTagStyle.None) {
            OceanTag(
                style = style.tagStyle
            )
        }
    }
}

@Composable
private fun HighlightLeadInvertedTextList(
    modifier: Modifier = Modifier,
    style: InvertedListItemStyle.HighlightLead
) {
    Column(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
            .fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = OceanTextStyle.description,
            color = OceanColors.interfaceDarkDown
        )
        OceanText(
            text = style.description,
            style = OceanTextStyle.lead,
        )
    }
}

sealed interface InvertedListItemStyle {
    data class Default(
        val content: ContentListStyle.Inverted,
        val tagStyle: OceanTagStyle = OceanTagStyle.None
    ) : InvertedListItemStyle

    data class ContentInfo(
        val title: String,
        val description: String,
        val descriptionStyle: DescriptionStyle.Default
    ) : InvertedListItemStyle

    data class Strikethrough(
        val title: String,
        val strokeText: String,
        val actualText: String
    ) : InvertedListItemStyle

    data class Highlight(
        val title: String,
        val description: String,
        val tagStyle: OceanTagStyle = OceanTagStyle.None
    ) : InvertedListItemStyle

    data class HighlightLead(
        val title: String,
        val description: String,
    ) : InvertedListItemStyle
}

sealed interface DescriptionStyle {
    data class Default(
        val icon: OceanIcons = OceanIcons.UNDEFINED,
        val color: Color = Color.Unspecified
    ) : DescriptionStyle
}