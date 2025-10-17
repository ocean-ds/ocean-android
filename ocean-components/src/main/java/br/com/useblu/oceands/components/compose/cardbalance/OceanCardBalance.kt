package br.com.useblu.oceands.components.compose.cardbalance

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.cardbalance.model.OceanCardBalanceItemInteraction
import br.com.useblu.oceands.components.compose.cardbalance.model.OceanCardBalanceItemModel
import br.com.useblu.oceands.components.compose.cardbalance.model.OceanCardBalanceItemType
import br.com.useblu.oceands.components.compose.shimmeringBrush
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanCardBalance(
    modifier: Modifier = Modifier,
    items: List<OceanCardBalanceItemModel>,
    hideContent: Boolean = false,
    isLoading: Boolean = false,
    onClickDelegate: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = OceanColors.interfaceLightDown,
                shape = OceanBorderRadius.MD.allCorners.shape()
            )
            .borderBackground(
                color = OceanColors.interfaceLightPure,
                borderRadius = OceanBorderRadius.MD.allCorners
            )
    ) {
        items.forEachIndexed { index, item ->
            ItemContent(
                item = item,
                hideContent = hideContent,
                isLoading = isLoading,
                onClickDelegate = onClickDelegate
            )
            if (index < items.lastIndex) {
                CardBalanceDivider()
            }
        }
    }
}

@Composable
private fun ItemContent(
    modifier: Modifier = Modifier,
    item: OceanCardBalanceItemModel,
    hideContent: Boolean,
    isLoading: Boolean,
    onClickDelegate: (() -> Unit)?
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
                    is OceanCardBalanceItemInteraction.Expandable -> {
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
            when (item.type) {
                is OceanCardBalanceItemType.Main -> ItemMainContent(
                    data = item.type,
                    hideContent = hideContent,
                    isLoading = isLoading
                )

                is OceanCardBalanceItemType.Text -> ItemTextContent(
                    modifier = Modifier.weight(1f),
                    data = item.type,
                    hideContent = hideContent
                )
            }

            OceanSpacing.StackXXS()

            when (item.interaction) {
                is OceanCardBalanceItemInteraction.Expandable ->
                    ItemExpandableInteraction(
                        showExpandedInfo = showExpandedInfo,
                        badges = item.interaction.badges
                    )

                is OceanCardBalanceItemInteraction.Action ->
                    OceanButton(
                        button = item.interaction.button
                    )
            }
        }

        AnimatedVisibility(
            visible = showExpandedInfo
        ) {
            when (item.interaction) {
                is OceanCardBalanceItemInteraction.Expandable ->
                    ItemExpandableContent(
                        data = item.interaction,
                        hideContent = hideContent,
                        isLoading = isLoading
                    )

                is OceanCardBalanceItemInteraction.Action -> Unit
            }
        }
    }
}

@Composable
private fun ItemExpandableContent(
    data: OceanCardBalanceItemInteraction.Expandable,
    hideContent: Boolean,
    isLoading: Boolean
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
                    style = OceanTextStyle.heading5,
                    color = OceanColors.interfaceLightPure,
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
                        color = OceanColors.interfaceLightPure
                    )
                }
            }
            if (index < data.items.lastIndex) {
                CardBalanceDivider()
            }
        }
    }
}

@Composable
private fun ItemTextContent(
    modifier: Modifier = Modifier,
    data: OceanCardBalanceItemType.Text,
    hideContent: Boolean
) {
    OceanText(
        modifier = modifier,
        text = data.getValue(hideContent = hideContent),
        style = OceanTextStyle.description,
        color = OceanColors.interfaceDarkDown
    )
}

@Composable
private fun BadgesInteraction(
    badges: List<String>
) {
    val size = 32f
    val take = if (badges.size > 3) 2 else badges.size
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(((-size) / 3f).dp)
        ) {
            badges.take(take).forEach { acquirer ->
                val icon = OceanIcons.fromToken("acquirer_$acquirer")
                if (icon != OceanIcons.UNDEFINED) {
                    OceanIcon(
                        modifier = Modifier
                            .clip(shape = OceanBorderRadius.Circle.allCorners.shape())
                            .size(size.dp),
                        iconType = icon
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .background(
                                color = OceanColors.brandPrimaryPure,
                                shape = OceanBorderRadius.Circle.allCorners.shape()
                            )
                            .clip(shape = OceanBorderRadius.Circle.allCorners.shape())
                            .size(size.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        OceanText(
                            text = acquirer.take(1).uppercase(),
                            color = OceanColors.interfaceLightPure,
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

@Composable
private fun ItemMainContent(
    data: OceanCardBalanceItemType.Main,
    hideContent: Boolean,
    isLoading: Boolean
) {
    Column {
        OceanText(
            text = data.title,
            style = OceanTextStyle.captionBold,
            color = OceanColors.interfaceDarkDown
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
                color = OceanColors.interfaceDarkDeep
            )
        }
    }
}

@Composable
private fun ItemExpandableInteraction(
    showExpandedInfo: Boolean,
    badges: List<String>
) {
    val animatedRotation = animateFloatAsState(
        targetValue = if (showExpandedInfo) 180f else 0f,
        label = "OceanCardBalanceItemInteraction"
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        BadgesInteraction(badges = badges)

        OceanSpacing.StackXXS()

        OceanIcon(
            modifier = Modifier
                .rotate(degrees = animatedRotation.value),
            iconType = OceanIcons.CHEVRON_DOWN_SOLID,
            tint = OceanColors.brandPrimaryPure
        )
    }
}

@Composable
private fun CardBalanceDivider() {
    HorizontalDivider(
        color = OceanColors.interfaceDarkDown.copy(alpha = 0.4f)
    )
}

@Preview
@Composable
private fun OceanCardBalancePreview() {
    OceanCardBalance(
        items = listOf(
            OceanCardBalanceItemModel(
                type = OceanCardBalanceItemType.Main(
                    title = "Saldo total na Blu",
                    value = "R$ 1.500.000,00"
                ),
                interaction = OceanCardBalanceItemInteraction.Expandable(
                    items = listOf(
                        "Saldo atual" to "R$ 1.000,00",
                        "Agenda" to "R$ 10.000,00"
                    ),
                    badges = listOf(
                        "rede",
                        "getnet",
                        "cielo"
                    )
                )
            ),
            OceanCardBalanceItemModel(
                type = OceanCardBalanceItemType.Text(
                    "Facilite a conciliação de cobranças PagBlu"
                ),
                interaction = OceanCardBalanceItemInteraction.Action(
                    button = OceanButtonModel(
                        text = "Extrato",
                        onClick = { },
                        buttonStyle = OceanButtonStyle.SecondarySmall
                    ),
                    canClickFullItem = false
                )
            )
        )
    )
}
