package br.com.useblu.oceands.components.compose.balance.blubalance

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanButton
import br.com.useblu.oceands.components.compose.OceanButtonModel
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.balance.BadgeStyle
import br.com.useblu.oceands.components.compose.balance.BadgesContent
import br.com.useblu.oceands.components.compose.balance.ItemExpandableContent
import br.com.useblu.oceands.components.compose.balance.ItemMainContent
import br.com.useblu.oceands.components.compose.balance.ItemTextContent
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemActionType
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemInteraction
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemModel
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderBackground
import br.com.useblu.oceands.utils.OceanIcons

@Composable
fun OceanBluBalance(
    modifier: Modifier = Modifier,
    items: List<OceanBalanceItemModel>,
    hideContent: Boolean = false,
    isLoading: Boolean = false,
    onClickDelegate: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            ItemContent(
                item = item,
                hideContent = hideContent,
                isLoading = isLoading,
                onClickDelegate = onClickDelegate
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
    item: OceanBalanceItemModel,
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
            when (item.type) {
                is OceanBalanceItemType.Main -> ItemMainContent(
                    data = item.type,
                    hideContent = hideContent,
                    isLoading = isLoading,
                    titleColor = OceanColors.brandPrimaryUp,
                    valueColor = OceanColors.interfaceLightPure
                )
                is OceanBalanceItemType.Text -> ItemTextContent(
                    modifier = Modifier.weight(1f),
                    data = item.type,
                    hideContent = hideContent,
                    textColor = OceanColors.brandPrimaryUp
                )
            }

            OceanSpacing.StackXXS()

            when (item.interaction) {
                is OceanBalanceItemInteraction.Expandable ->
                    ItemExpandableInteraction(showExpandedInfo = showExpandedInfo)
                is OceanBalanceItemInteraction.Action ->
                    ItemActionInteraction(data = item.interaction)
            }
        }

        AnimatedVisibility(
            visible = showExpandedInfo
        ) {
            when (item.interaction) {
                is OceanBalanceItemInteraction.Expandable ->
                    ItemExpandableContent(
                        data = item.interaction,
                        hideContent = hideContent,
                        isLoading = isLoading,
                        textColor = OceanColors.interfaceLightPure,
                        divider = { BluBalanceDivider() }
                    )
                is OceanBalanceItemInteraction.Action -> Unit
            }
        }
    }
}

@Composable
private fun ItemExpandableInteraction(
    showExpandedInfo: Boolean
) {
    val animatedRotation = animateFloatAsState(
        targetValue = if (showExpandedInfo) 180f else 0f,
        label = "OceanBluBalanceItemInteraction"
    )
    OceanIcon(
        modifier = Modifier
            .rotate(degrees = animatedRotation.value),
        iconType = OceanIcons.CHEVRON_DOWN_SOLID,
        tint = OceanColors.interfaceLightPure
    )
}

@Composable
private fun ItemActionInteraction(
    data: OceanBalanceItemInteraction.Action
) {
    when (val actionType = data.type) {
        is OceanBalanceItemActionType.Button -> OceanButton(
            button = actionType.button
        )
        is OceanBalanceItemActionType.ButtonSimple -> OceanButton(
            button = OceanButtonModel(
                text = actionType.title,
                onClick = actionType.onClick,
                buttonStyle = OceanButtonStyle.SecondarySmall
            )
        )
        is OceanBalanceItemActionType.Badges -> ItemActionBadgesInteraction(
            badgesData = actionType
        )
    }
}

@Composable
private fun ItemActionBadgesInteraction(
    badgesData: OceanBalanceItemActionType.Badges
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OceanSpacing.xxs)
    ) {
        BadgesContent(
            badges = badgesData.acquirers,
            style = BadgeStyle(
                size = 24.dp,
                shape = OceanBorderRadius.Circle.allCorners.shape(),
                iconBorderWidth = 1.dp,
                iconBorderColor = OceanColors.interfaceLightDown,
                fallbackBackgroundColor = OceanColors.interfaceLightPure,
                fallbackBorderWidth = 1.dp,
                fallbackBorderColor = OceanColors.interfaceLightDown,
                fallbackTextColor = OceanColors.brandPrimaryDown
            )
        )

        OceanIcon(
            iconType = badgesData.traillingIcon,
            tint = OceanColors.interfaceLightPure
        )
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
                OceanBalanceItemModel(
                    type = OceanBalanceItemType.Main(
                        title = "Saldo total na Blu",
                        value = "R$ 1.500.000,00"
                    ),
                    interaction = OceanBalanceItemInteraction.Expandable(
                        items = listOf(
                            "Saldo atual" to "R$ 1.000,00",
                            "Agenda" to "R$ 10.000,00"
                        )
                    )
                ),
                OceanBalanceItemModel(
                    type = OceanBalanceItemType.Text(
                        "Facilite a conciliação de cobranças PagBlu"
                    ),
                    interaction = OceanBalanceItemInteraction.Action(
                        type = OceanBalanceItemActionType.ButtonSimple(
                            title = "Saiba mais",
                            onClick = { }
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
                OceanBalanceItemModel(
                    type = OceanBalanceItemType.Main(
                        title = "Saldo total na Blu",
                        value = "R$ 1.000.000,00"
                    ),
                    interaction = OceanBalanceItemInteraction.Expandable(
                        showExpandedInfo = true,
                        items = listOf(
                            "Saldo atual" to "R$ 5.000,00",
                            "Agenda" to "R$ 95.000,00"
                        )
                    )
                ),
                OceanBalanceItemModel(
                    type = OceanBalanceItemType.Main(
                        title = "Saldo nas maquininhas",
                        value = "R$ 1.000.000,00"
                    ),
                    interaction = OceanBalanceItemInteraction.Action(
                        type = OceanBalanceItemActionType.Badges(
                            acquirers = listOf(
                                "test",
                                "rede",
                                "getnet",
                                "cielo",
                                "sicoob"
                            )
                        ),
                        action = { }
                    )
                )
            ),
            isLoading = false
        )
    }
}
