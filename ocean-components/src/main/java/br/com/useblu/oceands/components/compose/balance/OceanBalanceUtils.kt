package br.com.useblu.oceands.components.compose.balance

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemInteraction
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemModel
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemType
import br.com.useblu.oceands.components.compose.shimmeringBrush
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Composable
internal fun ItemMainContent(
    data: OceanBalanceItemType.Main,
    hideContent: Boolean,
    isLoading: Boolean,
    titleColor: Color,
    valueColor: Color
) {
    Column {
        OceanText(
            text = data.title,
            style = OceanTextStyle.captionBold,
            color = titleColor
        )

        if (isLoading) {
            val brush = shimmeringBrush()
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(72.dp)
                    .borderBackground(
                        brush = brush,
                        borderRadius = OceanBorderRadius.Tiny.allCorners
                    )
            )
        } else {
            OceanText(
                text = if (hideContent) data.hiddenValue else data.value,
                style = OceanTextStyle.heading4,
                color = valueColor
            )
        }
    }
}

@Composable
internal fun ItemTextContent(
    modifier: Modifier = Modifier,
    data: OceanBalanceItemType.Text,
    hideContent: Boolean,
    textColor: Color
) {
    OceanText(
        modifier = modifier,
        text = data.getValue(hideContent = hideContent),
        style = OceanTextStyle.description,
        color = textColor
    )
}

@Composable
internal fun ItemExpandableContent(
    data: OceanBalanceItemInteraction.Expandable,
    hideContent: Boolean,
    isLoading: Boolean,
    textColor: Color,
    divider: @Composable () -> Unit
) {
    Column {
        data.items.fastForEachIndexed { index, pair ->
            Row(
                modifier = Modifier
                    .padding(vertical = OceanSpacing.xxsExtra)
                    .padding(horizontal = OceanSpacing.xs)
            ) {
                OceanText(
                    text = pair.first,
                    style = OceanTextStyle.captionBold,
                    color = textColor,
                    modifier = Modifier
                        .weight(1f)
                )

                if (isLoading) {
                    val brush = shimmeringBrush()
                    Box(
                        modifier = Modifier
                            .height(18.dp)
                            .width(72.dp)
                            .borderBackground(
                                brush = brush,
                                borderRadius = OceanBorderRadius.Tiny.allCorners
                            )
                            .padding(vertical = OceanSpacing.xxs)
                    )
                } else {
                    OceanText(
                        text = if (hideContent) data.hiddenValue else pair.second,
                        style = OceanTextStyle.heading5,
                        color = textColor
                    )
                }
            }
            if (index < data.items.lastIndex) {
                divider()
            }
        }
    }
}

@Composable
internal fun BalanceItemContent(
    modifier: Modifier = Modifier,
    item: OceanBalanceItemModel,
    hideContent: Boolean,
    isLoading: Boolean,
    onClickDelegate: (() -> Unit)?,
    titleColor: Color,
    valueColor: Color,
    textColor: Color,
    interactionContent: @Composable (OceanBalanceItemInteraction, Boolean) -> Unit,
    expandableContent: @Composable (OceanBalanceItemInteraction.Expandable) -> Unit
) {
    var showExpandedInfo by remember { mutableStateOf(item.interaction.showExpandedInfo) }

    Column(
        modifier = modifier
            .clickable(
                enabled = item.interaction.canClickFullItem || onClickDelegate != null
            ) {
                onClickDelegate?.let {
                    it()
                } ?: when (item.interaction) {
                    is OceanBalanceItemInteraction.Expandable -> {
                        showExpandedInfo = showExpandedInfo.not()
                    }
                    else -> item.interaction.action()
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = OceanSpacing.xs)
                .padding(vertical = OceanSpacing.xxsExtra),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                when (item.type) {
                    is OceanBalanceItemType.Main -> ItemMainContent(
                        data = item.type,
                        hideContent = hideContent,
                        isLoading = isLoading,
                        titleColor = titleColor,
                        valueColor = valueColor
                    )
                    is OceanBalanceItemType.Text -> ItemTextContent(
                        data = item.type,
                        hideContent = hideContent,
                        textColor = textColor
                    )
                }
            }

            OceanSpacing.StackXXS()

            interactionContent(item.interaction, showExpandedInfo)
        }

        AnimatedVisibility(visible = showExpandedInfo) {
            when (item.interaction) {
                is OceanBalanceItemInteraction.Expandable ->
                    expandableContent(item.interaction)
                is OceanBalanceItemInteraction.Action -> Unit
            }
        }
    }
}

data class BadgeStyle(
    val size: Dp,
    val shape: Shape,
    val iconBorderWidth: Dp? = null,
    val iconBorderColor: Color? = null,
    val fallbackBackgroundColor: Color,
    val fallbackBorderWidth: Dp? = null,
    val fallbackBorderColor: Color? = null,
    val fallbackTextColor: Color,
    val useClip: Boolean = false
)

@Composable
internal fun BadgesContent(
    badges: List<String>,
    style: BadgeStyle
) {
    val take = if (badges.size > 3) 2 else badges.size
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(((-style.size.value) / 3f).dp)
        ) {
            badges.take(take).forEach { acquirer ->
                val icon = OceanIcons.fromToken("acquirer_$acquirer")
                if (icon != OceanIcons.UNDEFINED) {
                    val iconModifier = Modifier.size(style.size)
                    val finalModifier = if (style.useClip) {
                        iconModifier.clip(shape = style.shape)
                    } else {
                        if (style.iconBorderWidth != null && style.iconBorderColor != null) {
                            iconModifier.border(
                                width = style.iconBorderWidth,
                                color = style.iconBorderColor,
                                shape = style.shape
                            )
                        } else {
                            iconModifier
                        }
                    }
                    OceanIcon(
                        modifier = finalModifier,
                        iconType = icon
                    )
                } else {
                    val boxModifier = Modifier
                        .background(
                            color = style.fallbackBackgroundColor,
                            shape = style.shape
                        )
                        .size(style.size)

                    val finalBoxModifier = if (style.useClip) {
                        boxModifier.clip(shape = style.shape)
                    } else {
                        if (style.fallbackBorderWidth != null && style.fallbackBorderColor != null) {
                            boxModifier.border(
                                width = style.fallbackBorderWidth,
                                color = style.fallbackBorderColor,
                                shape = style.shape
                            )
                        } else {
                            boxModifier
                        }
                    }

                    Box(
                        modifier = finalBoxModifier,
                        contentAlignment = Alignment.Center
                    ) {
                        OceanText(
                            text = acquirer.take(1).uppercase(),
                            color = style.fallbackTextColor,
                            style = OceanTextStyle.eyebrow
                        )
                    }
                }
            }

            if (take < badges.size) {
                OceanBadge(
                    text = "${badges.size - take}",
                    prefix = "+",
                    type = OceanBadgeType.PRIMARY_INVERTED,
                    size = OceanBadgeSize.Small
                )
            }
        }
    }
}
