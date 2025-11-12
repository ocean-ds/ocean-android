package br.com.useblu.oceands.components.compose.balance.cardbalance

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
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
import br.com.useblu.oceands.components.compose.balance.BalanceItemContent
import br.com.useblu.oceands.components.compose.balance.ItemExpandableContent
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemActionType
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemInteraction
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemModel
import br.com.useblu.oceands.components.compose.balance.model.OceanBalanceItemType
import br.com.useblu.oceands.ui.compose.OceanBorderRadius
import br.com.useblu.oceands.ui.compose.OceanButtonStyle
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.borderWithBackground
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
        modifier = modifier.borderWithBackground(
            borderWidth = 1.dp,
            borderColor = OceanColors.interfaceLightDown,
            backgroundColor = OceanColors.interfaceLightPure,
            borderRadius = OceanBorderRadius.MD.allCorners
        )
    ) {
        items.forEachIndexed { index, item ->
            BalanceItemContent(
                item = item,
                hideContent = hideContent,
                isLoading = isLoading,
                onClickDelegate = onClickDelegate,
                titleColor = OceanColors.interfaceDarkDown,
                valueColor = OceanColors.interfaceDarkDeep,
                textColor = OceanColors.interfaceDarkDown,
                interactionContent = { interaction, showExpandedInfo ->
                    when (interaction) {
                        is OceanBalanceItemInteraction.Expandable ->
                            ItemExpandableInteraction(
                                showExpandedInfo = showExpandedInfo,
                                badges = interaction.badges,
                                wrapSize = interaction.wrapSize
                            )

                        is OceanBalanceItemInteraction.Action ->
                            when (val actionType = interaction.type) {
                                is OceanBalanceItemActionType.Button ->
                                    OceanButton(button = actionType.button)

                                else -> Unit
                            }
                    }
                },
                expandableContent = { expandable ->
                    ItemExpandableContent(
                        data = expandable,
                        hideContent = hideContent,
                        isLoading = isLoading,
                        textColor = OceanColors.interfaceDarkDown,
                        divider = { CardBalanceDivider() }
                    )
                }
            )
            if (index < items.lastIndex) {
                CardBalanceDivider()
            }
        }
    }
}

@Composable
private fun BadgesInteraction(
    badges: List<String>,
    wrapSize: Int
) {
    BadgesContent(
        wrapSize = wrapSize,
        badges = badges,
        style = BadgeStyle(
            size = OceanSpacing.md,
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
    badges: List<String>,
    wrapSize: Int
) {
    val animatedRotation = animateFloatAsState(
        targetValue = if (showExpandedInfo) 180f else 0f,
        label = "OceanCardBalanceItemInteraction"
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        BadgesInteraction(
            badges = badges,
            wrapSize = wrapSize
        )

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
        hideContent = false,
        items = listOf(
            OceanBalanceItemModel(
                type = OceanBalanceItemType.Main(
                    title = "Saldo total na Blu",
                    value = "R$ 1.500.000,00"
                ),
                interaction = OceanBalanceItemInteraction.Expandable(
                    bluBalanceItems = listOf(
                        "Saldo atual Blu" to "R$ 1.000,00",
                        "Agenda Blu" to "R$ 10.000,00"
                    ),
                    acquirersBalanceItems = listOf(
                        "Agenda GetNet" to "R$ 10.000,00",
                        "Agenda GetNet" to "R$ 10.000,00"
                    ),
                    wrapSize = 3,
                    lockedTitle = "Conforme você for usando mais a Blu, as seguintes  agendas ficarão disponíveis para você:",
                    lockedItems = listOf(
                        "Agenda Rede" to "R$ 50.000,00",
                        "Agenda Getnet" to "R$ 50.000,00"
                    ),
                    badges = listOf(
                        "blu",
                        "rede",
                        "getnet",
                        "mastercard"
                    ),
                    showExpandedInfo = true
                )
            ),
            OceanBalanceItemModel(
                type = OceanBalanceItemType.Main(
                    "Facilite a conciliação de cobranças PagBlu texto grande",
                    "R$ 250.000,00"
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
