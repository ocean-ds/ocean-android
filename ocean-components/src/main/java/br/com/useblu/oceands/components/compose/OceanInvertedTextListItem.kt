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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

        OceanInvertedTextListItemSkeleton(2)
    }
}

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
                        label = tagLabel,
                        type = tagType,
                        icon = tagIcon,
                        isSmall = true
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