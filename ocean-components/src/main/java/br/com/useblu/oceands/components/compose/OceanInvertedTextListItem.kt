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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Deprecated(
    "Deprecated! Use OceanInvertedTextListItem with InvertedListItemStyle instead"
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
        val tagLabel: String = "",
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

                if (tagLabel.isNotBlank()) {
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
                        .borderBackground(
                            brush = brush,
                            borderRadius = OceanBorderRadius.Tiny.allCorners
                        )
                )
                OceanSpacing.StackXXS()
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxSize()
                        .borderBackground(
                            brush = brush,
                            borderRadius = OceanBorderRadius.Tiny.allCorners
                        )
                )
            }
        }
    }
}

@Preview(heightDp = 1024)
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
            style = InvertedListItemStyle.ContentInfo(
                title = "Title",
                description = "Description",
                caption = "Caption",
                descriptionStyle = DescriptionStyle.Default(
                    icon = OceanIcons.PLACEHOLDER_SOLID,
                    color = OceanColors.statusNegativePure
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
            style = InvertedListItemStyle.Highlight(
                title = "Title",
                description = "Description",
                caption = "Caption",
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
                description = "Description"
            )
        )
        OceanInvertedTextListItem(
            modifier = Modifier.background(color = OceanColors.interfaceLightPure),
            style = InvertedListItemStyle.HighlightLead(
                title = "Title",
                description = "Description",
                caption = "Caption"
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
            style = InvertedListItemStyle.Strikethrough(
                title = "Title",
                strokeText = "Stroke",
                actualText = "Actual",
                caption = "Caption"
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
                style = ContentListStyle.Default(
                    title = style.title,
                    description = style.description,
                    caption = style.caption
                ),
                descriptionStyle = style.descriptionStyle
            )
        }

        is InvertedListItemStyle.Strikethrough -> {
            StrikethroughInvertedTextList(
                modifier = modifier,
                content = ContentListStyle.Strikethrough(
                    title = style.title,
                    description = style.strokeText,
                    newValue = style.actualText,
                    caption = style.caption
                )
            )
        }

        is InvertedListItemStyle.Highlight -> {
            HighlightInvertedTextList(
                modifier = modifier,
                style = ContentListStyle.Default(
                    title = style.title,
                    description = style.description,
                    caption = style.caption
                ),
                tagStyle = style.tagStyle
            )
        }

        is InvertedListItemStyle.HighlightLead -> {
            HighlightLeadInvertedTextList(
                modifier = modifier,
                style = ContentListStyle.Default(
                    title = style.title,
                    description = style.description,
                    caption = style.caption
                )
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
    content: ContentListStyle.Strikethrough
) {
    Column(
        modifier = modifier
            .padding(horizontal = OceanSpacing.xs, vertical = OceanSpacing.xxsExtra)
            .fillMaxWidth()
    ) {
        OceanContentList(
            style = content
        )
    }
}

@Composable
private fun ContentInfoInvertedTextList(
    modifier: Modifier = Modifier,
    style: ContentListStyle.Default,
    descriptionStyle: DescriptionStyle.Default
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
            if (descriptionStyle.icon != OceanIcons.UNDEFINED) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = descriptionStyle.icon.icon),
                    tint = descriptionStyle.color,
                    contentDescription = null
                )
            }

            OceanText(
                text = style.description,
                style = OceanTextStyle.paragraph.copy(
                    color = descriptionStyle.color,
                    fontFamily = OceanFontFamily.BaseRegular
                ),
                modifier = Modifier.weight(1f)
            )
        }

        if (style.caption.isNotBlank()) {
            OceanText(
                modifier = Modifier.padding(top = OceanSpacing.xxs),
                text = style.caption,
                style = OceanTextStyle.captionBold,
                color = OceanColors.interfaceDarkDown
            )
        }
    }
}

@Composable
private fun HighlightInvertedTextList(
    modifier: Modifier = Modifier,
    style: ContentListStyle.Default,
    tagStyle: OceanTagStyle
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
                style = OceanTextStyle.heading4
            )

            if (style.caption.isNotBlank()) {
                OceanText(
                    modifier = Modifier.padding(top = OceanSpacing.xxs),
                    text = style.caption,
                    style = OceanTextStyle.captionBold,
                    color = OceanColors.interfaceDarkDown
                )
            }
        }
        if (tagStyle != OceanTagStyle.None) {
            OceanTag(
                style = tagStyle
            )
        }
    }
}

@Composable
private fun HighlightLeadInvertedTextList(
    modifier: Modifier = Modifier,
    style: ContentListStyle.Default
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
            style = OceanTextStyle.lead
        )

        if (style.caption.isNotBlank()) {
            OceanText(
                modifier = Modifier.padding(top = OceanSpacing.xxs),
                text = style.caption,
                style = OceanTextStyle.captionBold,
                color = OceanColors.interfaceDarkDown
            )
        }
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
        val caption: String = "",
        val descriptionStyle: DescriptionStyle.Default
    ) : InvertedListItemStyle

    data class Strikethrough(
        val title: String,
        val strokeText: String,
        val actualText: String,
        val caption: String = ""
    ) : InvertedListItemStyle

    data class Highlight(
        val title: String,
        val description: String,
        val caption: String = "",
        val tagStyle: OceanTagStyle = OceanTagStyle.None
    ) : InvertedListItemStyle

    data class HighlightLead(
        val title: String,
        val description: String,
        val caption: String = ""
    ) : InvertedListItemStyle
}

sealed interface DescriptionStyle {
    data class Default(
        val icon: OceanIcons = OceanIcons.UNDEFINED,
        val color: Color = Color.Unspecified
    ) : DescriptionStyle
}
