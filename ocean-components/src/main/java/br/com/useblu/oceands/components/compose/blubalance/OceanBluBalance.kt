package br.com.useblu.oceands.components.compose.blubalance

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import br.com.useblu.oceands.components.compose.OceanBadge
import br.com.useblu.oceands.components.compose.OceanBadgeSize
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBalanceItemAction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemInteraction
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemModel
import br.com.useblu.oceands.components.compose.blubalance.model.OceanBluBalanceItemType
import br.com.useblu.oceands.model.OceanBadgeType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.ui.compose.borderRadius
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanBluBalance(
    modifier: Modifier = Modifier,
    items: List<OceanBluBalanceItemModel>,
    hideContent: Boolean = false
) {
    Column(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            ItemContent(
                item = item,
                hideContent = hideContent
            )
            if (index < items.lastIndex) {
                BluBalanceDivider()
            }
        }
    }
}

@Composable
private fun ItemContent(
    modifier: Modifier = Modifier,
    item: OceanBluBalanceItemModel,
    hideContent: Boolean
) {
    var showExpandedInfo by remember { mutableStateOf(item.interaction.showExpandedInfo) }

    Column(
        modifier
            .clickable {
                when (item.interaction) {
                    is OceanBluBalanceItemInteraction.Expandable -> {
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
                is OceanBluBalanceItemType.Main -> ItemMainContent(
                    data = item.type,
                    hideContent = hideContent
                )
                is OceanBluBalanceItemType.Text -> ItemTextContent(
                    modifier = Modifier.weight(1f),
                    data = item.type,
                    hideContent = hideContent
                )
            }

            OceanSpacing.StackXXS()

            when (item.interaction) {
                is OceanBluBalanceItemInteraction.Expandable ->
                    ItemExpandableInteraction(showExpandedInfo = showExpandedInfo)
                is OceanBluBalanceItemInteraction.Action ->
                    ItemActionInteraction(data = item.interaction)
            }
        }

        if (showExpandedInfo) {
            when (item.interaction) {
                is OceanBluBalanceItemInteraction.Expandable ->
                    ItemExpandableContent(
                        data = item.interaction
                    )
                is OceanBluBalanceItemInteraction.Action -> Unit
            }
        }
    }
}

@Composable
private fun ItemMainContent(
    data: OceanBluBalanceItemType.Main,
    hideContent: Boolean
) {
    Column {
        OceanText(
            text = data.title,
            style = OceanTextStyle.captionBold,
            color = OceanColors.brandPrimaryUp
        )

        OceanText(
            text = if (hideContent) data.hiddenValue else data.value,
            style = OceanTextStyle.heading4,
            color = OceanColors.interfaceLightPure
        )
    }
}

@Composable
private fun ItemTextContent(
    modifier: Modifier = Modifier,
    data: OceanBluBalanceItemType.Text,
    hideContent: Boolean
) {
    OceanText(
        modifier = modifier,
        text = data.getValue(hideContent = hideContent),
        style = OceanTextStyle.description,
        color = OceanColors.brandPrimaryUp
    )
}

@Composable
private fun ItemExpandableInteraction(
    showExpandedInfo: Boolean
) {
    OceanIcon(
        iconType = if (showExpandedInfo) {
            OceanIcons.CHEVRON_UP_SOLID
        } else {
            OceanIcons.CHEVRON_DOWN_SOLID
        },
        tint = OceanColors.interfaceLightPure
    )
}

@Composable
private fun ItemActionInteraction(
    data: OceanBluBalanceItemInteraction.Action
) {
    when (data.type) {
        is OceanBalanceItemAction.Button -> ItemActionButtonInteraction(
            data = data,
            buttonData = data.type
        )
        is OceanBalanceItemAction.Badges -> ItemActionBadgesInteraction(
            badgesData = data.type
        )
    }
}

@Composable
private fun ItemActionButtonInteraction(
    data: OceanBluBalanceItemInteraction.Action,
    buttonData: OceanBalanceItemAction.Button
) {
    OceanButton(
        button = OceanButtonModel(
            text = buttonData.title,
            onClick = data.action,
            buttonStyle = OceanButtonStyle.SecondarySmall
        )
    )
}

@Composable
private fun ItemActionBadgesInteraction(
    badgesData: OceanBalanceItemAction.Badges
) {
    val size = 24f
    val take = if (badgesData.icons.size > 3) 2 else badgesData.icons.size
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(((-size) / 3f).dp)
        ) {
            badgesData.icons.take(take).forEach { icon ->
                OceanIcon(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = OceanColors.interfaceLightDown,
                            shape = OceanBorderRadius.Circle.allCorners.shape()
                        )
                        .size(size.dp),
                    iconType = icon
                )
            }

            if (take < badgesData.icons.size) {
                OceanBadge(
                    text = "${badgesData.icons.size - take}",
                    prefix = "+",
                    type = OceanBadgeType.PRIMARY_INVERTED,
                    size = OceanBadgeSize.Small
                )
            }
        }

        OceanIcon(
            iconType = OceanIcons.CHEVRON_RIGHT_SOLID,
            tint = OceanColors.interfaceLightPure
        )
    }
}

@Composable
private fun ItemExpandableContent(
    data: OceanBluBalanceItemInteraction.Expandable
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

                OceanText(
                    text = pair.second,
                    style = OceanTextStyle.heading5,
                    color = OceanColors.interfaceLightPure
                )
            }
            if (index < data.items.lastIndex) {
                BluBalanceDivider()
            }
        }
    }
}

@Composable
private fun BluBalanceDivider() {
    HorizontalDivider(
        color = OceanColors.brandPrimaryUp.copy(alpha = 0.4f)
    )
}

@Composable
@Preview(widthDp = 320)
fun OceanBluBalancePreview() {
    Column {
        OceanBluBalance(
            modifier = Modifier
                .borderBackground(
                    color = Color(0xFF2244E8),
                    borderRadius = OceanBorderRadius.MD.allCorners
                ),
            items = listOf(
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Main(
                        title = "Saldo total na Blu",
                        value = "R$ 1.000.000,00"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Expandable(
                        items = listOf(
                            "Saldo atual" to "R$ 1.000,00",
                            "Agenda" to "R$ 1.000,00"
                        )
                    )
                ),
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Text(
                        "Facilite a conciliação\n" +
                            "de cobranças PagBlu"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Action(
                        type = OceanBalanceItemAction.Button(
                            title = "Saiba mais"
                        ),
                        action = { }
                    )
                )
            )
        )
    }
}

@Composable
@Preview(widthDp = 320)
fun OceanBluBalanceExpandedPreview() {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF2244E8))
    ) {
        OceanBluBalance(
            items = listOf(
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Main(
                        title = "Saldo total na Blu",
                        value = "R$ 1.000.000,00"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Expandable(
                        showExpandedInfo = true,
                        items = listOf(
                            "Saldo atual" to "R$ 1.000,00",
                            "Agenda" to "R$ 1.000,00"
                        )
                    )
                ),
                OceanBluBalanceItemModel(
                    type = OceanBluBalanceItemType.Main(
                        title = "Saldo nas maquininhas",
                        value = "R$ 1.000.000,00"
                    ),
                    interaction = OceanBluBalanceItemInteraction.Action(
                        type = OceanBalanceItemAction.Badges(
                            icons = listOf(
                                OceanIcons.ACQUIRER_REDE,
                                OceanIcons.ACQUIRER_GETNET,
                                OceanIcons.ACQUIRER_CIELO,
                                OceanIcons.ACQUIRER_PAGBANK,
                                OceanIcons.ACQUIRER_SICOOB
                            )
                        ),
                        action = { }
                    )
                )
            )
        )
    }
}
