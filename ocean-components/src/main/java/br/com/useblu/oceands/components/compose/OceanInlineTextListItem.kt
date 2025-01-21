package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
private fun OceanInlineTextListItemPreview() {
    Column(
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure),
    ) {
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.Default(title ="Title"),
            description = OceanInlineTextListItemDescription.Default(text = "Description")
        )
        OceanSpacing.StackXXS()
        OceanInlineTextListItem(
            title = OceanInlineTextListItemTitle.WithTag(title = "Title", tagIcon = OceanIcons.INFORMATION_CIRCLE_OUTLINE, tagText = "Tag"),
            description = OceanInlineTextListItemDescription.Default(text = "Description")
        )
    }
}

@Composable
fun OceanInlineTextListItem(
    modifier: Modifier = Modifier,
    title: OceanInlineTextListItemTitle,
    description: OceanInlineTextListItemDescription,
    size: OceanInlineTextListItemSize = OceanInlineTextListItemSize.DEFAULT
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(OceanSpacing.xs)
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
                style = OceanTextStyle.paragraph
            )
        }
        is OceanInlineTextListItemTitle.WithTag -> {
            Row {
                OceanText(
                    text = title.title,
                    style = OceanTextStyle.paragraph
                )

                Row {
                    title.tagIcon?.let {
                        OceanIcon(
                            iconType = it
                        )
                    }

                    OceanText(
                        text = title.tagText,
                        style = OceanTextStyle.paragraph
                    )
                }
            }
        }
        is OceanInlineTextListItemTitle.Custom -> {
            OceanText(
                text = title.title,
                style = OceanTextStyle.paragraph
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
            Row {
                description.icon?.let {
                    OceanIcon(
                        iconType = it
                    )
                }

                OceanText(
                    text = description.text,
                    style = OceanTextStyle.description
                )
            }
        }

        is OceanInlineTextListItemDescription.Strikethrough -> {
            OceanText(
                text = description.oldText,
                style = OceanTextStyle.descriptionStrike
            )

            OceanText(
                text = description.currentText,
                style = OceanTextStyle.description
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

enum class OceanInlineTextListItemSize {
    DEFAULT,
    SMALL;

    @Composable
    fun getSize(): TextUnit {
        when (this) {
            DEFAULT -> return OceanTextStyle.paragraph.fontSize
            SMALL -> return OceanTextStyle.description.fontSize
        }
    }
}

sealed interface OceanInlineTextListItemTitle {
    data class Default(val title: String): OceanInlineTextListItemTitle
    data class WithTag(val title: String, val tagIcon: OceanIcons? = null, val tagText: String): OceanInlineTextListItemTitle
    data class Custom(val title: String, val content: @Composable () -> Unit): OceanInlineTextListItemTitle
}

sealed interface OceanInlineTextListItemDescription {
    data class Default(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType

    data class Innactive(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType

    data class Positive(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType

    data class Warning(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType

    data class Highlight(
        override val icon: OceanIcons? = null,
        override val text: String
    ): OceanInlineTextListItemDescriptionGenericType

    data class Strikethrough(
        val oldText: String,
        val currentText: String
    ): OceanInlineTextListItemDescription

    data class Action(
        val title: String,
        val onClick: () -> Unit,
        val buttonStyle: OceanButtonStyle
    ): OceanInlineTextListItemDescription
}

internal interface OceanInlineTextListItemDescriptionGenericType: OceanInlineTextListItemDescription {
    val icon: OceanIcons?
    val text: String
}