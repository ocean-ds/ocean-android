package br.com.useblu.oceands.components.compose.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.ContentListStyle
import br.com.useblu.oceands.components.compose.ContentListTransactionStyle
import br.com.useblu.oceands.components.compose.OceanContentList
import br.com.useblu.oceands.components.compose.OceanDivider
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanShimmering
import br.com.useblu.oceands.components.compose.OceanTag
import br.com.useblu.oceands.components.compose.OceanTagLayout
import br.com.useblu.oceands.components.compose.OceanTagStyle
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.TransactionType
import br.com.useblu.oceands.components.compose.input.OceanSelectableBox
import br.com.useblu.oceands.extensions.compose.iconContainerBackground
import br.com.useblu.oceands.extensions.oceanFormatWithCurrency
import br.com.useblu.oceands.model.OceanTagType
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontFamily
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.FormatTypes
import br.com.useblu.oceands.utils.OceanIcons


@Preview
@Composable
fun OceanTransactionListItemPreview() {
    Column {
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = 10045.32,
            secondaryValue = 10002.78,
            valueWithSignal = true,
            valueIsHighlighted = true,
            time = "Time",
            tagTitle = "Title",
            icon = OceanIcons.LOCK_CLOSED_SOLID,
            onClick = {
                println("Clicked")
            }
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValueFormatted = "R$ 10.045,32",
            primaryValueFormattedColor = OceanColors.interfaceDarkUp,
            primaryValueFormattedIsStrike = true,
            secondaryValue = 10002.78,
            valueWithSignal = true,
            valueIsHighlighted = true,
            time = "Time",
            tagTitle = "Title",
            icon = OceanIcons.LOCK_CLOSED_SOLID,
            onClick = {
                println("Clicked")
            }
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = -10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            time = "Time",
            tagTitle = "Title",
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = -10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            valueIsCanceled = true,
            time = "Time",
            tagTitle = "Canceled",
            tagType = OceanTagType.Negative,
            icon = OceanIcons.LOCK_CLOSED_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            dimmedLabel = "Level 3",
            highlightedLabel = "Level 4",
            primaryValue = 10045.32,
            valueWithSignal = true,
            valueIsHighlighted = true,
            valueIsCanceled = true,
            time = "Time",
            tagTitle = "Canceled",
            tagType = OceanTagType.Negative,
            icon = OceanIcons.LOCK_CLOSED_SOLID,
            trailingIcon = OceanIcons.CHEVRON_RIGHT_SOLID
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            primaryValue = 10045.32,
            tagTitle = "Expiried",
            tagType = OceanTagType.Neutral,
            showCheckbox = true,
            isCheckboxSelected = false,
            isDisabled = true
        )
        OceanTransactionListItem(
            primaryLabel = "Level 1",
            secondaryLabel = "Level 2",
            primaryValue = 10045.32,
            tagTitle = "Processando",
            tagType = OceanTagType.NeutralPrimary,
            showCheckbox = true,
            isCheckboxSelected = true
        )
    }
}

@Composable
fun OceanTransactionListItem(
    primaryLabel: String,
    modifier: Modifier = Modifier,
    secondaryLabel: String? = null,
    dimmedLabel: String? = null,
    highlightedLabel: String? = null,
    primaryValue: Double? = null,
    primaryValueFormatted: String? = null,
    primaryValueFormattedColor: Color? = null,
    primaryValueFormattedIsStrike: Boolean = false,
    secondaryValue: Double? = null,
    valueIsHighlighted: Boolean = false,
    valueWithSignal: Boolean = false,
    valueIsCanceled: Boolean = false,
    valueIsStrike: Boolean = false,
    tagTitle: String? = null,
    tagType: OceanTagType = OceanTagType.Warning,
    time: String? = null,
    icon: OceanIcons? = null,
    trailingIcon: OceanIcons? = null,
    showDivider: Boolean = true,
    showCheckbox: Boolean = false,
    isCheckboxSelected: Boolean = false,
    onSelectedBox: ((Boolean) -> Unit)? = null,
    showError: Boolean = false,
    isDisabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        Row(
            modifier = modifier
                .background(color = OceanColors.interfaceLightPure)
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = !isDisabled
                )
                .padding(OceanSpacing.xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showCheckbox) {
                OceanSelectableBox(
                    showError = showError,
                    selected = isCheckboxSelected,
                    onSelectedBox = onSelectedBox,
                    enabled = !isDisabled,
                    modifier = Modifier.padding(end = OceanSpacing.xs),
                    interactionSource = interactionSource
                )
            }

            if (icon != null) {
                Box(
                    modifier = Modifier
                        .padding(end = OceanSpacing.xs)
                        .iconContainerBackground(true)
                        .size(40.dp)
                ) {
                    OceanIcon(
                        iconType = icon,
                        tint = OceanColors.interfaceDarkUp,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(24.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = OceanSpacing.xs)
            ) {
                if (highlightedLabel != null) {
                    Text(
                        text = highlightedLabel,
                        color = if (isDisabled) OceanColors.interfaceDarkUp else OceanColors.brandPrimaryDeep,
                        style = OceanTextStyle.captionBold
                    )

                    OceanSpacing.StackXXS()
                }

                Text(
                    text = primaryLabel,
                    fontSize = OceanFontSize.xs,
                    fontFamily = OceanFontFamily.BaseRegular,
                    color = if (isDisabled) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkPure
                )

                if (secondaryLabel != null) {
                    OceanSpacing.StackXXXS()
                    Text(
                        text = secondaryLabel,
                        style = OceanTextStyle.description,
                        color = if (isDisabled) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkDown
                    )
                }

                if (dimmedLabel != null) {
                    OceanSpacing.StackXXS()
                    Text(
                        text = dimmedLabel,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = OceanTextStyle.caption,
                        color = if (isDisabled) OceanColors.interfaceDarkUp else OceanColors.interfaceDarkDown
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (primaryValue != null) {
                    val color = when {
                        isDisabled || valueIsHighlighted && valueIsCanceled -> OceanColors.interfaceDarkUp
                        valueIsHighlighted && primaryValue > 0 -> OceanColors.statusPositiveDeep
                        else -> OceanColors.interfaceDarkPure
                    }

                    var formattedValue =
                        FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(primaryValue.toString())

                    if (valueWithSignal) {
                        if (primaryValue >= 0) {
                            formattedValue = "+ $formattedValue"
                        }
                    } else {
                        formattedValue = formattedValue.replace("-", "")
                    }
                    Text(
                        text = formattedValue,
                        color = color,
                        fontFamily = OceanFontFamily.BaseMedium,
                        style = if (valueIsStrike) {
                            OceanTextStyle.descriptionStrike
                        } else {
                            OceanTextStyle.description
                        }
                    )
                }

                if (!primaryValueFormatted.isNullOrBlank()) {
                    val color = when {
                        primaryValueFormattedColor != null -> primaryValueFormattedColor
                        isDisabled -> OceanColors.interfaceDarkUp
                        valueIsHighlighted -> OceanColors.statusPositiveDeep
                        else -> OceanColors.interfaceDarkPure
                    }

                    Text(
                        text = primaryValueFormatted,
                        color = color,
                        fontFamily = OceanFontFamily.BaseMedium,
                        style = if (primaryValueFormattedIsStrike) {
                            OceanTextStyle.descriptionStrike
                        } else {
                            OceanTextStyle.description
                        }
                    )
                }

                if (secondaryValue != null) {
                    Text(
                        text = secondaryValue.oceanFormatWithCurrency(),
                        color = OceanColors.interfaceDarkDown,
                        fontFamily = OceanFontFamily.BaseMedium,
                        style = OceanTextStyle.description
                    )
                }

                if (tagTitle != null) {
                    OceanSpacing.StackXXXS()
                    OceanTag(
                        style = OceanTagStyle.Default(
                            label = tagTitle,
                            type = tagType,
                            layout = OceanTagLayout.Small()
                        )
                    )
                }

                if (time != null) {
                    OceanSpacing.StackXXXS()
                    Text(
                        text = time,
                        style = OceanTextStyle.caption,
                        fontFamily = OceanFontFamily.BaseMedium
                    )
                }
            }

            if (trailingIcon != null) {
                OceanIcon(
                    iconType = trailingIcon,
                    tint = OceanColors.interfaceDarkUp,
                    modifier = Modifier
                        .padding(start = OceanSpacing.xxsExtra)
                        .size(20.dp)
                )
            }
        }

        if (showDivider) {
            OceanDivider()
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    heightDp = 700
)
@Composable
private fun TransactionListItemPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxxs),
        modifier = Modifier.background(color = OceanColors.interfaceLightDeep)
    ) {
        OceanTransactionListItem(
            style = TransactionListItemStyle.Default(
                contentInfo = ContentListStyle.Default(
                    title = "Title",
                    description = "Subtitle to long to be displayed on a single line",
                    caption = "Caption",
                ),
                contentValues = ContentListTransactionStyle.Transaction(
                    value = "R$ 1.000.000.000,00",
                    caption = "Aditional info",
                    tagStyle = OceanTagStyle.Default(
                        label = "Title",
                        type = OceanTagType.Warning,
                        layout = OceanTagLayout.Small()
                    ),
                    type = TransactionType.OUTFLOW,
                ),
                onClick = { println("Clicked") }
            )
        )
        OceanTransactionListItem(
            enabled = false,
            style = TransactionListItemStyle.Default(
                contentInfo = ContentListStyle.Default(
                    title = "Title",
                    description = "Subtitle to long to be displayed on a single line",
                    caption = "Caption",
                ),
                contentValues = ContentListTransactionStyle.Transaction(
                    value = "R$ 1.000.000.000,00",
                    caption = "Aditional info",
                    tagStyle = OceanTagStyle.Default(
                        label = "Title",
                        type = OceanTagType.Warning,
                        layout = OceanTagLayout.Small()
                    ),
                    type = TransactionType.OUTFLOW,
                ),
                onClick = { println("Clicked") }
            )
        )
        OceanTransactionListItem(
            isLoading = false,
            style = TransactionListItemStyle.WithChild(
                content = TransactionListItemStyle.Default(
                    contentInfo = ContentListStyle.Default(
                        title = "Title",
                        description = "Subtitle to long to be displayed on a single line",
                        caption = "Caption",
                    ),
                    contentValues = ContentListTransactionStyle.Transaction(
                        value = "R$ 2.000.000,00",
                        caption = "Aditional info",
                        tagStyle = OceanTagStyle.Default(
                            label = "Title",
                            type = OceanTagType.Warning,
                            layout = OceanTagLayout.Small()
                        ),
                        type = TransactionType.INFLOW,
                    ),
                    onClick = { println("Clicked") }
                ),
                children = listOf(
                    ChildListItemStyle.Default(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                        description = "Description",
                        value = "R$ 1.000.000,00",
                        type = TransactionType.OUTFLOW
                    ),
                    ChildListItemStyle.Default(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                        description = "Description",
                        value = "R$ 2.000.000,00",
                        type = TransactionType.INFLOW
                    )
                )
            )
        )

        OceanTransactionListItem(
            isLoading = true,
            style = TransactionListItemStyle.WithChild(
                content = TransactionListItemStyle.Default(
                    contentInfo = ContentListStyle.Default(
                    title = "Title",
                    description = "Subtitle to long to be displayed on a single line",
                    caption = "Caption",
                ),
                contentValues = ContentListTransactionStyle.Transaction(
                    value = "R$ 1.000.000.000,00",
                    caption = "Aditional info",
                    tagStyle = OceanTagStyle.Default(
                        label = "Title",
                        type = OceanTagType.Warning,
                        layout = OceanTagLayout.Small()
                    ),
                    type = TransactionType.OUTFLOW,
                ),
                onClick = { println("Clicked") }),
                children = List(3) {
                    ChildListItemStyle.Default(
                        icon = OceanIcons.PLACEHOLDER_SOLID,
                        description = "Description",
                        value = "R$ 1.000.000,00",
                        type = TransactionType.DEFAULT
                    )
                }
            )
        )
    }
}

@Composable
fun OceanTransactionListItem(
    modifier: Modifier = Modifier,
    style: TransactionListItemStyle,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
) {

    when (style) {
        is TransactionListItemStyle.Default -> {
            if (isLoading) {
                TransactionListItemSkeleton()
                return
            }
            DefaultTransactionListItem(
                modifier = modifier,
                contentInfo = style.contentInfo,
                contentValues = style.contentValues,
                onClick = style.onClick,
                enabled = enabled,
                readOnly = readOnly
            )
        }

        is TransactionListItemStyle.WithChild -> {
            if (isLoading) {
                TransactionListItemSkeleton(
                    repeatChild = style.children.size
                )
                return
            }
            TransactionListItemWithChild(
                modifier = modifier,
                readOnly = readOnly,
                enabled = enabled,
                style = style.content,
                children = style.children,
                onClick = style.content.onClick,
            )
        }
    }
}

@Composable
private fun DefaultTransactionListItem(
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    contentInfo: ContentListStyle,
    contentValues: ContentListStyle,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .padding(vertical = OceanSpacing.xs)
            .clickable(
                enabled = enabled && !readOnly,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        OceanContentList(
            style = contentInfo,
            enabled = enabled,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = OceanSpacing.xs)
                .weight(1f)
        )
        OceanContentList(
            style = contentValues,
            enabled = enabled,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = OceanSpacing.xxs)
                .weight(1f)
        )
        if (!readOnly) {
            IconButton(
                onClick = onClick,
                interactionSource = interactionSource,
                modifier = Modifier
                    .padding(start = OceanSpacing.xxsExtra, end = OceanSpacing.xxs)
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = OceanIcons.CHEVRON_RIGHT_OUTLINE.icon),
                    contentDescription = null,
                    colorFilter =
                    if (!enabled) ColorFilter.tint(OceanColors.interfaceDarkUp)
                    else null
                )
            }
        } else {
            OceanSpacing.StackXS()
        }
    }
}

@Composable
private fun TransactionListItemWithChild(
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    style: TransactionListItemStyle.Default,
    onClick: () -> Unit,
    children: List<ChildListItemStyle.Default>
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .clickable(
                enabled = enabled && !readOnly,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Row(
                modifier = modifier
                    .padding(vertical = OceanSpacing.xs)
                    .padding(end = OceanSpacing.xxsExtra)
            ) {
                OceanContentList(
                    style = style.contentInfo,
                    enabled = enabled,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = OceanSpacing.xs)
                        .weight(1f)
                )
                OceanContentList(
                    style = style.contentValues,
                    enabled = enabled,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = OceanSpacing.xxs)
                        .weight(1f)
                )
            }
            children.forEach { child ->
                OceanChildListItem(
                    style = child
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            if (!readOnly) {
                IconButton(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .padding(end = OceanSpacing.xxs)
                        .size(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = OceanIcons.CHEVRON_RIGHT_OUTLINE.icon),
                        contentDescription = null,
                        colorFilter =
                        if (!enabled) ColorFilter.tint(OceanColors.interfaceDarkUp)
                        else null
                    )
                }
            } else {
                OceanSpacing.StackXS()
            }
        }
    }
}

@Composable
fun OceanChildListItem(
    modifier: Modifier = Modifier,
    style: ChildListItemStyle.Default,
) {
    val (color, value) = when (style.type) {
        TransactionType.DEFAULT -> OceanColors.interfaceDarkPure to style.value
        TransactionType.OUTFLOW -> OceanColors.interfaceDarkPure to "- ${style.value}"
        TransactionType.INFLOW -> OceanColors.statusPositiveDeep to "+ ${style.value}"
        TransactionType.CANCELED -> OceanColors.interfaceDarkUp to style.value
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(OceanColors.interfaceLightPure)
            .padding(OceanSpacing.xxsExtra)
    ) {
        Image(
            painter = painterResource(id = style.icon.icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = OceanSpacing.xxsExtra)
                .weight(0.2f)
        )
        OceanText(
            text = style.description,
            style = OceanTextStyle.description,
            modifier = Modifier
                .padding(end = OceanSpacing.xxs)
                .weight(1f)
        )
        OceanText(
            text = value,
            style = OceanTextStyle.paragraph.copy(
                color = color
            )
        )
    }
}

@Composable
private fun TransactionListItemSkeleton(
    repeatChild: Int = 0
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.spacedBy(OceanSpacing.md),
            modifier = Modifier
                .background(OceanColors.interfaceLightPure)
                .padding(OceanSpacing.xs)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
                modifier = Modifier
                    .weight(2f)
            ) {
                OceanShimmering { brush ->
                    Spacer(
                        modifier = Modifier
                            .background(brush, RoundedCornerShape(4.dp))
                            .width(120.dp)
                            .height(16.dp)
                    )
                    repeat(2) {
                        Spacer(
                            modifier = Modifier
                                .background(brush, RoundedCornerShape(4.dp))
                                .fillMaxWidth()
                                .height(16.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(1f)
            ) {
                OceanShimmering { brush ->
                    repeat(3) {
                        if (it == 1) {
                            Spacer(
                                modifier = Modifier
                                    .background(brush, RoundedCornerShape(4.dp))
                                    .width(80.dp)
                                    .height(16.dp)
                            )
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .background(brush, RoundedCornerShape(4.dp))
                                    .fillMaxWidth()
                                    .height(16.dp)
                            )
                        }
                    }
                }
            }
        }
        if (repeatChild != 0) {
            OceanChildListItemSkeleton(repeatChild)
        }
    }
}

@Composable
private fun OceanChildListItemSkeleton(
    repeat: Int
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(OceanSpacing.xxs),
        modifier = Modifier
            .background(color = OceanColors.interfaceLightPure)
            .padding(horizontal = OceanSpacing.xs)
            .padding(top = OceanSpacing.xxsExtra ,bottom = OceanSpacing.xxsExtra)
    ){
        OceanShimmering { brush ->
            repeat(repeat) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(OceanSpacing.md),
                    modifier = Modifier
                        .background(OceanColors.interfaceLightPure)
                ) {
                    Spacer(
                        modifier = Modifier
                            .background(brush, RoundedCornerShape(4.dp))
                            .height(16.dp)
                            .width(120.dp)
                            .weight(2f)
                    )
                    Spacer(
                        modifier = Modifier
                            .background(brush, RoundedCornerShape(4.dp))
                            .height(16.dp)
                            .width(80.dp)
                            .weight(1f)
                    )
                }
            }
        }
    }
}

sealed interface TransactionListItemStyle {
    data class Default(
        val contentInfo: ContentListStyle,
        val contentValues: ContentListStyle,
        val onClick: () -> Unit
    ) : TransactionListItemStyle

    data class WithChild(
        val content: Default,
        val children: List<ChildListItemStyle.Default>,
    ) : TransactionListItemStyle
}

sealed interface ChildListItemStyle {
    data class Default(
        val icon: OceanIcons,
        val description: String,
        val value: String,
        val type: TransactionType,
    ) : ChildListItemStyle
}