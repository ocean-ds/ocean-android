package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 800
)
@Composable
private fun OceanContentListPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Default(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Default(
                title = "Title",
                description = "Description",
                caption = "Caption"
            ),
            enabled = false
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Default(
                title = "Title",
                description = "Description"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Default(
                title = "Title",
                caption = "Caption"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Default(
                title = "Title"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Inverted(
                title = "Title",
                description = "Description",
                caption = "Caption"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Inverted(
                title = "Title",
                description = "Description"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Strikethrough(
                title = "Title",
                description = "Description",
                newValue = "New Value",
                caption = "Caption"
            ),
            isLoading = false
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Inverted(
                title = "Title",
                description = "Description Unchanged",
                caption = "Caption",
                unchanged = true
            ),
            isLoading = false,
            enabled = true
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Inverted(
                title = "Title",
                description = "Description"
            ),
            isLoading = true
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 560
)
@Composable
private fun OceanContentListTransactionPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Small()
                ),
                caption = "Caption"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Small()
                ),
                caption = "Caption"
            ),
            enabled = false
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Small()
                )
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                tagStyle = OceanTagStyle.Default(
                    label = "Label",
                    layout = OceanTagLayout.Medium(),
                    type = OceanTagType.Positive
                )
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                caption = "Caption"
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.DEFAULT
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.OUTFLOW
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.INFLOW
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.CANCELED
            )
        )
    }
}

@Composable
fun OceanContentList(
    modifier: Modifier = Modifier,
    style: ContentListStyle,
    isLoading: Boolean = false,
    enabled: Boolean = true
) {
    if (isLoading) {
        OceanContentListSkeleton()
        return
    }

    when (style) {
        is ContentListStyle.Default -> DefaultContentList(
            modifier = modifier,
            style = style,
            enabled = enabled
        )

        is ContentListStyle.Inverted -> InvertedContentList(
            modifier = modifier,
            style = style,
            enabled = enabled
        )

        is ContentListStyle.Transaction -> TransactionContentList(
            modifier = modifier,
            style = style,
            enabled = enabled
        )

        is ContentListStyle.Strikethrough -> StrikethroughContentList(
            modifier = modifier,
            style = style,
            enabled = enabled
        )
    }
}

@Composable
private fun OceanContentListSkeleton() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        OceanShimmering { brush ->
            Spacer(
                modifier = Modifier
                    .borderBackground(
                        brush = brush,
                        borderRadius = OceanBorderRadius.Tiny.allCorners
                    )
                    .width(120.dp)
                    .height(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .borderBackground(
                        brush = brush,
                        borderRadius = OceanBorderRadius.Tiny.allCorners
                    )
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .borderBackground(
                        brush = brush,
                        borderRadius = OceanBorderRadius.Tiny.allCorners
                    )
                    .fillMaxWidth()
                    .height(16.dp)
            )
        }
    }
}

@Composable
private fun DefaultContentList(
    modifier: Modifier,
    style: ContentListStyle.Default,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = configTextStyle(
                style.titleStyle ?: OceanTextStyle.paragraph.copy(OceanColors.interfaceDarkPure),
                enabled
            )
        )

        if (style.description.isNotBlank()) {
            OceanText(
                text = style.description,
                style = configTextStyle(
                    style.descriptionStyle ?: OceanTextStyle.description,
                    enabled
                )
            )
        }

        if (style.caption.isNotBlank()) {
            OceanSpacing.StackXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(OceanTextStyle.captionBold, enabled)
            )
        }
    }
}

@Composable
private fun InvertedContentList(
    modifier: Modifier,
    style: ContentListStyle.Inverted,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = configTextStyle(
                style.titleStyle ?: OceanTextStyle.description,
                enabled
            )
        )

        OceanText(
            text = style.description,
            style = configTextStyle(
                originalStyle = style.descriptionStyle
                    ?: OceanTextStyle.paragraph.copy(OceanColors.interfaceDarkPure),
                isEnabled = enabled && !style.unchanged
            )
        )

        if (style.caption.isNotBlank()) {
            OceanSpacing.StackXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(OceanTextStyle.captionBold, enabled)
            )
        }
    }
}

@Composable
private fun StrikethroughContentList(
    modifier: Modifier,
    style: ContentListStyle.Strikethrough,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = configTextStyle(
                style.titleStyle ?: OceanTextStyle.description,
                enabled
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OceanText(
                text = style.description,
                style = style.descriptionStyle ?: OceanTextStyle.paragraph,
                textDecoration = if (style.newValue.isNotBlank()) TextDecoration.LineThrough else null,
                color = OceanColors.interfaceDarkDown
            )

            if (style.newValue.isNotBlank()) {
                OceanText(
                    modifier = Modifier.padding(start = OceanSpacing.xxxs),
                    text = style.newValue,
                    style = style.descriptionStyle ?: OceanTextStyle.paragraph,
                    color = OceanColors.statusPositiveDeep
                )
            }
        }

        if (style.caption.isNotBlank()) {
            OceanSpacing.StackXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(OceanTextStyle.captionBold, enabled)
            )
        }
    }
}

@Composable
private fun TransactionContentList(
    modifier: Modifier,
    style: ContentListStyle.Transaction,
    enabled: Boolean = true
) {
    val (color, value) = when (style.type) {
        TransactionType.DEFAULT -> OceanColors.interfaceDarkPure to style.value
        TransactionType.OUTFLOW -> OceanColors.interfaceDarkPure to "- ${style.value}"
        TransactionType.INFLOW -> OceanColors.statusPositiveDeep to "+ ${style.value}"
        TransactionType.CANCELED -> OceanColors.interfaceDarkUp to style.value
    }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = value,
            style = strikeThrough(
                configTextStyle(OceanTextStyle.paragraph.copy(color = color), enabled),
                style.type
            )
        )

        style.tagStyle?.let {
            OceanTag(
                style = style.tagStyle,
                enabled = enabled
            )
        }

        if (style.caption.isNotBlank()) {
            OceanText(
                text = style.caption,
                style = configTextStyle(
                    OceanTextStyle.captionBold,
                    enabled
                )
            )
        }
    }
}

@Composable
private fun configTextStyle(
    originalStyle: TextStyle,
    isEnabled: Boolean
): TextStyle = if (isEnabled) {
    originalStyle
} else {
    originalStyle.copy(color = OceanColors.interfaceDarkUp)
}

@Composable
fun strikeThrough(
    originalStyle: TextStyle,
    type: TransactionType
): TextStyle = if (type != TransactionType.CANCELED) {
    originalStyle
} else {
    originalStyle.copy(textDecoration = TextDecoration.LineThrough)
}

sealed interface ContentListStyle {
    data class Default(
        val title: String,
        val titleStyle: TextStyle? = null,
        val description: String = "",
        val descriptionStyle: TextStyle? = null,
        val caption: String = ""
    ) : ContentListStyle

    data class Inverted(
        val title: String,
        val titleStyle: TextStyle? = null,
        val description: String,
        val descriptionStyle: TextStyle? = null,
        val caption: String = "",
        val unchanged: Boolean = false
    ) : ContentListStyle

    data class Strikethrough(
        val title: String,
        val titleStyle: TextStyle? = null,
        val description: String,
        val descriptionStyle: TextStyle? = null,
        val caption: String = "",
        val newValue: String = ""
    ) : ContentListStyle

    data class Transaction(
        val value: String,
        val tagStyle: OceanTagStyle? = null,
        val caption: String = "",
        val type: TransactionType = TransactionType.DEFAULT
    ) : ContentListStyle
}

enum class TransactionType {
    INFLOW,
    OUTFLOW,
    CANCELED,
    DEFAULT
}
