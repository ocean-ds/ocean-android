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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.model.compose.OceanTagModel
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
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
                description = "Description Unchanged",
                descriptionStyle = OceanTextStyle.lead,
                caption = "Caption"
            ),
            isLoading = false,
            enabled = true
        )

        OceanSpacing.StackXXS()
        OceanContentList(
            style = ContentListStyle.Inverted(
                title = "Title",
                description = "Description Unchanged",
                caption = "Caption",
                captionStyle = OceanTextStyle.captionBold.copy(color = OceanColors.brandPrimaryPure),
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

        is ContentListStyle.Amount -> AmountContentList(
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
    if (!style.usesLegacyLayout()) {
        TokenContentList(
            modifier = modifier,
            title = style.title,
            titleStyle = style.titleStyle,
            description = style.description,
            descriptionStyle = style.descriptionStyle,
            caption = style.caption,
            captionStyle = style.captionStyle,
            inverted = false,
            size = style.size ?: ContentListSize.Md,
            type = if (enabled) style.type ?: ContentListType.Default else ContentListType.Inactive
        )
        return
    }

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
            OceanSpacing.StackXXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(
                    style.captionStyle ?: OceanTextStyle.captionBold,
                    enabled
                )
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
    if (!style.usesLegacyLayout()) {
        TokenContentList(
            modifier = modifier,
            title = style.title,
            titleStyle = style.titleStyle,
            description = style.description,
            descriptionStyle = style.descriptionStyle,
            caption = style.caption,
            captionStyle = style.captionStyle,
            inverted = true,
            size = style.size ?: ContentListSize.Md,
            type = if (enabled) style.type ?: ContentListType.Default else ContentListType.Inactive,
            descriptionUnchanged = style.unchanged
        )
        return
    }

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
            OceanSpacing.StackXXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(
                    style.captionStyle ?: OceanTextStyle.captionBold,
                    enabled
                )
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
    style.size?.let { size ->
        TokenStrikethroughContentList(
            modifier = modifier,
            style = style,
            size = size,
            enabled = enabled
        )
        return
    }

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
            OceanSpacing.StackXXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(
                    style.captionStyle ?: OceanTextStyle.captionBold,
                    enabled
                )
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
                    style.captionStyle ?: OceanTextStyle.captionBold,
                    enabled
                )
            )
        }
    }
}

/**
 * Renders the Figma `_Content List / Default` tokens (MR-615) for every `size`/`type`
 * combination that is not the legacy Md + Default layout.
 */
@Composable
private fun TokenContentList(
    modifier: Modifier,
    title: String,
    titleStyle: TextStyle?,
    description: String,
    descriptionStyle: TextStyle?,
    caption: String,
    captionStyle: TextStyle?,
    inverted: Boolean,
    size: ContentListSize,
    type: ContentListType,
    descriptionUnchanged: Boolean = false
) {
    val inactive = type == ContentListType.Inactive
    val emphasis = contentEmphasisStyle(size = size, type = type)
    val support = contentSupportStyle(size = size, inverted = inverted, inactive = inactive)

    val resolvedTitleStyle = titleStyle?.let { configTextStyle(it, !inactive) }
        ?: if (inverted) support else emphasis
    val resolvedDescriptionStyle = descriptionStyle?.let { configTextStyle(it, !inactive) }
        ?: if (inverted) emphasis else support

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = title,
            style = resolvedTitleStyle
        )

        if (description.isNotBlank()) {
            OceanText(
                text = description,
                style = configTextStyle(
                    originalStyle = resolvedDescriptionStyle,
                    isEnabled = !descriptionUnchanged
                )
            )
        }

        if (caption.isNotBlank()) {
            OceanSpacing.StackXXXS()
            OceanText(
                text = caption,
                style = configTextStyle(
                    captionStyle ?: OceanTextStyle.captionBold,
                    !inactive
                )
            )
        }
    }
}

@Composable
private fun TokenStrikethroughContentList(
    modifier: Modifier,
    style: ContentListStyle.Strikethrough,
    size: ContentListSize,
    enabled: Boolean
) {
    val isMd = size == ContentListSize.Md
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OceanText(
            text = style.title,
            style = configTextStyle(
                style.titleStyle ?: if (isMd) OceanTextStyle.description else OceanTextStyle.captionBold,
                enabled
            )
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
        ) {
            val baseStyle = style.descriptionStyle
                ?: if (isMd) OceanTextStyle.paragraph else OceanTextStyle.description
            OceanText(
                text = style.description,
                style = baseStyle.copy(color = OceanColors.interfaceDarkUp),
                textDecoration = if (style.newValue.isNotBlank()) TextDecoration.LineThrough else null
            )

            if (style.newValue.isNotBlank()) {
                OceanText(
                    text = style.newValue,
                    style = configTextStyle(
                        baseStyle.copy(color = OceanColors.statusPositiveDeep),
                        enabled
                    )
                )
            }
        }

        if (style.caption.isNotBlank()) {
            OceanSpacing.StackXXXS()
            OceanText(
                text = style.caption,
                style = configTextStyle(
                    style.captionStyle ?: OceanTextStyle.captionBold,
                    enabled
                )
            )
        }
    }
}

/**
 * Figma `_Content List / Amount` (MR-615): value, optional strikethrough value, Tag and
 * additional data, aligned to the end. The Tag layout follows [ContentListStyle.Amount.size].
 */
@Composable
private fun AmountContentList(
    modifier: Modifier,
    style: ContentListStyle.Amount,
    enabled: Boolean
) {
    val inactive = !enabled || style.type == AmountType.Inactive
    val isMd = style.size == ContentListSize.Md
    val baseStyle = if (isMd) OceanTextStyle.paragraph else OceanTextStyle.description
    val amountColor = when {
        inactive -> OceanColors.interfaceDarkUp
        style.type == AmountType.Positive || style.type == AmountType.Strikethrough ->
            OceanColors.statusPositiveDeep

        else -> OceanColors.interfaceDarkDeep
    }
    val amountText = if (style.type == AmountType.Negative) "- ${style.amount}" else style.amount
    val showStrikethrough = style.strikethroughAmount.isNotBlank() &&
        (style.type == AmountType.Strikethrough || style.type == AmountType.StrikethroughNeutral)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs)
            ) {
                if (showStrikethrough) {
                    OceanText(
                        modifier = Modifier.alignByBaseline(),
                        text = style.strikethroughAmount,
                        style = baseStyle.copy(color = OceanColors.interfaceDarkUp),
                        textDecoration = TextDecoration.LineThrough,
                        maxLines = 1
                    )
                }

                OceanText(
                    modifier = Modifier.alignByBaseline(),
                    text = amountText,
                    style = baseStyle.copy(
                        color = amountColor,
                        fontFamily = OceanFontFamily.BaseMedium
                    ),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
            }

            style.tag?.let {
                OceanTag(
                    style = OceanTagStyle.Default(
                        label = it.text,
                        layout = if (isMd) OceanTagLayout.Medium() else OceanTagLayout.Small(),
                        type = it.type
                    ),
                    enabled = !inactive
                )
            }
        }

        if (style.additionalData.isNotBlank()) {
            OceanText(
                text = style.additionalData,
                style = configTextStyle(OceanTextStyle.captionBold, !inactive),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun contentEmphasisStyle(
    size: ContentListSize,
    type: ContentListType
): TextStyle {
    val isMd = size == ContentListSize.Md
    val base = when {
        type == ContentListType.HighlightLead && isMd -> OceanTextStyle.lead
        type == ContentListType.HighlightLead -> OceanTextStyle.paragraph
        isMd -> OceanTextStyle.paragraph
        else -> OceanTextStyle.description
    }
    val color = when (type) {
        ContentListType.Inactive -> OceanColors.interfaceDarkUp
        ContentListType.Positive -> OceanColors.statusPositiveDeep
        ContentListType.Warning -> OceanColors.statusWarningDeep
        else -> OceanColors.interfaceDarkDeep
    }
    return if (type == ContentListType.Highlight) {
        base.copy(color = color, fontFamily = OceanFontFamily.BaseBold)
    } else {
        base.copy(color = color)
    }
}

@Composable
private fun contentSupportStyle(
    size: ContentListSize,
    inverted: Boolean,
    inactive: Boolean
): TextStyle {
    val base = when {
        size == ContentListSize.Md -> OceanTextStyle.description
        inverted -> OceanTextStyle.captionBold
        else -> OceanTextStyle.caption
    }
    return base.copy(
        color = if (inactive) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkDown
    )
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
        val caption: String = "",
        val captionStyle: TextStyle? = null,
        /** `null` keeps the legacy layout; any value renders the Figma tokens (MR-615). */
        val size: ContentListSize? = null,
        /** `null` keeps the legacy layout; any value renders the Figma tokens (MR-615). */
        val type: ContentListType? = null
    ) : ContentListStyle {
        internal fun usesLegacyLayout() = size == null && type == null
    }

    data class Inverted(
        val title: String,
        val titleStyle: TextStyle? = null,
        val description: String,
        val descriptionStyle: TextStyle? = null,
        val caption: String = "",
        val captionStyle: TextStyle? = null,
        val unchanged: Boolean = false,
        /** `null` keeps the legacy layout; any value renders the Figma tokens (MR-615). */
        val size: ContentListSize? = null,
        /** `null` keeps the legacy layout; any value renders the Figma tokens (MR-615). */
        val type: ContentListType? = null
    ) : ContentListStyle {
        internal fun usesLegacyLayout() = size == null && type == null
    }

    data class Strikethrough(
        val title: String,
        val titleStyle: TextStyle? = null,
        val description: String,
        val descriptionStyle: TextStyle? = null,
        val caption: String = "",
        val captionStyle: TextStyle? = null,
        val newValue: String = "",
        /** `null` keeps the legacy layout; any value renders the Figma tokens (MR-615). */
        val size: ContentListSize? = null
    ) : ContentListStyle

    data class Transaction(
        val value: String,
        val tagStyle: OceanTagStyle? = null,
        val caption: String = "",
        val captionStyle: TextStyle? = null,
        val type: TransactionType = TransactionType.DEFAULT
    ) : ContentListStyle

    /**
     * Figma `_Content List / Amount` (MR-615). [strikethroughAmount] is shown struck through
     * before [amount] when [type] is [AmountType.Strikethrough] or [AmountType.StrikethroughNeutral].
     * The Tag is Medium for [ContentListSize.Md] and Small for [ContentListSize.Sm].
     */
    data class Amount(
        val amount: String,
        val type: AmountType = AmountType.Default,
        val size: ContentListSize = ContentListSize.Md,
        val strikethroughAmount: String = "",
        val tag: OceanTagModel? = null,
        val additionalData: String = ""
    ) : ContentListStyle
}

/**
 * Opts a content block into the Figma tokens (MR-615) when it still uses the legacy layout:
 * the Transaction List family always renders the tokens, while the sibling lists keep theirs.
 */
internal fun ContentListStyle.withTokens(): ContentListStyle = when (this) {
    is ContentListStyle.Default -> if (size == null) copy(size = ContentListSize.Md) else this
    is ContentListStyle.Inverted -> if (size == null) copy(size = ContentListSize.Md) else this
    is ContentListStyle.Strikethrough -> if (size == null) copy(size = ContentListSize.Md) else this
    is ContentListStyle.Transaction,
    is ContentListStyle.Amount -> this
}

/** Figma `Size` of the content blocks: Md (default) or Sm (the former Child). */
enum class ContentListSize {
    Md,
    Sm
}

/** Figma `Type` of `_Content List / Default`. */
enum class ContentListType {
    Default,
    Inactive,
    Positive,
    Warning,
    Highlight,
    HighlightLead
}

/** Figma `Type` of `_Content List / Amount`. */
enum class AmountType {
    Default,
    Positive,
    Negative,
    Inactive,
    Strikethrough,
    StrikethroughNeutral
}

enum class TransactionType {
    INFLOW,
    OUTFLOW,
    CANCELED,
    DEFAULT
}
