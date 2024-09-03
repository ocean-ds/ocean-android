package br.com.useblu.oceands.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 620
)
@Composable
private fun OceanContentListPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
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
                description = "Description",
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
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
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
                ),
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
                ),
            )
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                caption = "Caption"
            ),
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.DEFAULT
            ),
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.OUTFLOW
            ),
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.INFLOW,
            ),
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Transaction(
                value = "R$ 1.000,00",
                type = TransactionType.CANCELED
            ),
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
                    .background(brush, RoundedCornerShape(4.dp))
                    .width(120.dp)
                    .height(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .background(brush, RoundedCornerShape(4.dp))
                    .fillMaxWidth()
                    .height(16.dp)
            )
            Spacer(
                modifier = Modifier
                    .background(brush, RoundedCornerShape(4.dp))
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
                OceanTextStyle.paragraph.copy(OceanColors.interfaceDarkPure),
                enabled
            )
        )

        if (style.description.isNotBlank()) {
            OceanText(
                text = style.description,
                style = configTextStyle(OceanTextStyle.description, enabled)
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
                OceanTextStyle.description,
                enabled
            )
        )

        OceanText(
            text = style.description,
            style = configTextStyle(
                OceanTextStyle.paragraph.copy(OceanColors.interfaceDarkPure),
                enabled
            )
        )

        OceanSpacing.StackXXS()

        if (style.caption.isNotBlank()) {
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
        val description: String = "",
        val caption: String = ""
    ) : ContentListStyle

    data class Inverted(
        val title: String,
        val description: String,
        val caption: String = ""
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
    DEFAULT;
}