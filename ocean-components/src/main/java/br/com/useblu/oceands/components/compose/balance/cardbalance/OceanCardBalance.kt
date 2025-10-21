package br.com.useblu.oceands.components.compose.balance.cardbalance

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
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
fun OceanCardBalance(
    modifier: Modifier = Modifier,
    items: List<OceanBalanceItemModel>,
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
                    titleColor = OceanColors.interfaceDarkDown,
                    valueColor = OceanColors.interfaceDarkDeep
                )

                is OceanBalanceItemType.Text -> ItemTextContent(
                    modifier = Modifier.weight(1f),
                    data = item.type,
                    hideContent = hideContent,
                    textColor = OceanColors.interfaceDarkDown
                )
            }

            OceanSpacing.StackXXS()

            when (item.interaction) {
                is OceanBalanceItemInteraction.Expandable ->
                    ItemExpandableInteraction(
                        showExpandedInfo = showExpandedInfo,
                        badges = item.interaction.badges
                    )

                is OceanBalanceItemInteraction.Action ->
                    when (val actionType = item.interaction.type) {
                        is OceanBalanceItemActionType.Button ->
                            OceanButton(button = actionType.button)
                        else -> Unit
                    }
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
                        divider = { CardBalanceDivider() }
                    )

                is OceanBalanceItemInteraction.Action -> Unit
            }
        }
    }
}

@Composable
private fun BadgesInteraction(
    badges: List<String>
) {
    BadgesContent(
        badges = badges,
        style = BadgeStyle(
            size = 32.dp,
            shape = OceanBorderRadius.Circle.allCorners.shape(),
            fallbackBackgroundColor = OceanColors.brandPrimaryPure,
            fallbackTextColor = OceanColors.interfaceLightPure,
            useClip = true
        )
    )
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
            OceanBalanceItemModel(
                type = OceanBalanceItemType.Main(
                    title = "Saldo total na Blu",
                    value = "R$ 1.500.000,00"
                ),
                interaction = OceanBalanceItemInteraction.Expandable(
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
            OceanBalanceItemModel(
                type = OceanBalanceItemType.Text(
                    "Facilite a conciliação de cobranças PagBlu"
                ),
                interaction = OceanBalanceItemInteraction.Action(
                    type = OceanBalanceItemActionType.Button(
                        button = OceanButtonModel(
                            text = "Extrato",
                            onClick = { },
                            buttonStyle = OceanButtonStyle.SecondarySmall
                        )
                    ),
                    action = { }
                )
            )
        )
    )
}
